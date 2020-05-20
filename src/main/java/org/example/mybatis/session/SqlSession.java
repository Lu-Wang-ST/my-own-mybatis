package org.example.mybatis.session;

import java.util.List;

public interface SqlSession {
    <T> T getMapper(Class<?> type);

    <T> List<T> selectList(String statement, Object param);
}
