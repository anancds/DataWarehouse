/**
 * Created by laiqin on 2017/1/9.
 */

var appMain = angular.module('mMain', ['ng', 'ngRoute']);

appMain.config(function ($routeProvider) {
    $routeProvider
        .when('home', {
            templateUrl: 'Home.html'
        })
        .when('home', {
            templateUrl: ''
        })
        .otherwise({
            redirectTo: 'Home.html'
        })
})