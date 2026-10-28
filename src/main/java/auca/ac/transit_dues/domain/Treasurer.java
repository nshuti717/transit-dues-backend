package auca.ac.transit_dues.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity 
@Table (name = "Treasurer")
public  class Treasurer {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private  UUID id;


    private String firstName;
    private String lastName;
    private String phone;




    @ManyToOne
    @JoinColumn (name = "Stage_id")
    private Stage stage;

    @ManyToMany
    @JoinTable(name ="treasurer_dues_type",
                joinColumns  = @JoinColumn (name = "treasurer_id"),
                inverseJoinColumns = @JoinColumn (name = "dues_type_id"))
            

private List<DuesType> duesTypes = new ArrayList<>();

public UUID getId() {
    return id;
}


public void setId(UUID id) {
     this.id = id;
     }
    public String getFirstName() {
        return firstName;
     }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
     }
    public void setLastName(String lastName) {
        this.lastName = lastName;
     }
    public String getPhone() {
        return phone;
     }
    public void setPhone(String phone) {
        this.phone = phone;
     }
    public Stage getStage() {
        return stage;
     }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public List<DuesType> getDuesTypes() {
        return duesTypes;
     }
    public void setDuesTypes(List<DuesType> duesTypes) {
        this.duesTypes = duesTypes;
     }















}