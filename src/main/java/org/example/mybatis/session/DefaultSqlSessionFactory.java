package org.example.mybatis.session;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration config;

    public DefaultSqlSessionFactory(Configuration config) {
        this.config = config;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(config);
    }
}
