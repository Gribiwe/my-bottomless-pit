function tryToRegistration() {
    var userData = {
        nickname: document.getElementById("nick_name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };
    $.ajax({
        type: "PUT",
        url: "/registration",
        dataType: "json",
        data: JSON.stringify(userData),
        success: function (data) {
            document.getElementById("result").innerText = "Registration completed successfully.";
        }
    });
}

