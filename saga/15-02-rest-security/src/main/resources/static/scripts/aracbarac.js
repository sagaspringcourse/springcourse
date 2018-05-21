//var host = "http://127.0.0.1:8081";
var gameData;
var host = "http://localhost:8081";
function statusMessage(msg) {
    $("#status").html(msg);
}

function renderTeams(players, i, classSuffix) {
    $.each(players, function (key, value) {
        $('#team' + i).append("<div class='block" + classSuffix + "'>" + value.displayName + "</div>");
        $("#team" + i).append("<div class='block" + classSuffix + "-split' data-attr='" + value.id + "'></div>");
    });
    $(".block" + classSuffix + "-split:last").remove();
}

function callServer() {
    $.ajax({
        type: "GET",
        url: host + '/game/start',
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        headers: createAuthorizationTokenHeader(),
        dataType: "json",
        success: function (data, status, jqXHR) {
            renderTeams(data['home']['players'], '1', '');
            renderTeams(data['away']['players'], '2', '1');
            homePlays();
            gameData = data;
        }
    });
}

function play(id, data) {

    $.ajax({
        type: "POST",
        url: host + '/game/play/' + id,
        data: JSON.stringify(gameData),
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        headers: createAuthorizationTokenHeader(),
        dataType: "json",
        success: function (data, status, jqXHR) {
            $("#team1, #team2").empty();
            gameData = data;
            statusMessage(data.msg);
            renderTeams(data['home']['players'], '1', '');
            renderTeams(data['away']['players'], '2', '1');
            homePlays();
        },

        error: function (jqXHR, status) {
            statusMessage("something went wrong " + status);
        }
    });
}

function homePlays() {
    $('.block1-split').click(function (e) {
        id = $(this).attr("data-attr");
        $('.block:first').animate({
            left: $(this).offset().left - 281,
            top: $(this).offset().top - 61
        }, 500, function () {
            play(id, gameData);
        });
    });
}
callServer();
