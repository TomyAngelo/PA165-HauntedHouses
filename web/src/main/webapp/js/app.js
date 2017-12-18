var module = angular.module('hauntedHouses', ['ngRoute', 'hauntedHousesControllers', 'hauntedHousesServices']);

module.config(function ($routeProvider) {

    $routeProvider
            .when('/', {
                templateUrl: 'partials/home.html'
            })
            .when('/house', {
                templateUrl: 'partials/houses.html',
                controller: 'housesCtrl'
            })
            .when('/house/newhouse', {
                templateUrl: 'partials/new_house.html',
                controller: 'newHouseCtrl'
            })
            .when('/house/:id', {
                templateUrl: 'partials/house_detail.html',
                controller: 'houseDetailCtrl'
            })
            .when('/ghost', {
                templateUrl: 'partials/ghosts.html',
                controller: 'ghostsCtrl'
            })
            .when('/ghost/:id', {
                templateUrl: 'partials/ghost_detail.html',
                controller: 'ghostDetailCtrl'
            })
            .when('/ability', {
                templateUrl: 'partials/abilities.html',
                controller: 'abilitiesCtrl'
            })
            .when('/ability/:id', {
                templateUrl: 'partials/ability_detail.html',
                controller: 'abilityDetailCtrl'
            })
            .when('/forbidden', {
                templateUrl: 'partials/forbidden.html'
            })
            .otherwise({redirectTo: '/'});

});

module.run(function ($rootScope, $location, $window, loggedUserFactory) {

    loggedUserFactory.getPrincipal(
            function (response) {

                var values = JSON.parse(response.data);

                $rootScope.principal = values.username;
                $rootScope.role = values.role;
            },
            function (response) {
                alert("An error occurred when getting the logged user.");
            }
    );

    $rootScope.unsuccessfulResponse = function (response) {
        if (response.status === 403) {
            $rootScope.page = $location.path();
            $location.path("/forbidden");
        } else if (response.status === 401) {
            $window.location.href = "login.html";
        } else if (response.status === 400 || response.status === 409) {
            document.getElementById('errorOutput').style.display = 'block';
            setTimeout(function () {
                document.getElementById('errorOutput').style.display = 'none';
            }, 3000);
        }
    };

});