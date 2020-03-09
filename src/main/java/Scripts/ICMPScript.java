package Scripts;


import BusinessRules.BusinessRule;
import BusinessRules.Interentitycomparerule;
import DAO.keysDao;

public class ICMPScript implements Script {


	public String generateScript(BusinessRule BRule) {
		keysDao keydao = new keysDao();
		Interentitycomparerule rule = (Interentitycomparerule) BRule;
		String FK1 = keydao.getSecondaryKey(rule.getTableValue(), rule.getSecondTableValue());
		String PK2 = keydao.getPrimaryKey(rule.getSecondTableValue());
		String script = "create or replace trigger BRG_VBMG_TRG_ICMP_" +  rule.getBusinessRulename() +
				 "\nbefore insert or update on " + rule.getTableValue() +  //VBMG_LEVERINGEN
				 "\nFOR EACH ROW" +
				 "\ndeclare" +
				 "\nl_passed boolean := true;" +
				 "\nl_error_stack varchar2(4000);" +
				 "\ncursor lc_ord is" +
				 "\nselect ord." + rule.getSecondColumnValue() + //prioriteit --columnValue2
				 "\nfrom " + rule.getSecondTableValue() + " ord" + //tableValue2, vbmg_orders
				 "\nwhere ord." + PK2 + " = :new." + FK1 + ";" + //--TableValue2 PK,  --Tablevalue1 FK
				 "\nl_prio  " + rule.getSecondTableValue() + "." + rule.getSecondColumnValue() + "%type;" + //--TableValue2.ColumnValue2"
				 "\nbegin" +
				 "\nopen lc_ord;" +
				 "\nfetch lc_ord into l_prio;" +
				 "\nclose lc_ord;" +
				 "\nl_passed := (:new." + rule.getColumnValue() + " " + rule.getOperator() + " l_prio);" +  //--ColumnValue1 Operator
				 "\nIF l_passed THEN" +
				 "\nDBMS_OUTPUT.PUT_LINE('Congratulations');" +
				 "\nELSE" +
				 "\nraise_application_error(-20101, '" + rule.getException() + "');" +  //--exception
				 "\nEND IF;" +
				 "\nEND;";

		return script;
	}
}
