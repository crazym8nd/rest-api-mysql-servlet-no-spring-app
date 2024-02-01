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
        eventDto.setId(event.getId());
        eventDto.setUser(UserMapper.convertEntityToDtoWithoutEvents(event.getUser()));
        eventDto.setFile(FileMapper.convertEntityToDto(event.getFile()));
        eventDto.setStatus(event.getStatus());

        return eventDto;
    }

    public static Event convertDtoToEntity(EventDto eventDto) {

        UserRepository userRepository = new UserRepositoryImpl();
        Event event = new Event();
        event.setId(eventDto.getId());

        User user = userRepository.getById(eventDto.getUser().getId());
        event.setUser(user);

        FileRepository fileRepository = new FileRepositoryImpl();
        File file = fileRepository.getById(eventDto.getFile().getId());
        event.setFile(file);
        event.setStatus(eventDto.getStatus());

        return event;
    }
}
