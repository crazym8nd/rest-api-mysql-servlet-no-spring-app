package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.model.User;

public class UserMapper {
    public static UserDto convertEntityToDtoWithoutEvents(User user) {
        UserDto userDto = new UserDto();
        if(user != null) {
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setStatus(user.getStatus());
        } else {
            userDto = null;
        }
        return userDto;
    }

    public static User convertDtoToEntityWithoutEvents(UserDto userDto) {
        User user= new User();
        if (userDto != null) {
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setStatus(userDto.getStatus());
        } else {
            user = null;
        }

        return user;
    }
}
