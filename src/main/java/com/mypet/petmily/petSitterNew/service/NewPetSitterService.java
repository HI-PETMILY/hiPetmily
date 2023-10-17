package com.mypet.petmily.petSitterNew.service;

import com.mypet.petmily.common.exception.petSitter.PetSitterRegistException;
import com.mypet.petmily.fileUpload.dao.FileUploadMapper;
import com.mypet.petmily.fileUpload.dto.FileUploadDTO;
import com.mypet.petmily.member.dto.PetDTO;
import com.mypet.petmily.petSitterNew.dao.NewPetSitterMapper;
import com.mypet.petmily.petSitterNew.dto.*;
import com.mypet.petmily.review.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@Transactional
public class NewPetSitterService {

    private final NewPetSitterMapper newPetSitterMapper;
    private final FileUploadMapper fileUploadMapper;

    public NewPetSitterService(NewPetSitterMapper newPetSitterMapper, FileUploadMapper fileUploadMapper) {
        this.newPetSitterMapper = newPetSitterMapper;
        this.fileUploadMapper = fileUploadMapper;
    }

    @Value("${image.image-dir}")
    private String IMAGE_DIR;



    public void registReservation(ReservationDTO reservation) {

        newPetSitterMapper.registReservation(reservation);
    }

    public NewPetSitterDTO petSitterProfile(NewPetSitterDTO petSitter) {

        NewPetSitterDTO petSitterInfo = newPetSitterMapper.selectAllInfo(petSitter);
        List<FileUploadDTO> fileUpload = fileUploadMapper.selectSitterImg(petSitter);
        List<CareerDTO> careerList = newPetSitterMapper.selectAllCareer(petSitter);
        List<PetTagDTO> petTagList = newPetSitterMapper.selectAllTag(petSitter);
        PetJsonMemberDTO petJsonMemberInfo = newPetSitterMapper.selectMemberInfo(petSitter);
        List<PetDTO> myPetList = newPetSitterMapper.selectMyPet(petSitter);
        List<ReviewDTO> reviewList = newPetSitterMapper.selectReview(petSitter);

        log.info("--petSitterInfo : {}", petSitterInfo);
        log.info("--fileUpload : {}", fileUpload);
        log.info("--careerList : {}", careerList);
        log.info("--petTagList : {}", petTagList);
        log.info("--petJsonMemberInfo : {}", petJsonMemberInfo);
        log.info("--myPetList : {}", myPetList);
        log.info("--reviewList : {}", reviewList);

        petSitterInfo.setSitterImgList(fileUpload);
        petSitterInfo.setCareerList(careerList);
        petSitterInfo.setPetTagList(petTagList);
        petSitterInfo.setPetJsonMemberInfo(petJsonMemberInfo);
        petSitterInfo.setMyPetList(myPetList);
        petSitterInfo.setReviewList(reviewList);

        return petSitterInfo;
    }

    public List<SitterScheduleDTO> petSitterSchedule(NewPetSitterDTO petMember) {
        return newPetSitterMapper.petSitterSchedule(petMember);
    }

    public PetJsonMemberDTO petSitterAddress(NewPetSitterDTO petMember) {
        return newPetSitterMapper.petSitterAddress(petMember);
    }

    public boolean petSitterCheck(NewPetSitterDTO petSitter) {

        String result = newPetSitterMapper.petSitterCheck(petSitter);

        return result != null;
    }

    public NewPetSitterDTO petSitterStat(NewPetSitterDTO petSitter) {
        // 펫시터 상태 조회용
        return newPetSitterMapper.selectAllInfo(petSitter);
    }

    public void petSitterRegist(NewPetSitterDTO petSitter) throws PetSitterRegistException {

        int loginMemberNo = petSitter.getPetMemberNo();
        List<String> regPetTagList = petSitter.getRegPetTagList();
        List<String> regCareerList = petSitter.getRegCareerList();
        String schedule = petSitter.getSchedule();
        List<MultipartFile> attachImageList = petSitter.getAttachImage();

        // 현재 날짜 로직
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String formatedNow = formatter.format(now);

        // 현재날짜
        petSitter.setPetRegistDate(formatedNow);
        petSitter.setPetStat("대기");

        // 파일 테이블에 추가(default 이미지가 있어서 로직 실행)
        if ( !attachImageList.isEmpty() ) {
            // Regist - img insert
            addFileUpload(petSitter, loginMemberNo, attachImageList);
        }

        // 태그정보 테이블에 추가
        if ( !regPetTagList.isEmpty() ) {
            // Regist - tag insert
            addTag(regPetTagList, loginMemberNo);
        }

        // 경력정보 테이블에 추가
        if ( !regCareerList.isEmpty() ) {
            // Regist - carrer insert
            addCareer(regCareerList, loginMemberNo);
        }

        // 펫시터 예약불가 날짜
        if( schedule != "" ) {
            // Regist - sitter schedule insert
            addSchedule(schedule, loginMemberNo);
        }

        // 펫시터 기본정보 등록
        int result = newPetSitterMapper.insetInfo( petSitter );

        if (result <= 0) {
            throw new PetSitterRegistException("펫시터 등록에 실패하였습니다.");
        }

    }


    // Regist - tag insert
    public void addTag(List<String> regPetTagList, int loginMemberNo) {

        PetTagDTO petTag = new PetTagDTO();
        petTag.setPetMemberNo( loginMemberNo );

        for (int i = 0; i < regPetTagList.size(); i++) {
            petTag.setTagContent( regPetTagList.get(i) );
            newPetSitterMapper.insertTag( petTag );
        }
        log.info("--regPetTagList : {}", regPetTagList);

    }

    // Regist - carrer insert
    private void addCareer(List<String> regCareerList, int loginMemberNo) {

        CareerDTO career = new CareerDTO();
        career.setPetMemberNo( loginMemberNo );

        for (int i = 0; i < regCareerList.size(); i++) {
            career.setCareerContent( regCareerList.get(i) );
            newPetSitterMapper.insertCareer( career );
        }
        log.info("--regCareerList : {}", regCareerList);

    }

    // Regist - sitter schedule insert
    private void addSchedule(String schedule, int loginMemberNo) {

        String[] days = schedule.split(", ");
        SitterScheduleDTO sitterSchedule = new SitterScheduleDTO();
        sitterSchedule.setPetMemberNo( loginMemberNo );
        sitterSchedule.setPetMemberStatus("예약불가");

        for (int i = 0; i < days.length; i++) {
            sitterSchedule.setPetMemberResDay( days[ i ] );
            newPetSitterMapper.insertSchedule( sitterSchedule );
        }
        log.info("--sitterSchedule : {}", sitterSchedule);

    }

    // Regist - fileUpload insert
    private void addFileUpload( NewPetSitterDTO petSitter, int loginMemberNo
            , List<MultipartFile> attachImageList ) {

        // 파일 업로드 부분 시작
        log.info("--attachImageList : {}", attachImageList);

        // 원래 제목 경로, 섬네일용 경로 저장
        String fileUploadDir = IMAGE_DIR + "original";
        String thumbnailDir = IMAGE_DIR + "thumbnail";

        File dir1 = new File(fileUploadDir);
        File dir2 = new File(thumbnailDir);

        /* 디렉토리가 없을 경우 생성한다. */
        if (!dir1.exists() || !dir2.exists()) {
            dir1.mkdirs();
            dir2.mkdirs();
        }

        /* 업로드 파일에 대한 정보를 담을 리스트 */
        List<FileUploadDTO> fileUploadList = new ArrayList<>();

        try {
            for (int i = 0; i < attachImageList.size(); i++) {
                /* 첨부파일이 실제로 존재하는 경우에만 로직 수행 */
                if (attachImageList.get(i).getSize() > 0) {

                    String originalFileName = attachImageList.get(i).getOriginalFilename();
                    log.info("--originalFileName : {}", originalFileName);

                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String savedFileName = i + "_img_" + UUID.randomUUID() + ext;
                    log.info("--savedFileName : {}", savedFileName);

                    /* 서버의 설정 디렉토리에 파일 저장하기
                     * MultipartFile 관련 */
                    attachImageList.get(i).transferTo(new File(fileUploadDir + "/" + savedFileName));

                    /* DB에 저장할 파일의 정보 처리 */
                    FileUploadDTO fileInfo = new FileUploadDTO();

                    fileInfo.setFileOriName(originalFileName);      // 오지리날 파일명 저장
                    fileInfo.setFileExtName(ext);                   // 확장명 저장
                    fileInfo.setFileSaveName(savedFileName);        // 저장용 파일명 저장
                    fileInfo.setFileSitterNo(loginMemberNo);        // 이미지 소유자 번호 저장
                    fileInfo.setFileMemberNo(loginMemberNo);        // 이미지 소유자 번호 저장

                    // 경로에 맨뒤쪽 / 필수로 넣어야 한다.
                    // 요청할때 필요한 경로를 입력해주는 것.
                    fileInfo.setFilePathName("/upload/original/");

                    if(i == 0) {
                        // 처음들어온 0번이 대표 파일
                        fileInfo.setFileType("MainImg");

                        /* 대표 사진에 대한 썸네일을 가공해서 저장한다. */
                        /* 이 부분이 썸네일 라이브러리 추가된 부분으로 사진 사이즈를 가공하기 위해서 추가 */
                        Thumbnails.of(fileUploadDir + "/" + savedFileName).size(780, 560)
                                .toFile(thumbnailDir + "/thumbnail_" + savedFileName);

                        fileInfo.setFile_T_PathName("/upload/thumbnail/thumbnail_" + savedFileName);
                    } else {
                        // 나머지는 서브 파일
                        fileInfo.setFileType("SubImg");
                        fileInfo.setFile_T_PathName("No");
                    }

                    fileUploadList.add(fileInfo);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < fileUploadList.size(); i++) {
            petSitter.setRegFileUpload( fileUploadList.get(i) );
            fileUploadMapper.petSitterAddImage( petSitter );
        }
        log.info("--fileUploadList : {}", fileUploadList);

    }



}
