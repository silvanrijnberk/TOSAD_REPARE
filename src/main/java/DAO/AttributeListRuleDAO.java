package DAO;

import BusinessRules.Attributelistrule;

import java.sql.*;

public class AttributeListRuleDAO extends DatabaseConnection {
	
	public boolean createAttributeListRule(Attributelistrule rule) {
		String query = createQuery(rule);
		boolean succes = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rule.getScriptCode());
			statement.setString(2, rule.getBusinessRulename());
			statement.setString(3, rule.getColumnValue());
			statement.setString(4, rule.getTableValue());
			statement.setString(5, rule.getListAsString());
			statement.setString(6, rule.getOperator());
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
	
	public String createQuery(Attributelistrule rule) {
		String result = "INSERT INTO ATTRIBUTELISTRULE (scriptcode, businessRuleName, ruleType, columnValue, tableValue, listValues, operator, exception) VALUES (?, ?, 'ALIS', ?, ?, ?, ?, ?)";
		return result;
	}
	
	public void updateAttributeListRule(String script, int ruleID) {
		int iD = selectMaxID();
		String query = "UPDATE ATTRIBUTELISTRULE SET SCRIPTCODE = ? WHERE RULEID = ?";
		
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
			ResultSet rs = statement.executeQuery("Select MAX(RULEID) FROM AttributeListRule");
			while (rs.next()) {
				result = rs.getInt("RuleID");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
