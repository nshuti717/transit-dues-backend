package auca.ac.transit_dues.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "stage")
public class Stage {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "Stage_number")
    private  int stageNumber;

    @Column (name = "name")
    private String name;

    public UUID getId() {
        return id;

    }

    public void setId(UUID id) {
        this.id = id;

    }

    public int getStageNumber() {
        return stageNumber;

    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}


