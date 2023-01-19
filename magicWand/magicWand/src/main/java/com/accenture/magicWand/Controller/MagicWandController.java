package com.accenture.magicWand.Controller;

import com.accenture.magicWand.Entity.MagicWand;
import com.accenture.magicWand.Service.MagicWandService;
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
public class MagicWandController {

    @Autowired
    MagicWandService service;

    @GetMapping("")
    public String intro(){
        return "Welcome!";
    }

    @GetMapping("magicWandList")
    public List<MagicWand> getMagicWands(){
        return service.getMagicWands();
    }

    @PostMapping("add")
    public MagicWand addMagicWand(@RequestBody MagicWand magicWand){
        return service.addMagicWand(magicWand);
    }

    @PutMapping("update")
    public MagicWand updateMagicWand(@RequestBody MagicWand magicWand){
        return service.updateMagicWand(magicWand);
    }

    @DeleteMapping("delete/{magicWandID}")
    public String deleteMagicWand(@PathVariable(value = "magicWandID") int magicWandID){
        service.deleteMagicWand(magicWandID);
        return ("Magic Wand with ID " + magicWandID + " is deleted");
    }
}
