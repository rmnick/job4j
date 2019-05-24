var success = "success";

$(function getSeat() {
    $.ajax({
        url: "../payment",
        dataType: "json",
        method: "get",
        success : function (data) {
            if (data.id === 0) {
                    alert("The ticket has been already reserved");
                window.location.href = "../index.html";
            } else {
                $('.pt-3').append(
                    "<h3 id='"+ data.id +"'>" + "you have chosen row " + data.row + " seat "
                    + data.number +", price : " + data.price + " rub." + "</h3>");
            }
        }
    });
});

function buy() {
    var userName = $('#username').val();
    var userPhone = $('#phone').val();
    var seatId = $("h3").attr("id");
    if (!validName(userName)) {
        alert("please enter correct name");
    } else if (!validPhone(userPhone)) {
        alert("please enter correct phone");
    } else if (!userCheck(userName, userPhone)) {
        alert("the phone number is already registered in another name");
    } else {
        var account = {"name": userName, "phone": userPhone, "seatId": seatId};
        $.ajax({
            method : "post",
            url :  "../payment",
            data : JSON.stringify(account),
            contentType : "application/json",
            dataType : "text",
            success : function (data) {
                alert(data);
                window.location.href = '../index.html';
            }
        });
    }
}

function validName(name) {
    var regExp =  /^[A-Z]{1}[a-z]{0,15}$/;
    return regExp.test(name);
}

function validPhone(phone) {
    var regExp =  /^(7){1}(9){1}(\d){9}$/;
    return regExp.test(phone);
}

function userCheck(userName, userPhone) {
    console.log("in check");
    var result = false;
    var msg;
    $.ajax({
        method : "post",
        url :  "../check",
        data : {name:userName, phone: userPhone},
        dataType : "text",
        async : false,
        success : function (data) {
           msg = data;
        }
    });
    console.log(msg);
    if (msg == success) {
        result = true;
    }
    return result;
}
