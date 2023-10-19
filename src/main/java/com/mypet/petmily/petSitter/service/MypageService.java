package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MypageService {

    private final PetSitterMapper petSitterMapper;

    private MypageService(PetSitterMapper petSitterMapper){ this.petSitterMapper = petSitterMapper;}



}
