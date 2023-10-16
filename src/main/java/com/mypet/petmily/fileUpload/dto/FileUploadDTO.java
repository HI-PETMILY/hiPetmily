package com.mypet.petmily.fileUpload.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class FileUploadDTO {

    private int fileNo;             // 파일번호
    private String fileOriName;     // 파일명  (오리지날 파일명)
    private String fileSaveName;    // 저장명  (저장 파일명)
    private String fileExtName;     // 확장자명
    private String filePathName;    // 경로명
    private String file_T_PathName; // 섬네일 경로명
    private String fileType;        // 구분

    private int filePetNo;          // 반려견 번호
    private int fileBoardNo;        // 게시글 번호
    private int fileRevNo;          // 후기 번호
    private int fileMemberNo;       // 회원 번호
    private int fileSitterNo;       // 펫시터 번호

}
