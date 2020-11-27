

function get_questions() {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question) {
            let questions_area = $("#questions-area");
            let questions = questions_area.val();
            if(!questions.includes(question)) {
                questions_area.val(questions + "\n" + question);
            }
        });
    });
}

setInterval(get_questions, 10000);