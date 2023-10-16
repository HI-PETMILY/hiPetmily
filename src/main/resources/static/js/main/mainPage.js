'use strict';

/* ============= 슬라이더 배너 시작 ============= */

// 슬라이드 전체 크기(width)
const $slide = document.querySelector(".slide");
let slideWidth = $slide.clientWidth;

// 좌우 버튼
const $prevBtn = document.querySelector(".slide_prev_button");
const $nextBtn = document.querySelector(".slide_next_button");

// 슬라이드 전체 선택
let slideBanners = document.querySelectorAll(".slide_banner");

// 슬라이드 개수
const maxSlide = slideBanners.length;

// 현재 슬라이드
let currSlide = 1;

// pagination 생성
const $pagination = document.querySelector(".slide_pagination");

for (let i = 0; i < maxSlide; i++) {

    if (i === 0) {
        $pagination.innerHTML += `<li class="active">•</li>`;
    } else {
        $pagination.innerHTML += `<li>•</li>`;
    }
}

// 생성된 pagination 리스트
const paginationItems = document.querySelectorAll(".slide_pagination > li");

// 무한 슬라이드를 위해 start, end 슬라이드 복제
const startSlide = slideBanners[ 0 ];
const endSlide = slideBanners[ slideBanners.length - 1 ];
const startElem = document.createElement("div");
const endElem = document.createElement("div");

// 생성된 div에 css 복제
startSlide.classList.forEach((c) => startElem.classList.add( c ));
startElem.innerHTML = startSlide.innerHTML;
endSlide.classList.forEach((c) => endElem.classList.add( c ));
endElem.innerHTML = endSlide.innerHTML;

// 각 복제한 엘리먼트 추가
slideBanners[ 0 ].before( endElem );
slideBanners[ slideBanners.length - 1 ].after( startElem );

// 슬라이드 전체 선택
slideBanners = document.querySelectorAll(".slide_banner");

// 1903 + 1 = 1904
let offset = slideWidth + currSlide;
slideBanners.forEach((banner) => {
    banner.setAttribute("style", `left: ${-offset}px`);
});

function nextMove() {

    currSlide++;
    // 마지막 슬라이드까지만
    if ( currSlide <= maxSlide ) {

        // 슬라이드를 이동시키기 위한 offset 계산
        const offset = slideWidth * currSlide;

        // 각 슬라이드 아이템의 left에 offset 적용
        slideBanners.forEach((banner) => {
            banner.setAttribute("style", `left: ${-offset}px`);
        });

        // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems.forEach((page) => page.classList.remove("active"));
        paginationItems[ currSlide - 1 ].classList.add("active");

    } else {
        // 무한 슬라이드(마지막 슬라이드시 다시 처음)
        currSlide = 0;

        let offset = slideWidth * currSlide;

        slideBanners.forEach((banner) => {
            banner.setAttribute("style", `transition: ${0}s; left: ${-offset}px`);
        });

        currSlide++;

        offset = slideWidth * currSlide;

        // 각 슬라이드 아이템의 left에 offset 적용
        setTimeout(() => {
            slideBanners.forEach((banner) => {
                banner.setAttribute("style", `transition: ${0.15}s; left: ${-offset}px`);
            });
        }, 0);

        // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems.forEach((page) => page.classList.remove("active"));
        paginationItems[ currSlide - 1 ].classList.add("active");
    }
}

function prevMove() {

    currSlide--;

    // 1번째 슬라이드 이하로 넘어가지 않게 하기 위해서
    if (currSlide > 0) {

        // 슬라이드를 이동시키기 위한 offset 계산
        const offset = slideWidth * currSlide;

        // 각 슬라이드 아이템의 left에 offset 적용
        slideBanners.forEach((banner) => {
            banner.setAttribute("style", `left: ${-offset}px`);
        });

        // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems.forEach((page) => page.classList.remove("active"));
        paginationItems[ currSlide - 1 ].classList.add("active");

    } else {

        // 무한 슬라이드
        currSlide = maxSlide + 1;
        let offset = slideWidth * currSlide;

        // 각 슬라이드 아이템의 left에 offset 적용
        slideBanners.forEach((banner) => {
            banner.setAttribute("style", `transition: ${0}s; left: ${-offset}px`);
        });

        currSlide--;

        offset = slideWidth * currSlide;

        // 각 슬라이드 아이템의 left에 offset 적용
        setTimeout(() => {
            slideBanners.forEach((banner) => {
                banner.setAttribute("style", `transition: ${0.15}s; left: ${-offset}px`);
            });
        }, 0);

        // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems.forEach((page) => page.classList.remove("active"));
        paginationItems[ currSlide - 1 ].classList.add("active");

    }
}

// 좌우 버튼 이벤트
$nextBtn.addEventListener("click", () => {
    nextMove();
});
$prevBtn.addEventListener("click", () => {
    prevMove();
});

// 브라우저 화면이 조정될 때 마다 slideWidth를 변경
window.addEventListener("resize", () => {
    slideWidth = $slide.clientWidth;
});

// 각 pagination 클릭 시 해당 슬라이드로 이동
for (let i = 0; i < maxSlide; i++) {

    // 각 클릭 이벤트 추가
    paginationItems[ i ].addEventListener("click", () => {

        // currSlide는 시작 위치가 1이기 때문에 + 1
        currSlide = i + 1;

        const offset = slideWidth * currSlide;

        // 각 슬라이드 아이템의 left에 offset 적용
        slideBanners.forEach((banner) => {
            banner.setAttribute("style", `left: ${-offset}px`);
        });

        // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems.forEach((page) => page.classList.remove("active"));
        paginationItems[ currSlide - 1 ].classList.add("active");

    });

}

// 슬라이드 배너 인터벌
let loopInterval = setInterval(() => {
    nextMove();
}, 5000);

/* ============= 슬라이더 배너 끝 ============= */


/* ============= 컨테이너 시작 ============= */

// 컨테이너 펫시터 찾기 버튼
if(document.getElementById("detail_button_search")) {

    const $detail_button_search = document.getElementById("detail_button_search");

    $detail_button_search.addEventListener("click", (event) => {

        location.href = "/";        // 추후 연결
    });
}

// 컨테이너 펫시터 지원 버튼(로그인 전)
if(document.getElementById("detail_button_apply1")) {

    const $detail_button_apply = document.getElementById("detail_button_apply1");

    $detail_button_apply.addEventListener("click", (event) => {

        alert("비회원은 회원가입부터 해주세요.\n회원일 경우 로그인하고 다시 눌러주세요.");

        location.href = "/member/mainRegist";
    });
}

// 컨테이너 펫시터 지원 버튼(로그인상태)
if(document.getElementById("detail_button_apply2")) {

    const $detail_button_apply = document.getElementById("detail_button_apply2");

    $detail_button_apply.addEventListener("click", (event) => {

        location.href = "/petSitterNew/regist";
    });
}

// 컨테이너 이용 프로세스 버튼
if(document.getElementById("detail_button_terms")) {

    const $detail_button_terms = document.getElementById("detail_button_terms");

    $detail_button_terms.addEventListener("click", (event) => {

        location.href = "/";        // 추후 연결
    });
}

// 후기 전체보기 버튼1
if(document.getElementById("review_all_btn1")) {

    const $review_all_btn1 = document.getElementById("review_all_btn1");

    $review_all_btn1.addEventListener("click", (event) => {

        location.href = "/";        // 추후 연결
    });
}

// 후기 전체보기 버튼2
if(document.getElementById("review_all_btn2")) {

    const $review_all_btn2 = document.getElementById("review_all_btn2");

    $review_all_btn2.addEventListener("click", (event) => {

        location.href = "/";        // 추후 연결
    });
}

// 후기 전체보기 버튼3
if(document.getElementById("review_all_btn3")) {

    const $review_all_btn3 = document.getElementById("review_all_btn3");

    $review_all_btn3.addEventListener("click", (event) => {

        location.href = "/";        // 추후 연결
    });
}

// 후기 더보기 버튼
if(document.getElementById("review_detail_button")) {

    const $review_detail_button = document.getElementById("review_detail_button");

    $review_detail_button.addEventListener("click", (event) => {

        location.href = "/";        // 추후 연결
    });
}

/* ============= 컨테이너 끝 ============= */