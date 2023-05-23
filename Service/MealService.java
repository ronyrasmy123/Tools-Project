package Service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import MODEl.Meal;

@Stateless
public class MealService {
    @PersistenceContext
    private EntityManager em;

    public Meal createMeal(Meal meal) {
        em.persist(meal);
        return meal;
    }

    public Meal updateMeal(Meal meal) {
        return em.merge(meal);
    }
    public List<Meal> getMealsByRestaurantId(Long restaurantId) {
        return em.createQuery("SELECT m FROM Meal m WHERE m.restaurant.id = :restaurantId", Meal.class)
            .setParameter("restaurantId", restaurantId)
            .getResultList();
    }

    public void deleteMeal(Long mealId) {
        Meal meal = em.find(Meal.class, mealId);
        if(meal != null) {
            em.remove(meal);
        }
    }

}
