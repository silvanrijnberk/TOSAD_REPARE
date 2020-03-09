package Scripts;


import BusinessRules.BusinessRule;
import BusinessRules.Tupleotherrule;

public class TOTHScript implements Script  {
	
	@Override
	public String generateScript(BusinessRule BRule) {
		Tupleotherrule rule = (Tupleotherrule) BRule;
		String script = "CREATE OR REPLACE TRIGGER " +"BRG_VBMG_TRG_TOTH_"+rule.getBusinessRulename()+
				 " \nBEFORE INSERT OR UPDATE ON " + rule.getTableValue() +
				 " \nFOR EACH ROW" + 
				 " \nDECLARE" +
				 " \nVALUE1 VARCHAR2;" +
				 " \nVALUE2 VARCHAR2;" +
				 " \nBEGIN" +
				 " \nVALUE1 := :NEW." + rule.getColumnValue() + ";" +
				 " \nVALUE2 := :NEW." + rule.getSecondcolumnvalue() + ";" +
				 " \nFOR objects in (SELECT COUNT(" + rule.getColumnValue() + ") FROM " + rule.getTableValue() + ")" +
				 " \nIF (objects = VALUE1)" +
				 " \nTHEN " +
				 " \nIF (VALUE2 " + rule.getOperator() + " " + rule.getSqlquery() + ") = FALSE" +
				 " \nTHEN " +
				 " \nRaise_Application_Error(-20000, '" + rule.getException() + "');" +
				 " \nEND IF;" +
				 " \nEND IF;" +
				 " \nEND;";
		
		return script;
	}

}
