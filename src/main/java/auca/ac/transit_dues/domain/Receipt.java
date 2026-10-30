package auca.ac.transit_dues.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String receiptType;
    private String remarks;
    private int amountRwf;
    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "dues_payment_id")
    private DuesPayment duesPayment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAmountRwf() {
        return amountRwf;
    }

    public void setAmountRwf(int amountRwf) {
        this.amountRwf = amountRwf;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public DuesPayment getDuesPayment() {
        return duesPayment;
    }

    public void setDuesPayment(DuesPayment duesPayment) {
        this.duesPayment = duesPayment;
    }

}
