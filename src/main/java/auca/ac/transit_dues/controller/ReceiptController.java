package auca.ac.transit_dues.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.transit_dues.domain.Receipt;
import auca.ac.transit_dues.service.ReceiptService;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/save")
    public ResponseEntity<?> saveReceipt(@RequestBody Receipt receipt, @RequestParam UUID duesPaymentId) {
        String msg = receiptService.saveReceipt(receipt, duesPaymentId);
        if (msg.contains("successfully")) {

            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        }
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReceipts() {
        return new ResponseEntity<>(receiptService.getAllReceipts(), HttpStatus.OK);
    }

    @GetMapping("/rider/{plateNumber}")
    public ResponseEntity<?> getReceiptsByRider(@PathVariable String plateNumber) {
        return new ResponseEntity<>(receiptService.getReceiptsByRider(plateNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable UUID id) {
        String msg = receiptService.deleteReceipt(id);
        if (msg.contains("successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

}
