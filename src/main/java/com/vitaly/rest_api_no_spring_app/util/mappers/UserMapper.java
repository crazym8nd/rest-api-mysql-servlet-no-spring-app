package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.model.User;

public class UserMapper {
    public static UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEvents(user.getEvents());
        userDto.setStatus(user.getStatus());

        return userDto;
    }

    public static User convertDtoToEntity(UserDto userDto) {
        User user= new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEvents(userDto.getEvents());
        user.setStatus(userDto.getStatus());

        return user;
    }
}
