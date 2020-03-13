package com.tao.service.impl;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tao.mapper.TbItemParamMapper;
import com.tao.pojo.TbItemParam;
import com.tao.pojo.TbItemParamExample;
import com.tao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TaotaoResult selectItemParamByItemId(Long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria cri = example.createCriteria();
        cri.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (list==null||list.size()<1){
            return  TaotaoResult.build(400,null);
        }
        return TaotaoResult.ok(list.get(0));
    }

    @Override
    public TaotaoResult saveItemParam(Long itemCatId, String paramDate) {
        System.out.println("paramDate: "+paramDate);
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramDate);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        try {
            tbItemParamMapper.insert(tbItemParam);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(400,"添加规格失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        try {
            TbItemParamExample example = new TbItemParamExample();
            PageHelper.startPage(page,rows);
            List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
            PageInfo<TbItemParam> info = new PageInfo<>(list);
            euDataGridResult.setRows(list);
            euDataGridResult.setTotal(info.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return euDataGridResult;
    }

    @Override
    public TaotaoResult deleteItemParam(String ids) {
        String[] idss = ids.split(",");
        try {
            for (String s : idss
                 ) {
                tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(s));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            TaotaoResult.build(400,"删除失败");
        }
        return TaotaoResult.ok();
    }
}
