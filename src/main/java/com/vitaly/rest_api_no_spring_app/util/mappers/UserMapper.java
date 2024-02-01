package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto convertEntityToDtoWithoutEvents(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setStatus(user.getStatus());

        return userDto;
    }

    public static User convertDtoToEntity(UserDto userDto) {
        User user= new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());

        if(userDto.getEvents() != null) {
            List<Event> events = userDto.getEvents().stream()
                    .map(EventMapper::convertDtoToEntity)
                    .collect(Collectors.toList());
            user.setEvents(events);
        }

        user.setStatus(userDto.getStatus());

        return user;
    }
}
