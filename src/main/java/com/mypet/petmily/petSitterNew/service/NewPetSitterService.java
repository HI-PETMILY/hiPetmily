package com.mypet.petmily.petSitterNew.service;

import com.mypet.petmily.petSitterNew.dao.NewPetSitterMapper;
import com.mypet.petmily.petSitterNew.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class NewPetSitterService {

    private final NewPetSitterMapper newPetSitterMapper;

    public NewPetSitterService(NewPetSitterMapper newPetSitterMapper) {
        this.newPetSitterMapper = newPetSitterMapper;
    }

    public void registReservation(ReservationDTO reservation) {

        newPetSitterMapper.registReservation(reservation);
    }
    public NewPetSitterDTO petSitterProfile(NewPetSitterDTO petSitter) {

        NewPetSitterDTO petSitterInfo = newPetSitterMapper.selectAllInfo(petSitter);
        List<CareerDTO> careerList = newPetSitterMapper.selectAllCareer(petSitter);
        List<PetTagDTO> petTagList = newPetSitterMapper.selectAllTag(petSitter);
        PetJsonMemberDTO petJsonMemberInfo = newPetSitterMapper.selectMemberInfo(petSitter);

        log.info("--petSitterInfo : {}", petSitterInfo);
        log.info("--careerList : {}", careerList);
        log.info("--petTagList : {}", petTagList);
        log.info("--petJsonMemberInfo : {}", petJsonMemberInfo);

        petSitterInfo.setCareerList(careerList);
        petSitterInfo.setPetTagList(petTagList);
        petSitterInfo.setPetJsonMemberInfo(petJsonMemberInfo);

        return petSitterInfo;
    }

    public List<SitterScheduleDTO> petSitterSchedule(NewPetSitterDTO petMember) {
        return newPetSitterMapper.petSitterSchedule(petMember);
    }

    public PetJsonMemberDTO petSitterAddress(NewPetSitterDTO petMember) {
        return newPetSitterMapper.petSitterAddress(petMember);
    }

    public void petSitterRegist( NewPetSitterDTO petSitter ) {

        int loginMemberNo = petSitter.getPetMemberNo();
        List<String> regPetTagList = petSitter.getRegPetTagList();
        List<String> regCareerList = petSitter.getRegCareerList();
        String schedule = petSitter.getSchedule();

        // 현재 날짜 로직
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String formatedNow = formatter.format(now);

        // 현재날짜
        petSitter.setPetRegistDate(formatedNow);
        // 초기 가입시 대기상태
        petSitter.setPetStat("대기");

        // 태그정보 테이블에 추가
        if ( !regPetTagList.isEmpty() ) {

            PetTagDTO petTag = new PetTagDTO();
            petTag.setPetMemberNo( loginMemberNo );

            for (int i = 0; i < regPetTagList.size(); i++) {
                petTag.setTagContent( regPetTagList.get(i) );
                newPetSitterMapper.insertTag( petTag );
            }
        }

        // 경력정보 테이블에 추가
        if ( !regCareerList.isEmpty() ) {

            CareerDTO career = new CareerDTO();
            career.setPetMemberNo( loginMemberNo );

            for (int i = 0; i < regCareerList.size(); i++) {
                career.setCareerContent( regCareerList.get(i) );
                newPetSitterMapper.insertCareer( career );
            }
        }

        // 펫시터 예약불가 날짜
        if( schedule != "" ) {

            String[] days = schedule.split(", ");
            SitterScheduleDTO sitterSchedule = new SitterScheduleDTO();
            sitterSchedule.setPetMemberNo( loginMemberNo );
            sitterSchedule.setPetMemberStatus("예약불가");

            for (int i = 0; i < days.length; i++) {
                sitterSchedule.setPetMemberResDay( days[ i ] );
                newPetSitterMapper.insertSchedule( sitterSchedule );
            }
        }

    }


}
