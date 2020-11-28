function answerQuestion() {

    let inputForQuestion = $("#selected_question");
    let inputForAnswer = $("#answer");
    let givenAnswer = inputForAnswer.val();
    let selectedQuestion = inputForQuestion.val();
    let selectedQuestionId = inputForQuestion.attr("class").replaceAll("info_questions ", "");
    console.log("You have answered : " + givenAnswer + "(" + selectedQuestion + ")");
    $.ajax({
        type: "POST",
        url: "/setAnswer",
        data: {
            'selectedQuestionId': selectedQuestionId,
            'givenAnswer': givenAnswer
        },
        cache: false,
        timeout: 600000,
        success: function (data, status) {

            console.log("SUCCESS ( " + status + " ) : ", data);

        },
        error: function (e) {

            console.log("ERROR : ", e);

        }
    });

    inputForAnswer.val("");

}

function openOneQuestion(e, txt) {
    $("#selected_question").val(txt);
    $("#questions_area").hide();
    $("#question_area").show();
}

function openAllQuestions() {
    $("#question_area").hide();
    $("#questions_area").show();
}

$(function () {

    openAllQuestions();

    $(document).on('click', '.info_questions', function (e) {
        let txt = $(e.target).text();
        openOneQuestion(e, txt);
    });
});

function get_questions() {
    $.get("/getQuestions", function (questions) {
        console.log(questions);

        $.each(questions, function (i, question_object) {
            var question_list = $("#questions_list");
            let already_asked_questions = question_list.val();
            if (!already_asked_questions.includes(question_object.question)) {
                question_list.val(already_asked_questions + "\n" + question_object.question);
                $('<p>', {
                    class: 'info_questions ' + question_object.id,
                    text: question_object.question
                }).appendTo('#questions_textarea');
                console.log("New question to be posted !");
            }
        });
    });
}

get_questions();
setInterval(get_questions, 10000);