package com.mypet.petmily.petSitter.dto;

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



    //펫시터 코드
    private int petMemberNo;

    //펫시터 등록일자
    private String petRegistDt;

    //펫상태
    private String petStatus;

    //대표문구

    private String petTitle;

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
    //은행
    private String petBank;
    //계좌번호
    private String petBankNo;

    //예금주
    private String petBankName;
    //닉네임
    private String nickname;
    //태그 내용
    private List<PetTagDTO> tagContentList;

    private List<ReviewDTO> reviewList;

//    private List<ReviewDTO> revContent;





}
