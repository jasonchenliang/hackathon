/**
 * Created by kuang on 19/06/2015.
 */
describe('example test', function() {
  beforeEach(module('loginApp'));

  var $controller;

  beforeEach(inject(function(_$controller_){
    // The injector unwraps the underscores (_) from around the parameter names when matching
    $controller = _$controller_;
  }));

  describe('$scope.reset', function() {
    it('Reset login form', function () {
      var $scope = {};
      var controller = $controller('loginCtrl', { $scope: $scope });
      $scope.account = 'kuang';
      $scope.password = 'password';
      $scope.reset();
      expect($scope.account).toEqual('');
      expect($scope.password).toEqual('');
    });
  });

  describe('$scope.print', function() {
    it('Print login user information', function () {
      var $scope = {};
      var controller = $controller('loginCtrl', { $scope: $scope });
      $scope.account = 'kuang';
      $scope.password = 'password';
      expect($scope.print()).toEqual('kuang | password');
    });
  });
});