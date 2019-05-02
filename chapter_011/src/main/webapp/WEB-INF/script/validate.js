function validate() {
    var result = true;
    var name = $('#name').val();
    var login = $('#login').val();
    var email = $('#email').val();
    var password = $('#password').val();

    if (!validName(name)) {
        alert("please enter correct name" + "/n" + "Example");
        result = false;
    }
    if (!validLogin(login)) {
        alert("please enter correct login" + "/n" + "example");
        result = false;
    }
    if (!validPass(password)) {
        alert("please enter password");
        result = false;
    }
    if (!validEmail(email)) {
        alert("please enter correct email" + "/n" + "example@example.ru");
        result = false;
    }
    return result;
}

function validName(name) {
    var regEx = /^[A-Z]{1}[a-z]{0,15}/;
    return regEx.test(name);
}

function validLogin(login) {
    var regEx = /[^-\s][a-zA-Z0-9-_\\s]*$/;
    return regEx.test(login);
}

function validEmail(email) {
    var regEx = /.+@.+\..+/;
    return regEx.test(email);
}

function validPass(password) {
    return password != '';
}