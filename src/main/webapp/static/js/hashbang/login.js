var authApp = angular.module("auth", [
    "ngSanitize",
    "angular-bind-html-compile"
]);

// $location.search({id: id, page : page });
// $location.path("/write").search({ id : id, page : $scope.page});
authApp.controller("authController", function ($scope, $http, $timeout, $location) {

    $scope.signIn = function () {
        $http.post("gz/login", {
            username: $("#username").val(),
            password: $("#password").val()
        }).then(function (response) {
            if (response.data.success) {
                console.log("login success");
                //$location.path("/list").search({id: id, page : 1 });
            }
        }, function(e){
            console.log(e);
        });
    };

    $scope.signOut = function () {
        $http({
            method: "post",
            url: "gz/logout",
            params: { }
        }).then(function (response) {
            if (response.data.success) {

            }
        });

    };

});
