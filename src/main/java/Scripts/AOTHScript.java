package Scripts;


import BusinessRules.Attributeotherrule;
import BusinessRules.BusinessRule;

public class AOTHScript implements Script {


	@Override
	public String generateScript(BusinessRule BRule) {
		Attributeotherrule rule = (Attributeotherrule) BRule;
		String script = "CREATE OR REPLACE TRIGGER " + "BRG_VBMG_TRG_AOTH_"+rule.getBusinessRulename() +
				"\nBEFORE INSERT OR UPDATE ON " + rule.getTableValue() +
				"\nFOR EACH ROW" +
				"\nDECLARE" +
				"\nVALUE VARCHAR2(50);" +
				"\nBEGIN" +
				"\nSELECT " + rule.getColumnValue() + ";" +
				"\nVALUE := :NEW." + rule.getColumnValue() + ";" +
				"\nIF (VALUE = " + rule.getSqlquery() + ") = FALSE" +
				"\nTHEN " +
				"\nRaise_Application_Error(-20000, '"+ rule.getException() + "');" +
				"\nEND IF;" +
				"\nEND;";

		return script;
	}

}
