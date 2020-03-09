package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessRules.Attributeotherrule;
import DAO.FacadeDAOImpl;
import Scripts.ScriptFactory;


//attribute other rule
public class AOTHService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOImpl = new FacadeDAOImpl();
    checkMethods cm = new checkMethods();
    ScriptFactory scriptFactory = new ScriptFactory();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String message = "Attribute Other Rule Created & Script Generated";
			boolean failure = false;
			String businessRuleName = request.getParameter("name");
			String sqlQuery = request.getParameter("sqlQuery");
			String tableValue = request.getParameter("table");
			String columnValue = request.getParameter("column");
			String exception = request.getParameter("exception");
			String operator = request.getParameter("operator");
			String ruleType = "AOTH";

			if (cm.isEmpty(new ArrayList<String>(Arrays.asList(businessRuleName, ruleType, tableValue, columnValue, sqlQuery, operator, exception)))) {
				message = "FAILED! a value is empty";
				failure = true;
			} else if (!failure) {
				Attributeotherrule rule = new Attributeotherrule("", businessRuleName, operator, columnValue, tableValue, exception, sqlQuery);
				rule.setScriptCode(scriptFactory.createScript(rule));
				boolean succes = DAOImpl.createAttributeOtherRule(rule);
				if (succes) {
					DAOImpl.executeAttributeOtherRuleScript(rule.getScriptCode());
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

