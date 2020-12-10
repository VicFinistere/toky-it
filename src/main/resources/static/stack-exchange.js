// For simplicity, we're using jQuery for some things
//   However, the library has no jQuery dependency
$(function(){
// Initialize library

$.get("/getStackKey", function (stack_key) {

    SE.init({
        // Parameters obtained by registering an app, these are specific to the SE
        //   documentation site
        clientId: 1,
        key: stack_key,
        // Called when all initialization is finished
        complete: function (data) {
            $('#login-button')
                .removeAttr('disabled')
                .text('Run Example With Version ' + data.version);
        }
    });
});

// Attach click handler to login button
$('#login-button').click(function() {

// Make the authentication call, note that being in an onclick handler
//   is important; most browsers will hide windows opened without a
//   'click blessing'
SE.authenticate({
success: function(data) {
alert(
'User Authorized with account id = ' +
data.networkUsers[0].account_id + ', got access token = ' +
data.accessToken
);
},
error: function(data) {
alert('An error occurred:\n' + data.errorName + '\n' + data.errorMessage);
},
networkUsers: true
});
});
});
