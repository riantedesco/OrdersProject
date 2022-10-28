package com.compass.msorder.domain;

import com.compass.msorder.util.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long number;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private Double total = 0.00;

    @NotNull
    private StatusOrderOption status;

    @NotNull
    @ManyToOne
    private ClientEntity client;

    @OneToMany
    private List<ProductOrderEntity> productOrders;

}
