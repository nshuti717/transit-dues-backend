package auca.ac.transit_dues.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.transit_dues.domain.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, UUID> {


}