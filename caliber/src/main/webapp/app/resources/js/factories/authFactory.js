auth.module("auth").factory("authfactory", function($log, $http, $cookies){
    var auth = {};

    $log.debug("Booted Authentication Factory");

    auth.auth = function(){

    };

    auth.authQC = function(){

    };

    auth.authVP = function(){

    };

    auth.authTrainer = function(){

    };

    function getCookie(){
        return $cookie.get("");
    }

    return auth;
});