package com.vitaly.rest_api_no_spring_app.dto;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
    private Integer id;
    private String name;
    private String filePath;
    private Status status;

}
