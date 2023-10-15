'use strict';

$(function(){

    imgSelect();
    addTagInput();
    addCareerInput();
    flatpickrApi();

});

// 이미지 선택 이벤트
function imgSelect() {

    /* div image area 요소 */
    const imageArea = document.querySelectorAll(".sitterReg_imgBox");
    /* input type=file 요소 */
    const fileElements = document.querySelectorAll("[type=file]");
    /* div 클릭 시 open 함수 동작하여 input type=file 클릭 */
    imageArea.forEach(item => item.addEventListener('click', open));
    /* 파일 첨부가 발생하는 상황에 preview 함수 동작 */
    fileElements.forEach(item => item.addEventListener('change', preview));

    function open() {
        /* 어떤 div인지 알아보기 위한 부분 */
        const index = Array.from(imageArea).indexOf(this);
        fileElements[index].click();
    }

    function preview() {

        const index = Array.from(fileElements).indexOf(this);

        /* 첨부 된 파일이 존재한다면 */
        if(this.files && this.files[0]) {
            /* preview를 만들때 사용하는 것 FileReader() */
            const reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            /* 파일 로드 후 동작하는 이벤트 설정 */
            reader.onload = function () {
                imageArea[index].innerHTML = `<img class='sitterReg_img' src='${reader.result}'>`;
            }
        }
    }

}

function addTagInput() {

    $('.tagBtnAdd').click (function () {
        $('.tag_buttons').append (
            '<input type="text" name="tagContent"><input type="button" class="tagBtnRemove" value="삭제"><br>'
        );
        $('.tagBtnRemove').on('click', function () {
            $(this).prev().remove ();
            $(this).next ().remove ();
            $(this).remove ();
        });
    });
}

function addCareerInput() {

    $('.careerBtnAdd').click (function () {
        $('.career_buttons').append (
            '<input type="text" name="careerContent"><input type="button" class="careerBtnRemove" value="삭제"><br>'
        );
        $('.careerBtnRemove').on('click', function () {
            $(this).prev().remove ();
            $(this).next ().remove ();
            $(this).remove ();
        });
    });
}


/* 캘린더(flatpickr) API 부분 ---------------------------- */
function flatpickrApi() {

    const startDateInput = document.getElementById('flatpickrDate');

    flatpickr(startDateInput, {
        dateFormat: 'Y-m-d' // 날짜 및 시간 형식 설정 (예: 2023-09-12)
        , mode: "multiple"
        , enableTime: false        // 시간 선택 활성화
        , minDate: 'today'        // 오늘 이전 날짜 선택 비활성화
        , inline: true
        , locale: 'ko',            // 한국어로 지역화
        onOpen: function() {
            // 위젯이 열릴 때 실행할 코드
        },
        onChange: function() {
            // 날짜가 변경될 때 실행할 코드
        },
        onClose: function() {

        },
    });
}