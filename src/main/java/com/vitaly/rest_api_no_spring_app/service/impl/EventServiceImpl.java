package com.vitaly.rest_api_no_spring_app.service.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.repository.EventRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.EventRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.EventService;
import com.vitaly.rest_api_no_spring_app.util.HibernateUtil;
import com.vitaly.rest_api_no_spring_app.util.mappers.EventMapper;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
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
    public List<EventDto> getAll() {
        List<EventDto> eventDtoList = new ArrayList<>();
            List<Event> events = eventRepository.getAll();
            for (Event event : events) {
                EventDto eventDto = EventMapper.convertEntityToDto(event);
                eventDtoList.add(eventDto);
            }
            return eventDtoList;
    }


    @Override
    public EventDto getById(Integer integer) {
        Event event = eventRepository.getById(integer);
        return EventMapper.convertEntityToDto(event);
    }

    @Override
    public EventDto create(EventDto eventDto) {
            Event event = EventMapper.convertDtoToEntity(eventDto);
            eventRepository.create(event);

            return eventDto;
    }

    @Override
    public EventDto update(EventDto eventDto) {
        Event event = EventMapper.convertDtoToEntity(eventDto);
        eventRepository.update(event);
        return eventDto;
    }

    @Override
    public void deleteById(Integer integer) {
        eventRepository.deleteById(integer);
    }

}
