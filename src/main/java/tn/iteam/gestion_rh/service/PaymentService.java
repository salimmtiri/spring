package tn.iteam.gestion_rh.service;

import tn.iteam.gestion_rh.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment savePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    void deletePayment(Long id);
}