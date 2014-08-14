// create the module and name it scotchApp
var wegetdatesApp = angular
		.module('wegetdatesApp', [ 'ngRoute', 'ngResource' ]);

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

// https://we.getdat.es/api/v1/cron?cron=0%200%2012%20*%20*%20?&from=2014-07-16T00:00:00&to=2014-07-26T00:00:00&max=100"

wegetdatesApp.factory('cronQuery', function($resource) {
	return $resource('https://we.getdat.es/api/v1/cron');
});

wegetdatesApp.controller('mainController', [ '$scope', '$location',
		function($scope, $location) {
			// create a message to display in our view
			$scope.message = 'Everyone come and see how good I look!';

			$scope.menuClass = function(page) {
				var current = $location.path().substring(1);
				return page === current ? "active" : "";
			};
		} ]);

wegetdatesApp.controller('annuallyController', function($scope) {
	$scope.message = 'Look! I am an about page.';
});

wegetdatesApp.controller('cronController', [ '$scope', 'cronQuery',
		function($scope, cronQuery) {

			$scope.clearForm = function() {
				$scope.data = {};
				$scope.result = null;
				$scope.error = null;
			};

			$scope.message = 'Contact us! JK. This is just a demo.';
			$scope.clearForm();
			// cron=0%200%2012%20*%20*%20?&from=2014-07-16T00:00:00&to=2014-07-26T00:00:00&max=100

			$scope.submitCron = function() {
				$scope.result = null;
				$scope.error = null;

				var args = {
					cron : $scope.data.cron,
					from : $scope.data.from,
					to : $scope.data.to,
					max : $scope.data.max
				};
				cronQuery.get(args, function(data) {
					$scope.result = data;
				}, function(error) {
					$scope.error = error;
				});
			};
		} ]);

wegetdatesApp.controller('dailyController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});

wegetdatesApp.controller('monthlyController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});

wegetdatesApp.controller('weeklyController', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
});