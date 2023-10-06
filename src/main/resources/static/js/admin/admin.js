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

//체크박스 전체 선택/해제
function selectAll(selectAll)  {
    const checkboxes
        = document.getElementsByName('rating');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}

//================= 회원 리스트 표 ==========================
//체크박스 전체 선택 클릭 이벤트
function allChecked(target){

    //전체 체크박스 버튼
    const checkbox = document.getElementById('allCheckBox');

    //전체 체크박스 버튼 체크 여부
    const is_checked = checkbox.checked;

    //전체 체크박스 제외한 모든 체크박스
    if(is_checked){
        //체크박스 전체 체크
        chkAllChecked()
    }

    else{
        //체크박스 전체 해제
        chkAllUnChecked()
    }
}

//자식 체크박스 클릭 이벤트
function chkClicked(){

    //체크박스 전체개수
    const allCount = document.querySelectorAll(".chk").length;

    //체크된 체크박스 전체개수
    const query = 'input[name="chk"]:checked'
    const selectedElements = document.querySelectorAll(query)
    const selectedElementsCnt = selectedElements.length;

    //체크박스 전체개수와 체크된 체크박스 전체개수가 같으면 전체 체크박스 체크
    if(allCount == selectedElementsCnt){
        document.getElementById('allCheckBox').checked = true;
    }

    //같지않으면 전체 체크박스 해제
    else{
        document.getElementById('allCheckBox').checked = false;
    }
}

//체크박스 전체 체크
function chkAllChecked(){
    document.querySelectorAll(".chk").forEach(function(v, i) {
        v.checked = true;
    });
}

//체크박스 전체 체크 해제
function chkAllUnChecked(){
    document.querySelectorAll(".chk").forEach(function(v, i) {
        v.checked = false;
    });
}


/*==============모달 창*/
const btn = document.getElementById('popupBtn');
const modal = document.getElementById('modalWrap');
const closeBtn = document.getElementById('closeBtn');

btn.onclick = function() {
    modal.style.display = 'block';
}
closeBtn.onclick = function() {
    modal.style.display = 'none';
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none"; /*  모달 밖에 누르면 꺼지게 */
    }
}