
angular.module('main', [])
.controller('mainController', function(#scope, $http) {
  $http.get("/products?listing=hot")
      .success(function (response) {
        $scope.hotItems = response;
      });
  $http.get("/products?listing=new")
      .success(function (response) {
        $scope.newItems = response;
      });
});