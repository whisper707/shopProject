package com.tao.service;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;

public interface ItemParamService {
    public TaotaoResult selectItemParamByItemId(Long itemId);
    public TaotaoResult saveItemParam( Long itemCatId , String paramDate);
    public EUDataGridResult getItemList(Integer page, Integer rows);
    public TaotaoResult deleteItemParam(String ids);
}
