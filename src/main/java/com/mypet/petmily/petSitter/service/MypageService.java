package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    private final PetSitterMapper petSitterMapper;

    private MypageService(PetSitterMapper petSitterMapper){ this.petSitterMapper = petSitterMapper;}


}
