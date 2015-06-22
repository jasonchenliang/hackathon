dependencies = [
    'ngRoute',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.services',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'myApp.routeConfig'
]

app = angular.module('myApp', dependencies)

angular.module('myApp.routeConfig', ['ngRoute'])
    .config ($routeProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/assets/partials/main.html'
            })
            .when('/login', {
                templateUrl: '/assets/partials/login.html'
            })
            .when('/search/:category/:keyword', {
                templateUrl: '/assets/partials/search.html'
            })
            .when('/category/:cid', {
                templateUrl: '/assets/partials/category.html'
            })
            .when('/product/:productId', {
                templateUrl: '/assets/partials/product.html'
            })
            .when('/users/create', {
                templateUrl: '/assets/partials/create.html'
            })
            .when('/users/edit/:firstName/:lastName', {
                templateUrl: '/assets/partials/update.html'
            })
            .otherwise({redirectTo: '/login'})
    .config ($locationProvider) ->
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })
    .run ($rootScope, $location, $log) ->
      $rootScope.$on('$locationChangeStart', ()->
        $log.debug "chnagestar"
        $log.debug $rootScope.currentUser

        if $rootScope.currentUser in [undefined, {}] then $location.path('/login')
        )


@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])
