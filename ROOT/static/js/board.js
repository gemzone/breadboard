var app = angular.module("booth", [
        "chieffancypants.loadingBar", "ngAnimate"
    ]).config(function(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = true;
    });

app.controller("boothCtrl", function ($scope, $http, $timeout, cfpLoadingBar) {
    
    $scope.signOut = function() {
        location.href="./logout";
    };

    $scope.signInModal = function() {
        $("#signInModal").modal();
    };

    $scope.userInfo = function() {
        alert("user_info");
    };

    $scope.signUpModal = function() {
        $("#signUpModal").modal();
    };



    $scope.signIn = function() {
        $http({
            method: 'post',
            url: './login',
            params: { 
                username : $("#username").val(), 
                password : $("#password").val()
            }
        }).then(function(response) {
            location.reload();
        });
    };

    $scope.start = function() {
        cfpLoadingBar.start();
    };

    $scope.complete = function () {
        cfpLoadingBar.complete();
    }

    $timeout(function() {
        console.log("timeout");
    //    $scope.complete();
    //    $scope.fakeIntro = false;
    }, 0);

    // fake the initial load so first time users can see it right away:
    //$scope.start();
    //$scope.fakeIntro = false;
    //$timeout(function() {
    //    $scope.complete();
    //    $scope.fakeIntro = false;
    //}, 50);
});






/*
$(document).on({
    ajaxStart: function() { $("#_loading").show();  },
    ajaxError: function() { $("#_loading").show();  },
    ajaxStop: function() { $("#_loading").hide(); }
});

window.onerror = function() {
    $("#_loading").hide();    // global error
};

// tooltip
$(document).ready(function() {
    $('[data-toggle="tooltip"]').tooltip();
});

$(document).bind("keydown", function (e) {
    switch (e.keyCode) {
        // ESC
        case 27: {
            break;
        }
        default: {
            // console.log(e.keyCode);
            break;
        }
    }
});
*/

