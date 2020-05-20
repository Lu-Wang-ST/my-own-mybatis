package org.example.mybatis.binding;

import org.example.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String statement = method.getDeclaringClass().getName() + "." + method.getName();
        return execute(statement, args);
    }

    private <E> Object execute(String statement, Object[] args){
        List<E> list = this.sqlSession.selectList(statement, null);
        return list;
    }
}
