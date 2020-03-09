package BusinessRules;

import java.util.ArrayList;

public class Attributelistrule implements BusinessRule{

  private ArrayList<String> listvalues;
  private String scriptCode;
  private String businessRulename;
  private String ruleType;
  private String operator;
  private String columnValue;
  private String tableValue;
  private String exception;


  public Attributelistrule(String sCode, String brName, String opr, String columnval, String tableval, String exc) {
    listvalues = new ArrayList<String>();
    this.scriptCode = sCode;
    this.businessRulename = brName;
    this.ruleType = "ALIS";
    this.operator = opr;
    this.columnValue = columnval;
    this.tableValue = tableval;
    this.exception = exc;

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
    ruleType = "ALIS";
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

  public ArrayList<String> getListvalues() {
    return listvalues;
  }

  public void setListvalues(ArrayList<String> listvalues) {
    this.listvalues = listvalues;
  }

  public void addListValue(String val) {
    listvalues.add(val);
  }

  public void removeListValue(String val) {
    for (int i = 0; i < listvalues.size(); i++) {
      if (listvalues.get(i).equalsIgnoreCase(val)) {
        listvalues.remove(i);
      }
    }
  }

  public String getListAsString(){
    String result = "";
    int counter = 0;
    for (String val : listvalues) {
      if (counter < (listvalues.size() - 1)) {
        result = result + val + ", ";
      }
      else {
        result += val;
      }
    }
    return result;
  }
}