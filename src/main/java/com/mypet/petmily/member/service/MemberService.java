package com.mypet.petmily.member.service;

import com.mypet.petmily.common.exception.member.MemberRegistException;
import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    /* 회원 닉네임 조회 */
    public boolean selectMemberByNickName(String nickName) {

        String result = memberMapper.selectMemberByNickName(nickName);

        return result != null;
    }

    /* 회원 가입 */
    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException {

        member.setMemberStat("활동");
        member.setMemberStatDate(new Date());

        int result1 = memberMapper.insertMember(member);
        int result2 = memberMapper.insertMemberRole();

        if (!(result1 > 0 && result2 > 0)) throw new MemberRegistException("회원 가입에 실패하였습니다.");


    }

    @Transactional
    public MemberDTO registerMembmer(String nickName) {
        MemberDTO member = new MemberDTO();
        member.setNickName(nickName);

        return member;
    }


}