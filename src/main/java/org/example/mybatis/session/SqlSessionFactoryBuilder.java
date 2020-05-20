package org.example.mybatis.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactoryBuilder() {
    }

    private Configuration parse(InputStream inputStream) {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            configuration.setProperties(properties);
            // 解析sql
            for (Map.Entry entry : properties.entrySet()) {
                String key = (String) entry.getKey();
                if (!key.startsWith("mapper.")) {
                    continue;
                }
                String statementId = key.substring(7);
                int index = statementId.lastIndexOf(".");
                String className = statementId.substring(0, index);
                String methodName = statementId.substring(index + 1);
                configuration.addMapper(Class.forName(className));
                configuration.addMappedStatement(statementId, (String) entry.getValue());
            }

            // TODO解析插件等其他配置
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public SqlSessionFactory build(InputStream inputStream) {
        // 此处默认传进来的是一个properties文件的文件流。
        // 解析配置文件的内容并设置到config中
        Configuration config = parse(inputStream);

        return new DefaultSqlSessionFactory(config);
    }
}
