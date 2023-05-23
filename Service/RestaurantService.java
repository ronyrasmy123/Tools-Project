package Service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import EnumS.OrderStatus;
import MODEl.Order;
import MODEl.Restaurant;
import MODEl.RestaurantReport;

@Stateless
public class RestaurantService {
    @PersistenceContext
    private EntityManager em;
    
    public List<Order> getOrdersByRestaurantId(Long restaurantId) {
        String jpql = "SELECT o FROM Order o WHERE o.restaurant.id = :restaurantId";
        TypedQuery<Order> query = em.createQuery(jpql, Order.class);
        query.setParameter("restaurantId", restaurantId);
        return query.getResultList();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        em.persist(restaurant);
        return restaurant;
    }
    
    
    

    public Restaurant updateRestaurant(Restaurant restaurant) {
        return em.merge(restaurant);
    }

    public Restaurant getRestaurantById(Long id) {
        return em.find(Restaurant.class, id);
    }

    public RestaurantReport createRestaurantReport(Long restaurantId, RestaurantService restaurantService) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant == null) {
            // handle error
            return null;
        }
        long totalEarnings = 0;
        long completedOrdersCount = 0;
        long canceledOrdersCount = 0;

        for (Order order : restaurant.getOrders(restaurantService)) {
            if (order.getOrder_status().equals(OrderStatus.COMPLETED)) {
                totalEarnings += order.getTotal_price();
                completedOrdersCount++;
            } else if (order.getOrder_status().equals(OrderStatus.CANCELED)) {
                canceledOrdersCount++;
            }
        }

        RestaurantReport report = new RestaurantReport(totalEarnings, completedOrdersCount, canceledOrdersCount);
        return report;
    } } 