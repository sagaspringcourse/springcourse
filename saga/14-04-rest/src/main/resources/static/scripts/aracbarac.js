function status(msg) {
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
    $.getJSON('/game/start', function (data) {
        renderTeams(data['home']['players'], '1', '');
        renderTeams(data['away']['players'], '2', '1');
        homePlays();
    });
}

function play(id) {
    $.getJSON('/game/play/' + id, function (data) {
        $("#team1, #team2").empty();
        status(data.msg);
        renderTeams(data['home']['players'], '1', '');
        renderTeams(data['away']['players'], '2', '1');
        homePlays();
    });
}

function homePlays() {
    $('.block1-split').click(function (e) {
        id = $(this).attr("data-attr");
        $('.block:first').animate({
            left: $(this).offset().left - 281,
            top: $(this).offset().top - 61
        }, 500, function () {
            play(id);
        });
    });
}
callServer();
