package com.accenture.wizard.Service;

import com.accenture.wizard.Entity.Wizard;
import com.accenture.wizard.Exception.WizardException;
import com.accenture.wizard.Repository.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WizardServices {

    @Autowired
    WizardRepository repository;
    public List<Wizard> getWizards() {
        return repository.findAll();
    }

    public Wizard addWizard (Wizard wizard){
        return repository.save(wizard);
    }

    public Wizard updateWizard(Wizard wizard){
        repository.findById(wizard.getWizardID()).orElseThrow(
                () -> new WizardException("Wizard with ID " + wizard.getWizardID() + " does not exist")
        );
        return repository.save(wizard);
    }

    public void deleteWizard(int wizardID){
        repository.findById(wizardID).orElseThrow(
                () -> new WizardException("Wizard with ID " + wizardID + " does not exist")
        );
        repository.deleteById(wizardID);
    }

}
