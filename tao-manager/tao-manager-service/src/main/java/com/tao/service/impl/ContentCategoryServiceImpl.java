package com.tao.service.impl;

import com.common.pojo.TaotaoResult;
import com.common.pojo.TreeNode;
import com.tao.mapper.TbContentCategoryMapper;
import com.tao.pojo.TbContentCategory;
import com.tao.pojo.TbContentCategoryExample;
import com.tao.service.ContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper ;

    @Override
    public List<TreeNode> getContentCatList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria cri = example.createCriteria();
        cri.andParentIdEqualTo(parentId);
        List<TbContentCategory> clist=tbContentCategoryMapper.selectByExample(example);
        List<TreeNode> tlist = new ArrayList<>();
        for (TbContentCategory t: clist) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(t.getId());
            treeNode.setText(t.getName());
            treeNode.setState(t.getIsParent()?"closed":"open");
            tlist.add(treeNode);
        }
        return tlist;
    }

    @Override
    public TaotaoResult insertContentCategory(Long parentId, String name) {
        try {
            TbContentCategory tbContentCategory = new TbContentCategory();
            tbContentCategory.setName(name);
            tbContentCategory.setIsParent(false);
            //状态。可选值:1(正常),2(删除)
            tbContentCategory.setStatus(1);
            tbContentCategory.setParentId(parentId);
            //改父类 的状态
            TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
            System.out.println(parent.getIsParent());
            if (!parent.getIsParent()){
                parent.setIsParent(true);
                tbContentCategoryMapper.updateByPrimaryKey(parent);
            }
            tbContentCategory.setSortOrder(1);
            tbContentCategory.setCreated(new Date());
            tbContentCategory.setUpdated(new Date());
            tbContentCategoryMapper.insert(tbContentCategory);
        } catch (Exception e) {
            e.printStackTrace();
            TaotaoResult.build(400,"添加失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContentCategory(Long id) {
        try {
            TbContentCategory t = tbContentCategoryMapper.selectByPrimaryKey(id);
            long pid = t.getParentId();
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria cri = example.createCriteria();
            cri.andParentIdEqualTo(pid);
            int num = tbContentCategoryMapper.countByExample(example);
            //再无其他子类节点
            if (num<=1){
                TbContentCategory pt = tbContentCategoryMapper.selectByPrimaryKey(pid);
                pt.setIsParent(false);
                tbContentCategoryMapper.updateByPrimaryKey(pt);
            }
            tbContentCategoryMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(400,"删除失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContentCategory(Long id, String name) {
        try {
            if (!StringUtils.isBlank(name)) {
                TbContentCategory t = tbContentCategoryMapper.selectByPrimaryKey(id);
                t.setName(name);
                tbContentCategoryMapper.updateByPrimaryKey(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(400,"修改失败");
        }
        return TaotaoResult.ok();
    }
}
