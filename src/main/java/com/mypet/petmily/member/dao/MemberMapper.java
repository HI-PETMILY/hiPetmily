package com.mypet.petmily.member.dao;

import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.dto.PetDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);

//    String selectMemberById(String memberId);

    String findId(String memberName, String phone);

    int insertMember(MemberDTO member);

    int insertMemberRole();

    int updateMember(MemberDTO modifyMember);

    int updatePassword(MemberDTO modifyPassword);

    int deleteMember(MemberDTO member);

    String selectMemberByNickName(String nickName);

    String selectMemberByMemberId(String memberId);

    int pwdCheck(MemberDTO dto);

    void pwdUpdate(MemberDTO dto);

    void insertPetProfile(PetDTO pet);

    /* 리뷰 */
    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);

    int selectTotalCount(Map<String, String> searchMap);

  
    List<PetDTO> selectPetProfileList(MemberDTO loginMember);

    PetDTO viewPetProfile(MemberDTO loginMember, int petCode);
    // 파라미터를 두개 이상 넘길 때는 xml설정으로 넘어갈 때 변수명으로 찾아야 함

    PetDTO viewFirstPetProfile(MemberDTO member);

    PetDTO petProfileUpdate(MemberDTO loginMember);

}
