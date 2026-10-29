package auca.ac.transit_dues.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.Treasurer;
import auca.ac.transit_dues.repository.TreasurerRepository;


@Service 
public  class TreasurerService {


    @Autowired 
    private TreasurerRepository treasurerRepository;

    public String saveTreasurer (Treasurer treasurer)  {
        treasurerRepository.save(treasurer);
        return "Treasurer saved successfully";
        
    }


    public List<Treasurer> getAllTreasurers() {
        return treasurerRepository.findAll();
    }

    public Optional<Treasurer> getTreasurerById(UUID id) {
        return treasurerRepository.findById(id);
    }


    public String updateTreasurer(UUID id, Treasurer treasurer) {
        Optional<Treasurer> found = treasurerRepository.findById(id);
        if (found.isPresent()) {
            Treasurer old = found.get();
            old.setFirstName(treasurer.getFirstName());
            old.setLastName(treasurer.getLastName());
            old.setPhone(treasurer.getPhone());
            treasurerRepository.save(old);
            return "Treasurer updated successfully";


        }

        return "The treasurer with that id does not exist";

    }

    public  String deleteTreasurer(UUID id) {

        Optional<Treasurer> found = treasurerRepository.findById(id);
        if (found.isPresent()) {
            treasurerRepository.deleteById(id);
            return "Treasurer deleted successfully";
        }
        return "The treasurer with that id does not exist";




    }

    public List<Treasurer> getTreasurersByDuesType(String name) {
    return treasurerRepository.findByDuesTypeName(name);
}









}
