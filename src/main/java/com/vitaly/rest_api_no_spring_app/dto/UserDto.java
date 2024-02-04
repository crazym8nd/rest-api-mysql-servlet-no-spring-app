package com.vitaly.rest_api_no_spring_app.dto;
//  25-Jan-24
// gh crazym8nd


import com.fasterxml.jackson.annotation.JsonInclude;
import com.vitaly.rest_api_no_spring_app.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String name;
    private List<EventDto> events;
    private Status status;
}
