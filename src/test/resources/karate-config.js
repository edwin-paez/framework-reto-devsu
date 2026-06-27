function fn() {
    var env = karate.env || 'qa';
    karate.log('karate.env =', env);

    var config = {};

    if (env === 'stg') {
        config.baseUrl = 'https://petstore.swagger.io/v2';
    } else if (env === 'dev') {
        config.baseUrl = 'https://petstore.swagger.io/dev/v2';
    } else if (env === 'prod') {
        config.baseUrl = 'https://petstore.swagger.io/prod/v2';
    } else {
        config.baseUrl = 'https://petstore.swagger.io/v2';
    }

    return config;
}
