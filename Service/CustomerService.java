package Service;


import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import MODEl.Order;
import MODEl.Runner;
import MODEl.Restaurant;

import EnumS.Status;
import EnumS.OrderStatus;

@Stateless
public class CustomerService {
    @PersistenceContext
    private EntityManager em;

    public Order createOrder(Order order) {
        // Select a random available runner and set it as busy
        List<Runner> availableRunners = em.createQuery("SELECT r FROM Runner r WHERE r.status = :status", Runner.class)
                                          .setParameter("status", Status.AVAILABLE)
                                          .getResultList();

        if (!availableRunners.isEmpty()) {
            int randomIndex = new Random().nextInt(availableRunners.size());
            Runner runner = availableRunners.get(randomIndex);
            runner.setStatus(Status.BUSY);
            em.merge(runner);

            order.setRunner(runner);
            order.setOrder_status(OrderStatus.PREPARING);
           
            em.persist(order);
            return order;
        }

        // If no available runner
        return null;
    }

    public Order updateOrder(Order order) {
        if (order.getOrder_status() != OrderStatus.CANCELED && order.getOrder_status() == OrderStatus.PREPARING) {
            return em.merge(order);
        }

        // If the order is canceled or not in the preparing state
        return null;
    }

    public List<Restaurant> listAllRestaurants() {
        return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
    }
}
