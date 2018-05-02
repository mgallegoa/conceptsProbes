var app = angular.module('aplicacion', []);

		app.controller('controlador', function($scope) {
			$scope.nombre= "Manuel";
			$scope.apellido= "Gallego";
			$scope.obj={nombre:'Jorge',apellido:'Aramburo'};
		});