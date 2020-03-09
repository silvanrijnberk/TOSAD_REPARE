package Scripts;

import BusinessRules.Attributerangerule;
import BusinessRules.BusinessRule;

public class ARNGScript implements Script {

	// only operator BETWEEM or NOT BETWEEN can work like this
	@Override
	public String generateScript(BusinessRule BRule) {
		Attributerangerule rule = (Attributerangerule)BRule;
		String script = "CREATE OR REPLACE TRIGGER " + "BRG_VBMG_TRG_ARNG_" + rule.getBusinessRulename() +
				"\nBEFORE INSERT OR UPDATE ON " +  rule.getTableValue() +
				"\nFOR EACH ROW" +
				"\nDECLARE" +
				"\nl_passed boolean:= true;" +
				"\nBEGIN" + 
				"\nl_passed := :NEW." + rule.getColumnValue() +" " + rule.getOperator() + " "+rule.getMinvalue()+" "+"AND"+ " " +rule.getMaxvalue()+ ";" +
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

