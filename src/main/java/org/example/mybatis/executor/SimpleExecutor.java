package org.example.mybatis.executor;

import org.example.mybatis.session.Configuration;
import org.example.mybatis.statement.StatementHandler;

import java.util.List;

public class SimpleExecutor implements Executor {

    private Configuration config;

    public SimpleExecutor(Configuration config) {
        this.config = config;
    }

    @Override
    public <T> List<T> qeury(String statement, Object param) {

        StatementHandler statementHandler = config.newStatementHandler();
        return statementHandler.query(statement, param);
    }
}
