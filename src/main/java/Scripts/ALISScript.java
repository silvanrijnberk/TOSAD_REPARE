package Scripts;

import BusinessRules.Attributelistrule;
import BusinessRules.BusinessRule;

public class ALISScript implements Script {


	@Override
	public String generateScript(BusinessRule BRule) {
		Attributelistrule rule = (Attributelistrule) BRule;
		String script = "CREATE OR REPLACE TRIGGER " + "BRG_VBMG_TRG_ALIS_"+rule.getBusinessRulename() +
				"\nBEFORE INSERT OR UPDATE ON " + rule.getTableValue() +
				"\nFOR EACH ROW" +
				"\nDECLARE" +
				"\nl_passed boolean := true;" +
				"\nBEGIN" +
				"\nl_passed := :new."+ rule.getColumnValue() + " " + rule.getOperator() + " (";
		for (String value : rule.getListvalues()){
			if (rule.getListvalues().indexOf(value) < (rule.getListvalues().size() - 1)) {
				script+= "'"+ value + "'," ;
			}
			else {
				script+= " '"+ value + "'" ;
			}
		}
		script += ");" +
				"\nif l_passed = false" +
				"\nTHEN " +
				"\nRaise_Application_Error(-20000, '" + rule.getException() + "');" +
				"\nEND IF;" +
				"\nEND;";
		return script;
	}

}