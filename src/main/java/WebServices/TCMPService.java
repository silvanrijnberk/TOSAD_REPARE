package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BusinessRules.Tuplecomparerule;
import DAO.FacadeDAOImpl;
import Scripts.ScriptFactory;


public class TCMPService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOImpl = new FacadeDAOImpl();
	checkMethods cm = new checkMethods();
	ScriptFactory scriptFactory = new ScriptFactory();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 String message = "Attribute Compare Rule Created & Script Generated";
			 boolean failure = false;
			 String ruleType = "TCMP";
			 String name = request.getParameter("name");
			 String operator = request.getParameter("operator");
			 String column1 = request.getParameter("column");
			 String column2 = request.getParameter("column2");
			 String table = request.getParameter("table");
			 String exception = request.getParameter("exception");
			 if (cm.isEmpty(new ArrayList(Arrays.asList(name, ruleType, table, column1,column2, operator, exception)))) {
	                message = "FAILED! a value is empty";
	                failure = true;
	            }
	            else
	         if (!failure) {
				 Tuplecomparerule rule = new Tuplecomparerule("", name, operator, column1, column2, table, exception);
				 rule.setScriptCode(scriptFactory.createScript(rule));
				 boolean succes = DAOImpl.createTupleCompareRule(rule);
				 if (succes) {
					 DAOImpl.executeTupleCompareRuleScript(rule.getScriptCode());
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
