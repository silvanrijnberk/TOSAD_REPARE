package WebServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FacadeDAOImpl;
import DAO.TestToolDatabaseConnection;

//Tuple Other Rule
public class TOTHService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacadeDAOImpl DAOImpl = null;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try (Connection con = TestToolDatabaseConnection.initializeDatabase()) {
			 PreparedStatement statement = con.prepareStatement("INSERT INTO TUPLEOTHERRULE (RuleType, sqlQuery, businessRuleName, operator, firstColumnValue, secondColumnValue, tableValue, exception) VALUES ('TOTH', ?, ?, ?, ?, ?, ?, ?)");
		
			 statement.setString(1, request.getParameter("name"));
			 statement.setString(2, request.getParameter("sqlquery"));
			 statement.setString(3, request.getParameter("operator"));
			 statement.setString(4, request.getParameter("firstcolumnvalue"));
			 statement.setString(5, request.getParameter("secondcolumnvalue"));
			 statement.setString(6, request.getParameter("tablevalue"));
			 statement.setString(7, request.getParameter("exception"));
			 
			 statement.executeUpdate(); 
			 statement.close();
			 con.close();
			 
			 PrintWriter out = response.getWriter();
	            out.println("<html><body><b>Successfully Inserted" + "</b></body></html>"); 
			 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

