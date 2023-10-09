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


// 체크박스 전체 선택/해제
function selectAll(selectAll) {
    const checkboxes = document.querySelectorAll('input[name="rating"]');
    const entireCheckbox = document.getElementById('entire');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    });

    // 개별 체크박스를 선택할 때 전체 체크박스가 풀리도록 추가
    if (!selectAll.checked) {
        entireCheckbox.checked = false;
    }
}






