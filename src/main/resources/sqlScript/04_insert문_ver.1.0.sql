----- C##PETMILY -----

-- 시퀀스명 참고 --
-- SEQ_TBL_SIGNUP_PATH_NO.NEXTVAL
-- SEQ_TBL_MEMBER_NO.NEXTVAL
-- SEQ_TBL_AUTHORITY_NO.NEXTVAL
-- SEQ_TBL_ROLE_NO.NEXTVAL				-- 사용안함
-- SEQ_TBL_ACCUMULATE_NO.NEXTVAL
-- SEQ_TBL_ALERT_NO.NEXTVAL
-- SEQ_TBL_MYPET_NO.NEXTVAL
-- SEQ_TBL_BOARD_CATEGORY_NO.NEXTVAL
-- SEQ_TBL_BOARD_NO.NEXTVAL
-- SEQ_TBL_PET_SITTER_NO.NEXTVAL		-- 사용안함
-- SEQ_TBL_FAVORITE_SITTER_NO.NEXTVAL	-- 사용안함
-- SEQ_TBL_SITTER_SCHEDULE_NO.NEXTVAL
-- SEQ_TBL_ADJUSTMENT_NO.NEXTVAL
-- SEQ_TBL_RESERVATION_NO.NEXTVAL
-- SEQ_TBL_PAYMENT_NO.NEXTVAL
-- SEQ_TBL_REFUND_NO.NEXTVAL			-- 사용안함
-- SEQ_TBL_REVIEW_NO.NEXTVAL
-- SEQ_TBL_FILE_NO.NEXTVAL




-- 가입경로 테이블 정보 추가
INSERT INTO TBL_SIGNUP_PATH(SIGNUP_PATH_CODE,SIGNUP_PATH_NAME)
    VALUES(SEQ_TBL_SIGNUP_PATH_NO.NEXTVAL,'자사페이지');
INSERT INTO TBL_SIGNUP_PATH(SIGNUP_PATH_CODE,SIGNUP_PATH_NAME)
    VALUES(SEQ_TBL_SIGNUP_PATH_NO.NEXTVAL,'카카오');
	
	
commit;
	

-- 회원 테이블 정보 추가
INSERT INTO TBL_MEMBER(MEMBER_NO,MEMBER_ID,MEMBER_PWD,MEMBER_NAME,PHONE,POST_NO,ADDRESS,ADDRESS2,POINT,GENDER,BIRTHDAY,MEMBER_STAT,WARNING_COUNT,MEMBER_STAT_DATE,SIGNUP_PATH_CODE)
VALUES(SEQ_TBL_MEMBER_NO.NEXTVAL,'admin','$2a$12$OoovdT4FMf7vszMob7SeiODn7HNJHNZDr6BJhSZ5XaLqs/IFyYckS','관리자','01012345678',03148,'서울 종로구','인사동 12길',1000, 'M', 230924,'활동',0,'2023-09-24',1);
INSERT INTO TBL_MEMBER(MEMBER_NO,MEMBER_ID,MEMBER_PWD,MEMBER_NAME,PHONE,POST_NO,ADDRESS,ADDRESS2,POINT,GENDER,BIRTHDAY,MEMBER_STAT,WARNING_COUNT,MEMBER_STAT_DATE,SIGNUP_PATH_CODE)
VALUES(SEQ_TBL_MEMBER_NO.NEXTVAL,'test01','$2a$12$OoovdT4FMf7vszMob7SeiODn7HNJHNZDr6BJhSZ5XaLqs/IFyYckS','테스트계정','01012345678',03148,'서울 종로구','인사동 12길',1000,'F',230924,'활동',0,'2023-09-24',2);
INSERT INTO TBL_MEMBER(MEMBER_NO,MEMBER_ID,MEMBER_PWD,MEMBER_NAME,PHONE,POST_NO,ADDRESS,ADDRESS2,POINT,GENDER,BIRTHDAY,MEMBER_STAT,WARNING_COUNT,MEMBER_STAT_DATE,SIGNUP_PATH_CODE)
VALUES(SEQ_TBL_MEMBER_NO.NEXTVAL,'test02@naver.com','$2a$12$OoovdT4FMf7vszMob7SeiODn7HNJHNZDr6BJhSZ5XaLqs/IFyYckS','테스트2계정','01012345678',03148,'서울 종로구','인사동 12길',9000,'M',230924,'탈퇴',0,'2023-09-24',1);

	

commit;
	

-- 권한 테이블 정보 추가
INSERT INTO TBL_AUTHORITY(AUTHORITY_CODE,AUTHORITY_NAME,AUTHORITY_DESC)
    VALUES(SEQ_TBL_AUTHORITY_NO.NEXTVAL,'ROLE_ADMIN','관리자');
INSERT INTO TBL_AUTHORITY(AUTHORITY_CODE,AUTHORITY_NAME,AUTHORITY_DESC)
    VALUES(SEQ_TBL_AUTHORITY_NO.NEXTVAL,'ROLE_PET_SITTER','펫시터');
INSERT INTO TBL_AUTHORITY(AUTHORITY_CODE,AUTHORITY_NAME,AUTHORITY_DESC)
    VALUES(SEQ_TBL_AUTHORITY_NO.NEXTVAL,'ROLE_MEMBER','일반회원');
	

commit;
	

-- 권한 서브 테이블 정보 추가
INSERT INTO TBL_ROLE(AUTHORITY_CODE,MEMBER_NO)
    VALUES(1,1);
INSERT INTO TBL_ROLE(AUTHORITY_CODE,MEMBER_NO)
    VALUES(2,2);
INSERT INTO TBL_ROLE(AUTHORITY_CODE,MEMBER_NO)
    VALUES(2,3);
INSERT INTO TBL_ROLE(AUTHORITY_CODE,MEMBER_NO)
    VALUES(3,3);
	

commit;
	

-- 적립금 테이블 정보 추가
INSERT INTO TBL_ACCUMULATE(ACCUM_POINT_CODE,ACCUM_REGIST_DATE,AMOUNT,ACCUM_DIVISION,ACCUM_STAT,ACCUM_STAT_DATE,MEMBER_NO)
    VALUES(SEQ_TBL_ACCUMULATE_NO.NEXTVAL,'',0,'','','',0);
	
commit;
	

-- 알림 테이블 정보 추가
INSERT INTO TBL_ALERT(NT_CODE,NT_SORT,NT_DATE_SENT,NT_CONTENT,MEMBER_NO)
    VALUES(SEQ_TBL_ALERT_NO.NEXTVAL,'','','',0);
	

commit;
	

-- 반려견 테이블 정보 추가
INSERT INTO TBL_MYPET(PET_CODE,PET_NAME,PET_AGE,PET_GENDER,NEUTRAL_YN,PET_BREED,PET_WEIGHT,PET_REGIST,P_SOCIAL,A_SOCIAL,BARK_LEVEL,SEP_ANXIETY,PET_DISEASE,PET_DISEASE_EXP,PET_ALLERGY,PET_ALLERGY_EXP,POTTY_TRAIN,MARKING_IN,VACCIN,HOS_NAME,HOS_PHONE,HOS_ADDRESS,MEMBER_NO)
    VALUES(SEQ_TBL_MYPET_NO.NEXTVAL,'','','','','',0,'','','','','','','','','','','','','','','',0);
	
	
	
commit;

	

-- 게시판 카테고리 테이블 정보 추가
INSERT INTO TBL_BOARD_CATEGORY(CATEGORY_CODE,CATEGORY_NAME)
    VALUES(SEQ_TBL_BOARD_CATEGORY_NO.NEXTVAL,'');
	
	
commit;	


-- 게시판 테이블 정보 추가
INSERT INTO TBL_BOARD(BOARD_NO,BOARD_NAME,BOARD_DETAIL,BOARD_REGIST_DATE,BOARD_VIEWS,BOARD_REVISE_DATE,BOARD_STAT,BOARD_DELETE_DATE,BOARD_SORT,BOARD_SECRET,MEMBER_NO,CATEGORY_CODE,REF_BOARD_NO)
    VALUES(SEQ_TBL_BOARD_NO.NEXTVAL,'','','','','','','','','',0,0,0);
	
	
commit;
	

-- 펫시터 테이블 정보 추가
INSERT INTO TBL_PET_SITTER(PET_MEMBER_NO,PRICE,REGIST_AVA_DT,REGIST_AVA_TM,REGIST_DT,ACCOUNT_HOLDER,BANK,ACCOUNT_NUM,ROOM,SMOKING_STATUS,ACTI_EXPERIENCE,DAY_CARE,ACTIVITY_AVA_NUM,DOG_EXIST,APPLICATION_REASON,FAMILY_NUM,JOB,INTRODUCE,EDUCATION_COMPLETE,CARE_ENV,FIRST_COMMENTS)
    VALUES(0,0,'','','','','',0,'','','','',0,'','',0,'','','','','');
	

commit;
	

-- 찜한펫시터 테이블 정보 추가
INSERT INTO TBL_FAVORITE_SITTER(MEMBER_NO,PET_MEMBER_NO)
    VALUES(0,0);
	

commit;
	

-- 펫시터예약일자 테이블 정보 추가
INSERT INTO TBL_SITTER_SCHEDULE(PET_MEMBER_RES_NO,PET_MEMBER_REG_DAY,PET_MEMBER_STATUS,PET_MEMBER_NO)
    VALUES(SEQ_TBL_SITTER_SCHEDULE_NO.NEXTVAL,'','',0);
	

commit;
	

-- 정산 테이블 정보 추가
INSERT INTO TBL_ADJUSTMENT(PETMEMBER_SUM_NO,TOTAL_PAYMENT,TOTAL_SUM,SUM_COMPLETE_DAY,STARTDAY,ENDDAY,ACCOUNT_HOLDER,BANK_NM,ACCOUNT,PET_MEMBER_NO)
    VALUES(SEQ_TBL_ADJUSTMENT_NO.NEXTVAL,0,0,'','','','','',0,0);
	

commit;
	

-- 예약 테이블 정보 추가
INSERT INTO TBL_RESERVATION(RESERV_CODE,RESERV_START,RESERV_END,RESERV_STATUS,RESERV_ACCUM,RESERV_APP_DATE,RESERV_UPDATE_DATE,RESERV_AMOUNT,MEMBER_NO,PET_MEMBER_NO)
    VALUES(SEQ_TBL_RESERVATION_NO.NEXTVAL,'','','',0,'','',0,0,0);
	

commit;
	

-- 결제 테이블 정보 추가
INSERT INTO TBL_PAYMENT(PAY_RECORD_CODE,PAY_PRICE,PAY_STAT,PAY_DATE,PAYMENT_CODE,RESERV_CODE)
    VALUES(SEQ_TBL_PAYMENT_NO.NEXTVAL,0,'','',0,0);
	

commit;
	

-- 환불 테이블 정보 추가
INSERT INTO TBL_REFUND(RESERV_CODE,RESERV_CCL_DATE,RESERV_CCL_DESC,RESERV_CCL_DESC_DE)
    VALUES(0,'','','');
	

commit;
	

-- 후기 테이블 정보 추가
INSERT INTO TBL_REVIEW(REV_CODE,REV_SCORE,REV_CONTENT,REV_CREATED_DATE,REV_MODIFY_DATE,REV_STATUS,REV_DELETE_DATE,MEMBER_NO,PET_MEMBER_NO,RESERV_CODE)
    VALUES(SEQ_TBL_REVIEW_NO.NEXTVAL,0,'','','','','',0,0,0);
	

commit;
	

-- 첨부파일 테이블 정보 추가
INSERT INTO TBL_FILE(FILE_NO,FILE_NAME,FILE_PATH_NAME,FILE_SAVE_NAME,FILE_EXT_NAME,FILE_VOLUME,FILE_TYPE,PET_CODE,BOARD_NO,REV_CODE,MEMBER_NO,PET_MEMBER_NO)
    VALUES(SEQ_TBL_FILE_NO.NEXTVAL,'','','','',0,'',0,0,0,0,0);
	
	
commit;

