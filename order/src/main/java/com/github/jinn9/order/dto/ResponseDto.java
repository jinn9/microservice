package com.github.jinn9.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private int statusCode;
    private String statusMessage;
}
