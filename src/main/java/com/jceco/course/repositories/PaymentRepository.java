package com.jceco.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
