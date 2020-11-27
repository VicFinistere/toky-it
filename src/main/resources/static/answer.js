

function get_questions() {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question) {
            let questions_area = $("#questions-area");
            let already_asked_questions = questions_area.val();
            if(!already_asked_questions.includes(question)) {
                questions_area.val(questions + "\n" + question);
            }
        });
    });
}

setInterval(get_questions, 10000);