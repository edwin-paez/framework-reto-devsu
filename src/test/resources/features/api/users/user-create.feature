@Create @Smoke @Regression
Feature: PetStore - Crear usuario

  Scenario: Crear un usuario nuevo exitosamente
    * def timestamp = java.lang.System.currentTimeMillis()
    * def username = 'devsu_user_' + timestamp
    * def payload = read('classpath:features/api/users/payloads/user.json')
    * set payload.username = username

    Given url baseUrl + '/user'
    And request payload
    When method POST
    Then status 200

    * match response.code == 200
    * match response.type == 'unknown'
    * match response.message == '#notnull'

    * def userId = response.message
    
    Given url baseUrl + '/user/' + username
    When method GET
    Then status 200

    * match response.username   == payload.username
    * match response.firstName  == payload.firstName
    * match response.lastName   == payload.lastName
    * match response.email      == payload.email
    * match response.phone      == payload.phone
    * match response.userStatus == payload.userStatus


    @BadUser
    Scenario: Crear un usuario con campo username en null
    
    * def payload = read('classpath:features/api/users/payloads/user.json')
    * def username = null
    * set payload.username = username

    Given url baseUrl + '/user'
    And request payload
    When method POST
    Then status 400


    @BadId
    Scenario: El id enviado por el cliente es ignorado por el backend

    * def payload = read('classpath:features/api/users/payloads/user.json')
    * set payload.id = 999999

    Given url baseUrl + '/user'
    And request payload
    When method POST
    Then status 200

    * def idString = karate.toString(payload.id)
    * match response.message != idString