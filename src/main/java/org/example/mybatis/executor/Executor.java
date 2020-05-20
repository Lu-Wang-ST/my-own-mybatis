package org.example.mybatis.executor;

import java.util.List;

public interface Executor {

    <T> List<T> qeury(String statement, Object param);
}
