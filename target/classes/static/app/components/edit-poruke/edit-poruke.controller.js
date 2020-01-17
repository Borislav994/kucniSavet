var kucniSavetApp = angular.module("kucniSavetApp");
kucniSavetApp.controller("editPorukeCtrl", function($scope, $routeParams, $http,
		$location) {

	$scope.poruke = [];
	$scope.zgrade = [];

	$scope.poruka = {};
	$scope.poruka.naslov = "";
	$scope.poruka.tip = "";
	$scope.poruka.opis = "";
	$scope.poruka.potrebanProcenat = "";
	$scope.poruka.zgradaId = "";

	var baseUrl = "/api/poruke/" + $routeParams.id;
	var zgradeUrl = "/api/zgrade";

	var getZgrade = function() {

		var promise = $http.get(zgradeUrl);

		promise.then(function success(res) {
			$scope.zgrade = res.data;
	

		}, function error() {
			alert("Unsuccessful fetch!")
		});

	}
	getZgrade();

	var getPoruke = function() {
		var promise = $http.get(baseUrl);
		promise.then(
			function uspeh(odg){
				$scope.poruka = odg.data;
			},
			function neuspeh(){
				console.log("Something went wrong!");
			}
		);
	}

	getPoruke();
	
	$scope.doEdit = function() {
		$http.put(baseUrl, $scope.poruka).then(function success() {
			$location.path("/poruke");
		}, function error() {
			alert("Something went wrong.");
		});
	}


});