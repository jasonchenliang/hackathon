var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope, $http) {

  $scope.account = "";
  $scope.password = "";

  $scope.print = function() {
    return $scope.account + " | " + $scope.password;
  };

  $scope.submit = function() {
    if ($scope.account === "account" && $scope.password === "password") {
      $http.get("http://www.w3schools.com/angular/customers.php")
        .success(function(response) {
          $scope.records = response.records;
        });
    } else {
      alert("Authentication error, please try again.");
      $scope.reset();
    }
  };

  $scope.reset = function() {
    $scope.account = "";
    $scope.password = "";
  };
});
