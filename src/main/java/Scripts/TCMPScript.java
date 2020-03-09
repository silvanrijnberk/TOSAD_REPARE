package Scripts;


import BusinessRules.BusinessRule;
import BusinessRules.Tuplecomparerule;

public class TCMPScript implements Script {


	@Override
	public String generateScript(BusinessRule BRule) {
		Tuplecomparerule rule = (Tuplecomparerule) BRule;
		String script = "CREATE OR REPLACE TRIGGER " + "BRG_VBMG_TRG_TCMP_" +rule.getBusinessRulename() +
				"\nBEFORE INSERT OR UPDATE ON " + rule.getTableValue() +
				"\nFOR EACH ROW" +
				"\nDECLARE" +
				"\nl_passed boolean := true;"+
				"\nVALUE1 VARCHAR2(50);" +
				"\nVALUE2 VARCHAR2(50);" +
				"\nBEGIN" +
				"\nVALUE1 := :NEW." + rule.getColumnValue() + ";" +
				"\nVALUE2 := :NEW." + rule.getSecondcolumnvalue() + ";" +
				"\nl_passed :=  value1" + " " + rule.getOperator() + " " + "value2;" +
				"\n IF l_passed THEN"+
				"\nDBMS_OUTPUT.PUT_LINE('Correct');" +
				"\nELSE" +
				"\nDBMS_OUTPUT.PUT_LINE('Incorrect');" +
				"\nraise_application_error(-20101, '" + rule.getException() + "');" +  //--exception
				"\nEND IF;" +
				"\nEND;";

		return script;
	}
}
