var kucniSavetApp = angular.module("kucniSavetApp");
kucniSavetApp.controller("GlasanjeCtrl", function($scope,$routeParams, $http, $location){
	var urlPoruka = "/api/poruke/" + $routeParams.id;
	
	
	$scope.glas = {};
	$scope.glas.predlogPodrzan = "";
	
	
	$scope.doGlasaj = function(){
		$http.post(urlPoruka, $scope.glas).then(
			function success(){
				$location.path("/poruke");
			},
			function error(){
				alert("Nesto je poslo po zlu.");
			}
		);
	}
});