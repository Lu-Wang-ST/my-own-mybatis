package org.example.mybatis.session;

import org.example.mybatis.binding.MapperRegistry;
import org.example.mybatis.statement.StatementHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {
    private Properties properties;
    private MapperRegistry mapperRegistry;
    private Map<String, String> mappedStatements = new HashMap<>();

    public Configuration() {
        this.mapperRegistry = new MapperRegistry();
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public <T> void addMapper(Class<T> type) {
        this.mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return (T) this.mapperRegistry.getMapper(type, sqlSession);
    }

    public void addMappedStatement(String id, String sql) {
        this.mappedStatements.put(id, sql);
    }

    public StatementHandler newStatementHandler() {
        return new StatementHandler(this);
    }

    public String getStatement(String statementId) {
        return this.mappedStatements.get(statementId);
    }
}
