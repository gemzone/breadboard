var app = angular.module("booth", [ "chieffancypants.loadingBar", "ngAnimate" ])
    .config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
});

app.controller("boothCtrl", function ($scope, $http, $timeout, $location, cfpLoadingBar) {

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

    $scope.pageMove = function(id, page) {
        $http({
            method: "post",
            url: "./api/list",
            params: { 
                id : id,
                page : page
            }
        }).then(function(response) {
            $scope.posts = response.data.posts;
            $scope.paging = response.data.paging;
            $scope.board = response.data.board;
            console.log($scope.board);
            console.log($scope.posts);
            console.log($scope.paging);
        });
    };

    $scope.start = function() {
        console.log("start");
        cfpLoadingBar.start();
    };

    $scope.complete = function () {
        console.log("complete");
        cfpLoadingBar.complete();
    }

    console.log($location);

    $scope.pageMove("test", 1 );
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

