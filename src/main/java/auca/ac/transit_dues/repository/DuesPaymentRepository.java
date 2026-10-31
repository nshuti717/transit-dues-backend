package auca.ac.transit_dues.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.domain.PaymentStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface DuesPaymentRepository extends JpaRepository<DuesPayment, UUID>{


    List<DuesPayment> findByStatusOrderByPaymentDateAsc(PaymentStatus status);
    
    List<DuesPayment>findByPaymentDateBetweenOrderByPaymentDateAsc(LocalDate start, LocalDate end);

    boolean existsByTreasurerIdAndPaymentDateAndStatusNot(UUID treasurerId, LocalDate paymentDate, PaymentStatus status);

    @Query("SELECT p.status, COUNT(p) FROM DuesPayment p GROUP BY p.status")
    List<Object[]> getStatusCounts();

    @Modifying
    @Query("UPDATE DuesPayment p SET p.status = :newStatus WHERE p.treasurer.id = :treasurerId AND p.paymentDate = :date AND p.status <> :keepStatus")
    int cancelDay(@Param("treasurerId") UUID treasurerId, @Param("date") LocalDate date, @Param("newStatus") PaymentStatus newStatus, @Param("keepStatus") PaymentStatus keepStatus);

    long countByPaymentDate(LocalDate paymentDate);

    long countByStatus(PaymentStatus status);







}