package com.compass.msorder.configuration.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageExceptionHandler {

    private Date timestamp;

    private Integer status;

    private String message;

}
