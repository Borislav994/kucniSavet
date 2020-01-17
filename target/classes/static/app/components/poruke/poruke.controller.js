var kucniSavetApp = angular.module("kucniSavetApp");
kucniSavetApp.controller("porukeCtrl", function($scope,$routeParams, $http, $location){
	
	$scope.poruke = [];
	$scope.zgrade = [];
	
	$scope.poruka = {};
	$scope.poruka.naslov = "";
	$scope.poruka.tip = "";
	$scope.poruka.opis = "";
	$scope.poruka.potrebanProcenat = "";
	$scope.poruka.zgradaId = "";
	
	$scope.searchParams = {};
	$scope.searchParams.zgradaUlica = $location.search().zgradaUlica != undefined ? $location.search().zgradaUlica  : "";
	$scope.searchParams.naslov = $location.search().naslov != undefined ? $location.search().naslov : "";
	$scope.searchParams.tip = $location.search().tip != undefined ? $location.search().tip : "";

	
	$scope.pageNum = 0;
	$scope.totalPages = 1
	
	var baseUrl = "/api/poruke";
	var zgradeUrl = "/api/zgrade";
	
var getZgrade = function(){
		
		var promise = $http.get(zgradeUrl);
		
		promise.then(
			function success(res){
				$scope.zgrade = res.data;
				
				
			},
			function error(){
				alert("Unsuccessful fetch!")
			}
		);

	}

getZgrade();

var getPoruke = function(){
	
	var config = { params: {} };
	
	//Dakle, polja config.params objekta moraju da se zovu kako back-end ocekuje
	if($scope.searchParams.zgradaUlica != ""){
		$location.search("zgradaUlica", $scope.searchParams.zgradaUlica);
		config.params.zgradaUlica = $scope.searchParams.zgradaUlica;
	}
	
	if($scope.searchParams.naslov != ""){
		config.params.naslov = $scope.searchParams.naslov;
	}
	
	if($scope.searchParams.tip != ""){
		config.params.tip = $scope.searchParams.tip;
	}

	config.params.pageNum = $scope.pageNum;
	
	$http.get(baseUrl, config).then(
		function success(res){
			$scope.poruke = res.data;
			$scope.totalPages = res.headers("totalPages");
		},
		function error(){
			alert("Something went wrong.");
		}
	);
}


getPoruke();



	
	$scope.doAdd = function(){
		$http.post(baseUrl, $scope.poruka).then(
			function success(){
				getPoruke();
				$location.path("/poruke");
			},
			function error(){
				alert("Couldn't add activity!");
			}
		);
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getPoruke();
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/poruke/edit/" + id);
	}
	
	$scope.goToGlasaj = function(id){
		$location.path("/poruke/glasaj/" + id);
	}
	
	$scope.doSearch = function(){

		var params =  {};
		
		if($scope.searchParams.zgradaUlica != ""){
			params.zgradaUlica = $scope.searchParams.zgradaUlica;
		}
		
		if($scope.searchParams.naslov != ""){
			params.naslov = $scope.searchParams.naslov;
		}
		
		if($scope.searchParams.tip != ""){
			params.tip = $scope.searchParams.tip;
		}
		
		$location.search(params);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getAutomobili();
	}
	
	
	
});