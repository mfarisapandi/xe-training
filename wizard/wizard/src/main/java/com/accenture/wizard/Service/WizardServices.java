package com.accenture.wizard.Service;

import com.accenture.wizard.Entity.Wizard;
import com.accenture.wizard.Exception.WizardException;
import com.accenture.wizard.Repository.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.*;

@Service
public class WizardServices {

    private static final Logger LOGGER = Logger.getLogger(WizardServices.class.getName());
    @Autowired
    WizardRepository repository;

    public List<Wizard> getWizards() {
        LOGGER.info("Retrieving all wizards from repository.");
        List<Wizard> wizards = repository.findAll();
        if (wizards.isEmpty()) {
            LOGGER.severe("No Magic Wands found");
        }
        return wizards;
    }

    public Wizard addWizard(Wizard wizard) {
            LOGGER.info("Adding wizard to repository");
            return repository.save(wizard);
    }

    public Wizard updateWizard(Wizard wizard) {
        LOGGER.info("Updating wizard in repository.");
        repository.findById(wizard.getWizardID()).orElseThrow(
                () -> {
                    LOGGER.severe("Wizard with ID " + wizard.getWizardID() + " does not exist");
                    return new WizardException(WizardException.ID_DOES_NOT_EXIST);
                }
        );
        return repository.save(wizard);
    }

    public void deleteWizard(int wizardID) {
            LOGGER.info("Deleting wizard with ID: " + wizardID + " from repository.");
            repository.findById(wizardID).orElseThrow(
                    () -> {
                        LOGGER.severe("Error deleting wizard with ID " + wizardID + ": " + "Does not exist");
                        return new WizardException(WizardException.ID_DOES_NOT_EXIST);
                    }
            );
            repository.deleteById(wizardID);
    }


}
