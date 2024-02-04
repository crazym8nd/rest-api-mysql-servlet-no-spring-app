package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.FileRepository;
import com.vitaly.rest_api_no_spring_app.repository.UserRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.FileRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.repository.impl.UserRepositoryImpl;

public class EventMapper {
    public static EventDto convertEntityToDto(Event event) {
        EventDto eventDto= new EventDto();
        if (event!= null) {
            eventDto.setId(event.getId());
            eventDto.setUser(UserMapper.convertEntityToDtoWithoutEvents(event.getUser()));
            eventDto.setFile(null);
            eventDto.setStatus(event.getStatus());
        } else {
            eventDto.setId(null);
            eventDto.setUser(null);
            eventDto.setFile(null);
            eventDto.setStatus(null);
        }
        return eventDto;
    }

    public static Event convertDtoToEntity(EventDto eventDto) {
        UserRepository userRepository = new UserRepositoryImpl();
        FileRepository fileRepository = new FileRepositoryImpl();

        Event event = new Event();

        if (eventDto != null) {
            event.setId(eventDto.getId());

            User user = userRepository.getById(eventDto.getUser().getId());
            event.setUser(user);

            File file = fileRepository.getById(eventDto.getFile().getId());
            event.setFile(file);

            event.setStatus(eventDto.getStatus());
        }else {
            event.setId(null);
            event.setUser(null);
            event.setFile(null);
            event.setStatus(null);
        }

        return event;
    }
}
