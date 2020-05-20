package org.example.mybatis.session;

import org.example.mybatis.executor.Executor;
import org.example.mybatis.executor.SimpleExecutor;

import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration config;
    private Executor executor;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
        executor = new SimpleExecutor(config);
    }

    @Override
    public <T> T getMapper(Class<?> type) {
        return (T) this.config.getMapper(type, this);
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        String statement = this.config.getStatement(statementId);
        return executor.qeury(statement, param);
    }
}
