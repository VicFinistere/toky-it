/* Event listener : Getting answer using button */
$(function () {
    $('#chat_button').on('click', function () {
        sending_text();
    });
});

/* Event listener : Getting answer pressing enter */
$(function () {
    $(document).keypress(function (e) {
        if (e.which === 13) {
            sending_text();
        }
    });
});

/* Robot Introduction */
$(function () {
    introduction();
});

function scroll() {
    //Debug
    console.debug("Scrolling down the conversation (messaging.js)");

    var chat_area = document.getElementById('chat_area');
    chat_area.scrollTop = chat_area.scrollHeight;
}

function introduction() {
    //Debug
    console.debug("Message introduction (messaging.js)");
    var d = new Date();
    var timer = d.toLocaleTimeString();
    $('<p>', {class: 'robot_info_msg', text: "Hello !"}).appendTo('#chat_area');
    $("<p class='text-center'><small style='font-size:10px;margin-right: 65%;'>" + timer + "</small></p>").appendTo('#chat_area');
    $('<p>', {class: 'robot_info_msg', text: "Any questions ?"}).appendTo('#chat_area');
    $("<p class='text-center'><small style='font-size:10px;margin-right: 65%;'>" + timer + "</small></p>").appendTo('#chat_area');

    scroll();
}

function clear_text() {
    //Debug
    console.debug("clear_text (messaging.js)");

    if ($("#text_error").not(':empty')) {
        //Debug
        console.debug("Text_error is cleared (messaging.js)");
        $("#text_error").empty();
    }
}

function sending_text() {
    //Debug
    console.debug("sending_text (messaging.js)");

    clear_text();

    //Getting the input value
    var input_text = $("#input_text").val();

    //Getting the content in page
    var content = $("#answer").html();

    //If the input is filled
    if (input_text !== "") {
        //We append the input text
        $('<p>', {class: 'user_bubble_msg', text: input_text}).appendTo('#chat_area');
        var d = new Date();
        var timer = d.toLocaleTimeString();
        $("<small style='font-size:10px;margin:0 auto; margin-left: 65%;'>" + timer + "</small>").appendTo('#chat_area');
        scroll();
        answer();
    }

    //If the input if empty
    else {
        //Warn
        console.debug("No message in input text ! (messaging.js)");

        //It has to be something in input
        var text = "Ask something!...";

        //If it's not already said
        if (content !== text) {
            //Debug
            console.debug("Giving empty input text error(messaging.js)");

            //Say it!
            $("#text_error").html(text);
        }
    }
}

function answer() {
    //Debug
    console.debug("answer (messaging.js))");

    var user_input = $('#chat_area').children('p.user_bubble_msg').last();
    var user_text = user_input.html();
    var user_checked_text = user_text.toLowerCase();

    bot_answering(user_checked_text);

    //Input text value to null
    $("#input_text").val("");

}

var user_input = $('#chat_area').children('p.user_bubble_msg').last();
var robot_input = $('#chat_area').children('p.robot_info_msg').last();

var sentences = ['bonjour', 'salut', 'coucou'];
var answers = [['coucou', 'bonjour', 'salut'], [], []];

function bot_answering(user_checked_text) {
    //Log
    console.log("bot_answering (bot_answer.js)");
    var dteNow = new Date();
    var intYear = dteNow.getFullYear();
    var intMonth = dteNow.getMonth();
    var intDay = dteNow.getDay();
    console.log(intYear, intMonth, intDay);
    $.ajax({
        url: 'https://api.wit.ai/message?v=' + 2020 + '' + 11 + '' + 26 + '&q=' + user_checked_text,
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + "${bearer}");
        },
        data: {},
        success: function (html_code, status) {
            console.log("Success in bot answering (" + status + ":" + html_code + ")")
        },
        error: function (result, status, error) {
            console.log("Error in bot answering" + error + "(" + status + ")");
        },
        complete: function (result, status) {
            console.log("Result : " + result + '' + result.intents[0].name + "(" + status + ")")
        },
    });

    // function get_random_answer(user_checked_text) {
    //     for(var i = 0; i < sentences.length; i++){
    //         if(sentences[i] === user_checked_text ){
    //             var options = answers[i];
    //             var response = options[Math.floor(Math.random() * options.length)];
    //         }
    //     }
    //     if(response){
    //         return response
    //     } else {
    //         return user_checked_text;
    //     }
    // }


    scroll();
}

function bot_response(response) {
    if (user_input.css('color') !== 'rgb(230, 230, 230)') {
        $('<p>', {class: 'robot_info_msg', text: response}).appendTo('#chat_area');
        var d = new Date();
        var timer = d.toLocaleTimeString();
        $("<p class='text-center'><small style='font-size:10px;margin-right: 65%;'>" + timer + "</small></p>").appendTo('#chat_area');
        user_input.css('color', 'rgb(230, 230, 230)');
    }
}

function askRandomly() {

    $.ajax({
        url: 'https://opentdb.com/api.php?amount=1&category=18&type=multiple',
        type: 'GET',
        contentType: "text/html; charset=UTF-8",
        data: {},
        success: function (data) {
            $("#input_text").val(data.results[0].question);
            sending_text();
            scroll();
            setTimeout(function () {
                bot_response(data.results[0].correct_answer);
                scroll();
            }, 4000);
        }
    });
}
