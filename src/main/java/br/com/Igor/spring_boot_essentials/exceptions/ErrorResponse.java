package br.com.Igor.spring_boot_essentials.exceptions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private String status;
}
