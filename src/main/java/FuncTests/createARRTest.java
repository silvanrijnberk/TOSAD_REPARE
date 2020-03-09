package FuncTests;

import DAO.*;

public class createARRTest {

    public static void main(String[] args) {
        AttributeCompareRuleDAO acmp = new AttributeCompareRuleDAO();
        AttributeRangeRuleDAO arng = new AttributeRangeRuleDAO();
        AttributeListRuleDAO alis = new AttributeListRuleDAO();
        AttributeOtherRuleDAO aoth = new AttributeOtherRuleDAO();
        EntityOtherRuleDAO eoth = new EntityOtherRuleDAO();
        TupleCompareRuleDAO tcmp = new TupleCompareRuleDAO();
        InterEntityCompareRuleDAO icmp = new InterEntityCompareRuleDAO();
        TupleOtherRuleDAO toth = new TupleOtherRuleDAO();
        ModifyRuleDAO modi = new ModifyRuleDAO();

//        System.out.println(acmp.createAttributeCompareRule("rulename", "compareVal", "operator", "column", "table", "exception"));
//        System.out.println(arng.createAttributeRangeRule("rulename", "column ", "table", 0, 5, "exception"));
//        System.out.println(aoth.createAttributeOtherRule("rulename", "sqlquery", "operator", "column", "table", "exception"));
//        System.out.println(alis.createAttributeListRule("rulename", "column", "table", "listval", "exception"));
//        System.out.println(eoth.createEntityOtherRule("rulename", "operator", "sqlquery", "column", "table", "exception"));
//        System.out.println(tcmp.createTupleCompareRule("rulename", "operator", "firstcolumn", "secondcolumn", "table", "exception"));
//        System.out.println(icmp.createInterEntityOtherRule("rulename", "operator", "firstcolumn", "secondColumn", "firstTable", "secondTable", "exception"));
//        System.out.println(toth.createTupleOtherRule("query", "rulename", "operator", "firstcolumn", "Secondcolumn", "table", "exception"));
//        System.out.println(modi.createModifyRule("rulename", "operator", "firstTable", "secondTable", "firstColumn", "secondColumn", "checkvalue", "exception"));

    }
}
