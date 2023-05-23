package Service;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import MODEl.Order;
import MODEl.User;


@Stateless
public class OrderService {

    @PersistenceContext
    private EntityManager em;

    public Order createOrder(Order order, User user) {
        // Set the customer ID based on the logged in user's ID or role
        Long customerId;
        if(user.getRole().equals("customer")) {
            customerId = user.getId();
        } else {
            // Handle case where the user is not a customer (e.g. admin)
            customerId = null;
        }
        order.setId(customerId);
        
        em.persist(order);
        return order;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public Order getOrderById(Long orderId) {
        return em.find(Order.class, orderId);
    }
}
