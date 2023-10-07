'use strict';

$(document).ready(function(){

    timeSelectBoxAdd();
    dogSelectBoxAdd();

    // $("#startTime").change(function(){
    //     // value값 가져오기
    //     var val = $("#startTime :selected").val();
    //
    //     console.log(val);
    // });

});


function timeSelectBoxAdd() {

    let strHours = '';
    let time = '';

    for(let i = 0 ; i < 24; i++){
        if(i == 9){
            time = '0'+i;
            strHours += '<option value="'+time+'00'+'" selected>'+time+'시 00분</option>';
            strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
        }else if( i < 10){
            time = '0'+ i;
            strHours += '<option value="'+time+'00'+'">'+time+'시 00분</option>';
            strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
        }else{
            time = i;
            strHours += '<option value="'+time+'00'+'">'+time+'시 00분</option>';
            strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
        }
    }

    $("#startTime").html(strHours);
    $("#endTime").html(strHours);

}

function dogSelectBoxAdd() {

    let dogSelect = '';

    for(let i = 0 ; i < 2; i++){
        if(i == 0){
            dogSelect += '<option value="'+'0'+i+'">선택하기</option>';
        } else {
            dogSelect += '<option value="'+'0'+i+'">토리</option>';
        }
    }
    $("#dogSelect").html(dogSelect);
}




/* 캘린더(flatpickr) API 부분 ---------------------------- */

const startDateInput = document.getElementById('startDate');
const endDateInput = document.getElementById('endDate');



flatpickr(startDateInput, {
    dateFormat: 'Y-m-d', // 날짜 및 시간 형식 설정 (예: 2023-09-12)
    enableTime: false,        // 시간 선택 활성화
    minDate: 'today',        // 오늘 이전 날짜 선택 비활성화
    // defaultDate: '시작 날짜',
    disable: ['2023-10-12', '2023-10-15'], // 특정 날짜 비활성화
    locale: 'ko',            // 한국어로 지역화
    onOpen: function() {
        // 위젯이 열릴 때 실행할 코드
    },
    onChange: function() {
        // 날짜가 변경될 때 실행할 코드
    },
    onClose: function() {
        // 위젯이 닫힐 때 실행할 코드
        const selectStartDate = document.getElementById('startDate').value;

        flatpickr(endDateInput, {
            dateFormat: 'Y-m-d', // 날짜 및 시간 형식 설정 (예: 2023-09-12 15:30)
            enableTime: false,        // 시간 선택 활성화
            minDate: selectStartDate,        // 오늘 이전 날짜 선택 비활성화
            // defaultDate: '종료 날짜',    // 초기 날짜 설정 (현재 날짜와 시간)
            disable: ['2023-10-12', '2023-10-15'], // 특정 날짜 비활성화
            locale: 'ko',            // 한국어로 지역화
            onOpen: function(selectedDates, dateStr, instance) {
                // 위젯이 열릴 때 실행할 코드
            },
            onClose: function(selectedDates, dateStr, instance) {
                // 위젯이 닫힐 때 실행할 코드
            },
            onChange: function(selectedDates, dateStr, instance) {
                // 날짜가 변경될 때 실행할 코드
            },
        });
    },

});
/* 캘린더(flatpickr) API 부분 ---------------------------- */

/* 캘린더(fullcalendar) API 부분 ---------------------------- */

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var today = new Date().toISOString().split('T')[0];     // 현재 날짜
    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            start: 'prev',
            center: 'title',
            end: 'next',
        },
        height: 450,
        initialView: 'dayGridMonth',
        navLinks: false,
        editable: false,
        fixedWeekCount: false,
        locale : 'ko',
        validRange: { start: today },
        dayCellContent: function (info) {
            var number = document.createElement("a");
            number.classList.add("fc-daygrid-day-number");
            number.innerHTML = info.dayNumberText.replace("일", '').replace("日","");
            if (info.view.type === "dayGridMonth") {
                return {
                    html: number.outerHTML
                };
            }
            return {
                domNodes: []
            };
        },
        events: [
            {
                start: '2023-10-10',
                end: '2023-10-10',
                display: 'background',
                backgroundColor: '#2d72f1'
            },
            {
                start: '2023-10-13',
                end: '2023-10-13',
                display: 'background',
                backgroundColor: '#2d72f1'
            }
        ],

    });
    calendar.render();

});

/* 캘린더(fullcalendar) API 부분 ---------------------------- */

/* 지도 API 부분 ---------------------------- */

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.56710, 126.97633), // 지도의 중심좌표
        level: 2, // 지도의 확대 레벨
        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
    };

// 지도를 생성한다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 지도에 확대 축소 컨트롤을 생성한다
var zoomControl = new kakao.maps.ZoomControl();

// 지도의 우측에 확대 축소 컨트롤을 추가한다
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// 지도에 마커를 생성하고 표시한다
var marker = new kakao.maps.Marker({
    position: new kakao.maps.LatLng(37.56682, 126.97865), // 마커의 좌표
    map: map // 마커를 표시할 지도 객체
});

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('서울특별시 종로구 인사동길 12', function(result, status) {

    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    }
});

/* 지도 API 부분 ---------------------------- */