package DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolDbSchemaDAO extends DatabaseConnection {


    public List selectAllRules(){
        List ruleTypes = new ArrayList(Arrays.asList("ATTRIBUTECOMPARERULE", "ATTRIBUTELISTRULE", "ATTRIBUTEOTHERRULE", "ATTRIBUTERANGERULE", "ENTITYOTHERRULE", "INTERENTITYCOMPARERULE", "MODIFYRULE", "TUPLECOMPARERULE", "TUPLEOTHERRULE"));
        List result = new ArrayList();
        for (Object x : ruleTypes) {
            List rules = getRules((String) x);
            for(Object r : rules)
                result.add(r);

        }
        return result;
    }

    public List<List<String>> getRules(String table) {
        List<List<String>> rules = new ArrayList();
        try (Connection con =super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
            PreparedStatement statementRuleID = con.prepareStatement("SELECT * FROM " + table);
            ResultSet result = statementRuleID.executeQuery();
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                List<String> rule = new ArrayList<String>();
                for (int i = 1; i <= columnsNumber; i++) {
                    rule.add(rsmd.getColumnName(i) + "-;-" + result.getString(i));
                }
                rules.add(rule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rules;
    }



    public boolean deleteRule(String id, String table) {
        String tableName = "";
        switch (table){
            case "ACMP":
                tableName = "ATTRIBUTECOMPARERULE";
                break;
            case "ALIS":
                tableName = "ATTRIBUTELISTRULE";
                break;
            case "AOTH":
                tableName = "ATTRIBUTEOTHERRULE";
                break;
            case "ARNG":
                tableName = "ATTRIBUTERANGERULE";
                break;
            case "EOTH":
                tableName = "ENTITYOTHERRULE";
                break;
            case "ICMP":
                tableName = "INTERENTITYCOMPARERULE";
                break;
            case "MODI":
                tableName = "MODIFYRULE";
                break;
            case "TCMP":
                tableName = "TUPLECOMPARERULE";
                break;
            case "TOTH":
                tableName = "TUPLEOTHERRULE";
                break;
            default:
                return false;
        }

        try (Connection con = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())){
            PreparedStatement preparedStatement = con.prepareStatement("Delete FROM "+tableName+" WHERE RULEID = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}