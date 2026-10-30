package auca.ac.transit_dues.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.domain.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

    @Query("SELECT d FROM Receipt d WHERE d.duesPayment.rider.plateNumber = :plateNumber ORDER BY d.issuedDate DESC")
    List<Receipt> findByRiderPlateNumber(@Param("plateNumber") String plateNumber);

    boolean existsByDuesPayment(DuesPayment duesPayment);

}
