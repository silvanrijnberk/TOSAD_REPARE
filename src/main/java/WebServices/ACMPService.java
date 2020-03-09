package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessRules.Attributecomparerule;
import DAO.FacadeDAOImpl;
import Scripts.ScriptFactory;


public class ACMPService extends HttpServlet {
    private static final long serialVersionUID = 1L;
    FacadeDAOImpl DAOimpl = new FacadeDAOImpl();
    checkMethods cm = new checkMethods();
    ScriptFactory scriptFactory = new ScriptFactory();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String businessRuleName = request.getParameter("name");
        String tableValue = request.getParameter("table");
        String columnValue = request.getParameter("column");
        String compareValue = request.getParameter("comparevalue");
        String operator = request.getParameter("operator");
        String exception = request.getParameter("exception");
        String ruleType = "ACMP";
        String message = "Attribute Compare Rule Created & Script Generated";
        boolean failure = false;

        if (cm.isEmpty(new ArrayList(Arrays.asList(businessRuleName, ruleType, tableValue, columnValue, compareValue, operator, exception)))) {
            message = "FAILED! a value is empty";
            failure = true;
        } else if (!cm.correctOperator(operator, compareValue)) {
            message = "FAILED! " + cm.getMessage();
            failure = true;
        }
        if (!failure) {
            Attributecomparerule rule = new Attributecomparerule("", businessRuleName, operator, columnValue, tableValue, exception, compareValue);
            rule.setScriptCode(scriptFactory.createScript(rule));
            boolean succes = DAOimpl.createAttributeCompareRule(rule);
            if (succes) {
                DAOimpl.executeAttributeCompareRuleScript(rule.getScriptCode());
            }

        }
        PrintWriter out = response.getWriter();
        out.println("<html><body><b>" + message + "</b><br>"
                + "</br><input type=\"button\" onclick=\"window.location.href = "
                + "'http://localhost:8081/BRGTEAM3/';\" value=\"Return\"/> <button onclick=\"goBack()\">Go Back</button>\r\n" +
                "\r\n" +
                "<script>\r\n" +
                "function goBack() {\r\n" +
                "  window.history.back();\r\n" +
                "}\r\n" +
                "</script></body></html>");
        out.close();


    }

}





