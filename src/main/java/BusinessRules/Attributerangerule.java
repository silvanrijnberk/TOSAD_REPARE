package BusinessRules;

public class Attributerangerule implements BusinessRule {

  private float minvalue;
  private float maxvalue;
  private String scriptCode;
  private String businessRulename;
  private String ruleType;
  private String operator;
  private String columnValue;
  private String tableValue;
  private String exception;


  public Attributerangerule(String sCode, String brName, String opr, String columnval, String tableval, String exc, float minval, float maxval) {
    this.scriptCode = sCode;
    this.businessRulename = brName;
    this.ruleType = "ARNG";
    this.operator = opr;
    this.columnValue = columnval;
    this.tableValue = tableval;
    this.exception = exc;
    this.minvalue = minval;
    this.maxvalue = maxval;

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
    ruleType = "ARNG";
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

  public float getMinvalue() {
    return minvalue;
  }

  public void setMinvalue(float minvalue) {
    this.minvalue = minvalue;
  }

  public float getMaxvalue() {
    return maxvalue;
  }

  public void setMaxvalue(int maxvalue) {
    this.maxvalue = maxvalue;
  }
}
