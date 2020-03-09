package DAO;

import BusinessRules.Attributecomparerule;
import BusinessRules.BusinessRule;

import java.sql.*;

public class AttributeCompareRuleDAO extends DatabaseConnection {
	
	public boolean createAttributeCompareRule(Attributecomparerule rule) {
		String query = createQuery(rule);
		boolean succes = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rule.getScriptCode());
			statement.setString(2, rule.getBusinessRulename());
			statement.setString(3, rule.getCheckValue());
			statement.setString(4, rule.getOperator());
			statement.setString(5, rule.getColumnValue());
			statement.setString(6, rule.getTableValue());
			statement.setString(7, rule.getException());

			if (statement.executeUpdate() == 1) { // 1 row updated!
				succes = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return succes;
	}
	
	public String createQuery(Attributecomparerule rule) {
		String query = "INSERT INTO ATTRIBUTECOMPARERULE (scriptcode, businessRuleName, ruleType, compareValue, Operator, columnValue, tableValue, exception) VALUES (?, ?, 'ACMP', ?, ?, ?, ?, ?)";
		return query;
	}

	
	// Set the script in the tooldatabase (generatedcode) and set the ID (RULEID)
	public void updateAttributeCompareRule (String script, int ruleID) {
		int iD = selectMaxID();
		String query = "UPDATE ATTRIBUTECOMPARERULE set SCRIPTCODE = ? WHERE RULEID = ?";
		
		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1,  script);
			statement.setInt(2, iD);
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

	public int selectMaxID() {
		int result = 0;
		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select MAX(RULEID) FROM AttributeCompareRule");
			while (rs.next()) {
				result = rs.getInt("RuleID");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
