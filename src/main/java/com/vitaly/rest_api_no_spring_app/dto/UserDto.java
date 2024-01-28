package com.vitaly.rest_api_no_spring_app.dto;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String name;
    private List<EventDto> events;
    private Status status;

}
