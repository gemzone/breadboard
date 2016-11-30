var authApp = angular.module("auth", [
    "ngSanitize", 
    "angular-bind-html-compile"
]);

// $location.search({id: id, page : page });
// $location.path("/write").search({ id : id, page : $scope.page});
authApp.controller("authController", function ($scope, $routeParams, $http, $timeout, $location) {

    $scope.pageMove = function(id, page) {
        $http({
            method: "post",
            url: "gz/list",
            params: { id : id, page : page }
        }).then(function(response) {
            $scope.posts = response.data.posts;
            $scope.paging = response.data.paging;
            $scope.board = response.data.board;
        });
    };

});
