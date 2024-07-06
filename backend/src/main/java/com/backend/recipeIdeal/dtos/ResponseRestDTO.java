package com.backend.recipeIdeal.dtos;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRestDTO<T> {

    private String statusCode;
    private String message;
    private T data;

}
