package DAO;

import BusinessRules.Modifyrule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyRuleDAO extends DatabaseConnection {

	public boolean createModifyRule(Modifyrule rule) {
		String query = createQuery(rule);
		boolean success = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rule.getScriptCode());
			statement.setString(2, rule.getBusinessRulename());
			statement.setString(3, rule.getOperator());
			statement.setString(4, rule.getTableValue());
			statement.setString(5, rule.getColumnValue());
			statement.setString(6, rule.getSecondColumnValue());
			statement.setString(7, rule.getCheckvalue());
			statement.setString(8, rule.getException());

			if (statement.executeUpdate() == 1) { // 1 row updated!
				success = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public String createQuery(Modifyrule rule) {
		String result = "INSERT INTO MODIFYRULE (Scriptcode, RuleType, businessRuleName, operator, firstTableValue, firstColumnValue, secondColumnValue, checkValue, exception) VALUES (?, 'MODI', ?, ?, ?, ?, ?, ?, ?)";
		return result;
	}

	public void updateModifyRule(String script, int ruleID) {
		String query = "UPDATE MODIFYRULE set SCRIPTCODE = ? where RULEID = ?";

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
