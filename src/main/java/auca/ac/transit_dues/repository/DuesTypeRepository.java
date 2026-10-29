package auca.ac.transit_dues.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import auca.ac.transit_dues.domain.DuesType;

@Repository 
public interface DuesTypeRepository extends JpaRepository<DuesType, UUID>{

    @Query("SELECT d FROM DuesType d WHERE d.treasurers IS EMPTY")

    List<DuesType> findUnusedDuesTypes();
    
}