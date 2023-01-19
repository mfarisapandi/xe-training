package com.accenture.wizard.Entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "wizard_info")
@Data
public class Wizard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wizard_id")
    private int wizardID;
    @Column(name = "wizard_name")
    private String wizardName;
    @Column(name = "wizard_age")
    private int wizardAge;
    @Column(name = "joined_date")
    private LocalDate joinedDate;
    @Column(name = "is_active")
    private String isActive;

    public Wizard() {
    }

    public Wizard(int wizardID, String wizardName, int wizardAge,
                  LocalDate joinedDate, String isActive) {
        this.wizardID = wizardID;
        this.wizardName = wizardName;
        this.wizardAge = wizardAge;
        this.joinedDate = joinedDate;
        this.isActive = isActive;
    }

    public int getWizardID() {
        return wizardID;
    }

    public void setWizardID(int wizardID) {
        this.wizardID = wizardID;
    }

    public String getWizardName() {
        return wizardName;
    }

    public void setWizardName(String wizardName) {
        this.wizardName = wizardName;
    }

    public int getWizardAge() {
        return wizardAge;
    }

    public void setWizardAge(int wizardAge) {
        this.wizardAge = wizardAge;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
