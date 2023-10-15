package com.mypet.petmily.petSitterNew.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NewPetSitterDTO {

    private int petMemberNo;
    private String petRegistDate;
    private String petStat;
    private String petTitle;
    private String petIntroduce;
    private String petPlace;
    private int petCareCount;
    private char petBabyYn;
    private char petPence;
    private char petMypetYn;
    private char petSmokYn;
    private String petCareSize;
    private String petCareAge;
    private String petCheckIn;
    private String petCheckOut;
    private String petRequest;
    private String petImpossible;
    private int petM_DayPay;
    private int petM_TimePay;
    private int petL_DayPay;
    private int petL_TimePay;
    private String petBank;
    private String petBankNo;
    private String petBankName;

    /* 펫시터 Profile 화면 */
    private List<CareerDTO> careerList;
    private List<PetTagDTO> petTagList;
    private PetJsonMemberDTO petJsonMemberInfo;

    /* 펫시터 regist */
    private int loginMemberNo;
    private List<String> regPetTagList;
    private List<String> regCareerList;
    private String schedule;

}

