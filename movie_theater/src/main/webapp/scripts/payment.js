
$(document).ready(function () {
    $.ajax({
        url: "../payment",
        dataType: "json",
        method: "get",
        success : function (data) {
            $('.pt-3').append(
                "<h3 id='"+ data.id +"'>" + "you have chosen row " + data.row + " seat "
                + data.number +", price : " + data.price + " rub." + "</h3>");
        }
    });
});

function buy() {
    var name = $('#username').val();
    var phone = $('#phone').val();
    console.log(name);
    console.log(phone);
    if (!validName(name)) {
        alert("enter correct name");
    } else if (!validPhone(phone)) {
        alert("enter correct phone");
    } else {
        alert("success");
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