package auca.ac.transit_dues.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import auca.ac.transit_dues.domain.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, UUID> {

List<Rider>findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
@Query("SELECT DISTINCT r FROM DuesPayment p JOIN p.rider r WHERE p.treasurer.id = :treasurerId")

List<Rider> findRidersByTreasurerId(@Param("treasurerId") UUID treasurerId);

}