package WebServices;

import DAO.TargetDbSchemaDAO;

import java.util.List;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("targetdb")
public class TargetDbResource {

    @GET
    @Path("/tables")
    @Produces("application/json")
    public String getTargetTables() {
        TargetDbSchemaDAO schema = new TargetDbSchemaDAO();
        JsonArrayBuilder jabp = Json.createArrayBuilder();
        List<String> tables = schema.SelectTables();
        for (String t : tables) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("table", t);

            JsonObject productJson = job.build();
            jabp.add(productJson);
        }
        JsonArray productArray = jabp.build();
        return productArray.toString();
    }

    @GET
    @Path("/columns/{table}")
    @Produces("application/json")
    public String getTargetColumn(@PathParam("table") String table) {
        TargetDbSchemaDAO schema = new TargetDbSchemaDAO();
        JsonArrayBuilder jabc = Json.createArrayBuilder();
        List<String> columns = schema.SelectColumns(table);
        for (String c : columns) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("column", c);

            JsonObject columnJson = job.build();
            jabc.add(columnJson);
        }
        JsonArray columnArray = jabc.build();
        return columnArray.toString();

    }
}