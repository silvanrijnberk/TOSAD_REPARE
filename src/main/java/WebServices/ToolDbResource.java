package WebServices;

import DAO.ToolDbSchemaDAO;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tooldb")
public class ToolDbResource {

    @GET
    @Path("/AllRules")
    @Produces("application/json")
    public String getAllRules() {
        ToolDbSchemaDAO toolDbSchemaDao = new ToolDbSchemaDAO();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        List<List<String>> data = toolDbSchemaDao.selectAllRules();
        JsonObjectBuilder res = Json.createObjectBuilder();
        String ruleType = "";
        String temp = "";
        for (List<String> r : data) {
            if (r.size() < 1)
                continue;
            JsonObjectBuilder job = Json.createObjectBuilder();
            if (!ruleType.equals(temp)) {
                JsonArray productJsonArray = jsonArrayBuilder.build();

                res.add(ruleType, productJsonArray);
                jsonArrayBuilder = Json.createArrayBuilder();
                temp = ruleType;
            }
            for (String att : r) {
                if (att.split("-;-")[0].equalsIgnoreCase("RULETYPE")) {
                    if(ruleType.isEmpty()){
                        temp = ruleType;
                    }
                        ruleType = att.split("-;-")[1];

                }
                job.add(att.split("-;-")[0], att.split("-;-")[1]);
            }

            JsonObject productJson = job.build();
            jsonArrayBuilder.add(productJson);


        }

        JsonObject result = res.build();
        System.out.println(result.toString());
        return result.toString();
    }


    @DELETE
    @Path("/delete/{table}/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("table") String table, @PathParam("id") String id) {
        System.out.println(id);
        ToolDbSchemaDAO toolDbSchemaDAO = new ToolDbSchemaDAO();
        if (!toolDbSchemaDAO.deleteRule(id, table)) {
            return Response.status(404).build();
        }
        return Response.ok().build();
    }
}