

function get_questions() {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question) {
            var question_list = $("#questions_list");
            let already_asked_questions = question_list.val();
            if(!already_asked_questions.includes(question)) {
                question_list.val(already_asked_questions + "\n" + question);
                $('<p>', {class: 'info_questions', text: question}).appendTo('#questions_area');
                console.log("New question to be posted !");
            }
        });
    });
}

get_questions();
setInterval(get_questions, 10000);