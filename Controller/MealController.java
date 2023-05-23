package Controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import MODEl.Meal;
import Service.MealService;


@Path("/meals")
public class MealController {

    @Inject
    private MealService mealService;
   
    @POST
    @Consumes("application/json")
    public Response createMeal(Meal meal) {
        mealService.createMeal(meal);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateMeal(@PathParam("id") Long id, Meal meal) {
        meal.setId(id);
        mealService.updateMeal(meal);
        return Response.ok().build();
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    @Produces("application/json")
    public Response getMealsByRestaurantId(@PathParam("restaurantId") Long restaurantId) {
        List<Meal> meals = mealService.getMealsByRestaurantId(restaurantId);
        return Response.ok(meals).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMeal(@PathParam("id") Long id) {
        mealService.deleteMeal(id);
        return Response.noContent().build();
    }
}
