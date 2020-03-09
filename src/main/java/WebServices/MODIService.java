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

import BusinessRules.Interentitycomparerule;
import BusinessRules.Modifyrule;
import DAO.FacadeDAOImpl;
import DAO.TestToolDatabaseConnection;
import Scripts.ScriptFactory;

public class MODIService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOImpl = new FacadeDAOImpl();
	ScriptFactory scriptFactory = new ScriptFactory();
	checkMethods cm = new checkMethods();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String ruleType = "ICMP";
			String name = request.getParameter("name");
			String operator = request.getParameter("operator");
			String column1 = request.getParameter("firstcolumnvalue");
			String column2 = request.getParameter("secondcolumnvalue");
			String table1 = request.getParameter("firsttablevalue");
			String check = request.getParameter("checkvalue");
			String exception = request.getParameter("exception");
			boolean failure = false;
			String message = "Modify Rule Created & Script Generated";

		if (cm.isEmpty(new ArrayList<String>(Arrays.asList(name, ruleType, table1, column1, column2, operator, exception, check)))) {
			message = "FAILED! a value is empty";
			failure = true;
		} else if (!failure) {
			Modifyrule rule = new Modifyrule("", name, operator, column1, column2, table1, exception, check);
			rule.setScriptCode(scriptFactory.createScript(rule));
			boolean succes = DAOImpl.createModifyRule(rule);
			if (succes) {
				DAOImpl.executeModifyRuleScript(rule.getScriptCode());
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

