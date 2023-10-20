![image](https://github.com/HI-PETMILY/hiPetmily/assets/23203621/2cb2f960-5cca-41a1-b7e0-69c1d42448fd)# Sub-Project-hiPetmily
스프링부트_애왼동물 돌봄 펫밀리 프로젝트

- 펫밀리 프로젝트는 반려동물과 함께하는 행복한 일상에 필요한 중개 서비스를 만들어가는 팀입니다. 


# 🖥️ 프로젝트 소개
- 전세계 모든 반려인들의 가장 큰 고민거리는 무엇일까요?
- 여행, 외출시 혼자 남겨진 반려동물에 대한 걱정과 믿고 맡길 곳이 없다는 점입니다
- 반려동물을 가족처럼 정성스럽게 돌봐줄 '누군가'가 있다면,
- 반려동물과 떨어져 있어도 함께하는 듯한 기분을 느끼게 하는 '기술'이 존재한다면,
- 반려인들이 더 행복한 삶을 지낼 수 있을 것 이라는 믿음에서 펫밀리가 탄생하였습니다
- 즉 반려동물 보호자와 펫시터간의 관리를 용이하게 할 수 있도록 돕기위함이 펫밀리의 Identity 입니다.



# ⌛ 개발 기간
- 23.9.25 ~ 23.10.20
  
  ![image](https://github.com/HI-PETMILY/hiPetmily/assets/23203621/ff648189-a849-4032-9b6f-22cfa1d61e20)


## 🤼‍♀️ 멤버 구성
- 팀원 : 김형수(https://github.com/brorsoo) - 메인페이지, 헤더&푸터, 펫시터 등록페이지, 펫시터 상세페이지, 예약
- 팀원 : 이소정(https://github.com/spehil) - 펫시터 목록 페이지, 펫시터 마이페이지
- 팀원 : 박미림(https://github.com/HI-SAYA) - 회원가입, 회원 정보 수정, 회원탈퇴, 지난 예약 조회(상세+결제내역), 마이페이지
- 팀원 : 박유리(https://github.com/qkrdbfl) - 회원관리, 관리자페이지
- 팀원 : 정수현(https://github.com/wjdtngus3) - 게시판, 고객센터페이지
- 팀원 : 주서현(https://github.com/jooseohyeon) - 로그인, id/pwd 찾기, 펫 프로필 관리, 후기 조회, 진행중인 예약 조회, 마이페이지

## ⚙️ 개발환경
- 운영체제 : Windows 10 64bit
- 버전관리 : Github
- 개발도구 : IntelliJ IDEA
- 빌드툴 : Gradle
- 프로그래밍 언어 : Java 11 JDK11, javaScript
- 마크업 언어 : HTML, CSS3
- 라이브러리 : jquery-3.6
- 프레임워크 : Spring Boot 2.7.16 , Mybatis
- 데이터베이스 : Oracle 18c
- SQL 개발 툴 : SQL Developer
- 모델링 툴 : DA#Modeler5

# 📌 주요 기능
메인 페이지 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5(main-page))
- 각종 화면 이동
- 배너 슬라이드
- 세션에 따라 Header 정보 변경
- 최신 리뷰 3개 조회

로그인 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5(Login))
- DB값 검증
- ID찾기, 임시비밀번호 생성

회원가입 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5%EC%86%8C%EA%B0%9C(Member))
- ID 중복 체크
- 닉네임 중복 체크
- 입력 문자 제한
- 약관 미동의시 가입 버튼 비활성화
- 주소API

마이 페이지 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5%EC%86%8C%EA%B0%9C())
- 내 정보 확인(회원 정보 수정(주소, 닉네임)
- 펫 프로필 확인(등록, 수정, 삭제)
- 후기 조회
- 진행중인 예약(조회)
- 지난 예약 조회(상세+결제 조회)


1대1문의 및 공지사항 고객센터페이지 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5(5))
- 공지사항 목록/등록/수정/조회 확인
- 1:1문의 목록/등록/수정/조회 확인 
- 카카오톡 1:1상담 API


 펫시터 마이페이지 -  [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%ED%8E%AB%EC%8B%9C%ED%84%B0-%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80))
  - 펫시터 프로필 조회 
 

 펫시터 목록 조회 -  [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5%EC%86%8C%EA%B0%9C(%ED%8E%AB%EC%8B%9C%ED%84%B0-%EB%AA%A9%EB%A1%9D-%EC%A1%B0%ED%9A%8C))
- 펫시터 목록 조회
- 해당 펫시터의 제일 최신 후기 1개만 화면에 조회   
  

펫시터 등록 페이지 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5(%ED%8E%AB%EC%8B%9C%ED%84%B0-%EB%93%B1%EB%A1%9D))
- 배너 이미지 첨부파일 등록
- 입력하고싶은 태그, 경력교육 추가
- 예약일정 등록(flatpickrAPI 캘린더)

펫시터 상세 페이지 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5(%ED%8E%AB%EC%8B%9C%ED%84%B0-%EC%83%81%EC%84%B8))
- 펫시터 등록시 첨부한 이미지 출력
- 펫시터별 각종 데이터 조회
- 후기 조회(3개 이상 더보기 버튼 작동)
- 예약하기(flatpickrAPI 캘린더)
- 예약가능 날짜 표시(fullcalendarAPI 캘린더)
- 펫시터 위치(카카오 map API)

관리자 페이지 - [상세보기-WIKI이동](https://github.com/HI-PETMILY/hiPetmily/wiki/%EC%A3%BC%EC%9A%94%EA%B8%B0%EB%8A%A5%EC%86%8C%EA%B0%9C(admin))
- 가입한 회원들 조회,수정,삭제
- 회원에게 적립금 주기
- 펫시터 지원한 회원 조회, 지원서 조회


