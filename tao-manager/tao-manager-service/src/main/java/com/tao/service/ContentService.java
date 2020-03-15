package com.tao.service;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.tao.pojo.TbContent;

import java.util.List;
import java.util.Set;

public interface ContentService {
    public EUDataGridResult getContentList(Integer page, Integer rows, Long categoryId);
    public TaotaoResult saveContent(TbContent content);
    public TaotaoResult deleteContent(String ids);
    public TaotaoResult updateContent(TbContent content);
    public long getbyids(Long ids);
}
