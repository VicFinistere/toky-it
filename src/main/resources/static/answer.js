

$(function () {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question) {
            let questions_area = $("#questions-area");
            let questions = questions_area.val();
            questions_area.val(questions + "\n" + question);
        });
    });
});