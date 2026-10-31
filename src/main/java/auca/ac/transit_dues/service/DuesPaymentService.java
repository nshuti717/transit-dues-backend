
package auca.ac.transit_dues.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.domain.PaymentStatus;
import auca.ac.transit_dues.repository.DuesPaymentRepository;
import auca.ac.transit_dues.repository.ReceiptRepository;

@Service
public class DuesPaymentService{

    @Autowired
    private DuesPaymentRepository duesPaymentRepository;

    @Autowired
    private ReceiptRepository receiptRepository;


    public String saveDuesPayment(DuesPayment duesPayment) {
        if (duesPayment.getPaymentDate().isBefore(LocalDate.now())) {
            return "Dues payment date cannot be in the past";
        }
        if (duesPayment.getTreasurer() != null) {
            boolean alreadyHasPayment = duesPaymentRepository.existsByTreasurerIdAndPaymentDateAndStatusNot(
                    duesPayment.getTreasurer().getId(), duesPayment.getPaymentDate(), PaymentStatus.REVERSED);
            if (alreadyHasPayment) {
                return "Treasurer already has a payment recorded on that date";
            }
        }
        duesPayment.setStatus(PaymentStatus.DECLARED);
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
            if (receiptRepository.existsByDuesPayment(found.get())) {
                return "That dues payment has receipts, delete them first";
            }
            duesPaymentRepository.deleteById(id);
            return "Dues payment deleted successfully";
        }
        return "The dues payment with that id does not exist";
    }



    public List<DuesPayment> getPaymentsByStatus(PaymentStatus status) {

    return duesPaymentRepository.findByStatusOrderByPaymentDateAsc(status);

}

public List<DuesPayment> getPaymentsBetween(LocalDate start, LocalDate end) {

    return duesPaymentRepository.findByPaymentDateBetweenOrderByPaymentDateAsc(start, end);
}

public String changeStatus(UUID id, PaymentStatus newStatus) {
    Optional<DuesPayment> found = duesPaymentRepository.findById(id);
    if (found.isEmpty()) {
        return "The dues payment with that id does not exist";
    }
    DuesPayment payment = found.get();
    PaymentStatus current = payment.getStatus();

    boolean allowed = false;
    if (current == PaymentStatus.DECLARED
            && (newStatus == PaymentStatus.VERIFIED || newStatus == PaymentStatus.REVERSED)) {
        allowed = true;
    }
    if (current == PaymentStatus.VERIFIED
            && (newStatus == PaymentStatus.POSTED || newStatus == PaymentStatus.REVERSED)) {
        allowed = true;
    }
    if (!allowed) {
        return "Cannot change status from " + current + " to " + newStatus;
    }

    payment.setStatus(newStatus);
    duesPaymentRepository.save(payment);
    return "Dues payment status updated successfully";
}

public List<Object[]> getStatusCounts() {
    return duesPaymentRepository.getStatusCounts();
}

@Transactional
public String cancelDay(UUID treasurerId, LocalDate date) {
    int count =duesPaymentRepository.cancelDay(treasurerId, date, PaymentStatus.REVERSED, PaymentStatus.POSTED);
    return count + " dues payments cancelled";
}

public Page<DuesPayment> getPaymentsPage(Pageable pageable) {
    return duesPaymentRepository.findAll(pageable);
}








}