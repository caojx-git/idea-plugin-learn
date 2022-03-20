package com.caojx.learn.idea.plugin.infrastructure.utils;

import com.caojx.learn.idea.plugin.infrastructure.po.Column;
import com.caojx.learn.idea.plugin.infrastructure.po.Table;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库工具类
 */
public class DBHelper {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String database;

    private Properties properties;

    public DBHelper(String host, Integer port, String username, String password, String database) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        properties = new Properties();
        properties.put("user", this.username);
        properties.put("password", this.password);
        properties.setProperty("remarks", "true");
        properties.put("useInformationSchema", "true");
    }


    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Connection getConnection(String database) {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public List<String> getDatabases() {
        Connection connection = getConnection();
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getCatalogs();
            List<String> rs = new ArrayList<>();
            while (resultSet.next()) {
                String db = resultSet.getString("TABLE_CAT");
                if (StringUtils.equalsIgnoreCase(db, "information_schema")) {
                    continue;
                }
                rs.add(db);
            }
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<String> getAllTableName(String database) {
        this.database = database;
        Connection connection = this.getConnection(database);
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            List<String> ls = new ArrayList<>();
            while (resultSet.next()) {
                String s = resultSet.getString("TABLE_NAME");
                ls.add(s);
            }
            return ls;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnection(connection);
        }
    }

    public Table getTable(String tableName) {
        Connection conn = getConnection(this.database);
        try {
            DatabaseMetaData metaData = conn.getMetaData();

            ResultSet rs = metaData.getTables(null, "", tableName, new String[]{"TABLE"});
            Table table = null;
            if (rs.next()) {
                table = new Table(rs.getString("REMARKS"), tableName, getAllColumn(tableName, conn));
            }
            return table;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    public List<Column> getAllColumn(String tableName, Connection connection) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            String primaryKey = null;
            while (primaryKeys.next()) {
                primaryKey = primaryKeys.getString("COLUMN_NAME");
            }

            ResultSet rs = metaData.getColumns(null, null, tableName, null);
            List<Column> columns = new ArrayList<>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                Column column = new Column(rs.getString("REMARKS"), columnName, rs.getInt("DATA_TYPE"));
                if (columnName.endsWith(primaryKey)) {
                    column.setId(true);
                }
                columns.add(column);
            }
            return columns;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String testDatabase() {
        Connection conn = getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT VERSION() AS MYSQL_VERSION");
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("MYSQL_VERSION");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return "Err";
    }
}
