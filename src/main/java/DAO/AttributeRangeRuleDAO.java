package DAO;

import BusinessRules.Attributerangerule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AttributeRangeRuleDAO extends DatabaseConnection {
	
	public boolean createAttributeRangeRule(Attributerangerule rule) {
		String query = createQuery(rule);
		boolean succes = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rule.getScriptCode());
			statement.setString(2, rule.getBusinessRulename());
			statement.setString(3, rule.getColumnValue());
			statement.setString(4, rule.getTableValue());
			statement.setFloat(5, rule.getMinvalue());
			statement.setFloat(6, rule.getMaxvalue());
			statement.setString(7, rule.getOperator());
			statement.setString(8, rule.getException());


			if (statement.executeUpdate() == 1) { // 1 row updated!
				succes = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return succes;
	}

	//add operator
	public String createQuery(Attributerangerule rule) {
		String result = "INSERT INTO ATTRIBUTERANGERULE (scriptcode, businessRuleName, ruleType, columnValue, tableValue, minValue, maxValue, operator, exception) VALUES (?, ?, 'ARNG', ?, ?, ?, ?, ?, ?)";
		return result;
	}
	
	public void updateAttributeRangeRule(String script, int ruleID) {
		String query = "UPDATE ATTRIBUTERANGERULE SET SCRIPTCODE = ? WHERE RULEID = ?";
		
		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1,  script);
			statement.setInt(2, ruleID);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Execute the Script to the targetDatabase.
	public boolean executeScriptToTargetDB(String script) {
		boolean result = false;
		try (Connection connection = super.getConnection(TargetDatabaseConnection.getDbDriver(), TargetDatabaseConnection.getDbUrl(), TargetDatabaseConnection.getDbUsername(), TargetDatabaseConnection.getDbPassword())){
			Statement statement = connection.createStatement();
			
			result = statement.execute(script);
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
