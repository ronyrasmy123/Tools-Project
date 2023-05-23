package Controller;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import MODEl.User;
import Service.UserService;

@Path("/users")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Path("/signup")
    @Consumes("application/json")
    public Response signUp(User user, @FormParam("deliveryFee") Double deliveryFee) {
        userService.signUp(user, deliveryFee);
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response login(@FormParam("name") String name, @FormParam("password") String password) {
        User user = userService.login(name, password);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok().entity(user).build();
    }
}
