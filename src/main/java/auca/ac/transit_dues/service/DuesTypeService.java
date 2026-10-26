package auca.ac.transit_dues.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.DuesType;
import auca.ac.transit_dues.repository.DuesTypeRepository;

@Service
public class DuesTypeService {

    @Autowired
    private DuesTypeRepository DuesTypeRepository;

    public String saveDuesType (DuesType duesType){
        DuesTypeRepository.save(duesType);
        return "Dues type saved successfully";
    }

    public List<DuesType> getAllDuesTypes() {
        return DuesTypeRepository.findAll();
    }

    public Optional<DuesType> getDuesTypeById(UUID id) {
        return DuesTypeRepository.findById(id);

    }

    public String updateDuesType (UUID id, DuesType newData) {
        Optional<DuesType> found = DuesTypeRepository.findById(id);
        if (found.isEmpty()) {
            return "The dues type with that id does not exist";
        }
        
    DuesType duesType = found.get();
    duesType.setName(newData.getName());
    DuesTypeRepository.save(duesType);
    return "Dues type updated successfully";


    }
    
    public String deleteDuesType (UUID id) {
        Optional<DuesType> found = DuesTypeRepository.findById(id);
        if (found.isEmpty()) {
            return "The dues type with that id does not exist";

        }
        DuesTypeRepository.deleteById(id);
        return "Dues type deleted successfully.";
    }






}
