$(document).ready(function () {
    $.ajax({
        url: "./location",
        method: "get",
        complete: function (data) {
            var result =  "<option name=\"countryVar\"></option>";
            var countries = JSON.parse(data.responseText);
            for (var i = 0; i < countries.length; i++) {
                result += "<option value=\""+ countries[i] + "\" name=\"countryVar\">" + countries[i] + "</option>";
            }
            document.getElementById("country").innerHTML = result;
        }
    });
});

function change() {
    $.ajax({
        url: "./location",
        method: "post",
        data: {"country" : $("#country").val()},
        complete: function (data) {
            var result =  "<option name=\"cityVar\"></option>";
            var cities = JSON.parse(data.responseText);
            for (var i = 0; i < cities.length; i++) {
                result += "<option value=\""+ cities[i] + "\" name=\"cityVar\">" + cities[i] + "</option>";
            }
            document.getElementById("city").innerHTML = result;
        }
    });
}