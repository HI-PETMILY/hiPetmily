package com.mypet.petmily.petSitter.dto;

import com.mypet.petmily.member.dto.AuthorityDTO;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.dto.MemberRoleDTO;
import com.mypet.petmily.petSitterNew.dto.PetTagDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PetSitterDTO {

    private int petMemberNo;    //펫시터 코드
    private String petRegistDt; //펫시터 등록일자
    private String petStatus;   //펫상태
    private String petTitle;    //대표문구
    private String petIntroduce;
    private String petPlace;
    private int petCareCount;
    private char petBabyYn;
    private char petSmoke;
    private String petCareSize;
    private String petCareAge;
    private String petCheckIn;
    private String petCheckOut;
    private String petRequest;
    private String petImpossible;
    private int petMDayPay;
    private int petMTimePay;
    private int petLDayPay;
    private int petLTimePay;
    private String petBank;     //은행
    private String petBankNo;   //계좌번호
    private String petBankName; //예금주
    private String nickname;    //닉네임

    private List<PetTagDTO> tagContentList; //태그 내용
    private List<ReviewDTO> reviewList;

    private MemberDTO member;
    private List<MemberRoleDTO> memberRoleList;      // 한 멤버는 여러 권한을 가질 수 있다
    private AuthorityDTO memberCode;

//    private List<ReviewDTO> revContent;





}
