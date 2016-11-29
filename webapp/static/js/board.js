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

boardApp.controller("boardPostsController", function ($scope, $routeParams, $http, $timeout, $location, $sce, cfpLoadingBar) {


    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.pageMove($routeParams.id, $routeParams.page);
        $scope.page = $routeParams.page;
    });

    // $location.search({id: id, page : page });
    // $location.path("/write").search({ id : id, page : $scope.page});

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
/*
        $.getJSON("gz/view?callback=viewProc&id=" + id + "&postId=" + postId, function(response) {
            $scope.board = response.board;
            $scope.post = response.post;
            $scope.postTextHtml = $sce.trustAsHtml(response.post.text);
            $scope.postComments = response.postComments;
            $scope.nextPost = response.nextPost;
            $scope.prevPost = response.prevPost;
            $scope.postUser = response.postUser;   // 작성한 유저
        });
*/

/*
        $.ajax({
            url: "gz/view?callback=viewProc&id=" + id + "&postId=" + postId ,
            dataType: "jsonp"
        });
*/

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

    $scope.pagePosts = function(id) {
        $location.path("/list").search({ id : id, page : $scope.page});
    };
});


boardApp.controller("boardPostWriteController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {

    $scope.page = $routeParams.page;
    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.board($routeParams.id);
    });

    $scope.board = function (id) {
        $http({
            method: "post",
            url: "./gz/board",
            params: { id : id }
        }).then(function(response) {
            $scope.board = response.data.board;
        });
    }

    //$scope.view = function(id, page, postId) {
    //    $scope.page = page;
    //    $location.search({id: id, page: page, postId : postId });
    //}

    //$scope.pagePosts = function(id) {
    //    $location.path("/list").search({ id : id, page : $scope.page});
    //};
    $scope.tinymceModel = "";
    $scope.tinymceOptions = {
        plugins: 'link image code',
        toolbar: 'undo redo | bold italic | alignleft aligncenter alignright | code'
    };

    $scope.postAdd = function(id) {
        $http({
            method: "post",
            url: "gz/post/add",
            params: {
                id: id,
                title: $("#postTitle").val(),
                text: $scope.tinymceModel
            },
            headers: {
                "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
            }
        }).success(function(response) {
            
        }).finally(function() {
            $location.path("/list").search({id: id, page : $scope.page });
        });
    };

    // $scope.list = function(id, page) {
    //    $location.path("/list").search({id: id, page : $scope.page });
    // }


});

