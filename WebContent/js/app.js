var mainApp = angular.module("mainApp", ['ngRoute','ngGrid','mainController','angularUtils.directives.dirPagination']);
mainApp.config(['$routeProvider', function($routeProvider) {
   $routeProvider.
 
   when('/addMember', {
      templateUrl: 'templates/AddMember.html', controller: 'AddMemberController'
   }).
   
   when('/viewMember', {
      templateUrl: 'templates/viewMember.html', controller: 'ViewMemberController'
   }).
   
   when('/addProject', {
	      templateUrl: 'templates/AddProject.html', controller: 'AddProjectController'
	   }).
   
   when('/viewData', {
	      templateUrl: 'templates/viewProject.html', controller: 'viewDataController'
	   }).
  
	   when('/viewAvailability',{
		   templateUrl: 'templates/viewAvail.html', controller: 'viewAvailController'
	   }).   
	   
   otherwise({
      redirectTo: 'index.html'
   });
	
}]);  