package com.vitaly.rest_api_no_spring_app.repository.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.UserRepository;
import com.vitaly.rest_api_no_spring_app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        List<User> usersToShow;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            usersToShow = session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.status =:status", User.class)
                    .setParameter("status", Status.ACTIVE)
                    .getResultList();
        } catch (HibernateException e) {
            return Collections.emptyList();
        }
        return usersToShow;
    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            user =  session.createQuery("FROM User u LEFT  JOIN FETCH u.events WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (HibernateException e){
            System.out.println("Error while getting user by id ");
        }
        if(user != null){
            return user;
        } else {
            user = new User(-1, "NO SUCH USER", null);
            return user;
        }

    }

    @Override
    public User create(User user) {
        if(user != null){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
            } catch (HibernateException e){
                System.out.println("Error while creating user ");
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        if(user != null){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                session.merge(user);
                session.getTransaction().commit();
                return user;

            } catch (HibernateException e){
                System.out.println("Error while creating user ");
                user = new User(-1, "NO SUCH USER", null);
                return user;
            }
        }
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        User user = getById(id);
        if(user!= null){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                user.setStatus(Status.DELETED);
                session.merge(user);
                session.getTransaction().commit();
            } catch (HibernateException e){
                System.out.println("Error while deleting user ");
            }
        }
    }
}