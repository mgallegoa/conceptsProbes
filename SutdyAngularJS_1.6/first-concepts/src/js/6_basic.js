var app = angular.module('aplicacion', []);

		app.controller('controlador', function($scope) {
			$scope.nombre= "Manuel";
			$scope.apellido= "Gallego";
			$scope.productos = {
				nombre:'detergente',
				costo:'10',
				pendientes: [
					'trapera',
					'escoba',
					'manzanas',
					'jabon'
				]
			};
			
			/*$scope.clickFunction = function(productos) {
				productos.click = 'SI';
			}*/
		});
		
		app.directive("primeraDirectiva", function() {
			return {
				templateUrl: "primeraDirectiva.html",
				restrict: "EA" //E: Element A: Attribute C:Classes M: Comments
				// no recomendable usar replease: true
				// Encapsular la funcion con el controller
				, controller: function($scope) {
					$scope.clickFunction = function(productos) {
						productos.click = 'SI encapsula';
					}
				}
				
			};
		});