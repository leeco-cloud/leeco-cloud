package com.coco.cloud.mybatis.framework;

import com.coco.cloud.mybatis.framework.mapper.Blog;
import com.coco.cloud.mybatis.framework.mapper.BlogMapper;
import com.coco.cloud.mybatis.framework.session.DefaultSqlSession;
import com.coco.cloud.mybatis.framework.session.SqlSessionFactory;

public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        DefaultSqlSession sqlSession = factory.build().openSqlSession();
        // 获取MapperProxy代理
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);

        System.out.println("第一次查询: " + blog);
        System.out.println();
        blog = mapper.selectBlogById(1);
        System.out.println("第二次查询: " + blog);
    }
}
