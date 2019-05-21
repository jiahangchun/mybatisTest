package com.mapper;

import com.domain.BlogDO;

import java.util.List;

public interface BlogMapper {
    public List<BlogDO> selectBlog(Long id);
}
