var app = angular.module("booth", [ 
    "angular-loading-bar",
    "ngAnimate", 
    "ngSanitize", 
    "angular-bind-html-compile",
    "ui.tinymce",
    "ngRoute"
]).config(function($routeProvider, $locationProvider, cfpLoadingBarProvider) { //, cfpLoadingBarProvider) {
    //cfpLoadingBarProvider.latencyThreshold = 500;
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

// boardPostsController
app.controller("boardPostsController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {
    console.log($routeParams);
    
    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.pageMove($routeParams.id, $routeParams.page);
    });

    $scope.list = function(id, page) {
        $location.search({id: id, page : page });
    }

    $scope.pageMove = function(id, page) {
        $http({
            method: "post",
            url: "./gz/list",
            params: { id : id, page : page }
        }).then(function(response) {
            $scope.posts = response.data.posts;
            $scope.paging = response.data.paging;
            $scope.board = response.data.board;
           // $scope.user = response.data.user;
        });
    };
});

// boardPostViewController
app.controller("boardPostViewController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {
    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        $scope.postView($routeParams.id, $routeParams.postId);
        $scope.page = $routeParams.page;
    });

    $scope.view = function(id, page, postId) {
        $scope.page = page;
        $location.search({id: id, page: page, postId : postId });
    }

    $scope.postView = function(id, postId) {
        $http({
            method: "post",
            url: "./gz/view",
            params: { id : id, postId : postId }
        }).then(function(response) {
            $scope.board = response.data.board;
            $scope.post = response.data.post;
            $scope.postTextHtml = $sce.trustAsHtml(response.data.post.text);
            $scope.postComments = response.data.postComments;
            $scope.nextPost = response.data.nextPost;
            $scope.prevPost = response.data.prevPost;
            $scope.postUser = response.data.postUser; // 작성한 유저
        });
    };

    $scope.pagePosts = function(id) {
        $location.path("/list").search({ id : id, page : $scope.page});
    };
});


app.controller("boardController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {

    $scope.$on("$routeChangeSuccess", function(e, current, pre) {
        
        // if( $location.path() == "/list") { 
            
            // $rootScope.pageMove(current.params.id, current.params.page);

        // e.targetScope.pageMove(current.params.id, 1);

        // console.log("app.run : e");
        // console.log(e);
        // console.log("app.run : current.params");
        // console.log(current.params);

        // console.log('Current route name: ');
        // console.log($location.path());
        // }
    });

    // $routeParams.id;
    //routeProvider.when('/tasks/:id/edit/', {templateUrl: '/tasks/' + idParam + '/edit'});
    //$location.path('/tasks/' + idParam + '/edit/');

/*
    $scope.start = function() {
        cfpLoadingBar.start();
    };

    $scope.complete = function () {
        cfpLoadingBar.complete();
    }
*/


/*
    $scope.template = {};

    $http.get("./write").success(function(response, status, headers, config) {
        $scope.template.write = $sce.trustAsHtml(response);
    });
    $http.get("./view").success(function(response, status, headers, config) {
        $scope.template.view = $sce.trustAsHtml(response);
    });
    $http.get("./list").success(function(response, status, headers, config) {
        $scope.template.list = $sce.trustAsHtml(response);
    });
    console.log($scope.template);
*/
    $scope.tinymceModel = "";
    //$scope.getContent = function() {
    //    console.log('Editor content:', $scope.tinymceModel);
    //};
    //$scope.setContent = function() {
    //    $scope.tinymceModel = 'Time: ' + (new Date());
    //};
    $scope.tinymceOptions = {
        plugins: 'link image code',
        toolbar: 'undo redo | bold italic | alignleft aligncenter alignright | code'
    };

    $scope.view = function(id, postId) {
        $scope.pageView.apply(this, arguments);
    };

    $scope.postAdd = function(id) {
        $.ajax({
            type: "post",
            url: "./postAdd",
            data: { 
                    id : "test",
                    title : $("#postTitle").val(), 
                    text : $scope.tinymceModel // $("#postText").val()
            }, success:function(data){
                $scope.list(id, 1);
            }
        });
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
            url: "./gz/view",
            params: { id : id, postId : postId }
        }).then(function(response) {
            $scope.board = response.data.board;
            
            $scope.post = response.data.post;
            $scope.postTextHtml = $sce.trustAsHtml(response.data.post.text);

            $scope.postComments = response.data.postComments;

            $scope.nextPost = response.data.nextPost;
            $scope.prevPost = response.data.prevPost;
            $scope.postUser = response.data.postUser; // 작성한 유저
        });
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

});

//app.run(function($rootScope, $http, $location, $routeParams, $sce) {

//    $rootScope.$on("$routeChangeSuccess", function(e, current, pre) {
        
/*
        console.log("app.run : e");
        console.log(e);
        console.log("app.run : current.params");
        console.log(current.params);

        console.log('Current route name: location');
        console.log($location);
*/
        
        // if( $location.path() == "/list") { 
            
            // $rootScope.pageMove(current.params.id, current.params.page);

        // e.targetScope.pageMove(current.params.id, 1);

        // console.log("app.run : e");
        // console.log(e);
        // console.log("app.run : current.params");
        // console.log(current.params);

        // console.log('Current route name: ');
        // console.log($location.path());
        // }
//    });
//});
