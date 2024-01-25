package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.model.Event;

public class EventMapper {
    public static EventDto convertEntityToDto(Event event) {
        EventDto eventDto= new EventDto();
        eventDto.setId(event.getId());
        eventDto.setUser(event.getUser());
        eventDto.setFile(event.getFile());
        eventDto.setStatus(event.getStatus());

        return eventDto;
    }

    public static Event convertDtoToEntity(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setUser(eventDto.getUser());
        event.setFile(eventDto.getFile());
        event.setStatus(eventDto.getStatus());

        return event;
    }
}
