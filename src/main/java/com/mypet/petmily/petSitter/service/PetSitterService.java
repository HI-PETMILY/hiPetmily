package com.mypet.petmily.petSitter.service;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.petSitter.dao.PetSitterMapper;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        if (!(result > 0)) {
            throw new PetSitterRegistException("펫시터 예약 등록을 실패하였습니다.");
        }
    }

    public PetSitterDTO selectAllInfo(PetSitterDTO petMember) {

        return petSitterMapper.selectAllInfo(petMember);
    }

}
