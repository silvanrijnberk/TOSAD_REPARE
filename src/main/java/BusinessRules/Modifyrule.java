package BusinessRules;

public class Modifyrule implements BusinessRule {

  private String scriptCode;
  private String businessRulename;
  private String ruleType;
  private String operator;
  private String columnValue;
  private String secondColumnValue;
  private String tableValue;
  private String exception;
  private String checkvalue;

  public Modifyrule(String sCode, String brName, String opr, String columnval, String secondColumnVal, String tableval, String exc, String checkVal) {
    this.scriptCode = sCode;
    this.businessRulename = brName;
    this.ruleType = "MODI";
    this.operator = opr;
    this.columnValue = columnval;
    this.tableValue = tableval;
    this.exception = exc;
    this.secondColumnValue = secondColumnVal;
    this.checkvalue = checkVal;

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
    ruleType = "MODI";
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


  @Override
  public String getException() {
    return exception;
  }

  @Override
  public void setException(String exc) {
    exception = exc;
  }

  public String getCheckvalue() {
    return checkvalue;
  }

  public void setCheckvalue(String checkvalue) {
    this.checkvalue = checkvalue;
  }

}
