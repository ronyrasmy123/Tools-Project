package Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import Service.RunnerService;

@Path("/runners")
public class RunnerController {
    @Inject
    private RunnerService runnerService;

    @PUT
    @Path("/{runnerId}/orders/{orderId}/delivered")
    public Response markOrderAsDelivered(@PathParam("runnerId") Long runnerId, @PathParam("orderId") Long orderId) {
    	runnerService.deliverOrder(runnerId, orderId);
        return Response.ok().build();
    }

    @GET
    @Path("/{runnerId}/completedTripsCount")
    @Produces("application/json")
    public Response getCompletedTripsCount(@PathParam("runnerId") Long runnerId) {
    	long count = runnerService.completedTrips(runnerId);
        return Response.ok(count).build();
    }
}