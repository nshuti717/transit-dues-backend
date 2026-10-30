package auca.ac.transit_dues.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.domain.PaymentStatus;
import auca.ac.transit_dues.domain.Receipt;
import auca.ac.transit_dues.repository.ReceiptRepository;
import auca.ac.transit_dues.repository.DuesPaymentRepository;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private DuesPaymentRepository duesPaymentRepository;

    public String saveReceipt(Receipt receipt, UUID duesPaymentId) {
        Optional<DuesPayment> found = duesPaymentRepository.findById(duesPaymentId);
        if (found.isEmpty()) {
            return "The dues payment with that id does not exist";
        }
        DuesPayment payment = found.get();
        if (payment.getStatus() != PaymentStatus.POSTED) {
            return "Receipts are only for POSTED dues payments";
        }
        receipt.setIssuedDate(LocalDate.now());
        receipt.setDuesPayment(payment);
        receiptRepository.save(receipt);
        return "Receipt saved successfully";
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public List<Receipt> getReceiptsByRider(String plateNumber) {
        return receiptRepository.findByRiderPlateNumber(plateNumber);
    }

    public String deleteReceipt(UUID id) {
        Optional<Receipt> found = receiptRepository.findById(id);
        if (found.isPresent()) {
            receiptRepository.deleteById(id);
            return "Receipt deleted successfully";
        }
        return "The receipt with that id does not exist";
    }

}
