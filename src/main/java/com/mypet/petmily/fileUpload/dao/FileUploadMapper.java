package com.mypet.petmily.fileUpload.dao;

import com.mypet.petmily.fileUpload.dto.FileUploadDTO;
import com.mypet.petmily.petSitterNew.dto.NewPetSitterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileUploadMapper {

    // 펫시터 등록시 이미지 파일 추가
    void petSitterAddImage(NewPetSitterDTO petSitter);
    // 펫시터 등록시 이미지 파일 조회
    List<FileUploadDTO> selectSitterImg(NewPetSitterDTO petSitter);

    /* 반려동물 프로필 이미지 등록*/
    void insertAttachment(FileUploadDTO fileupload);

    /* 반려동물 프로필 이미지 조회 */
    FileUploadDTO petProfileImg(int petCode);
}
