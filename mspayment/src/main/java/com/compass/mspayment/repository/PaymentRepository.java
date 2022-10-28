package com.compass.mspayment.repository;

import com.compass.mspayment.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT p FROM PaymentEntity p WHERE p.idOrder = :idOrder AND p.cpfClient = :cpfClient")
    Optional<PaymentEntity> findByIdOrderAndCpfClient(Long idOrder, String cpfClient);

}
