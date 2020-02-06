package com.coco.cloud.mybatis.framework.mapper;

import com.coco.cloud.mybatis.framework.annotation.Entity;
import com.coco.cloud.mybatis.framework.annotation.Select;

@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = ?")
    public Blog selectBlogById(Integer bid);

}
