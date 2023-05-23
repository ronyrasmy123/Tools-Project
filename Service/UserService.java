package Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import EnumS.Role;
import MODEl.User;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;
    
    public Role getRole(User user) {
        return user.getRole();
    }
    public User signUp(User user, Double deliveryFee) {
        if (user.getRole() == Role.RUNNER) {
            user.setDeliveryFee(deliveryFee);
        }
        em.persist(user);
        return user;
    }

    public User login(String name, String password) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.password = :password", User.class)
                    .setParameter("name", name)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public boolean verifyRole(Long userId, Role role) {
        try {
            User user = em.find(User.class, userId);
            return user != null && user.getRole() == role;
        } catch (Exception e) {
            return false;
        }
    }
}
