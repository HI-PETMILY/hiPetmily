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

/* 검색 부분 ==========================*/
function searchMembers() {
    var searchValue = document.getElementById("searchInput").value; // 검색 입력란의 값을 가져옵니다.

    if (searchValue === "") {
        // 검색 입력란이 비어 있으면 알림 메시지를 띄우고 검색 요청을 중지합니다.
        alert("다시 확인 후 검색어를 입력해 주세요.");
        return false; // 검색 요청을 중지하기 위해 false를 반환합니다.
    }
}



















