package com.vitaly.rest_api_no_spring_app.dto;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
    private Integer id;
    private User user;
    private File file;
    private Status status;
}
