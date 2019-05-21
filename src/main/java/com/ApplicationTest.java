package com;

import com.domain.BlogDO;
import com.mapper.BlogMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ApplicationTest {
    public static void main(String[] args) {
        SqlSession session = SqlSessionHelper.getSqlSession();
        try {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            List<BlogDO> blogDOS= blogMapper.selectBlog(1L);
            blogDOS.forEach(x->{
                System.out.println(x.getName());
            });
//            BlogDto blogDto = blogMapper.selectById("1");
//            Assert.assertNotNull(blogDto);
//            show(blogDto);


        } finally {
            session.close();
        }
    }
}
