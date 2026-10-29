
package auca.ac.transit_dues.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.repository.DuesPaymentRepository;

@Service
public class DuesPaymentService{

    @Autowired
    private DuesPaymentRepository duesPaymentRepository;


    public String saveDuesPayment(DuesPayment duesPayment) {
        duesPaymentRepository.save(duesPayment);
        return "Dues payment saved successfully";
    }


    public List<DuesPayment> getAllDuesPayments() {
        return duesPaymentRepository.findAll();
    }



    public Optional<DuesPayment> getDuesPaymentById(UUID id) {
        return duesPaymentRepository.findById(id);
    }

    public String updateDuesPayment(UUID id, DuesPayment duesPayment) {
        Optional<DuesPayment> found =duesPaymentRepository.findById(id);
        if (found.isPresent()) {
            DuesPayment old =found.get();
            old.setPaymentDate(duesPayment.getPaymentDate());
            old.setAmount(duesPayment.getAmount());
            if (duesPayment.getRider() != null) {
                old.setRider(duesPayment.getRider());
            }
            if (duesPayment.getTreasurer() != null) {
                old.setTreasurer(duesPayment.getTreasurer());
            }
            duesPaymentRepository.save(old);
            return "Dues payment updated successfully";

        }

        return "The dues payment with that id does not exist";
    }

    public String deleteDuesPayment(UUID id){

        Optional<DuesPayment> found =duesPaymentRepository.findById(id);
        if (found.isPresent()) {
            duesPaymentRepository.deleteById(id);
            return "Dues payment deleted successfully";
        }
        return "The dues payment with that id does not exist";
    }
}