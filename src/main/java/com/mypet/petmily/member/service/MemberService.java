package com.mypet.petmily.member.service;

import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


}
