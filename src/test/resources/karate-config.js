function fn() {
    var env = karate.env || 'qa';
    karate.log('karate.env =', env);

    var config = {};

    if (env === 'qa') {
        config.baseUrl = 'https://petstore.swagger.io/v2';
    } else if (env === 'staging') {
        config.baseUrl = 'https://petstore.swagger.io/v2'; // mismo host — sustituir por URL real de staging
    } else {
        throw 'Unknown environment: ' + env;
    }

    return config;
}
