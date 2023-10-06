/* 마이페이지 + 내 정보 확인  */
window.onload = function() {



    /* 마이 페이지 -> 내 정보 확인 페이지로 이동 */
    if(document.getElementById("my_info")) {
        const $update = document.getElementById("my_info");
        $update.onclick = function() {
            location.href = "/member/update";
        }
    }


    /* 내 정보 확인 - 수정하기 버튼 */
    if(document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function() {
            location.href = "/member/update";
        }
    }


    /* 내 정보 확인 - 비밀번호 변경 버튼 */
    if(document.getElementById("updatePassword")) {
        const $update = document.getElementById("updatePassword");
        $update.onclick = function() {
            location.href = "/member/updatePassword";
        }
    }

    /* 내 정보 확인 - 회원 탈퇴 버튼 */
    if(document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function() {
            location.href = "/member/delete";
        }
    }




    <!-- 사진 업로드 즉시 실행 함수 -->
    (function(){
        /* div image area 요소 */
        const imageArea = document.querySelectorAll(".image-area");
        /* input type=file 요소 */
        const fileElements = document.querySelectorAll("[type=file]");
        /* div 클릭 시 open 함수 동작하여 input type=file 클릭 */
        imageArea.forEach(item => item.addEventListener('click', open));
        /* 파일 첨부가 발생하는 상황에 preview 함수 동작 */
        fileElements.forEach(item => item.addEventListener('change', preview));

        function open() {
            const index = Array.from(imageArea).indexOf(this);
            fileElements[index].click();
        }

        function preview() {
            const index = Array.from(fileElements).indexOf(this);
            console.log(this);
            console.log(this.files, this.files[0]);
            /* 첨부 된 파일이 존재한다면 */
            if(this.files && this.files[0]) {
                const reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                /* 파일 로드 후 동작하는 이벤트 설정 */
                reader.onload = function() {
                    console.log(reader.result);
                    if(index === 0) {
                        imageArea[index].innerHTML = `<img src='${reader.result}' style='width:245px;height:265px'>`;
                    } else {
                        imageArea[index].innerHTML = `<img src='${reader.result}' style='width:245px;height:265px'>`;

                    }
                }
            }
        }

    })();
}