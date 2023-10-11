package com.mypet.petmily.petSitter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PetSitterDTO {

    private int petMemberNo;
    private int price;
    private Date availRegistDt;
    private Date registDt;
    private String availRegistTm;
    private String accountHolder;
    private String bank;
    private int account;
    private String room;
    private char smokingStatus;
    private char petsitterExperience;
    private char dayCare;
    private int activityAvailDay;
    private char dogExist;
    private String applicationReason;
    private int family;
    private String job;
    private String introduce;
    private String petsitterEducation;
    private String careEnv;
    private String firstComments;

}
