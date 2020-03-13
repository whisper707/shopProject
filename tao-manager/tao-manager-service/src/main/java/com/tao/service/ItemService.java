package com.tao.service;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.tao.pojo.TbItem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public interface ItemService {
    public TbItem getItemById(long itemId);
    public EUDataGridResult getList(int page,int rows);
    public TaotaoResult saveItem(TbItem tbItem, String desc,String itemparam);
    public TaotaoResult deleteItem(String ids);
    public TaotaoResult selectDesc(Long id);
    public TaotaoResult updateItem(TbItem item,String desc);
}
