//package com;
//
//import com.mybatis.domain.BlogDO;
//import com.mybatis.mapper.BlogMapper;
//import org.apache.ibatis.session.SqlSession;
//
//import java.util.List;
//
///**
// * @author jiahangchun
// */
//public class ApplicationTest {
//    public static void main(String[] args) {
//        SqlSession session = SqlSessionHelper.getSqlSession();
//        try {
//            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
//            List<BlogDO> blogDOS= blogMapper.selectBlog(1L);
//            blogDOS.forEach(x->{
//                System.out.println(x.getName());
//            });
//        } finally {
//            session.close();
//        }
//    }
//}
