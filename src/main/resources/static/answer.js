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


function scroll() {
    //Debug
    console.debug("Scrolling down the conversation (messaging.js)");

    var chat_area = document.getElementById('chat_area');
    chat_area.scrollTop = chat_area.scrollHeight;
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

var user_input = $('#chat_area').children('p.user_bubble_msg').last();
var robot_input = $('#chat_area').children('p.robot_info_msg').last();

var sentences = ['bonjour', 'salut', 'coucou'];
var answers = [['coucou', 'bonjour', 'salut'], [], []];

function answer(response) {
    if (user_input.css('color') !== 'rgb(230, 230, 230)') {
        $('<p>', {class: 'robot_info_msg', text: response}).appendTo('#chat_area');
        var d = new Date();
        var timer = d.toLocaleTimeString();
        $("<p class='text-center'><small style='font-size:10px;margin-right: 65%;'>" + timer + "</small></p>").appendTo('#chat_area');
        user_input.css('color', 'rgb(230, 230, 230)');
    }
}


$(function () {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question) {
            let questions_area = $("#questions-area");
            let questions = questions_area.val();
            questions_area.val(questions + question);
        });
    });
});