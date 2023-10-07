window.onload = function () {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.addEventListener("click", function () {
            location.href = "/member/login";
        });
    }


};

// 아이디 찾기 버튼에 대한 클릭 이벤트
function findUserId() {

    var name = document.getElementById("inputName_id").value;
    var phone = document.getElementById("inputPhone_id").value;
    var findIdResult = document.getElementById("findIdResult").value;

}

// 휴대폰 or 이메일로 비밀번호 찾기 선택 시 보이는 화면
function find_Pwd(num) {
    if (num == '1') {
        document.getElementById("findPwdByPhone").style.display = "";
        document.getElementById("findPwdByEmail").style.display = "none";
    } else {
        document.getElementById("findPwdByPhone").style.display = "none";
        document.getElementById("findPwdByEmail").style.display = "";

    }
}

// 사진 등록
document.addEventListener('DOMContentLoaded', function () {
    (function () {
        /* div image area 요소 */
        const imageArea = document.querySelectorAll(".pet-profile-img");
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
            // 첨부된 파일이 존재한다면
            if (this.files && this.files[0]) {
                const reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                // 파일 로드 후 동작하는 이벤트
                reader.onload = function () {
                    console.log(reader.result);
                    imageArea[index].innerHTML = `<img src='${reader.result}' style='width:120px;height:100px'>`;
                }
            }
        }
    })();
});


