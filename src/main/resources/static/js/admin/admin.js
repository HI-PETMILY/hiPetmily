/* 네비바 + 메뉴바 */
$(document).ready(function () {
    // 초기 상태 설정
    $('.bar-menu').hide(); // 모든 관리 메뉴 숨김
    $('#site-operation').show(); // 사이트 운영 관리 메뉴 보이기
    $('#nav-home').addClass('show active'); // 사이트 운영 탭 활성화
    $('#nav-home-tab').addClass('active');

    // 탭 메뉴 클릭 시 관리 메뉴 변경
    $('.nav-link').on('click', function () {
        // 모든 관리 메뉴 숨김
        $('.bar-menu').hide();

        // 클릭한 탭에 따라 해당 관리 메뉴 보이기
        if ($(this).attr('id') === 'nav1-tab') {
            $('#site-operation').show(); // 사이트 운영 관리 메뉴 보이기
        } else if ($(this).attr('id') === 'nav2-tab') {
            $('#sns-mail').show(); // SNS/메일 관리 메뉴 보이기
        } else if ($(this).attr('id') === 'nav3-tab') {
            $('#statistics').show(); // 통계 관리 메뉴 보이기
        } else if ($(this).attr('id') === 'nav4-tab') {
            //     $('#environment-settings').show(); // 환경설정 관리 메뉴 보이기
        }

        // 활성화된 탭 설정
        $('.tab-pane').removeClass('show active');
        $('.nav-link').removeClass('active');
        $($(this).attr('data-bs-target')).addClass('show active');
        $(this).addClass('active');

    });
});

// 맨위 검색 체크 박스 전체 선택/해제
function selectAll(selectAll) {
    const checkboxes = document.querySelectorAll('input[name="rating"]');
    const entireCheckbox = document.getElementById('entire');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    });

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', function () {
            //전체 체크에서 개별 체크를 눌렀을때 전체 체크 풀림
            if (!this.checked) {
                entireCheckbox.checked = false;
            }
        });
    });
}

/*==============팝업창*/
document.getElementById("passwordChangeButton").addEventListener("click", openPasswordChangePopup);

function openPasswordChangePopup() {
    // 사용자 클릭에 의한 팝업 열기
    var popup = window.open("popPasswordChange", "PasswordChangePopup", "width=750,height=800,left=650,top=180");
    if (!popup || popup.closed || typeof popup.closed == 'undefined') {
        // 팝업 창이 차단되었거나 팝업 창이 닫힌 경우 처리
        alert("팝업 창이 차단되었거나 팝업 창이 닫혔습니다. 팝업 차단을 확인하세요.");
    }
}

function openInquiryPopup() {
    // "1대1문의" 버튼을 눌렀을 때 팝업 창을 띄우는 코드를 작성합니다.
    var popup =window.open("popInquiry", "InquiryPopup", "width=750, height=800, left=650, top=180");
}

function openManagementPopup() {
    // "관리" 버튼을 눌렀을 때 팝업 창을 띄우는 코드를 작성합니다.
    var popup =window.open("popManagement", "ManagementPopup", "width=750, height=800, left=650, top=180");
}


/* =====================검색 부분 ==========================*/
function searchMembers() {
    var searchValue = document.getElementById("searchInput").value; // 검색 입력란의 값을 가져옵니다.

    // "rating" 이름을 가진 체크박스들의 NodeList를 만듭니다 (등급으로 필터링하려고 가정).
    var checkboxes = document.querySelectorAll("input[id=entire]");

    var checkCommon = document.getElementById("common");
    var checkPetSitter = document.getElementById("petSitter");
    var checkList = 0;

    if(searchValue == "") {
        alert("입력된 검색어가 없어 회원등급으로 회원목록을 띄웁니다.");
        if(checkCommon = true && checkPetSitter == false) checkList = 1;
        if(checkCommon = false && checkPetSitter == true) checkList = 2;
        if(checkCommon = true && checkPetSitter == true) checkList = 3;
    }
    // alert(checkCommon.checked, checkPetSitter.checked)
    // 체크박스를 순환합니다.
    // for (var i = 0; i < checkboxes.length; i++) {
    //     alert(checkboxes)
    //     // 체크박스가 선택되었는지 확인합니다.
    //     if (checkboxes[i].checked) {
    //
    //         console.log("켁박", checkboxes[i].value);
    //         // 선택된 체크박스 값에 기반한 작업을 수행할 수 있습니다.
    //         // 예를 들어, 선택한 체크박스 값에 따라 "rating" 값을 사용하여 필터링할 수 있습니다.
    //
    //         // 이 예제에서는 선택한 체크박스 값들을 콘솔에 표시합니다.
    //         // console.log("선택한 체크박스 값: " + checkboxes[i].value);
    //     }
    // }

    // if (searchValue == "") {
    //     // 검색 입력란이 비어 있으면 알림 메시지를 띄우고 검색 요청을 중지합니다.
    //     alert("입력된 검색어가 없어 회원등급으로 회원목록을 띄웁니다.");
    //     // return false; // 검색 요청을 중지하기 위해 false를 반환합니다.???????? 항상 false를 반환하면 알럿뜨고 지워지면서 에러나잖아 멍청한 챗지피티야!!!!!!!!!!!!!!1
    // }
    //
    // var searchCondition = document.getElementById("searchCondition").value;
    //
    // ////////////////////////////////////////////////////////////
    // // [작성중] memberNo(회원번호)로 검색할 경우 알럿창 띄움 + 페이지 넘김 방지 처리해야함
    // var searchValueType = console.log(searchValue);
    // if(searchValue != "" && searchCondition == "memberNo" && searchValueType != "number") {
    //     alert("회원 번호는 숫자형태만 입력가능합니다.");
    // }
    // ////////////////////////////////////////////////////////////
    //
    //

    // 이제 'searchValue'를 사용하여 부분 문자열 검색 결과를 필터링할 수 있습니다.
    // 검색 조건을 서버로 보내거나 페이지를 업데이트하는 등의 작업을 수행할 수 있습니다.
    // 여기서는 'searchValue'를 로깅하겠습니다.
    // console.log("검색어: " + searchValue);

    // 필요한 경우 추가 작업을 수행할 수 있으며, 실제 검색을 위해서는 검색 조건을 서버로 보내고 데이터베이스 쿼리를 처리해야 합니다.



}


















