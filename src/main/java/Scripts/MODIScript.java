package Scripts;


import BusinessRules.BusinessRule;
import BusinessRules.Modifyrule;

public class MODIScript implements Script {


	public String generateScript(BusinessRule BRule){
		Modifyrule rule = (Modifyrule) BRule;
		String script = "CREATE OR REPLACE TRIGGER " + "BRG_VBMG_TRG_MODI_" + rule.getBusinessRulename() +
				" \nBEFORE INSERT OR UPDATE ON " + rule.getTableValue() +
				" \nFOR EACH ROW" +
				" \nDECLARE" +
				"\nl_oper varchar2 ( 3 );" +
				"\nl_passed boolean := true;" +
				"\nBEGIN" +
				"\nIF UPDATING" +
				"\nTHEN" +
				"\nl_oper := 'UPD';" +
				"\nEND IF;" +
				"\nif :new." + rule.getColumnValue() + " " + "=" + " "+"'"+rule.getCheckvalue()+"'"+ " " + "AND" +
				"\n l_oper = 'UPD'" +
				"\nTHEN" +
				"\n l_passed := :new."+rule.getSecondColumnValue()+" " + rule.getOperator() + " " +":"+ "old."+rule.getSecondColumnValue()+";" +
				"\n IF NOT l_passed" +
				"\nTHEN" +
				"\nRAISE_APPLICATION_ERROR(-20040, '" + rule.getException() + "'); " +
				"\nEND IF;" +
				"\nEND IF;" +
				"\nEND;";
		return script;
	}

}