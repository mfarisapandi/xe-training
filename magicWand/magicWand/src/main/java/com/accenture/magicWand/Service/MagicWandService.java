package com.accenture.magicWand.Service;

import com.accenture.magicWand.Entity.*;
import com.accenture.magicWand.Exception.*;
import com.accenture.magicWand.Repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.logging.*;

@Service
public class MagicWandService {

    private static final Logger LOGGER = Logger.getLogger(MagicWandService.class.getName());

    @Autowired
    MagicWandRepository repository;

    public List<MagicWand> getMagicWands(){
        LOGGER.info("Getting all Magic Wands");
        List<MagicWand> magicWands = repository.findAll();
        if (magicWands.isEmpty()) {
            LOGGER.severe("No Magic Wands found");
        }
        return magicWands;
    }

    public MagicWand addMagicWand(MagicWand magicWand){
        LOGGER.info("Adding Magic Wand");
        return repository.save(magicWand);
    }

    public MagicWand updateMagicWand(MagicWand magicWand){
        LOGGER.info("Updating Magic Wand: " + magicWand.getMagicWandID());
        repository.findById(magicWand.getMagicWandID()).orElseThrow(
                () -> {
                    LOGGER.severe("Magic Wand with ID " + magicWand.getMagicWandID() + " does not exist");
                    return new MagicWandException(MagicWandException.ID_DOES_NOT_EXIST);
                }
        );
        return repository.save(magicWand);
    }

    public void deleteMagicWand(int magicWandID){
        LOGGER.info("Deleting Magic Wand with ID: " + magicWandID);
        repository.findById(magicWandID).orElseThrow(
                () -> {
                    LOGGER.severe("Magic Wand with ID " + magicWandID + " does not exist");
                    return new MagicWandException(MagicWandException.ID_DOES_NOT_EXIST);
                }
        );
        repository.deleteById(magicWandID);
    }
}
