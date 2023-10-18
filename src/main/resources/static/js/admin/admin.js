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

// 회원 리스트 체크 박스 전체 선택/해제=========================
// 'selectAll' 함수를 정의함. 체크박스 전체 선택/해제.
function selectAll(selectAll) {

    // 'chkList' 이름 속성을 가진 모든 입력(체크박스) 요소를 선택합니다.
    const checkboxes = document.querySelectorAll('input[name="chkList"]');

    // 'listChkAll' id를 가진 체크박스 요소를 선택합니다.
    const listChkAllCheckbox = document.getElementById('listChkAll');

    // 'checkboxes' 배열의 각 요소에 대한 반복 작업을 수행합니다.
    checkboxes.forEach((checkbox) => {
        // 'selectAll' 체크박스의 상태에 따라 개별 체크박스의 'checked' 속성을 설정합니다.
        checkbox.checked = selectAll.checked;
    });

    // 'checkboxes' 배열의 각 요소에 대한 반복 작업을 다시 수행합니다.
    checkboxes.forEach((checkbox) => {
        // 개별 체크박스의 'change' 이벤트에 대한 리스너를 추가합니다.
        checkbox.addEventListener('change', function () {

            // 개별 체크박스의 상태가 해제되면,
            if (checkboxes.checked == false) {
                // 'listChkAll' 체크박스를 해제 상태로 설정합니다.
                listChkAllCheckbox.checked = false;
            }
        });
    });
}


//회원 상세정보 관리 팝업창 반응형 메뉴바 ========================
function onMenuClick() {
    var navbar = document.getElementById('navigation-bar');
    var responsive_class_name = 'responsive'

    navbar.classList.toggle(responsive_class_name)
}


//팝업창 메뉴바 ======================
// 내용 전환을 위한 코드
function showContent(contentId) {
    // 모든 내용 섹션을 숨김
    document.getElementById('content-member').style.display = 'none';
    document.getElementById('content-community').style.display = 'none';
    document.getElementById('content-personalInquiry').style.display = 'none';


    // 선택한 내용 섹션을 표시
    document.getElementById('content-' + contentId).style.display = 'block';
}



