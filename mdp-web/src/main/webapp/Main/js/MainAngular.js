/**
 * Created by laiqin on 2017/1/9.
 */

var app = angular.module('mMain',['ngRoute']);
app.config(['$routeProvider', function($routeProvider){
    $routeProvider
        .when('/',{
            controller: 'HomeController',
            contrllerAs: 'home',
            templateUrl: 'MainPartOne.html'
        }).when('/one',{
        controller: 'HomeController',
        contrllerAs: 'home',
        templateUrl: 'MainPartOne.html'
    })
        .when('/two',{
            controller: 'HomeController',
            contrllerAs: 'home',
            templateUrl: 'MainPartTwo.html'
        });
}])