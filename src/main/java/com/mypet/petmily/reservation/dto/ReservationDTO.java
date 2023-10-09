package com.mypet.petmily.reservation.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetsitterDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class ReservationDTO {

    private Long reserveCode;           // 예약 코드
    private Date reserveStart;          // 예약 시작일
    private Date reserveEnd;            // 예약 종료일
    private String reserveStatus;       // 예약 상태
    private Date reserveAppDate;        // 예약 신청일
    private Date reserveUpdateDate;     // 예약 상태 변경일
    private int reserveAmount;          // 예약 금액
    private MemberDTO memberNo;         // 회원 코드
    private PetsitterDTO petMemberNo;   // 펫시터 코드
}
