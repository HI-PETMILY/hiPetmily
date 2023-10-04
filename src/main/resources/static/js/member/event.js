window.onload = function() {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.addEventListener("click", function () {
            location.href = "/member/login";
        });
    }
    //아이디 중복 검사
    var emailConfirm=document.getElementById("email");
    emailConfirm.addEventListener('keyup', function (){
        var xhr,url='member/find_id-pwd', data='email='+emailConfirm.value;
        xhr=doajax(url,data);
        xhr.onload=function (){
            var text;
                if(xhr.status==200){    // success:function(data)부분 통신 성공 시 200 반환
                    if(xhr.response=='true'){
                        text='인증번호를 전송했습니다.';
                    }else{
                        text='인증번호 전송에 실패했습니다.';
                    }
                    alert(text)
                }
        }
    })
};

// 아이디 찾기 버튼에 대한 클릭 이벤트
function findUserId(){

    var name = document.getElementById("inputName_id").value;
    var phone = document.getElementById("inputPhone_id").value;
    var findIdResult = document.getElementById("findIdResult").value;

    //이름 또는 휴대폰 번호가 비어있으면 findIdReuslt div를 숨김
    if(name && phone){
        // Ajax 요청
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "member/find_id-pwd", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function (){
            if(xhr.readyState == 4 && xhr.status == 200){
                // 서버로부터 받은 응답 처리
                document.getElementById("result").innerHTML = xhr.responseText;
                findIdResult.style.display='block';
            }
        };
        xhr.send("inputName_id=" + name + "&inputPhone_id=" + phone);
    } else{
        // 이름 휴대폰번호가 비어있으면 결과를 숨김
        findIdResult.style.display='none';
    }
}

// 휴대폰 or 이메일로 비밀번호 찾기 선택 시 보이는 화면
function find_Pwd(num){
    if (num == '1'){
        document.getElementById("findPwdByPhone").style.display="";
        document.getElementById("findPwdByEmail").style.display="none";
    } else{
        document.getElementById("findPwdByPhone").style.display="none";
        document.getElementById("findPwdByEmail").style.display="";

    }
}

// 비밀번호 찾기 버튼 클릭 이벤트
$("#findPwdSubmit").click(function (){
    const userEmail = $("#memberId").val();
    const sendEmail = document.forms["sendEmail"];
    $.ajax({
        type: 'POST',
        url: '/member/find_id-pwd',
        data: {
            'email':userEmail,
        },
        dataType: "text",
        success: function (result){
            if(result == "no"){     //서버로부터 받은 응답을 보내는게 어디쥐
                alert('임시 비밀번호가 발송되었습니다.');
                sendEmail.submit();
            }else{
                alert('가입되지 않은 이메일입니다.');
            }
        }, error:function (){
            console.log('이메일 에러 발생')
        }
    })
});

