package Scripts;

import BusinessRules.Attributecomparerule;
import BusinessRules.BusinessRule;

public class ACMPScript implements Script {

	@Override
	public String generateScript(BusinessRule BRule) {
		Attributecomparerule rule = (Attributecomparerule) BRule;
		String script = "CREATE OR REPLACE TRIGGER " + "BRG_VBMG_TRG_ACMP_"+rule.getBusinessRulename()+
				"\nBEFORE INSERT or UPDATE ON " + rule.getTableValue() +
				"\nFOR EACH ROW" +
				"\nDECLARE" +
				"\nl_passed boolean:=true;" +
				"\nBEGIN" +
				"\nl_passed := :new."+ rule.getColumnValue() +" "+ rule.getOperator() + " "+ rule.getCheckValue()+ ";" +
				"\nIF l_passed THEN" +
				"\nDBMS_OUTPUT.PUT_LINE('Correct')"+ ";" +
				"\nELSE"+
				"\nDBMS_OUTPUT.PUT_LINE('Incorrect')"+";"+
				"\nraise_application_error(-20000,"+"'"+rule.getException()+"'"+");" +
				"\nEND IF;" +
				"\nEND;";
		return script;
	}
}
