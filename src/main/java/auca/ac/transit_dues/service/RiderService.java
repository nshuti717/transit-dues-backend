package auca.ac.transit_dues.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.Rider;
import auca.ac.transit_dues.repository.RiderRepository;



@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    public String saveRider(Rider rider) {
        riderRepository.save(rider);
        return "Rider saved successfully";
    }


    public List<Rider>getAllRiders() {
        return riderRepository.findAll();
    }
    public Optional<Rider>getRiderById(UUID id) {
        return riderRepository.findById(id);
    }

    public String updateRider(UUID id, Rider rider) {
        Optional<Rider>found =riderRepository.findById(id);
        if (found.isPresent()){
            Rider old = found.get();
            old.setFirstName(rider.getFirstName());
            old.setLastName(rider.getLastName());
            old.setPlateNumber(rider.getPlateNumber());
            old.setPhone(rider.getPhone());
            riderRepository.save(old);
            return "Rider Updated successfully";
        }
        return "The rider with that id does not exist";
    }


    public String deleteRider(UUID id) {
        Optional<Rider> found =riderRepository.findById(id);
        if (found.isPresent()) {
            riderRepository.deleteById(id);
            return "Rider deleted successfully";
        }
        return "The rider with that id does not exist";
    }

    public List<Rider> getRidersByLastName(String lastName) {
        
    return riderRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
}






}