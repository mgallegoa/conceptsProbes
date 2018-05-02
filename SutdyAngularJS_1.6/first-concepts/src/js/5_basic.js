var app = angular.module('aplicacion', []);

		app.controller('controlador', function($scope) {
			$scope.nombre= "Manuel";
			$scope.apellido= "Gallego";
			$scope.obj={nombre:'Jorge',apellido:'Aramburo'};
		});
		
		app.directive("primeraDirectiva", function() {
			return {
				template: "<div><h5>Ejemplo de directiva<h5></div>",
				restrict: "EA" //E: Element A: Attribute C:Classes M: Comments
			};
		});