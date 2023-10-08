package com.mypet.petmily.member.service;

import com.mypet.petmily.common.exception.member.MemberModifyException;
import com.mypet.petmily.common.exception.member.MemberPasswordUpdateException;
import com.mypet.petmily.common.exception.member.MemberRegistException;
import com.mypet.petmily.common.exception.member.MemberRemoveException;
import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MemberDTO;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /* 회원 가입 */
    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException {

        member.setMemberStat("활동");
        member.setMemberStatDate(new Date());
        member.setRegistDate(new Date());
        member.setSignupPathCode(1);

        int result1 = memberMapper.insertMember(member);
        int result2 = memberMapper.insertMemberRole();

        if (!(result1 > 0 && result2 > 0)) throw new MemberRegistException("회원 가입에 실패하였습니다.");
    }

    /* 회원 정보 수정 */
    @Transactional
    public void modifyMember(MemberDTO modifyMember) throws MemberModifyException {

        int result = memberMapper.updateMember(modifyMember);

        if (!(result > 0)) throw new MemberModifyException("회원 정보 수정에 실패하였습니다.");
    }


    /* 비밀 번호 변경 */
    @Transactional
    public void modifyPassword(MemberDTO modifyPassword) throws MemberPasswordUpdateException {

        int result = memberMapper.updatePassword(modifyPassword);

        if (!(result > 0)) throw new MemberPasswordUpdateException("비밀번호 변경에 실패하였습니다.");
    }

    @Transactional
    public void removeMember(MemberDTO member) throws MemberRemoveException {
        int result = memberMapper.deleteMember(member);

        if (!(result > 0)) {
            throw new MemberRemoveException("회원 탈퇴에 실패하였습니다.");
        }
    }


    public boolean selectMemberByNickName(String nickName){

        String result = memberMapper.selectMemberByNickName(nickName);

        return result != null;


    }

    /* 아이디 찾기 */
    public String findId(String memberName, String phone) {
        return memberMapper.findId(memberName, phone);
    }

    /* 비밀번호 찾기 */
    public int pwdCheck(MemberDTO dto) {
        return memberMapper.pwdCheck(dto);
    }

    /* 비밀번호 업데이트 */
    public void pwdUpdate(MemberDTO dto) {
        String newUpdatePwd = passwordEncoder.encode(dto.getPassword());
        dto.setMemberPwd(newUpdatePwd);
        memberMapper.pwdUpdate(dto);
    }
}