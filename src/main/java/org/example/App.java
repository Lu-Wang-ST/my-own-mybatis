package org.example;

import org.example.app.mapper.MemberMapper;
import org.example.mybatis.session.SqlSession;
import org.example.mybatis.session.SqlSessionFactory;
import org.example.mybatis.session.SqlSessionFactoryBuilder;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(App.class.getClassLoader().getResourceAsStream("config.properties"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        System.out.println(memberMapper.selectAll());;
    }
}
