/* 마이페이지 + 내 정보 확인  */
/* 마이 페이지 -> 내 정보 확인 페이지로 이동 */
window.onload = function() {

    if(document.getElementById("my_info")) {
        const $update = document.getElementById("my_info");
        $update.onclick = function() {
            location.href = "/member/myInfo";
        }
    }

    /* 내 정보 확인 파일 업로드 버튼 */
    document.getElementById('uploadButton').addEventListener('click', function () {
        document.getElementById('fileInput').click();
    });


    /* 내 정보 확인 - 수정하기 버튼 */
    if(document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function() {
            location.href = "/member/myInfo";
        }
    }

    /* 내 정보 확인 - 탈퇴하기 버튼 */
    if(document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function() {
            location.href = "/member/delete";
        }
    }

    /* 수정 버튼 클릭하면 modifyMember 메서드 호출 */
    // document.getElementById('modifyButton').addEventListener('click', function () {
    //     // 수정에 필요한 데이터 수집 (예: 닉네임, 비밀번호 등)
    //     var nickname = document.getElementById('nickname').value;
    //     var password = document.getElementById('password').value;
    //     var password2 = document.getElementById('password2').value;
    //
    //     // AJAX 또는 폼 전송을 통해 modifyMember 메서드 호출
    //     var xhr = new XMLHttpRequest();
    //     xhr.open('POST', '/member/myInfo', true);
    //     xhr.setRequestHeader('Content-Type', 'application/json');
    //     xhr.onreadystatechange = function () {
    //         if (xhr.readyState === 4 && xhr.status === 200) {
    //             // 수정 완료 후의 처리 (예: 화면 갱신 등)
    //             alert('회원 정보가 수정되었습니다.');
    //         }
    //     };
    //     // 데이터를 JSON 형식으로 전송 (서버에서는 JSON 데이터를 처리할 수 있어야 함)
    //     var data = {
    //         nickname: nickname,
    //         password: password,
    //         password2: password2
    //     };
    //     xhr.send(JSON.stringify(data));
    // });
}