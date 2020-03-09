package BusinessRules;

public class Interentitycomparerule implements BusinessRule {

  private String scriptCode;
  private String businessRulename;
  private String ruleType;
  private String operator;
  private String columnValue;
  private String secondColumnValue;
  private String tableValue;
  private String secondTableValue;
  private String exception;

  public Interentitycomparerule(String sCode, String brName, String opr, String columnval, String secondColumnVal, String tableval, String secondTableVal, String exc) {
    this.scriptCode = sCode;
    this.businessRulename = brName;
    this.ruleType = "ICMP";
    this.operator = opr;
    this.columnValue = columnval;
    this.tableValue = tableval;
    this.exception = exc;
    this.secondColumnValue = secondColumnVal;
    this.secondTableValue = secondTableVal;

  }


//  @Override
//  public String getRuleId() {
//    return ruleId;
//  }
//
//  @Override
//  public void setRuleId(String rID) {
//    ruleId = rID;
//  }

  @Override
  public String getScriptCode() {
    return scriptCode;
  }

  @Override
  public void setScriptCode(String sCode) {
    scriptCode = sCode;
  }

  @Override
  public String getBusinessRulename() {
    return businessRulename;
  }

  @Override
  public void setBusinessRulename(String brName) {
    businessRulename = brName;
  }

  @Override
  public String getRuleType() {
    return ruleType;
  }

  @Override
  public void setRuleType() {
    ruleType = "ICMP";
  }

  @Override
  public String getOperator() {
    return operator;
  }

  @Override
  public void setOperator(String opr) {
    operator = opr;
  }

  @Override
  public String getColumnValue() {
    return columnValue;
  }

  @Override
  public void setColumnValue(String columnVal) {
    columnValue = columnVal;
  }

  public String getSecondColumnValue() {
    return secondColumnValue;
  }

  public void setSecondColumnValue(String secondColumnValue) {
    this.secondColumnValue = secondColumnValue;
  }

  @Override
  public String getTableValue() {
    return tableValue;
  }

  @Override
  public void setTableValue(String tableVal) {
    tableValue = tableVal;
  }

  public String getSecondTableValue() {
    return secondTableValue;
  }

  public void setSecondTableValue(String secondTableValue) {
    this.secondTableValue = secondTableValue;
  }

  @Override
  public String getException() {
    return exception;
  }

  @Override
  public void setException(String exc) {
    exception = exc;
  }

}
