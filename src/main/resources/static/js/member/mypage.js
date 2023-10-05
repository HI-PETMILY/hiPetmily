/* 마이페이지 + 내 정보 확인  */
window.onload = function() {



    /* 마이 페이지 -> 내 정보 확인 페이지로 이동 */
    if(document.getElementById("my_info")) {
        const $update = document.getElementById("my_info");
        $update.onclick = function() {
            location.href = "/member/update";
        }
    }


    /* 내 정보 확인 파일 업로드 버튼 */
    // document.getElementById('uploadButton').addEventListener('click', function () {
    //     document.getElementById('fileInput').click();
    // });


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

    /* 비밀번호 - 변경하기 버튼 누를 시 */
    document.addEventListener("DOMContentLoaded", function () {
        // 버튼 요소 가져오기
        var modifyPasswordButton = document.getElementById("modifyPassword");

        // 버튼 클릭 이벤트 핸들러 등록
        modifyPasswordButton.addEventListener("click", function (event) {
            event.preventDefault(); // 기본 폼 제출 동작 방지

            // 폼 요소 가져오기
            var form = document.querySelector("form");

            // 폼을 서버로 제출
            form.submit();
        });
    });


    /* 내 정보 확인 - 탈퇴하기 버튼 */
    if(document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function() {
            location.href = "/member/delete";
        }
    }


}