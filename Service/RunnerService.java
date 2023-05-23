package Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import MODEl.Runner;
import MODEl.Order;

import EnumS.Status;
import EnumS.OrderStatus;

@Stateless
public class RunnerService {

    @PersistenceContext
    private EntityManager em;

    public void deliverOrder(Long runnerId, Long orderId) {
        Runner runner = em.find(Runner.class, runnerId);
        Order order = em.find(Order.class, orderId);

        if (runner != null && order != null && order.getRunner().equals(runner)
            && order.getOrder_status() == OrderStatus.PREPARING) {
            order.setOrder_status(OrderStatus.DELIVERED);
            runner.setStatus(Status.AVAILABLE);

            em.merge(order);
            em.merge(runner);
        }
    }

    public Long completedTrips(Long runnerId) {
        return em.createQuery("SELECT COUNT(o) FROM Order o WHERE o.runner.id = :runnerId "
                + "AND o.orderStatus = :orderStatus", Long.class)
                .setParameter("runnerId", runnerId)
                .setParameter("orderStatus", OrderStatus.DELIVERED)
                .getSingleResult();
    }
}
