'use strict';

$(function(){

    bannerImgCreate();

    flatpickrApi();
    timeSelectBoxAdd();
    dogSelectBoxAdd();

    getReservation();

    fullcalendarApi();
    kakaoMapApi();

});

function getReservation() {

    let startDate = "";
    let endDate = "";
    let dayCount = "";

    let startTime = "";
    let endTime = "";
    let timeCount = "";

    $("#startDate").change(function(){
        startDate = $("#startDate").val().replace(/\-/g, "");

        $("#dogSelect").val("00").prop("selected", true);
    });

    $("#endDate").change(function(){
        endDate = $("#endDate").val().replace(/\-/g, "");
        dayCount = endDate - startDate;

        $("#dogSelect").val("00").prop("selected", true);
    });


    $("#startTime").change(function(){
        // endTime을 startTime보다 큰값만 다시 그려줌

        let hour = "";
        let strHours = '';
        let time = '';

        startTime = $("#startTime :selected").val();

        if ( startTime.substring(0, 1) == 0 ) {
            hour = startTime.substring(1, 2);
        } else {
            hour = startTime.substring(0, 2);
        }

        strHours += '<option value="default" selected>시작 선택</option>';

        for(let i = 0 ; i < 24; i++){

            if (hour <= i) {
                if( i < 10 ){

                    time = '0'+ i;

                    if(startTime.substring(2, 3) == 3 && startTime.substring(1, 2) == i) {
                        strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
                    } else {
                        strHours += '<option value="'+time+'00'+'">'+time+'시 00분</option>';
                        strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
                    }
                } else {

                    time = i;

                    if(startTime.substring(2, 3) == 3 && startTime.substring(0, 2) == i) {
                        strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
                    } else {
                        strHours += '<option value="'+time+'00'+'">'+time+'시 00분</option>';
                        strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
                    }
                }
            }
        }

        $("#endTime").html(strHours);
        $("#dogSelect").val("00").prop("selected", true);
    });


    $("#endTime").change(function(){

        endTime = $("#endTime :selected").val();

        if (endTime == startTime) {
            alert("시작시간과 종료시간이 같습니다. 다시 선택해주세요.");
            $("#endTime").val("default").prop("selected", true);
        }

        let startTime_hour = startTime.substring(0,2);
        let endTime_hour = endTime.substring(0,2);

        let compareHour = endTime - startTime;
        let setHour = endTime_hour - startTime_hour;

        // 30분 반올림해서 +1시간
        if ( setHour != 0 && compareHour.toString().slice(-2) == 30 ) {
            setHour += 1;
        }

        // 1시간 미만일때 +1시간
        if (setHour == 0) {
            setHour = 1;
        }

        timeCount = setHour;

        $("#dogSelect").val("00").prop("selected", true);
    });

    $("#dogSelect").change(function(){

        if (!startDate || !endDate || !startTime || !endTime) {
            alert("날짜와 시간을 먼저 선택해주세요.");
            $("#dogSelect").val("00").prop("selected", true);
        } else {
            let dayCountTotal = dayCount * 35000;
            let timeCountTotal = timeCount * 2500;
            let vatTotal = (dayCountTotal + timeCountTotal) * 0.1;
            let total = dayCountTotal + timeCountTotal + vatTotal;

            $("#pay_day").html(dayCount + "박 : " + dayCountTotal.toLocaleString() + " 원");
            $("#pay_hour").html(timeCount + "시간 : " + timeCountTotal.toLocaleString() + " 원");
            $("#pay_vat").html("부가세 : " + vatTotal.toLocaleString() + " 원");
            $("#pay_total").html("총 합계 : " + total.toLocaleString() + " 원");

            let resStartDate = $("#startDate").val().concat(' ', startTime.slice(0,2) + ":" + startTime.slice(2,4) );
            let resEndDate = $("#endDate").val().concat(' ', endTime.slice(0,2) + ":" + endTime.slice(2,4) );

            $("#startDateTime").val(resStartDate);
            $("#endDateTime").val(resEndDate);
            $("#resDayCount").val(dayCount);
            $("#resTimeCount").val(timeCount);
            $("#resVat").val(vatTotal);
            $("#resTotalAmount").val(total);
            $("#resAppDate").val(new Date().toLocaleString());
            $("#resUpdateDate").val(new Date().toLocaleString());
        }

    });

}


function bannerImgCreate() {

    // 메인 배너 생성
    $(".psp_img_main").append("<img id='mainImg' src='/static_Image/petSitter/profile_sub"+1+".png'>");

    for (let i = 1; i < 7; i++) {

        // li 추가
        $(".psp_img_sub").append("<li class='psp_img_subImg'><img id='subImg_"+i+"' src='/static_Image/petSitter/profile_sub"+i+".png'></li>");

        // 서브 이미지 클릭 이벤트 추가
        $("#subImg_"+i).click(function () {
            $("#mainImg").attr("src", "/static_Image/petSitter/profile_sub"+i+".png");
        });

        // 서브 이미지 마우스오버 이벤트 추가
        $("#subImg_"+i).mouseover(function () {
            $("#subImg_"+i).css({ "transform" : "scale(1.05)", "zIndex" : "1", "transition" : "all 0.1s" });
        }).mouseout(function () {
            $("#subImg_"+i).css({ "transform" : "scale(1)", "zIndex" : "0", "transition" : "all 0.1s" });
        });

    }

}



function timeSelectBoxAdd() {

    let strHours = '';
    let time = '';

    strHours += '<option value="default" selected>시작 선택</option>';

    for(let i = 0 ; i < 24; i++){

        if( i < 10 ){
            time = '0'+ i;
            strHours += '<option value="'+time+'00'+'">'+time+'시 00분</option>';
            strHours += '<option value="'+time+'30'+'">'+time+'시 30분</option>';
        } else {
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
            dogSelect += '<option value="'+'0'+i+'">---- 선택하기 ----</option>';
        } else {
            dogSelect += '<option value="'+'0'+i+'">중소형(15kg미만)</option>';
        }
    }
    $("#dogSelect").html(dogSelect);
}




/* 캘린더(flatpickr) API 부분 ---------------------------- */
function flatpickrApi() {

    // url에 petMemberNo 를 받아옴
    const urlParams = new URL(location.href).searchParams;
    const urlPetMemberNo = urlParams.get('petMemberNo');
    let scheduleArr;

    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');

    // 예약불가 스케줄 비동기
    $.ajax({
        type: "POST"
        , url: "/petSitter/petSitterSchedule"
        , dataType: "JSON"
        , contentType: "application/json; charset=utf-8"
        , data : JSON.stringify({
            petMemberNo :  urlPetMemberNo
        })
        , async : false
        , success: function (data) {
            let events = [];

            $.each(data, function (index, item) {
                events.push( item.petMemberResDay );
            });

            scheduleArr = events;
        }
    });

    flatpickr(startDateInput, {
        dateFormat: 'Y-m-d' // 날짜 및 시간 형식 설정 (예: 2023-09-12)
        , enableTime: false        // 시간 선택 활성화
        , minDate: 'today'        // 오늘 이전 날짜 선택 비활성화
        // , defaultDate: '시작 날짜',
        , disable: scheduleArr // 특정 날짜 비활성화
        , locale: 'ko',            // 한국어로 지역화
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
                disable: scheduleArr, // 특정 날짜 비활성화
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
}

/* fullcalendar API 부분 ---------------------------- */
function fullcalendarApi() {

    // url에 petMemberNo 를 받아옴
    const urlParams = new URL(location.href).searchParams;
    const urlPetMemberNo = urlParams.get('petMemberNo');

    let calendarEl = document.getElementById('calendar');
    let today = new Date().toISOString().split('T')[0];     // 현재 날짜
    let calendar = new FullCalendar.Calendar(calendarEl, {
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
            let number = document.createElement("a");
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
        events: function(info, successCallback, failureCallback) {

            $.ajax({
                type: "POST"
                , url: "/petSitter/petSitterSchedule"
                , dataType: "JSON"
                , contentType: "application/json; charset=utf-8"
                , data : JSON.stringify({
                    petMemberNo :  urlPetMemberNo
                })
                , async : false
                , success: function (data) {
                    let events = [];

                    $.each(data, function (index, item) {
                        events.push({
                            start : item.petMemberResDay
                            ,display: 'background'
                            ,backgroundColor: '#f15b2d'
                        });
                    });

                    successCallback(events);
                }
            });

        }
    });
    calendar.render();
}


/* 지도 API 부분 ---------------------------- */
function kakaoMapApi() {

    // url에 petMemberNo 를 받아옴
    const urlParams = new URL(location.href).searchParams;
    const urlPetMemberNo = urlParams.get('petMemberNo');
    let petAddress;

    $.ajax({
        type: "POST"
        , url: "/petSitter/petSitterAddress"
        , dataType: "JSON"
        , contentType: "application/json; charset=utf-8"
        , data : JSON.stringify({
            petMemberNo :  urlPetMemberNo
        })
        , async : false
        , success: function (data) {
            petAddress = data.address;
        }
    });

    let mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.56710, 126.97633), // 지도의 중심좌표
            level: 2, // 지도의 확대 레벨
            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
        };

    // 지도를 생성한다
    let map = new kakao.maps.Map(mapContainer, mapOption);

    // 지도에 확대 축소 컨트롤을 생성한다
    let zoomControl = new kakao.maps.ZoomControl();

    // 지도의 우측에 확대 축소 컨트롤을 추가한다
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 지도에 마커를 생성하고 표시한다
    let marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(37.56682, 126.97865), // 마커의 좌표
        map: map // 마커를 표시할 지도 객체
    });

    // 주소-좌표 변환 객체를 생성합니다
    let geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch( petAddress, function(result, status) {

        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {

            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            let marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });

}
