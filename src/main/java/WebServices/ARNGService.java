package WebServices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessRules.Attributerangerule;
import DAO.FacadeDAOImpl;
import Scripts.ScriptFactory;

public class ARNGService extends HttpServlet {
    private static final long serialVersionUID = 1L;
    FacadeDAOImpl DAOImpl = new FacadeDAOImpl();
    ScriptFactory scriptFactory = new ScriptFactory();
    checkMethods cm = new checkMethods();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "Attribute Range Rule Created & Script Generated" ;
        String name = request.getParameter("name");
        String columnValue = request.getParameter("column");
        String tableValue = request.getParameter("table");
        String maxvalue = request.getParameter("maxvalue");
        String minvalue = request.getParameter("minvalue");
        String operator = request.getParameter("operator");
        String exception = request.getParameter("exception");
        String ruleType = "ARNG";
        if (!cm.minMax(minvalue, maxvalue))
            message = "min value has to be lower then max value";
        else {
            Attributerangerule rule = new Attributerangerule("", name, operator, columnValue, tableValue, exception, Integer.parseInt(minvalue), Integer.parseInt(maxvalue));
            rule.setScriptCode(scriptFactory.createScript(rule));
            boolean succes = DAOImpl.createAttributeRangeRule(rule);
            if (succes) {
                DAOImpl.executeAttributeRangeRuleScript(rule.getScriptCode());
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


