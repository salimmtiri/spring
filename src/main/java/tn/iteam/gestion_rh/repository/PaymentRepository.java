package tn.iteam.gestion_rh.repository;

import tn.iteam.gestion_rh.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query methods (if needed) can be defined here
}