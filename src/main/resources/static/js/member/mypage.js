window.onload = function() {

    if(document.getElementById("my_info")) {
        const $update = document.getElementById("my_info");
        $update.onclick = function() {
            location.href = "/member/myInfo";
        }
    }

}