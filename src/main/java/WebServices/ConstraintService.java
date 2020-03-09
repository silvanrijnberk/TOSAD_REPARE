//package WebServices;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import DAO.FacadeDAOImpl;
//import Scripts.FacadeRuleImpl;
//
//public class ConstraintService extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	FacadeDAOImpl DAOImpl = null;
//	FacadeRuleImpl ruleImpl = null;
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String constraintType = "constraint";
//		String tableValue = request.getParameter("table");
//		String expression = request.getParameter("expression");
//		String constraintName = request.getParameter("name");
//		ruleImpl = new FacadeRuleImpl();
//		DAOImpl = new FacadeDAOImpl();
//		String constraint = ruleImpl.createConstraint(constraintType, constraintName, tableValue, expression);
//		DAOImpl.executeConstraint(constraint);
//		PrintWriter out = response.getWriter();
//
//
//        out.println("<html><body><b>Attribute Compare Rule Created & Script Generated</b><br>"
//        		+ "</br><input type=\"button\" onclick=\"window.location.href = "
//        		+ "'http://localhost:8081/BRGTEAM3/Define.html';\" value=\"Return\"/></body></html>");
//        out.close();
//
//        System.out.println(constraint);
//	}
//}
