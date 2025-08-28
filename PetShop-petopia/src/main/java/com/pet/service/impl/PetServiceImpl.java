package com.pet.service.impl;

import com.pet.entity.Pet;
import com.pet.repository.PetRepository;
import com.pet.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;


    @Override
    public List<Pet> getPets() {
        List<Pet> pets = petRepository.findAll();
        return pets;
    }
}
