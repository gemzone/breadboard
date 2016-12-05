

/*
var boardApp = angular.module("board", [
    "ngSanitize",
    "angular-bind-html-compile",
    "ui.tinymce",
    "ngRoute"
]);

boardApp.controller("boardController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {
*/

    /*
    $scope.pageMove = function (id, page) {
        $http({
            method: "post",
            url: "gz/list",
            params: { id: id, page: page }
        }).then(function (response) {
            $scope.posts = response.data.posts;
            $scope.paging = response.data.paging;
            $scope.board = response.data.board;
        });
    };

    $scope.postView = function (id, postId) {
        $http({
            method: "post",
            url: "gz/post",
            params: { id: id, postId: postId }
        }).then(function (response) {
            $scope.board = response.data.board;
            $scope.post = response.data.post;
            $scope.postTextHtml = $sce.trustAsHtml(response.data.post.text);
            $scope.postComments = response.data.postComments;
            $scope.nextPost = response.data.nextPost;
            $scope.prevPost = response.data.prevPost;
            $scope.postUser = response.data.postUser;   // 작성한 유저
        });
    };


    $scope.board = function (id) {
        $http({
            method: "post",
            url: "gz/board",
            params: { id: id }
        }).then(function (response) {
            $scope.board = response.data.board;
        });
    }


    $scope.tinymceOptions = {
        plugins: 'link image code',
        toolbar: 'undo redo | bold italic | alignleft aligncenter alignright | code'
    };
    */
/*
});
console.log("board.js");
*/
