package com.mypet.petmily.payment.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.dto.PetDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationHistoryDTO {

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
    private PetSitterDTO resPetSitter;
    private PetDTO resPet;

}
