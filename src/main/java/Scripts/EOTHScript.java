package Scripts;


import BusinessRules.BusinessRule;
import BusinessRules.Entityotherrule;

public class EOTHScript implements Script {
	
	@Override
	public String generateScript(BusinessRule BRule) {
		Entityotherrule rule = (Entityotherrule) BRule;
		String script = " CREATE OR REPLACE TRIGGER " +"BRG_VBMG_TRG_EOTH_" + rule.getBusinessRulename() +
				" \nBEFORE INSERT OR UPDATE ON " + rule.getTableValue() +
				" \nFOR EACH ROW" + 
				" \nDECLARE" +
				" \nBEGIN" +
				" \nIF (" + rule.getColumnValue() + " != " + rule.getSqlquery() + ")" +
				" \nTHEN " +
				" \nRaise_Application_Error(-20000, '" + rule.getException() + "');" +
				" \nEND IF;" +
				" \nEND;";
		
		return script;
	}

}
