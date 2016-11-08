var app = angular.module("booth", [
        "chieffancypants.loadingBar", "ngAnimate"
    ]).config(function(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = true;
    });

app.controller("boothCtrl", function ($scope, $http, $timeout, cfpLoadingBar) {
    
    //$scope.fetch = function() {
//        $scope.subreddit = getRandomSubreddit();
//        $http({
//            method: "jsonp",
//            url: "./login"
//        }).jsonp("./login")
//            .success(function(data) {
//            $scope.posts = data.data.children;
//        });
//    };


//    $http({
//        method: 'jsonp',
//        url: './login',
//        params: { username : "test123", password : "password123" }
//    }).success(function(response) {
//        $scope.content = response.data;
//    }).error(function(response) {
//        console.log('error');
//    });


//    $http({
//        method: 'jsonp',
//        url: './login',
//        params: { username : "test123", password : "password123" }
//    })

/*
    $http({
        method: 'post',
        url: './login',
        params: { username : "test123", password : "password123" }
    }).then(function(response) {
        $scope.content = response.data;
    });
*/


    $scope.start = function() {
        cfpLoadingBar.start();
    };

    $scope.complete = function () {
        cfpLoadingBar.complete();
    }

    // fake the initial load so first time users can see it right away:
    // $scope.start();
    // $scope.complete();
    //$scope.fakeIntro = false;
    //$timeout(function() {
     //   $scope.complete();
      //  $scope.fakeIntro = false;
    //}, 750);
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

