//package FuncTests;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import BusinessRules.AttributeRules;
//import BusinessRules.BusinessRule;
//import BusinessRules.InterEntityRules;
//import BusinessRules.TupleRules;
//import Scripts.AOTHScript;
//import Scripts.ARNGScript;
//import Scripts.FacadeRuleImpl;
//import Scripts.ScriptFactory;
//
//public class GeneratorTest {
//
//	public static void main(String[] args) throws SQLException{
//		ScriptFactory factory = new ScriptFactory();
//		BusinessRule businessRule = new BusinessRule();
//		AttributeRules attributeRules = null;
//		TupleRules tupleRules = null;
//
//		String businessRuleName = "buisnessRuleNaam";
//		String exception = "foutmelding";
//		String ruleType = "ACMP";
//		String firstValueCheck = "Value1";
//		String secondValueCheck = "Value2";
//		String operator = "operator"; // Have to check and or add this in actual code.
//		String compareValue = "compareValue";
//		String listOperator = "koe";
//		String listValues = "Sjaak";
//
//		int minValue = 1000;
//		int maxValue = 1500;
//
//		String columnValue = "columnValue";
//		String secondColumnValue = "ID";
//		String tableValue = "TableValue";
//		String sql = "SELECT ID FROM KLANTEN";
//		FacadeRuleImpl impl = new FacadeRuleImpl();
//
//		//klaar
//		if(ruleType.contains("ARNG")) {
//			attributeRules = factory.createAttributeRuleScript(ruleType);
//			attributeRules.addColumns(columnValue);
//			attributeRules.addListTables(tableValue);
//			businessRule.setName(businessRuleName);
//			businessRule.setExceptionMessage(exception);
//			((ARNGScript) attributeRules).setMaxValue(maxValue);
//			((ARNGScript) attributeRules).setMinValue(minValue);
//			System.out.println(impl.createScriptForAttributeRangeRule(ruleType, businessRuleName, columnValue, tableValue, minValue, maxValue, operator, exception));
//		}
//
//		//klaar
//		if(ruleType.contains("ACMP")) {
//			attributeRules = factory.createAttributeRuleScript(ruleType);
//			attributeRules.addColumns(columnValue);
//			attributeRules.addListTables(tableValue);
//			attributeRules.setColumnValueCheck(columnValue, compareValue);
//			businessRule.setName(businessRuleName);
//			businessRule.setExceptionMessage(exception);
//			System.out.println(impl.createScriptForAttributeCompareRule(ruleType, businessRuleName, operator, columnValue, tableValue, compareValue, exception));
//		}
//
//		//snap ik nog niet
//		if(ruleType.contains("AOTH")) {
//			attributeRules = factory.createAttributeRuleScript(ruleType);
//			attributeRules.addColumns(columnValue);
//			attributeRules.addListTables(tableValue);
//			((AOTHScript) attributeRules).setSqlQuery(sql);
//			businessRule.setName(businessRuleName);
//			businessRule.setExceptionMessage(exception);
//			System.out.println(impl.createScriptForAttributeOtherRule(ruleType, businessRuleName, sql, operator, columnValue, tableValue, exception));
//		}
//	}
//
//}
