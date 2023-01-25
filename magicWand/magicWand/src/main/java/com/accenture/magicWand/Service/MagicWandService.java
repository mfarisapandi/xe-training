package com.accenture.magicWand.Service;

import com.accenture.magicWand.Entity.*;
import com.accenture.magicWand.Exception.*;
import com.accenture.magicWand.Repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MagicWandService {

    @Autowired
    MagicWandRepository repository;

    public List<MagicWand> getMagicWands(){
        return repository.findAll();
    }

    public MagicWand addMagicWand(MagicWand magicWand){
        return repository.save(magicWand);
    }

    public MagicWand updateMagicWand(MagicWand magicWand){
        repository.findById(magicWand.getMagicWandID()).orElseThrow(
                () -> new MagicWandException(MagicWandException.ID_DOES_NOT_EXIST)
        );
        return repository.save(magicWand);
    }

    public void deleteMagicWand(int magicWandID){
        repository.findById(magicWandID).orElseThrow(
                () -> new MagicWandException(MagicWandException.ID_DOES_NOT_EXIST)
        );
        repository.deleteById(magicWandID);
    }
}
