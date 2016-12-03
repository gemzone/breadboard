var boothApp = angular.module("booth", [
    "board",
    "auth",
    "ui.router"
]).run(function($rootScope, $location, $stateParams, $state) {

    console.log($state.current);
    console.log($stateParams);
    console.log($state);

}).config(function ($stateProvider, $urlRouterProvider, $routeProvider, $locationProvider, cfpLoadingBarProvider) {
    cfpLoadingBarProvider.latencyThreshold = 0;
    cfpLoadingBarProvider.includeSpinner = true;
    $locationProvider.hashPrefix("!");


    // $urlRouterProvider.otherwise('/list');

    // HOME STATES AND NESTED VIEWS ========================================
    
    $stateProvider.state('board', {
        url: '/board',
       //  templateUrl: "e/list",
        views: {
            '': { templateUrl: 'board/list' },
            //'columnOne@about': { template: 'Look I am a column!' },
            //'columnTwo@about': {
            //    templateUrl: 'table-data.html',
            //    controller: 'scotchController'
           // }
        }

        //controller: "boardPostsController",
        //params: {
        //    id: "test"
        //}
    });
    alert("a");


    /*
    $stateProvider.state('home', {
        url: '/home',
        templateUrl: 'partial-home.html'
        // nested list with custom controller
    }).state('home.list', {
            url: '/list',
            templateUrl: 'partial-home-list.html',
            controller: function ($scope) {
                $scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
            }
    }).state('home.paragraph', {
            url: '/paragraph',
            template: 'I could sure use a drink right now.'
    }).state('about', {
        url: '/about',
        views: {
            '': { templateUrl: 'partial-about.html' },
            'columnOne@about': { template: 'Look I am a column!' },
            'columnTwo@about': {
                templateUrl: 'table-data.html',
                controller: 'scotchController'
            }
        }
    });
    */

    /*
    .config(function ($routeProvider, $locationProvider, cfpLoadingBarProvider) {
        cfpLoadingBarProvider.latencyThreshold = 0;
        cfpLoadingBarProvider.includeSpinner = true;
        $locationProvider.hashPrefix("!");

        $routeProvider.when("/list", {
            templateUrl: "e/list",
            controller: "boardPostsController"
        }).when("/view", {
            templateUrl: "e/view",
            controller: "boardPostViewController",
        }).when("/write", {
            templateUrl: "e/write",
            controller: "boardPostWriteController"
        }).otherwise({
            redirectTo: "/list"
        });
    */


});