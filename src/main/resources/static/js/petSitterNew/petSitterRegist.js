'use strict';

$(function(){

    addTagInput();
    addCareerInput();
    flatpickrApi();

});

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