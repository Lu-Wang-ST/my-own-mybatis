package org.example.mybatis.statement;

import org.example.app.entity.Member;
import org.example.mybatis.session.Configuration;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatementHandler {
    private Configuration config;

    public StatementHandler(Configuration configuration) {
        this.config = configuration;
    }


    public <T> List<T> query(String statement, Object param) {
        Connection connection = null;
        Statement ps = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(config.getProperty("db.url"), config.getProperty("db.username"), config.getProperty("db.password"));
            ps = connection.createStatement();
            resultSet = ps.executeQuery(statement);

            while (resultSet.next()) {
                Object instance = Member.class.newInstance();
                for (Field field : Member.class.getDeclaredFields()) {
                    instance = setValue(instance, field, resultSet);
                }
                list.add((T) instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    private Object setValue(Object instance, Field field, ResultSet resultSet) {
        field.setAccessible(true);
        Class<?> typeClass = field.getType();
        try {
            if (typeClass == String.class) {
                field.set(instance, resultSet.getString(field.getName()));
            } else if (typeClass == Integer.class || typeClass == int.class) {
                field.set(instance, resultSet.getInt(field.getName()));
            } else {
                field.set(instance, resultSet.getString(field.getName()));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
