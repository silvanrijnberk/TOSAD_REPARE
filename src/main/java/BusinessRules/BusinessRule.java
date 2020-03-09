package BusinessRules;

public interface BusinessRule {


//    String getRuleId();
//    void setRuleId(String ruleId);

    String getScriptCode();
    void setScriptCode(String scriptCode);

    String getBusinessRulename();
    void setBusinessRulename(String businessRulename);

    String getRuleType();
    void setRuleType();

    String getOperator();
    void setOperator(String operator);

    String getColumnValue();
    void setColumnValue(String columnValue);

    String getTableValue();
    void setTableValue(String tableValue);

    String getException();
    void setException(String exception);
}
