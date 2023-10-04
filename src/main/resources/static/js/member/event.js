window.onload = function() {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.addEventListener("click", function () {
            location.href = "/member/login";
        });
    }
}

function findUserId(){
    var name = document.getElementById("inputName_id").value;
    var phone = document.getElementById("inputPhone_id").value;

    // Ajax 요청
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "member/find_id-pwd", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function (){
        if(xhr.readyState == 4 && xhr.status == 200){
            // 서버로부터 받은 응답 처리
            document.getElementById("result").innerHTML = xhr.responseText;
        }
    };
    xhr.send("inputName_id=" + name + "&inputPhone_id=" + phone);
}

// 휴대폰 or 이메일로 비밀번호 찾기
function find_Pwd(num){
    if (num == '1'){
        document.getElementById("findPwdByPhone").style.display="";
        document.getElementById("findPwdByEmail").style.display="none";
    } else{
        document.getElementById("findPwdByPhone").style.display="none";
        document.getElementById("findPwdByEmail").style.display="";

    }
}