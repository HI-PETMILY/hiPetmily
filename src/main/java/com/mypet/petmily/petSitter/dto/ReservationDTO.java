package com.mypet.petmily.petSitter.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReservationDTO {

    private int resCode;
    private String startDateTime;
    private String endDateTime;
    private String resStatus;
    private int accum;
    private int resTotalAmount;
    private int resDayCount;
    private int resTimeCount;
    private int resVat;
    private String resAppDate;
    private String resUpdateDate;
    private MemberDTO resMember;
    private PetSitterDTO2 resPetSitter;

}
