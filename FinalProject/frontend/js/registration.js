function tryToRegistration() {
    var userData = {
        nick: document.getElementById("nick_name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    }
    $.ajax({
        type: "PUT",
        url: "/user/registration",
        dataType: "json",
        data: JSON.stringify(userData),
        success: function (data) {
            document.getElementById("result").innerText = "Registration completed successfully.";
        }
    });
}

