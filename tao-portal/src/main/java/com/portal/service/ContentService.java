package com.portal.service;



import com.tao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    public List<TbContent> getContentList(long contentCid);
}
