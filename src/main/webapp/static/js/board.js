var boardApp = angular.module("board", [ 
    "angular-loading-bar",
    "ngAnimate", 
    "ngSanitize", 
    "angular-bind-html-compile",
    "ui.tinymce",
    "ngRoute"
]).config(function($routeProvider, $locationProvider, cfpLoadingBarProvider) {
    cfpLoadingBarProvider.latencyThreshold = 0;
    cfpLoadingBarProvider.includeSpinner = true;
    $locationProvider.hashPrefix("!");
    $routeProvider.when("/list", {
        templateUrl : "e/list",
        controller: 'boardPostsController'
    }).when("/view", {
        templateUrl : "e/view",
        controller: 'boardPostViewController'
    }).when("/write", {
        templateUrl : "e/write",
        controller: 'boardPostWriteController'
    }).otherwise({
        redirectTo: "/list"
    });
});

// $location.search({id: id, page : page });
// $location.path("/write").search({ id : id, page : $scope.page});
boardApp.controller("boardPostsController", function ($scope, $routeParams, $http, $timeout, $location, $sce, cfpLoadingBar) {
    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.pageMove($routeParams.id, $routeParams.page);
        $scope.page = $routeParams.page;
    });

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

boardApp.controller("boardPostViewController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {
    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.postView($routeParams.id, $routeParams.postId);
        $scope.page = $routeParams.page;
    });

    $scope.postView = function(id, postId) {
        $http({
            method: "post",
            url: "gz/post",
            params: { id : id, postId : postId }
        }).then(function(response) {
            $scope.board = response.data.board;
            $scope.post = response.data.post;
            $scope.postTextHtml = $sce.trustAsHtml(response.data.post.text);
            $scope.postComments = response.data.postComments;
            $scope.nextPost = response.data.nextPost;
            $scope.prevPost = response.data.prevPost;
            $scope.postUser = response.data.postUser;   // 작성한 유저
        });
    };
});

boardApp.controller("boardPostWriteController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {

    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.board($routeParams.id);
    });

    $scope.board = function (id) {
        $http({
            method: "post",
            url: "gz/board",
            params: { id : id }
        }).then(function(response) {
            $scope.board = response.data.board;
        });
    }

    $scope.tinymceOptions = {
        plugins: 'link image code',
        toolbar: 'undo redo | bold italic | alignleft aligncenter alignright | code'
    };

    $scope.postAdd = function(id) {
        $http.post( "gz/post/add", {
            id: id,
            title: $("#postTitle").val(),
            text: $(tinymce.settings.selector).val()
        }).then(function() {
            $location.path("/list").search({id: id, page : 1 });
        }, function(e){
            alert(e);
        });
    };
});

