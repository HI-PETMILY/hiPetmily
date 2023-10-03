package com.mypet.petmily.member.service;

import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MailDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;

    }

    public String findId(String memberName, String phone) {
        return memberMapper.findId(memberName, phone);
    }


}
