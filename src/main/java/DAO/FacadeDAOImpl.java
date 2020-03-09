package DAO;

import BusinessRules.*;

public class FacadeDAOImpl {
	AttributeRangeRuleDAO ARNG = new AttributeRangeRuleDAO();
	AttributeListRuleDAO ALIS = new AttributeListRuleDAO();
	AttributeCompareRuleDAO ACMP = new AttributeCompareRuleDAO();
	AttributeOtherRuleDAO AOTH = new AttributeOtherRuleDAO();
	
	TupleCompareRuleDAO TCMP = new TupleCompareRuleDAO();
	TupleOtherRuleDAO TOTH = new TupleOtherRuleDAO();
	EntityOtherRuleDAO EOTH = new EntityOtherRuleDAO();
	ModifyRuleDAO MODI = new ModifyRuleDAO();
	InterEntityCompareRuleDAO ICMP = new InterEntityCompareRuleDAO();
	
	ConstraintDAO cons = new ConstraintDAO();
	
	
	// Update to tool database based on generated script and ruleID
	public void saveScriptAttributeRangeRule(String script, int ruleID) {
		ARNG.updateAttributeRangeRule(script, ruleID);
	}
	
	public void saveScriptAttributeListRule(String script, int ruleID) {
		ALIS = new AttributeListRuleDAO();
		ALIS.updateAttributeListRule(script, ruleID);
	}
	
	public void saveScriptAttributeCompareRule(String script, int ruleID) {
		ACMP = new AttributeCompareRuleDAO();
		ACMP.updateAttributeCompareRule(script, ruleID);
	}
	
	public void saveScriptAttributeOtherRule(String script, int ruleID) {
		AOTH = new AttributeOtherRuleDAO();
		AOTH.updateAttributeOtherRule(script, ruleID);
	}
	
	public void saveScriptTupleCompareRule(String script, int ruleID) {
		TCMP = new TupleCompareRuleDAO();
		TCMP.updateTupleCompareRule(script, ruleID);
	}
	
	public void saveScriptTupleOtherRule(String script, int ruleID) {
		TOTH = new TupleOtherRuleDAO();
		TOTH.updateTupleOtherRule(script, ruleID);
	}
	
	public void saveScriptEntityOtherRule(String script, int ruleID) {
		EOTH = new EntityOtherRuleDAO();
		EOTH.updateEntityOtherRule(script, ruleID);
	}
	
	public void saveScriptModifyRule(String script, int ruleID) {
		MODI = new ModifyRuleDAO();
		MODI.updateModifyRule(script, ruleID);
	}
	
	public void saveScriptInterEntityCompareRule(String script, int ruleID) {
		ICMP = new InterEntityCompareRuleDAO();
		ICMP.updateInterEntityCompareRule(script, ruleID);
	}
	
	//execute Script to target database.
	public void executeAttributeRangeRuleScript(String script) {
		ARNG.executeScriptToTargetDB(script);
	}
	
	public void executeAttributeListRuleScript(String script) {
		ALIS = new AttributeListRuleDAO();
		ALIS.executeScriptToTargetDB(script);
	}

	public void executeAttributeCompareRuleScript(String script) {
		ACMP = new AttributeCompareRuleDAO();
		ACMP.executeScriptToTargetDB(script);
	}
	
	public void executeAttributeOtherRuleScript(String script) {
		AOTH = new AttributeOtherRuleDAO();
		AOTH.executeScriptToTargetDB(script);
	}
	
	public void executeTupleCompareRuleScript(String script) {
		TCMP = new TupleCompareRuleDAO();
		TCMP.executeScriptToTargetDB(script);
	}
	
	public void executeTupleOtherRuleScript(String script) {
		TOTH = new TupleOtherRuleDAO();
		TOTH.executeScriptToTargetDB(script);
	}
	
	public void executeEntityOtherRuleScript(String script) {
		EOTH = new EntityOtherRuleDAO();
		EOTH.executeScriptToTargetDB(script);
	}
	
	public void executeModifyRuleScript(String script) {
		MODI = new ModifyRuleDAO();
		MODI.executeScriptToTargetDB(script);
	}
	
	public void executeInterEntityCompareRuleScript(String script) {
		ICMP = new InterEntityCompareRuleDAO();
		ICMP.executeScriptToTargetDB(script);
	}
	
	public void executeConstraint(String constraint) {
		cons = new ConstraintDAO();
		cons.executeScriptToTargetDB(constraint);
	}
	//====================================================================================

	public boolean createAttributeCompareRule(Attributecomparerule rule) {
		return ACMP.createAttributeCompareRule(rule);
	}

	public boolean createAttributeListRule(Attributelistrule rule) {
		return ALIS.createAttributeListRule(rule);
	}

	public boolean createAttributeOtherRule(Attributeotherrule rule) {
		return AOTH.createAttributeOtherRule(rule);
	}

	public boolean createAttributeRangeRule(Attributerangerule rule) {
		return ARNG.createAttributeRangeRule(rule);
	}

	public boolean createEntityOtherRule(Entityotherrule rule) {
		return EOTH.createEntityOtherRule(rule);
	}

	public boolean createInterEntityCompareRule(Interentitycomparerule rule) {
		return ICMP.createInterEntityCompareRule(rule);
	}

	public boolean createModifyRule(Modifyrule rule) {
		return MODI.createModifyRule(rule);
	}

	public boolean createTupleCompareRule(Tuplecomparerule rule) {
		return TCMP.createTupleCompareRule(rule);
	}

	public boolean createTupleOtherRule(Tupleotherrule rule) {
		return TOTH.createTupleOtherRule(rule);
	}
	
}
