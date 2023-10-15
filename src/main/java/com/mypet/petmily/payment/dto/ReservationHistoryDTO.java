package com.mypet.petmily.payment.dto;

import com.mypet.petmily.petSitterNew.dto.NewPetSitterDTO;
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
    private PetMemberDTO resMember;
    private NewPetSitterDTO resPetSitter;
    private PetResDTO resPet;

}
