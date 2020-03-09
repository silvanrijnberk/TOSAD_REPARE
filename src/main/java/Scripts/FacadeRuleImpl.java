//package Scripts;
//
//import java.util.ArrayList;
//
//import BusinessRules.AttributeRules;
//import BusinessRules.BusinessRule;
//import BusinessRules.EntityRules;
//import BusinessRules.InterEntityRules;
//import BusinessRules.ModifyRules;
//import BusinessRules.TupleRules;
//
//public class FacadeRuleImpl {
//	ScriptFactory factory = new ScriptFactory();
//	BusinessRule businessRule = new BusinessRule();
//	AttributeRules attributeRules = null;
//	TupleRules tupleRules = null;
//	EntityRules entityRules = null;
//	InterEntityRules interEntityRules = null;
//	ModifyRules modifyRules = null;
//
//	//All the different scripts will be generated based on the businessRuleTypes
//
//	public String createScriptForAttributeRangeRule(String ruleType, String businessRuleName, String columnValue, String tableValue, int minValue, int maxValue, String operator, String exception) {
//		attributeRules = factory.createAttributeRuleScript(ruleType);
//		attributeRules.addColumns(columnValue);
//		attributeRules.addListTables(tableValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		((ARNGScript) attributeRules).setMaxValue(maxValue);
//		((ARNGScript) attributeRules).setMinValue(minValue);
//		return attributeRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//	}
//
//	public String createScriptForAttributeCompareRule(String ruleType, String businessRuleName, String operator, String columnValue, String tableValue, String compareValue, String exception) {
//		attributeRules = factory.createAttributeRuleScript(ruleType);
//		businessRule.setName(businessRuleName);
//		attributeRules.addListTables(tableValue);
//		attributeRules.addColumns(columnValue);
//		attributeRules.setColumnValueCheck(columnValue, compareValue);
//		businessRule.setExceptionMessage(exception);
//		return attributeRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//	}
//
//	public String createScriptForAttributeOtherRule(String ruleType, String businessRuleName, String SqlQuery, String operator, String columnValue, String tableValue, String exception) {
//		attributeRules = factory.createAttributeRuleScript(ruleType);
//		attributeRules.addColumns(columnValue);
//		attributeRules.addListTables(tableValue);
//		((AOTHScript) attributeRules).setSqlQuery(SqlQuery);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		return attributeRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//	}
//
//
//	public String createScriptForAttributeListRule(String ruleType, String businessRuleName, String operator, String columnValue, ArrayList<String> listValues, String tableValue, String exception) {
//		attributeRules = factory.createAttributeRuleScript(ruleType);
//		for(String values : listValues) {
//			attributeRules.addValuesToListRule(values);
//		}
//		attributeRules.addColumns(columnValue);
//		attributeRules.addListTables(tableValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		return attributeRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//	}
//
//	public String createScriptForTupleCompareRule(String ruleType, String businessRuleName, String operator, String firstColumnValue, String secondColumnValue, String firstTableValue, String exception) {
//		tupleRules = factory.createTupleRuleScript(ruleType);
//		tupleRules.addColumns(firstColumnValue);
//		tupleRules.addColumns(secondColumnValue);
//		tupleRules.addListTables(firstTableValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		return tupleRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//	}
//
//	public String createScriptForTupleOtherRule(String ruleType, String businessRuleName, String operator, String SqlQuery, String firstColumnValue, String secondColumnValue, String firstTableValue, String exception) {
//		tupleRules = factory.createTupleRuleScript(ruleType);
//		tupleRules.addColumns(firstColumnValue);
//		tupleRules.addColumns(secondColumnValue);
//		tupleRules.addListTables(firstTableValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		((TOTHScript) tupleRules).setSqlQuery(SqlQuery);
//		return tupleRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//
//	}
//
//	public String createScriptForEntityRule(String ruleType, String businessRuleName, String operator, String firstColumnValue, String SqlQuery, String firstTableValue, String exception) {
//		entityRules = factory.createEntityRuleScript(ruleType);
//		entityRules.addColumns(firstColumnValue);
//		entityRules.addListTables(firstTableValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		((EOTHScript) entityRules).setSqlQuery(SqlQuery);
//		return entityRules.generateScript(businessRule.getName(), operator, businessRule.getExceptionMessage());
//	}
//
//	public String createScriptForInterEntityRule(String ruleType, String businessRuleName, String operator, String firstColumnValue, String secondColumnValue, String firstTableValue, String secondTableValue, String table1FK, String table2PK, String exception) {
//		interEntityRules = factory.createInterEntityRuleScript(ruleType);
//		interEntityRules.addColumns(firstColumnValue);
//		interEntityRules.addColumns(secondColumnValue);
//		interEntityRules.addListTables(firstTableValue);
//		interEntityRules.addListTables(secondTableValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		return interEntityRules.generateScript(businessRule.getName(), operator, table1FK, table2PK, businessRule.getExceptionMessage());
//	}
//
//	public String createScriptForModifyRule(String ruleType, String businessRuleName, String operator, String firstTableValue, String secondTableValue, String firstColumnValue, String secondColumnValue, String checkValue, String exception) {
//		modifyRules = factory.createModifyRuleScript(ruleType);
//		modifyRules.addListTables(firstTableValue);
//		modifyRules.addListTables(secondTableValue);
//		modifyRules.addColumns(firstColumnValue);
//		modifyRules.addColumns(secondColumnValue);
//		modifyRules.setColumnValueCheck(secondColumnValue, checkValue);
//		businessRule.setName(businessRuleName);
//		businessRule.setExceptionMessage(exception);
//		return modifyRules.generateScript(businessRule.getName(), "", businessRule.getExceptionMessage());
//	}
//
//	public String createConstraint(String constraintType, String constraintName, String tableValue, String expression) {
//		attributeRules = factory.createConstraint(constraintType);
//		businessRule.setName(constraintName);
//		attributeRules.addListTables(tableValue);
//		((Constraint) attributeRules).setExpression(expression);
//		return attributeRules.generateScript(businessRule.getName(),"", "");
//
//	}
//}