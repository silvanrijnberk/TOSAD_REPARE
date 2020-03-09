package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TargetDbSchemaDAO extends DatabaseConnection {

    Connection connection = super.getConnection(TargetDatabaseConnection.getDbDriver(), TargetDatabaseConnection.getDbUrl(), TargetDatabaseConnection.getDbUsername(), TargetDatabaseConnection.getDbPassword());

    public List<String> SelectTables() {

        List<String> result = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT table_name\n" +
                    "FROM user_tables\n" +
                    "ORDER BY table_name");
            while (resultSet.next()) {
                String table_name = resultSet.getString("TABLE_NAME");
                result.add(table_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public List SelectColumns(String name) {

        List<String> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT column_name\n" +
                    "FROM user_tab_cols\n" +
                    "WHERE table_name = ?");

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String columnName = resultSet.getString("column_name");
                result.add(columnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
}