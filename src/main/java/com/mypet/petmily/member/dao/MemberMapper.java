package com.mypet.petmily.member.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);

//    String selectMemberById(String memberId);


}
