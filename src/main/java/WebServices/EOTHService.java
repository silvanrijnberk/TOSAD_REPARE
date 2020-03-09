package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessRules.Attributelistrule;
import BusinessRules.Entityotherrule;
import DAO.FacadeDAOImpl;
import Scripts.ScriptFactory;

//EntityOtherRule
public class EOTHService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOImpl = new FacadeDAOImpl();
	ScriptFactory scriptFactory = new ScriptFactory();
	checkMethods cm = new checkMethods();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message = "Entity Other Rule Created & Script Generated";
		String name = request.getParameter("name");
		String query = request.getParameter("sqlquery");
		String operator = request.getParameter("operator");
		String column = request.getParameter("firstcolumnValue");
		String table = request.getParameter("tabluevalue");
		String exception = request.getParameter("exception");
		String ruleType = "EOTH";
		boolean failure = false;

		if (cm.isEmpty(new ArrayList<String>(Arrays.asList(name, ruleType, table, column, operator, exception, query)))) {
			message = "FAILED! a value is empty";
			failure = true;
		} else if (!failure) {
			Entityotherrule rule = new Entityotherrule("", name, operator, column, table, exception, query);
			rule.setScriptCode(scriptFactory.createScript(rule));
			boolean succes = DAOImpl.createEntityOtherRule(rule);
			if (succes) {
				DAOImpl.executeEntityOtherRuleScript(rule.getScriptCode());
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
