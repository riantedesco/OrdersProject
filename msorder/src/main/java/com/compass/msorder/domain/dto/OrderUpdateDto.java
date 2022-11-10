package com.compass.msorder.domain.dto;

import com.compass.msorder.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderUpdateDto {

    private Long id;

    private Long number;

    private LocalDateTime dateTime;

    private Double total;

    private StatusOrderOption status;

    private ClientDto client;

}
