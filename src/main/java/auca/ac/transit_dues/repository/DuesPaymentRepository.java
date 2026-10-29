package auca.ac.transit_dues.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.domain.PaymentStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DuesPaymentRepository extends JpaRepository<DuesPayment, UUID>{


    List<DuesPayment> findByStatusOrderByPaymentDateAsc(PaymentStatus status);
    
    List<DuesPayment>findByPaymentDateBetweenOrderByPaymentDateAsc(LocalDate start, LocalDate end);







}