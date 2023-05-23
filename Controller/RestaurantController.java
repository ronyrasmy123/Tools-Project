package Controller;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import MODEl.Meal;
import MODEl.Restaurant;
import MODEl.RestaurantReport;
import Service.MealService;
import Service.RestaurantService;


@Path("/restaurants")
public class RestaurantController {
    @Inject
    private RestaurantService restaurantService;
    @Inject
    private MealService mealService;
    
    @POST
    @Consumes("application/json")
    public Response createRestaurant(Restaurant restaurant) {
        restaurantService.createRestaurant(restaurant);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getRestaurant(@PathParam("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            // handle error
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(restaurant).build();
    }

    @POST
    @Path("/{restaurantId}/meals")
    @Consumes("application/json")
    public Response createMeal(@PathParam("restaurantId") Long restaurantId, Meal meal) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant == null) {
            // handle error
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        meal.setRestaurant(restaurant);
        mealService.createMeal(meal);
        return Response.status(Response.Status.CREATED).build();
    }

    

    @GET
    @Path("/{id}/report")
    @Produces("application/json")
    public Response getRestaurantReport(@PathParam("id") Long id) {
        RestaurantReport report = restaurantService.createRestaurantReport(id, new RestaurantService());
        if (report == null) {
            // handle error
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(report).build();
    }
} 


