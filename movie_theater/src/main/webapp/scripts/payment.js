
// $(document).ready(function getTicket() {
//     $.ajax({
//         url : "./payment",
//         method : "GET",
//         complete : function (data) {
//             // $('.pt-3').append(
//             //     "<h3 id='"+ data.id +"'>" + "Вы выбрали ряд " + data.row + " место "
//             //     + data.number +", Сумма : " + data.price + " рублей." + "</h3>")
//             console.log(data);
//         }
//     });
// });

$(document).ready(function () {
    $.ajax({
        url: "../payment",
        method: "get",
        complete : function (data) {
            // $('.pt-3').append(
            //     "<h3 id='"+ data.id +"'>" + "Вы выбрали ряд " + data.row + " место "
            //     + data.number +", Сумма : " + data.price + " рублей." + "</h3>")
            console.log(data.responseText);
        }
    });
});