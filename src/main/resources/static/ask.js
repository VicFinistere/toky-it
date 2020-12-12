/* Event listener : Getting answer using button */
$(function () {
    $('#chat_button').on('click', function (e) {
        sending_text();
        e.preventDefault();
    });
});

/* Event listener : Getting answer pressing enter */
$(function () {
    $(document).keypress(function (e) {
        if (e.which === 13) {
            sending_text();
            e.preventDefault();
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

    $("#input_text").val("");
}

function sending_text(isAnswerExpected = true) {
    //Debug
    console.debug("sending_text (messaging.js)");

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
        if (isAnswerExpected) {
            answer();
        }
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

        }
    }

    clear_text();
}

function answer() {
    //Debug
    console.debug("answer (messaging.js))");
    let theme = $("#question_theme").val();
    let user_text = $('#chat_area').children('p.user_bubble_msg').last().html();
    let userCheckedText = "[" + theme + "]" + user_text;
    bot_answering(userCheckedText);

    //Input text value to null
    $("#input_text").val("");

}


function bot_answering(user_checked_text) {
    //Log
    console.log("bot_answering (bot_answer.js)");
    console.log("New question : " + user_checked_text);

    $.ajax({
        type: "POST",
        url: "/getInput",
        data: {'userInput': user_checked_text},
        cache: false,
        timeout: 600000,
        success: function (data, status) {

            console.log("SUCCESS ( " + status + " ) : ", data);

        },
        error: function (e) {

            console.log("ERROR : ", e);

        }
    });

    bot_response("Someone is coming to answer your question...otherwise try to google it !");
    scroll();
}

function bot_response(response) {
    let chat_area = $('#chat_area');
    let user_input = chat_area.children('p.user_bubble_msg').last();
    if (user_input.css('color') !== 'rgb(255, 255, 255)') {
        $('<p>', {class: 'robot_info_msg', text: response}).appendTo('#chat_area');
        let d = new Date();
        let timer = d.toLocaleTimeString();
        $("<p class='text-center'><small style='font-size:10px;margin-right: 65%;'>" + timer + "</small></p>").appendTo('#chat_area');
        user_input.css('color', 'rgb(255, 255, 255)');
    }
}

function askRandomly() {
    $.get("https://opentdb.com/api.php?amount=1&category=18&type=multiple", function (data) {
        if (data.results) {
            var question = data.results[0].question.replace(/&quot;/g, '\"').replace(/&#039/g, "'");
            var correct_answer = data.results[0].correct_answer.replace(/&quot;/g, '\\"').replace(/&#039/g, "'");
            $("#input_text").val(question);
            sending_text(false);
            scroll();
            setTimeout(function () {
                bot_response(correct_answer);
                scroll();
            }, 4000);
        }
    });

}

function getUser(){
    // Store user in hidden input (cookies unwanted)
    if ($("#user").val() === '') {

        $("#user").val("User undefined");
        $.get("/getUser", function (user) {
            $("#user").val(user.attributes.login);
        });
    }
}