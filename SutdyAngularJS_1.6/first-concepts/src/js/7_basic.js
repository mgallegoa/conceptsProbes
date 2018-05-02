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
			
			console.log($scope);
			
		});
		
		app.directive("primeraDirectiva", function() {
			return {
				templateUrl: "scopeDirectiva.html",
				restrict: "EA", //E: Element A: Attribute C:Classes M: Comments
				//scope: true, // 2. Crea un prototype parent property
				scope: {productos:'='}, // 3. Crea un prototype parent property  <primera-directiva productos="productos"></primera-directiva>
				controller: function($scope) {
					$scope.clickFunction = function(productos) {
						productos.click = 'SI encapsula';
					}
					console.log($scope);
				}
			};
		});