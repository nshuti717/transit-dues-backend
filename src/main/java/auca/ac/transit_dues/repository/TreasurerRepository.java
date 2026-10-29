package auca.ac.transit_dues.repository;


import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import auca.ac.transit_dues.domain.Treasurer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository 
public interface TreasurerRepository extends JpaRepository<Treasurer, UUID> {

    @Query("SELECT DISTINCT t FROM Treasurer t JOIN t.duesTypes d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
List<Treasurer> findByDuesTypeName(@Param("name") String name);

     
}