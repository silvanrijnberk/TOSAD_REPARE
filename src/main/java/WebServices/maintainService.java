package WebServices;

import DAO.TestToolDatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class maintainService extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List ruleTypes = new ArrayList(Arrays.asList("ATTRIBUTECOMPARERULE", "ATTRIBUTELISTRULE", "ATTRIBUTEOTHERRULE", "ATTRIBUTERANGERULE", "ENTITYOTHERRULE", "INTERENTITYCOMPARERULE", "MODIFYRULE", "TUPLECOMPARERULE", "TUPLEOTHERRULE"));
        List rulenames = new ArrayList(Arrays.asList("ATTRIBUTE COMPARE RULE", "ATTRIBUTE LIST RULE", "ATTRIBUTE OTHER RULE", "ATTRIBUTE RANGE RULE", "ENTITY OTHER RULE", "INTERENTITY COMPARE RULE", "MODIFY RULE", "TUPLE COMPARE RULE", "TUPLE OTHER RULE"));
        String text = "";
        for (Object x : ruleTypes) {
            List rules = getRules((String) x);
            String ruleTitle = (String) rulenames.get(ruleTypes.indexOf(x));
            ruleTitle = ruleTitle.substring(0, 1) + ruleTitle.substring(1).toLowerCase();
            text += "<h2>" + ruleTitle + "</h2>";
            for (Object y : rules) {
                int ruleID = 0;
                for (Object z : (List) y) {

                    String rule = (String) z;
                    String ruleName = rule.split(";")[0].toLowerCase();
                    ruleName = ruleName.substring(0, 1).toUpperCase() + ruleName.substring(1);
                    String ruleValue = rule.split(";")[1];
                    text += ruleName + ": " + ruleValue + "<br>";
                    if (ruleName.equalsIgnoreCase("RULEID")) {
                        ruleID = Integer.parseInt(ruleValue);
                    }
                }
                text += "<form method=\"POST\" action=\"maintain.html\" >\n" +
                        "   <input type=\"hidden\" name=\"rule\" value=\"" + ruleID + "\"/><br />\n" +
                        "   <input type=\"hidden\" name=\"table\" value=\"" + x + "\"/><br />\n" +
                        " <input type=\"submit\"name=\"delete\" value=\"delete\"/>"+
                        "  </form>";
                text += " <br>";
            }

        }
        PrintWriter out = resp.getWriter();

        out.println("<html>" +
                "<h1>Maintain</h1>" +
                "" +
                "<body><b>" + text + "</b><br>"
                + "</br><input type=\"button\" onclick=\"window.location.href = "
                + "'http://localhost:8081/BRGTEAM3';\" value=\"Return\"/></body></html>");
        out.close();

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection con = TestToolDatabaseConnection.initializeDatabase()) {
            String ruleID = req.getParameter("rule");
            System.out.println(ruleID);
            String table = req.getParameter("table");
            PreparedStatement statementDelete = con.prepareStatement("DELETE FROM " + table +" WHERE RULEID = "+ruleID);
            statementDelete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        doGet(req,resp);
    }

    public List getRules(String table) {
        List rules = new ArrayList();
        try (Connection con = TestToolDatabaseConnection.initializeDatabase()) {
            PreparedStatement statementRuleID = con.prepareStatement("SELECT * FROM " + table);
            ResultSet result = statementRuleID.executeQuery();
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                List rule = new ArrayList<String>();
                for (int i = 1; i <= columnsNumber; i++) {
                    rule.add(rsmd.getColumnName(i) + ";" + result.getString(i));
                }
                rules.add(rule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return rules;
    }
}
