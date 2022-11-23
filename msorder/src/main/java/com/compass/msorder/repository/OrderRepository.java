package com.compass.msorder.repository;

import com.compass.msorder.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o WHERE o.id = :id AND o.number = :number AND o.customer.cpf = :cpfCustomer")
    Optional<OrderEntity> findByIdNumberAndCpfCustomer(Long id, Long number, String cpfCustomer);

}
