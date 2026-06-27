@Read @Smoke @Regression
Feature: PetStore - Buscar usuario

  Scenario: Buscar el usuario creado por username
    * def setup = call read('classpath:features/api/users/user-setup.feature')
    * def username = setup.username
    * def payload  = setup.payload
    * def userId   = setup.userId

    Given url baseUrl + '/user/' + username
    When method GET
    Then status 200

    * match response.username    == payload.username
    * match response.firstName   == payload.firstName
    * match response.lastName    == payload.lastName
    * match response.email       == payload.email
    * match response.phone       == payload.phone
    * match response.userStatus  == payload.userStatus
    * def responseIdStr = karate.toString(response.id)
    * match responseIdStr == userId