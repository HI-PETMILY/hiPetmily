package com.mypet.petmily.payment.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class PetResDTO {

    private int petCode;           // 반려견 코드
    private String petName;         // 반려견 이름
    private String petAge;          // 반려견 나이
    private String petGender;       // 반려견 성별
    private String neutralYn;       //중성화 여부
    private String petBreed;        // 견종
    private int petWeight;          // 몸무게
    private String petRegist;       // 반려견 등록(내장형, 외장형, 등록인식표, x)
    private String pSocial;         // 사람과 친화력
    private String aSocial;         // 다른 동물과 친화력
    private String barkLevel;       // 짖음 정도
    private String sepAnxiety;      // 분리불안 증세
    private String petDisease;      // 지병
    private String petDiseaseExp;   // 지병 상세 설명
    private String petAllergy;      // 알러지
    private String petAllergyExp;   // 알러지 상세 설명
    private String pottyTrain;      // 배변 교육
    private String markingIn;       // 실내 마킹
    private String vaccin;          // 예방 접종
    private String hosName;         // 자주 가는 병원 명
    private String hosPhone;        // 자주 가는 병원 전화번호
    private String hosAddress;      // 병원 간략 주소
    private String registStatus;    // 반려동물 등록 상태(Y, N)
    private PetMemberDTO member;
}
