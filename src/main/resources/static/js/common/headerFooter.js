'use strict';

window.onload = function () {

    const header = document.getElementById("header");
    const classList = header.classList;

    // 로고 버튼
    if(document.getElementById("headerLogImg")) {

        const $headerLogImg = document.getElementById("headerLogImg");

        $headerLogImg.addEventListener("click", (event) => {

            location.href = "/";
        });

    }

    // 타이틀 버튼
    if(document.getElementById("headerTitle")) {

        const $headerTitle = document.getElementById("headerTitle");

        if( classList.contains("white") ) {
            $headerTitle.style.color = "#364f6b";
        } else {
            $headerTitle.style.color = "white";
        }

        $headerTitle.addEventListener("click", (event) => {

            location.href = "/";
        });

    }

    // 공지사항 버튼
    if(document.getElementById("headerBtn1")) {

        const $headerBtn1 = document.getElementById("headerBtn1");

        $headerBtn1.addEventListener("click", (event) => {

            location.href = "/board/notice/list";    // 추후 연결
        });

        $headerBtn1.addEventListener("mouseover", (event) => {

            $headerBtn1.style.color = "#81BDF1";
            $headerBtn1.style.transform = "scale(1.07)";
            $headerBtn1.style.zIndex = "1";
            $headerBtn1.style.transition = "all 0.1s";
        });

        $headerBtn1.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerBtn1.style.color = "#364f6b";
            } else {
                $headerBtn1.style.color = "white";
            }

            $headerBtn1.style.transform = "scale(1)";
            $headerBtn1.style.zIndex = "0";
            $headerBtn1.style.transition = "all 0.1s";
        });

    }

    // 펫시터 찾기 버튼
    if(document.getElementById("headerBtn2")) {

        const $headerBtn2 = document.getElementById("headerBtn2");

        $headerBtn2.addEventListener("click", (event) => {

            location.href = "/";    // 추후 연결
        });

        $headerBtn2.addEventListener("mouseover", (event) => {

            $headerBtn2.style.color = "#81BDF1";
            $headerBtn2.style.transform = "scale(1.07)";
            $headerBtn2.style.zIndex = "1";
            $headerBtn2.style.transition = "all 0.1s";
        });

        $headerBtn2.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerBtn2.style.color = "#364f6b";
            } else {
                $headerBtn2.style.color = "white";
            }

            $headerBtn2.style.transform = "scale(1)";
            $headerBtn2.style.zIndex = "0";
            $headerBtn2.style.transition = "all 0.1s";
        });

    }

    // 펫시터 지원 버튼
    if(document.getElementById("headerBtn3")) {

        const $headerBtn3 = document.getElementById("headerBtn3");

        $headerBtn3.addEventListener("click", (event) => {

            location.href = "/";    // 추후 연결
        });

        $headerBtn3.addEventListener("mouseover", (event) => {

            $headerBtn3.style.color = "#81BDF1";
            $headerBtn3.style.transform = "scale(1.07)";
            $headerBtn3.style.zIndex = "1";
            $headerBtn3.style.transition = "all 0.1s";
        });

        $headerBtn3.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerBtn3.style.color = "#364f6b";
            } else {
                $headerBtn3.style.color = "white";
            }

            $headerBtn3.style.transform = "scale(1)";
            $headerBtn3.style.zIndex = "0";
            $headerBtn3.style.transition = "all 0.1s";
        });

    }

    // 고객센터 버튼
    if(document.getElementById("headerBtn4")) {

        const $headerBtn4 = document.getElementById("headerBtn4");

        $headerBtn4.addEventListener("click", (event) => {

            location.href = "/board/info";    // 추후 연결
        });

        $headerBtn4.addEventListener("mouseover", (event) => {

            $headerBtn4.style.color = "#81BDF1";
            $headerBtn4.style.transform = "scale(1.07)";
            $headerBtn4.style.zIndex = "1";
            $headerBtn4.style.transition = "all 0.1s";
        });

        $headerBtn4.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerBtn4.style.color = "#364f6b";
            } else {
                $headerBtn4.style.color = "white";
            }

            $headerBtn4.style.transform = "scale(1)";
            $headerBtn4.style.zIndex = "0";
            $headerBtn4.style.transition = "all 0.1s";
        });

    }

    // 회원가입 버튼
    if(document.getElementById("headerJoin")) {

        const $headerJoin = document.getElementById("headerJoin");
        const $headerJoinImg = document.getElementById("headerJoinImg");

        $headerJoin.addEventListener("click", (event) => {

            location.href = "/member/mainRegist";    // 추후 연결
        });

        $headerJoin.addEventListener("mouseover", (event) => {

            $headerJoinImg.setAttribute("src","/static_Image/common/main/add_user_bu.png");

            $headerJoinImg.style.width = "62px";
            $headerJoinImg.style.height = "50px";
            $headerJoin.style.transform = "scale(1.07)";
            $headerJoin.style.zIndex = "1";
            $headerJoin.style.transition = "all 0.1s";

        });

        $headerJoin.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerJoinImg.setAttribute("src","/static_Image/common/main/add_user_bl.png");
            } else {
                $headerJoinImg.setAttribute("src","/static_Image/common/main/add_user_wh.png");
            }

            $headerJoinImg.style.width = "62px";
            $headerJoinImg.style.height = "52px";
            $headerJoin.style.transform = "scale(1)";
            $headerJoin.style.zIndex = "0";
            $headerJoin.style.transition = "all 0.1s";

        });

    }

    // 로그인 버튼
    if(document.getElementById("headerLogin")) {

        const $headerLogin = document.getElementById("headerLogin");
        const $headerLoginImg = document.getElementById("headerLoginImg");

        $headerLogin.addEventListener("click", (event) => {

            location.href = "/member/login";    // 추후 연결
        });

        $headerLogin.addEventListener("mouseover", (event) => {

            $headerLoginImg.setAttribute("src","/static_Image/common/main/login_bu.png");

            $headerLoginImg.style.width = "62px";
            $headerLoginImg.style.height = "50px";
            $headerLogin.style.transform = "scale(1.07)";
            $headerLogin.style.zIndex = "1";
            $headerLogin.style.transition = "all 0.1s";

        });

        $headerLogin.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerLoginImg.setAttribute("src","/static_Image/common/main/login_bl.png");
            } else {
                $headerLoginImg.setAttribute("src","/static_Image/common/main/login_wh.png");
            }

            $headerLoginImg.style.width = "62px";
            $headerLoginImg.style.height = "50px";
            $headerLogin.style.transform = "scale(1)";
            $headerLogin.style.zIndex = "0";
            $headerLogin.style.transition = "all 0.1s";
        });

    }

    // 마이페이지 버튼
    if(document.getElementById("headerMypage")) {

        const $headerMypage = document.getElementById("headerMypage");
        const $headerMypageImg = document.getElementById("headerMypageImg");

        $headerMypage.addEventListener("click", (event) => {

            location.href = "/member/mypage";    // 추후 연결
        });

        $headerMypage.addEventListener("mouseover", (event) => {

            $headerMypageImg.setAttribute("src","/static_Image/common/main/mypage_bu.png");

            $headerMypageImg.style.width = "62px";
            $headerMypageImg.style.height = "50px";
            $headerMypage.style.transform = "scale(1.07)";
            $headerMypage.style.zIndex = "1";
            $headerMypage.style.transition = "all 0.1s";

        });

        $headerMypage.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerMypageImg.setAttribute("src","/static_Image/common/main/mypage_bl.png");
            } else {
                $headerMypageImg.setAttribute("src","/static_Image/common/main/mypage_wh.png");
            }

            $headerMypageImg.style.width = "62px";
            $headerMypageImg.style.height = "50px";
            $headerMypage.style.transform = "scale(1)";
            $headerMypage.style.zIndex = "0";
            $headerMypage.style.transition = "all 0.1s";
        });

    }

    // 로그아웃 버튼
    if(document.getElementById("headerLogout")) {

        const $headerLogout = document.getElementById("headerLogout");
        const $headerLogoutImg = document.getElementById("headerLogoutImg");

        $headerLogout.addEventListener("click", (event) => {

            location.href = "/";    // 추후 연결
        });

        $headerLogout.addEventListener("mouseover", (event) => {

            $headerLogoutImg.setAttribute("src","/static_Image/common/main/logout_bu.png");

            $headerLogoutImg.style.width = "62px";
            $headerLogoutImg.style.height = "50px";
            $headerLogout.style.transform = "scale(1.07)";
            $headerLogout.style.zIndex = "1";
            $headerLogout.style.transition = "all 0.1s";

        });

        $headerLogout.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerLogoutImg.setAttribute("src","/static_Image/common/main/logout_bl.png");
            } else {
                $headerLogoutImg.setAttribute("src","/static_Image/common/main/logout_wh.png");
            }

            $headerLogoutImg.style.width = "62px";
            $headerLogoutImg.style.height = "50px";
            $headerLogout.style.transform = "scale(1)";
            $headerLogout.style.zIndex = "0";
            $headerLogout.style.transition = "all 0.1s";
        });

    }

    // 관리자 버튼
    if(document.getElementById("headerAdmin")) {

        const $headerAdmin = document.getElementById("headerAdmin");
        const $headerAdminImg = document.getElementById("headerAdminImg");

        $headerAdmin.addEventListener("click", (event) => {

            location.href = "/";    // 추후 연결
        });

        $headerAdmin.addEventListener("mouseover", (event) => {

            $headerAdminImg.setAttribute("src","/static_Image/common/main/admin_bu.png");
            $headerAdminImg.style.width = "62px";
            $headerAdminImg.style.height = "50px";
            $headerAdmin.style.transform = "scale(1.07)";
            $headerAdmin.style.zIndex = "1";
            $headerAdmin.style.transition = "all 0.1s";
        });

        $headerAdmin.addEventListener("mouseleave", (event) => {

            if( classList.contains("white") ) {
                $headerAdminImg.setAttribute("src","/static_Image/common/main/admin_bl.png");
            } else {
                $headerAdminImg.setAttribute("src","/static_Image/common/main/admin_wh.png");
            }

            $headerAdminImg.style.width = "62px";
            $headerAdminImg.style.height = "50px";
            $headerAdmin.style.transform = "scale(1)";
            $headerAdmin.style.zIndex = "0";
            $headerAdmin.style.transition = "all 0.1s";
        });

    }


    // Footer event --------------------------------------------------------
    // 로고 버튼
    if(document.getElementById("footerLogImg")) {

        const $footerLogImg = document.getElementById("footerLogImg");

        $footerLogImg.addEventListener("click", (event) => {

            location.href = "/";    // 추후 연결
        });

    }

    // 이용약관 버튼
    if(document.getElementById("footerBtn1")) {

        const $footerBtn1 = document.getElementById("footerBtn1");

        $footerBtn1.addEventListener("click", (event) => {

            location.href = "/board/terms";    // 추후 연결
        });

        $footerBtn1.addEventListener("mouseover", (event) => {

            $footerBtn1.style.transform = "scale(1.07)";
            $footerBtn1.style.zIndex = "1";
            $footerBtn1.style.transition = "all 0.1s";
            $footerBtn1.style.color = "#81BDF1";
        });

        $footerBtn1.addEventListener("mouseleave", (event) => {

            $footerBtn1.style.transform = "scale(1)";
            $footerBtn1.style.zIndex = "0";
            $footerBtn1.style.transition = "all 0.1s";
            $footerBtn1.style.color = "white";
        });

    }

    // 개인정보처리방침 버튼
    if(document.getElementById("footerBtn2")) {

        const $footerBtn2 = document.getElementById("footerBtn2");

        $footerBtn2.addEventListener("click", (event) => {

            location.href = "/board/policy";    // 추후 연결
        });

        $footerBtn2.addEventListener("mouseover", (event) => {

            $footerBtn2.style.transform = "scale(1.07)";
            $footerBtn2.style.zIndex = "1";
            $footerBtn2.style.transition = "all 0.1s";
            $footerBtn2.style.color = "#81BDF1";
        });

        $footerBtn2.addEventListener("mouseleave", (event) => {

            $footerBtn2.style.transform = "scale(1)";
            $footerBtn2.style.zIndex = "0";
            $footerBtn2.style.transition = "all 0.1s";
            $footerBtn2.style.color = "white";
        });

    }


    // 안전보상프로그램 버튼
    if(document.getElementById("footerBtn3")) {

        const $footerBtn3 = document.getElementById("footerBtn3");

        $footerBtn3.addEventListener("click", (event) => {

            location.href = "/board/safe";    // 추후 연결
        });

        $footerBtn3.addEventListener("mouseover", (event) => {

            $footerBtn3.style.transform = "scale(1.07)";
            $footerBtn3.style.zIndex = "1";
            $footerBtn3.style.transition = "all 0.1s";
            $footerBtn3.style.color = "#81BDF1";
        });

        $footerBtn3.addEventListener("mouseleave", (event) => {

            $footerBtn3.style.transform = "scale(1)";
            $footerBtn3.style.zIndex = "0";
            $footerBtn3.style.transition = "all 0.1s";
            $footerBtn3.style.color = "white";
        });

    }

    // 자주하는질문 버튼
    if(document.getElementById("footerBtn4")) {

        const $footerBtn4 = document.getElementById("footerBtn4");

        $footerBtn4.addEventListener("click", (event) => {

            location.href = "/board/faq";    // 추후 연결
        });

        $footerBtn4.addEventListener("mouseover", (event) => {

            $footerBtn4.style.transform = "scale(1.07)";
            $footerBtn4.style.zIndex = "1";
            $footerBtn4.style.transition = "all 0.1s";
            $footerBtn4.style.color = "#81BDF1";
        });

        $footerBtn4.addEventListener("mouseleave", (event) => {

            $footerBtn4.style.transform = "scale(1)";
            $footerBtn4.style.zIndex = "0";
            $footerBtn4.style.transition = "all 0.1s";
            $footerBtn4.style.color = "white";
        });

    }


}
