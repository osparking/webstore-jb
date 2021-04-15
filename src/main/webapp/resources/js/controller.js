var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl', function($scope, $http) {
	$scope.refreshCart = function(cartId) {
		$http.get('/webstore-jb/rest/cart/' + $scope.cartId)
		.success(function(data) {
			$scope.cart = data;
		});
	};
	
$scope.clearCart = function() {
	$http.delete('/webstore-jb/rest/cart/' + $scope.cartId)
	.success(function(data) {
		$scope.refreshCart($scope.cartId);
	});
};

$scope.initCartId = function(cartId) {
$scope.cartId = cartId;
$scope.refreshCart($scope.cartId);
};

$scope.addToCart = function(productId) {
	$http.put('/webstore-jb/rest/cart/add/' + productId)
	.success(function(data) {
	alert("Product Successfully added to the Cart!");
	});
};

$scope.removeFromCart = function(productId) {
$http.put('/webstore-jb/rest/cart/remove/' + productId)
.success(function(data) {
$scope.refreshCart($scope.cartId);
});
};
});