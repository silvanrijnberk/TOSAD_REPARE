package BusinessRules;

public class Tupleotherrule implements BusinessRule {

  private String scriptCode;
  private String businessRulename;
  private String ruleType;
  private String operator;
  private String columnValue;
  private String tableValue;
  private String exception;
  private String secondcolumnvalue;
  private String sqlquery;

  public Tupleotherrule(String sCode, String brName, String opr, String columnval, String secondColumnVal, String tableval, String exc, String qry) {
    this.scriptCode = sCode;
    this.businessRulename = brName;
    this.ruleType = "TOTH";
    this.operator = opr;
    this.columnValue = columnval;
    this.secondcolumnvalue = secondColumnVal;
    this.tableValue = tableval;
    this.exception = exc;
    this.sqlquery = qry;

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
    ruleType = "TOTH";
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

  public String getSecondcolumnvalue() {
    return secondcolumnvalue;
  }

  public void setSecondcolumnvalue(String secondcolumnvalue) {
    this.secondcolumnvalue = secondcolumnvalue;
  }

  public String getSqlquery() {
    return sqlquery;
  }

  public void setSqlquery(String sqlquery) {
    this.sqlquery = sqlquery;
  }
}
