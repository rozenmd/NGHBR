// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        testAPI(response.authResponse.accessToken);
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.

    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.

    }
}

function login() {
    FB.login(function(response) {
        statusChangeCallback(response);
    });
}


window.fbAsyncInit = function() {
    FB.init({
        appId      : '420176448180335',
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.2' // use version 2.2
    });


};

// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI(accessTocken) {
    FB.api('/me', { locale: 'en_US', fields: 'name, email, first_name, last_name'}, function(response) {
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        $.ajax({
            url:'/api/users/authenticate',
            type:"POST",
            dataType: "json",
            data: JSON.stringify({ssoId: response.id, facebookId: response.id, email: response.email, firstName: response.first_name, lastName: response.last_name, password: accessTocken}), //Stringified Json Object
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function(data){
                //redirecting once logged in
                window.location = '/profile';
            },
            error: function(XMLHttpRequest, textStatus, errorThrown ){
                console.log(textStatus);
                console.log(errorThrown);
            }
        });

    });
}

function logout() {
    FB.logout(function(response) {
        console.log('Logged out!');
    });
}