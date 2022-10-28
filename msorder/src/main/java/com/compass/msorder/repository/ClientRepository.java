package com.compass.msorder.repository;

import com.compass.msorder.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT c FROM ClientEntity c WHERE c.email LIKE :email")
    Optional<ClientEntity> findByEmail(String email);

}
