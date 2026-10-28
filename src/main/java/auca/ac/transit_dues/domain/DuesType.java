package auca.ac.transit_dues.domain;

import java.lang.annotation.Inherited;
import java.util.UUID;
 
import javax.annotation.processing.Generated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;







@Entity
@Table(name = "dues_type")
public class DuesType {

    @Id 
    @GeneratedValue (strategy =GenerationType.UUID)
    private UUID id;
    
    @Column(name ="name")
    private String name;

    @ManyToMany(mappedBy = "duesTypes")
    @JsonIgnore 
    private List<Treasurer>  treasurers;





    public void setId(UUID id) {
        this.id = id;

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    } 
    public void setName(String name) {
        this.name = name;
    } 



    public List<Treasurer> getTreasurers() {
        return treasurers;
    }

    public void setTreasures(List<Treasurer> treasurers){
        this.treasurers = treasurers;
    }




}


