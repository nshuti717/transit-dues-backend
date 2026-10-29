package auca.ac.transit_dues.domain;

import java.lang.management.LockInfo;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity 
@Table (name = "dues_payment")
public class DuesPayment {



    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate paymentDate;

    @Enumerated (EnumType.STRING)
    private PaymentStatus status;
    private  double amount;

    @ManyToOne  
    @JoinColumn (name = "rider_id")
    private Rider rider;


    @ManyToOne 
    @JoinColumn (name = "treasurer_id")
    private Treasurer treasurer;


    public UUID getId() {
 return id; 
}
    public void setId(UUID id) { 
this.id = id; 
}
    public LocalDate getPaymentDate() { 
return paymentDate;
 }
    public void setPaymentDate(LocalDate paymentDate) { 
this.paymentDate = paymentDate; 
}
    public PaymentStatus getStatus() { 
return status;
 }
    public void setStatus(PaymentStatus status) { 
this.status = status;
 }
    public double getAmount() { 
return amount; 
}
    public void setAmount(double amount) {
 this.amount = amount; 
}
    public Rider getRider() {
 return rider;
 }
    public void setRider(Rider rider) { 
this.rider = rider;
 }
    public Treasurer getTreasurer() { 
return treasurer; 
}
    public void setTreasurer(Treasurer treasurer) {
 this.treasurer = treasurer;
 }


    
}