package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PetSitterService {

    private final PetSitterMapper petSitterMapper;

    public PetSitterService(PetSitterMapper petSitterMapper) {
        this.petSitterMapper = petSitterMapper;
    }

    public void registReservation(ReservationDTO reservation) throws PetSitterRegistException {

        int result = petSitterMapper.registReservation(reservation);

        log.info("$$%$#@33333 : {}", result);

        if (!(result > 0)) {
            throw new PetSitterRegistException("펫시터 예약 등록을 실패하였습니다.");
        }
    }

//    public int searchResNo(ReservationDTO reservation) {
//        log.info("1111111111233333 : {}");
//        log.info("43443434434 : {}", petSitterMapper.searchResNo(reservation));
//
//        return 0;
//    }


}
