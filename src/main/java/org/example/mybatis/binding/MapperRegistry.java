package org.example.mybatis.binding;

import org.example.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
    private Map<Class<?>, MapperProxyFactory> mapper = new HashMap<>();

    public <T> void addMapper(Class<T> type) {
        this.mapper.put(type, new MapperProxyFactory(type));
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory mapperProxyFactory = this.mapper.get(type);
        return (T) mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> boolean hasMapper(Class<T> type) {
        return mapper.containsKey(type);
    }
}
