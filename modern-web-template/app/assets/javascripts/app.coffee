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
            .when('/category/:id)', {
                templateUrl: '/assets/partials/category.html'
            })
            .when('/users/create', {
                templateUrl: '/assets/partials/create.html'
            })
            .when('/users/edit/:firstName/:lastName', {
                templateUrl: '/assets/partials/update.html'
            })
            .otherwise({redirectTo: '/'})
    .config ($locationProvider) ->
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })

@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])