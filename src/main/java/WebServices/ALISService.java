package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessRules.Attributelistrule;
import DAO.FacadeDAOImpl;

import Scripts.ScriptFactory;

public class ALISService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOimpl = new FacadeDAOImpl();
    checkMethods cm = new checkMethods();
    ScriptFactory scriptFactory = new ScriptFactory();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String businessRuleName = request.getParameter("name");
		String columnValue = request.getParameter("column");
		String tableValue = request.getParameter("table");
		String listValues = request.getParameter("listvalues");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(listValues.split(" ")));
		String operator = request.getParameter("operator");
		String exception = request.getParameter("exception");
		String ruleType = "ALIS";
		String message = "Attribute Compare Rule Created & Script Generated";
		boolean failure = false;

		if (cm.isEmpty(new ArrayList<String>(Arrays.asList(businessRuleName, ruleType, tableValue, columnValue, operator, exception)))) {
			message = "FAILED! a value is empty";
			failure = true;
		} else if (!failure) {
			Attributelistrule rule = new Attributelistrule("", businessRuleName, operator, columnValue, tableValue, exception);
			rule.setListvalues(list);
			rule.setScriptCode(scriptFactory.createScript(rule));
			boolean succes = DAOimpl.createAttributeListRule(rule);
			if (succes) {
				DAOimpl.executeAttributeListRuleScript(rule.getScriptCode());
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
