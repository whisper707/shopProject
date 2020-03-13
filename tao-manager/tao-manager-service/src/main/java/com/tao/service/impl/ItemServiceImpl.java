package com.tao.service.impl;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.common.util.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tao.mapper.TbItemDescMapper;
import com.tao.mapper.TbItemMapper;
import com.tao.mapper.TbItemParamItemMapper;
import com.tao.pojo.*;
import com.tao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    public TbItem getItemById(long itemId) {
        TbItem t=tbItemMapper.selectByPrimaryKey(itemId);
        return t;
    }

    @Override
    public EUDataGridResult getList(int page, int rows) {

        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> tlist = tbItemMapper.selectByExample(example);
        PageInfo<TbItem> info = new PageInfo<>(tlist);
        EUDataGridResult e = new EUDataGridResult();
        e.setTotal(info.getTotal());
        e.setRows(tlist);
        return e;
    }

    @Override
    public TaotaoResult saveItem(TbItem tbItem, String desc,String itemparam) {
        long itemId = IDUtils.genItemId();
        //tbItem
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //desc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        //param
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemparam);
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        try {
            tbItemMapper.insert(tbItem);
            tbItemDescMapper.insert(tbItemDesc);
            tbItemParamItemMapper.insert(tbItemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(400,"添加商品失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteItem(String ids) {
        try {
            String[] idss = ids.split(",");
            for (String s:idss
                 ) {
                long itemid = Long.parseLong(s);
                tbItemDescMapper.deleteByPrimaryKey(itemid);
                tbItemMapper.deleteByPrimaryKey(itemid);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return TaotaoResult.build(400,"删除异常");
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult selectDesc(Long id) {
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        return TaotaoResult.ok(tbItemDesc);
    }

    @Override
    public TaotaoResult updateItem(TbItem item, String desc) {

        try {
            TbItem item1 = tbItemMapper.selectByPrimaryKey(item.getId());
            item.setStatus((byte)2);
            item.setCreated(item1.getCreated());
            item.setUpdated(new Date());
            tbItemMapper.updateByPrimaryKey(item);

            TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(item.getId());
            tbItemDesc.setItemDesc(desc);
            tbItemDesc.setUpdated(new Date());
            tbItemDescMapper.updateByPrimaryKeyWithBLOBs(tbItemDesc);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(400,"修改出现异常");
        }
        return TaotaoResult.ok();
    }
}
