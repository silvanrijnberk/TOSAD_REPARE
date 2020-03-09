package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class keysDao extends DatabaseConnection{

    public String getPrimaryKey(String table) {

        try (Connection connection = super.getConnection(TargetDatabaseConnection.getDbDriver(), TargetDatabaseConnection.getDbUrl(), TargetDatabaseConnection.getDbUsername(), TargetDatabaseConnection.getDbPassword())){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT column_name FROM all_cons_columns WHERE constraint_name = (SELECT constraint_name FROM user_constraints WHERE UPPER(table_name) = UPPER('" + table + "') AND CONSTRAINT_TYPE = 'P')");
            while (result.next()) {
                return(result.getString("column_name"));
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "helaas";
    }

    public String getSecondaryKey(String table, String targetTable) {
        try (Connection connection = super.getConnection(TargetDatabaseConnection.getDbDriver(), TargetDatabaseConnection.getDbUrl(), TargetDatabaseConnection.getDbUsername(), TargetDatabaseConnection.getDbPassword())){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT a.column_name FROM all_cons_columns a JOIN all_constraints c ON a.owner = c.owner AND a.constraint_name = c.constraint_name JOIN all_constraints c_pk ON c.r_owner = c_pk.owner AND c.r_constraint_name = c_pk.constraint_name WHERE c.constraint_type = 'R'AND a.table_name = '" + table + "' AND c_pk.table_name = '" + targetTable + "'");
            while (result.next()) {
                return((result.getString(1)));

            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return("helaas");
    }

}
