package com.mypet.petmily.petSitter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetSitterDTO {

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

}

