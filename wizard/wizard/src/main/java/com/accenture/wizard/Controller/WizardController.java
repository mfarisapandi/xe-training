package com.accenture.wizard.Controller;

import com.accenture.wizard.Entity.Wizard;
import com.accenture.wizard.Service.WizardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("api/xe/")
public class WizardController {

    @Autowired
    WizardServices service;

    @GetMapping("")
    public String intro(){
        return "Welcome!";
    }

    @GetMapping("wizardList")
    public List<Wizard> getWizards(){
        return service.getWizards();
    }

    @PostMapping("add")
    public Wizard addWizard(@RequestBody Wizard wizard){
        return service.addWizard(wizard);
    }

    @PutMapping("update")
    public Wizard updateWizard(@RequestBody Wizard wizard){
        return service.updateWizard(wizard);
    }

    @DeleteMapping("delete/{wizardID}")
    public String deleteWizard(@PathVariable(value = "wizardID") int wizardID){
        service.deleteWizard(wizardID);
        return ("Wizard with ID " + wizardID + " is deleted");
    }

}
