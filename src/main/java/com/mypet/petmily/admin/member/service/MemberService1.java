package com.mypet.petmily.admin.member.service;


import com.mypet.petmily.admin.member.dao.MembersMapper;
import com.mypet.petmily.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class MemberService1 {

    private final MembersMapper membersMapper;

    public MemberService1(MembersMapper membersMapper) {this.membersMapper = membersMapper;}

    public List<MemberDTO> selectMemberList() {

        log.info("memberList : {} " , membersMapper.selectMemberList());

//        System.out.println(membersMapper.selectMemberList());

        return membersMapper.selectMemberList();

    }
}
