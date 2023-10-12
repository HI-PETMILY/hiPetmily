
    /* =============================================================================
                                           regist.html
     =============================================================================== */


     /* 닉네임 중복 확인 */
    if (document.getElementById("duplicationCheck")) {
        const $duplication = document.getElementById("duplicationCheck");
        const $nickNameInput = document.getElementById("nickName");

        $duplication.onclick = function () {
            let nickName = $nickNameInput.value.trim();

            if (nickName === "") {
                alert("사용하실 닉네임을 입력하세요.");
                return; // 닉네임 필드가 비어 있으면 중복 확인을 실행하지 않음
            }

            fetch("/member/nickNameDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({nickName: nickName})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));
        }
    }


    /* 이메일 중복 확인 */
    if (document.getElementById("duplicationCheck2")) {
        const $duplicationEmail = document.getElementById("duplicationCheck2");
        const $memberId = document.getElementById("memberId");

        $duplicationEmail.onclick = function () {
            let memberId = $memberId.value.trim();

            if (memberId === "") {
                alert("사용하실 이메일을 입력하세요.");
                return; // 이메일 필드가 비어 있으면 중복 확인을 실행하지 않음
            }

            fetch("/member/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({ memberId: memberId })
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }

    /* input에 영어랑 숫자만 입력하게 하기 - 이메일 */
    const inputEngNum = document.getElementById('memberId');
    inputEngNum.addEventListener('input', function (event) {
        const inputValue = event.target.value;
        // event.target은 이벤트를 발생시킨 요소를 가리킨다. -> 입력필드
        event.target.value = inputValue.replace(/[^\w\s@.]/ig, '');
        // event.target.value는 입력 필드의 현재 값을 나타낸다.
        // => 정규 표현식을 사용하여 입력을 필터링하고, 필터링된 결과를 다시 입력 필드에 할당하여 불필요한 문자를 제거한다.
    });


    /* input에 영어랑 한글만 입력하게 하기 - 이름 */
    const inputEngKor = document.getElementById('memberName');
    inputEngKor.addEventListener('input', function (event) {
        const inputValue4 = event.target.value;
        event.target.value = inputValue4.replace(/[^A-Za-z가-힣\s]/g, '');
    });


    /* input에 영어랑 숫자, 특수문자 입력하게 하기 - 패스워드 */
    const inputAll = document.getElementById('password');
    inputAll.addEventListener('input', function (event) {
        const inputValue2 = event.target.value;
        event.target.value = inputValue2.replace(/[^A-Za-z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/g, '');
    });


    /* input에 숫자 입력하게 하기 - 연락처 */
    const inputNum = document.getElementById('phone');
    inputNum.addEventListener('input', function (event) {
        const inputValue3 = event.target.value;
        event.target.value = inputValue3.replace(/\D/g, '');
    });


    /* 전체 동의 */
    const checkAll = document.getElementById("check_all");
    const normalCheckboxes = document.querySelectorAll(".normal");

    checkAll.addEventListener("click", function () {
        normalCheckboxes.forEach(function (checkbox) {
            checkbox.checked = checkAll.checked;
        });

        updateSubmitButtonState();
    });

    /* 개별 체크박스의 클릭 이벤트 핸들러(전체 동의 체크 상태 업데이트) */
    normalCheckboxes.forEach(function (checkbox) {
        checkbox.addEventListener("click", function () {
            var is_checked = true;
            normalCheckboxes.forEach(function (checkbox) {
                if (!checkbox.checked) {
                    is_checked = false;
                }
            });
            checkAll.checked = is_checked;

            updateSubmitButtonState();
        });
    });

    /* 필수 약관 체크박스 눌렀을 때 가입 버튼 활성화 */
    const requiredCheckboxes = document.querySelectorAll(".required");
    const submitButton = document.querySelector(".regist_button input[type='submit']");

// 필수 약관 체크박스들 상태 체크
    function updateSubmitButtonState() {
        var allRequiredChecked = true;

        // 모든 필수 약관 체크 여부 확인
        requiredCheckboxes.forEach(function (checkbox) {
            if (!checkbox.checked) {
                allRequiredChecked = false;
            }
        });

        submitButton.disabled = !allRequiredChecked;
    }

    updateSubmitButtonState();

