function get_questions() {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question) {
            let questions_area = $("#chat-area");
            if (questions_area.val()) {
                let already_asked_questions = questions_area.val();
                if (!already_asked_questions.includes(question)) {
                    $('<p>', {class: 'robot_info_msg', text: question}).appendTo('#chat_area');
                }
            } else {
                $('<p>', {class: 'robot_info_msg', text: question}).appendTo('#chat_area');
            }
        });
    });
}

get_questions();
setInterval(get_questions, 10000);