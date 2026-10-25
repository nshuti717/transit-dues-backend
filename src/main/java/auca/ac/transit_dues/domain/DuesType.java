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

@Entity
@Table(name = "dues_type")
public class DuesType {

    @Id 
    @GeneratedValue (strategy =GenerationType.UUID)
    private UUID id;
    
    @Column(name ="name")
    private String name;

    public void setId(UUID id) {
        this.id = id;

    }

    public String getName() {
        return name;
    } 
    public void setName(String name) {
        this.name = name;
    } 




}


