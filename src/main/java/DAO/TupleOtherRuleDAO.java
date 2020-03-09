package DAO;

import BusinessRules.Tupleotherrule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TupleOtherRuleDAO extends DatabaseConnection {

	public boolean createTupleOtherRule(Tupleotherrule rule) {
		String query = createQuery(rule);
		boolean success = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);

			if (statement.executeUpdate() == 1) { // 1 row updated!
				success = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public String createQuery(Tupleotherrule rule) {
		String result = String.format("INSERT INTO TUPLEOTHERRULE (scriptcode, RuleType, sqlQuery, businessRuleName, operator, firstColumnValue, secondColumnValue, tableValue, exception) VALUES ('%s', 'TOTH', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
				rule.getScriptCode(),
				rule.getSqlquery(),
				rule.getBusinessRulename(),
				rule.getOperator(),
				rule.getColumnValue(),
				rule.getSecondcolumnvalue(),
				rule.getTableValue(),
				rule.getException());
		return result;
	}

	public void updateTupleOtherRule(String script, int ruleID) {
		String query = "UPDATE TUPLEOTHERRULE set SCRIPTCODE = ? WHERE RULEID = ?";

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

