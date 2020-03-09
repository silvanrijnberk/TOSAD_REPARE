package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessRules.Entityotherrule;
import BusinessRules.Interentitycomparerule;
import DAO.FacadeDAOImpl;
import DAO.TestToolDatabaseConnection;
import DAO.keysDao;
import Scripts.ScriptFactory;


public class ICMPService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOImpl = new FacadeDAOImpl();
	ScriptFactory scriptFactory = new ScriptFactory();
	checkMethods cm = new checkMethods();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 // get values
			String ruleType = "ICMP";
			String name = request.getParameter("name");
			String operator = request.getParameter("operator");
			String column1 = request.getParameter("column");
			String column2 = request.getParameter("column2");
			String table1 = request.getParameter("table");
			String table2 = request.getParameter("table2");
			String exception = request.getParameter("exception");
			boolean failure = false;
			String message = "Inter Entity Compare Rule Created & Script Generated";

			if (cm.isEmpty(new ArrayList<String>(Arrays.asList(name, ruleType, table1, column1, table2, column2, operator, exception)))) {
				message = "FAILED! a value is empty";
				failure = true;
			} else if (!failure) {
				Interentitycomparerule rule = new Interentitycomparerule("", name, operator, column1, column2, table1, table2, exception);
				rule.setScriptCode(scriptFactory.createScript(rule));
				boolean succes = DAOImpl.createInterEntityCompareRule(rule);
				if (succes) {
					DAOImpl.executeInterEntityCompareRuleScript(rule.getScriptCode());
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



