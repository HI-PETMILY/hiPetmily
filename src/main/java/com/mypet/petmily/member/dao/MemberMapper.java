package com.mypet.petmily.member.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    String selectMemberByNickName(String nickName);

    int insertMember(MemberDTO member);

    int insertMemberRole();
}
