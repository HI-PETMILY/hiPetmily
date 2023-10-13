package com.mypet.petmily.payment.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitterNew.dto.PetJsonMemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProgressReserveDTO {

    private int reserveCode;                // 예약 코드
    private String startDateTime;           // 서비스 시작 일시
    private String endDateTime;             // 서비스 종료 일시
    private String reserveStatus;           // 예약 상태
    private int accum;                      // 적립금
    private int TotalAmount;                // 결제 총액
    private int reserveDayCount;            // 예약 일수
    private int reserveTimeCount;           // 예약 시간
    private int reserveVat;                 // 예약 부가세
    private String reserveAppDate;          // 예약 신청일자
    private String reserveUpdateDate;       // 예약 상태 변경일
    private MemberDTO reserveMember;        // 예약 신청 회원
    private String petSitter;     // 펫시터 (코드, 닉네임)

}
