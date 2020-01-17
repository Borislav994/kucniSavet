var kucniSavetApp = angular.module("kucniSavetApp");
kucniSavetApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/poruke', {
			templateUrl : '/app/components/poruke/poruke.html'
		})
		.when('/poruke/edit/:id', {
			templateUrl : '/app/components/edit-poruke/edit-poruke.html'
		})
		.when('/poruke/glasaj/:id', {
			templateUrl : '/app/components/glas/glas.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);