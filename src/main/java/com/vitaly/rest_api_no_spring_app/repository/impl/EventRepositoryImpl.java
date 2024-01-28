package com.vitaly.rest_api_no_spring_app.repository.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.repository.EventRepository;
import com.vitaly.rest_api_no_spring_app.util.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class EventRepositoryImpl implements EventRepository{

    @Override
    public List<Event> getAll() {
        List<Event> eventsToShow;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {

        eventsToShow = session.createQuery("FROM Event e JOIN FETCH e.file LEFT JOIN FETCH e.user WHERE e.status = 'ACTIVE'", Event.class)
                .list();
        return eventsToShow;
    } catch (HibernateException e) {
        return Collections.emptyList();
    }
}

@Override
public Event getById(Integer integer) {
    Event event;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        event = (Event) session.createQuery("FROM Event e JOIN FETCH e.file LEFT JOIN FETCH e.user WHERE e.id = :eid")
                .setParameter("eid", integer)
                .uniqueResult();
    }
    if(event!= null) {
        return  event;
    } else {
        throw new IllegalArgumentException("No such event");
    }
}

@Override
public Event create(Event event) {
    if(event!= null){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Error while creating event ");
        }
    }
    return event;
}

@Override
public Event update(Event event) {
    if(event!= null) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(event);
            session.getTransaction().commit();
            return event;
        }
    } else {
        return new Event(-1, null,null,null);
    }
}

@Override
public void deleteById(Integer integer) {
    Event event = getById(integer);
    if(event!= null){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            event.setStatus(Status.DELETED);
            session.merge(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Error while deleting event ");
        }
    }
}
}
