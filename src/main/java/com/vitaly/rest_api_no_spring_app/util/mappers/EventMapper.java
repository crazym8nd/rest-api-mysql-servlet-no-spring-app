package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.User;

public class EventMapper {
    public static EventDto convertEntityToDto(Event event) {
        EventDto eventDto= new EventDto();

        if (event!= null) {
            eventDto.setId(event.getId());
            eventDto.setUser(UserMapper.convertEntityToDtoWithoutEvents(event.getUser()));
            eventDto.setFile(FileMapper.convertEntityToDto(event.getFile()));
            eventDto.setStatus(event.getStatus());
        } else {
            eventDto = null;
        }
        return eventDto;
    }

    public static Event convertDtoToEntity(EventDto eventDto) {
        Event event = new Event();
        if (eventDto != null) {
            event.setId(eventDto.getId());

            User user = UserMapper.convertDtoToEntityWithoutEvents(eventDto.getUser());
            event.setUser(user);

            File file = FileMapper.convertDtoToEntity(eventDto.getFile());
            event.setFile(file);

            event.setStatus(eventDto.getStatus());
        }else {
           event = null;
        }

        return event;
    }
}
