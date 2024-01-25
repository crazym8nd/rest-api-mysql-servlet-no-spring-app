package com.vitaly.rest_api_no_spring_app.repository.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.UserRepository;
import com.vitaly.rest_api_no_spring_app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        List<User> usersToShow = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
            usersToShow = session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.status =:status", User.class)
                    .setParameter("status", Status.ACTIVE)
                    .list();
        } catch (HibernateException e) {
            return Collections.emptyList();
        }
        return usersToShow;
    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            user = (User) session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.id = :id")
                    .setParameter("id", id)
                    .list().get(0);
        } catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Error while getting user by id ");
        }
        return user;
    }

    @Override
    public User create(User user) {
        if(user != null){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
            } catch (HibernateException e){
                e.printStackTrace();
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
                e.printStackTrace();
                System.out.println("Error while creating user ");
            }
        }
        return new User(-1,"NO SUCH USER", null);
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
                e.printStackTrace();
                System.out.println("Error while deleting user ");
            }
        }
    }
}