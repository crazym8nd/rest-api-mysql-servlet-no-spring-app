package com.vitaly.rest_api_no_spring_app.service.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.repository.EventRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.EventRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public EventServiceImpl(){
        this.eventRepository = new EventRepositoryImpl();
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    @Override
    public Event create(Event event) {
        return eventRepository.create(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
}
