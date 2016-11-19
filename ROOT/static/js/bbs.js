var app = angular.module("booth", [ "chieffancypants.loadingBar", "ngAnimate", "ngSanitize", "angular-bind-html-compile" ])
    .config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
});

app.controller("boothCtrl", function ($scope, $http, $timeout, $location, $sce, cfpLoadingBar) {

    // in controller
    $scope.init = function () {
        $scope.view("test", 1);
  //  $scope.list("test", 1);
    };

    $scope.view = function(id, postId) {
        $http.get("./view").success(function(response, status, headers, config) {
            $scope.body = $sce.trustAsHtml(response);
        }).error(function(err, status, headers, config){
        });
        $scope.pageView.apply(this, arguments);
    };

    $scope.list = function(id, page){
        $http.get("./list").success(function(response, status, headers, config) {
            $scope.body = $sce.trustAsHtml(response);    
        }).error(function(err, status, headers, config){
        });
        $scope.pageMove.apply(this, arguments);
    };
    
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
            params: { id : id, page : page }
        }).then(function(response) {
            $scope.posts = response.data.posts;
            $scope.paging = response.data.paging;
            $scope.board = response.data.board;
           // $scope.user = response.data.user;
        });
    };

    // {comments: [],…}
    // board:{headerContent: "", creationTime: "2016-11-14 21:11:43.783", memo: "", postCount: 0, boardId: 1,…}
    // comments:[]
    // next_post:{hitCount: 0, creationTime: "2016-11-14 14:42:36.613", ip: "0:0:0:0:0:ffff:0:0 ",…}
    // post:{hitCount: 0, creationTime: "2016-11-10 00:44:58.207", ip: "0:0:0:0:0:ffff:0:0 ",…}
    // prev_post:{hitCount: 0, creationTime: "2016-11-10 00:44:51.37", ip: "0:0:0:0:0:ffff:0:0 ",…}
    // user:{creationTime: "2016-11-05 02:09:34.123", name: "cc", admin: false, permission: 9, comment: "",…}
    $scope.pageView = function(id, postId) {
        $http({
            method: "post",
            url: "./api/view",
            params: { id : id, postId : postId }
        }).then(function(response) {
            $scope.board = response.data.board;

            $scope.post = response.data.post;
            $scope.postComments = response.data.postComments;

            $scope.nextPost = response.data.nextPost;
            $scope.prevPost = response.data.prevPost;
            $scope.postUser = response.data.postUser; // 작성한 유저
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

});
