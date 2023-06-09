package shop.mtcoding.hiberapp.model;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    // api 문서들은 ok만 받고 그대로 db값을 돌려 주기 때문에 return을 받아 사용한다.
    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }
    @Transactional
    public User update(User user) {
        return em.merge(user);
    }
    @Transactional
    public void delete(User user) {
        em.remove(user);
    }
    public User findById(Long id) {
        return em.find(User.class, id);
    }
    public List<User> findAll(int page, int row) {
        return em.createQuery("select u from User u", User.class)
                .setFirstResult(page * row)
                .setMaxResults(row)
                .getResultList();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

}
