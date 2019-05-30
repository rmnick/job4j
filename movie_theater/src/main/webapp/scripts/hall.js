
$(document).ready(function () {
    $.ajax({
        type : "GET",
        url : "./seats",
        dataType : "json",
        success : function (data) {
            var table = $("table tbody");
            table.empty();
            $.each(data, function (index, value) {
                var akk = "<tr><th>" + index + "</th>";
                $.each(value, function (index, value) {
                    if (!value.booked) {
                        akk+="<td><input type='radio' name='place' value='";
                        akk+= value.id;
                        akk+="'>";
                        akk+=" row " + value.row + ", place " + value.number;
                        akk+="</input></td>";
                    } else {
                        akk+="<td><input type='radio' name='place' disabled>";
                        akk+=" row " + value.row + ", place " + value.number;
                        akk+="</input></td>";
                    }
                });
                akk+="</tr>";
                table.append(akk);

            });
        }
    });
});

function checkChoose(input) {
    var result = true;
    if (input.length === 0) {
        result = false;
    }
    return result;
}

function pay() {
    var value = $('input[name=place]:checked');
    if (checkChoose(value)) {
        var place = value.val();
        $.ajax({
            type : 'POST',
            url : "./seats",
            data : {id : place},
            dataType : "text",
            success : function (data) {
                window.location.href = "view/payment.html";
            }
        });
    } else {
        alert("you did not choose your seat");
    }
}