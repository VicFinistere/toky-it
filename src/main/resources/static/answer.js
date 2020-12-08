function getAnswers(idQuestion) {

    console.log(idQuestion);

    $.ajax({
        type: "POST",
        url: "/getQuestion",
        data: {'idQuestion': idQuestion},
        cache: false,
        timeout: 600000,
        success: function (question, status) {

            console.log("SUCCESS ( " + status + " ) : ", question);
            $.each(question.answer, function (i, current_answer) {
                let answers_list = $("#selected_question_answers");
                if (!answers_list.val().includes(current_answer.answer)) {
                    let current_value = answers_list.val();
                    if (current_value) {
                        answers_list.val(current_value + "\n" + current_answer.answer);
                    } else {
                        answers_list.val(current_answer.answer);
                    }

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
    openAllQuestions();

}

function openOneQuestion(e, idQuestion, txt) {
    $("#selected_question_answers").val("");
    $("#selected_question").val(txt);
    $("#selected_question_id").val(idQuestion);
    $("#questions_area").hide();
    $("#question_area").show();
    getAnswers(idQuestion);
    setInterval(getAnswers(idQuestion), 10000);
}

function openAllQuestions() {
    $('#questions_textarea').load(location.href + ' #questions_textarea');
    $("#question_area").hide();
    $("#questions_area").show();
}

$(function () {

    openAllQuestions();

    $(document).on('click', '.info_questions', function (e) {
        let txt = $(e.target).text();
        let idQuestion = $(e.target).attr("class").replaceAll("info_questions ", "");
        openOneQuestion(e, idQuestion, txt);
    });
});

function isASpam(question_object) {
    let status = "";
    if (question_object.answer) {
        $.each(question_object.answer, function (i, current_answer) {
            if (current_answer.answer.includes("not an IT question")) {
                status = "spam";
                return status;
            }
        });
    }
    return status
}

function reloadQuestions(filter) {
    $("#questions_textarea").val("");
    $("#questions_list").val("");
    $('#questions_textarea').load(location.href + ' #questions_textarea');
    $("#questions_filter").val(filter);
    get_questions();
}

function insertQuestion(question_list, already_asked_questions, question_object) {
    question_list.val(already_asked_questions + "\n" + question_object.question);
    $('<p>', {
        class: 'info_questions ' + question_object.id,
        text: question_object.question
    }).appendTo('#questions_textarea');
}

function get_questions() {
    let filter = $("#questions_filter").val();
    if (filter) {

        if (filter === "info") {
            $.get("/getInfoQuestions", function (data) {
                console.log("Searching " + filter + " questions");
                print_questions(data);
            });
        }

        else if(filter === "code")
        {
            $.get("/getCodeQuestions", function (data) {
                console.log("Searching " + filter + " questions");
                print_questions(data);
            });
        }

        else if(filter === "answered")
        {
            $.get("/getAnsweredQuestions", function (data) {
                console.log("Searching " + filter + " questions");
                print_questions(data);
            });
        }

        else if(filter === "answeredIT")
        {
            $.get("/getAnsweredItQuestions", function (data) {
                console.log("Searching " + filter + " questions");
                print_questions(data);
            });
        }

        else if(filter === "spam")
        {
            $.get("/getSpamQuestions", function (data) {
                console.log("Searching " + filter + " questions");
                print_questions(data);
            });
        }


    }
}

function print_questions(questions){
    $.each(questions, function (i, question_object) {
        console.log("Question object " + question_object + "question : " + question_object.q);
        var question_list = $("#questions_list");
        let already_asked_questions = question_list.val();

        // Filter
        if (!already_asked_questions.includes(question_object.question)) {

            insertQuestion(
                question_list,
                already_asked_questions,
                question_object);

        } else {
            console.log("Question already printed " + question_object.question);
        }
    });
}

get_questions();
setInterval(get_questions, 10000);