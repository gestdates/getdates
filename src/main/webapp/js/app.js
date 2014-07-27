// create the module and name it scotchApp
var wegetdatesApp = angular.module('wegetdatesApp', [ 'ngRoute' ]);

// configure our routes
wegetdatesApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : '/pages/index.html',
		controller : 'mainController'
	})

	// route for the about page
	.when('/about', {
		templateUrl : '/pages/about.html',
		controller : 'mainController'
	})

	.when('/annually', {
		templateUrl : '/pages/annually.html',
		controller : 'annuallyController'
	})
	// route for the contact page
	.when('/contact', {
		templateUrl : '/pages/contact.html',
		controller : 'mainController'
	})

	.when('/cron', {
		templateUrl : '/pages/cron.html',
		controller : 'cronController'
	})

	.when('/daily', {
		templateUrl : '/pages/daily.html',
		controller : 'dailyController'
	})

	.when('/methods', {
		templateUrl : '/pages/methods.html',
		controller : 'mainController'
	})

	.when('/monthly', {
		templateUrl : '/pages/monthly.html',
		controller : 'monthlyController'
	})

	.when('/weekly', {
		templateUrl : '/pages/weekly.html',
		controller : 'weeklyController'
	});
});

// create the controller and inject Angular's $scope
wegetdatesApp.controller('mainController', function($scope) {
	// create a message to display in our view
	$scope.message = 'Everyone come and see how good I look!';

	$scope.menuClass = function(page) {
		var current = $location.path().substring(1);
		return page === current ? "active" : "";
	};
});

wegetdatesApp.controller('annuallyController', function($scope) {
	$scope.message = 'Look! I am an about page.';
});

wegetdatesApp.controller('cronController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});

wegetdatesApp.controller('dailyController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});

wegetdatesApp.controller('monthlyController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});

wegetdatesApp.controller('weeklyController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});