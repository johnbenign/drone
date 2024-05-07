package com.benign.drone.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDto {

    public ResponseDto(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }
    private int statusCode;
    private String message;
    private Object data;
}
