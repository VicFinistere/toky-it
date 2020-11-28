function getAnswers(idQuestion) {

    console.log(idQuestion);

    $.ajax({
        type: "POST",
        url: "/getQuestion",
        data: {'idQuestion': idQuestion},
        cache: false,
        timeout: 600000,
        success: function (question, status) {

            console.log("SUCCESS ( " + status + " ) : ", data);
            $.each(question.answer, function (i, answer) {

                if (!$("#selected_question_answers").val().includes(answer)) {
                    $('<p>', {
                        class: "answer",
                        text: answer
                    }).appendTo('#selected_question_answers');
                }
            });
        },
        error: function (e) {

            console.log("ERROR : ", e);

        }
    });
}

function answerQuestion() {

    let inputForQuestion = $("#selected_question");
    let inputForAnswer = $("#answer");
    let givenAnswer = inputForAnswer.val();
    let selectedQuestion = inputForQuestion.val();
    let selectedQuestionId = $("#selected_question_id").val();

    console.log("Answering : " + givenAnswer + "(" + selectedQuestion + "#" + selectedQuestionId + ")");
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

function openOneQuestion(e, idQuestion, txt) {
    $("#selected_question").val(txt);
    $("#selected_question_id").val(idQuestion);
    $("#questions_area").hide();
    $("#question_area").show();
    getAnswers(idQuestion);
    setInterval(getAnswers(idQuestion), 10000);
}

function openAllQuestions() {
    $("#question_area").hide();
    $("#questions_area").show();
}

$(function () {

    openAllQuestions();

    $(document).on('click', '.info_questions', function (e) {
        let txt = $(e.target).text();
        let idQuestion =$(e.target).attr("class").replaceAll("info_questions ", "");
        openOneQuestion(e, idQuestion, txt);
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