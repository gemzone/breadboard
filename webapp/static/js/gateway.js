var gatewayApp = angular.module("gateway", [ 
    "angular-loading-bar",
    "ngAnimate", 
    "ngSanitize", 
    "angular-bind-html-compile",
    "ui.tinymce",
    "ngRoute"
]).config(function($routeProvider, $locationProvider, cfpLoadingBarProvider) {
    //cfpLoadingBarProvider.latencyThreshold = 500;
    cfpLoadingBarProvider.includeSpinner = true;
    $locationProvider.hashPrefix("!");
    
});

gatewayApp.controller("signUpController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {
    
    





});

gatewayApp.controller("signInController", function ($scope, $routeParams, $http, $timeout, $location, $sce) {








});
