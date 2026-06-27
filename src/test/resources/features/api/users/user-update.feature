@Update @Regression
Feature: PetStore - Actualizar usuario


  Scenario: Actualizar datos del usuario

    * def setup    = call read('classpath:features/api/users/user-setup.feature')
    * def username = setup.username
    * def userId   = setup.userId
    * def payloadUpdate = read('classpath:features/api/users/payloads/user.json')
    * set payloadUpdate.firstName = 'Carlos'
    * set payloadUpdate.lastName = 'Paez'
    * set payloadUpdate.email = 'carlos.paez.updated@devsu.com'
    * set payloadUpdate.phone = '3001234567'
    * set payloadUpdate.username = username
    * set payloadUpdate.id = userId
    * set payloadUpdate.userStatus = 1


    Given url baseUrl + '/user/' + username
    And request payloadUpdate
    When method PUT
    Then status 200

    * match response.code    == 200
    * match response.type    == 'unknown'
    * match response.message == userId


    Given url baseUrl + '/user/' + username
    When method GET
    Then status 200

    * match response.username  == username
    * match response.firstName == payloadUpdate.firstName
    * match response.email     == payloadUpdate.email
    * match response.lastName  == payloadUpdate.lastName
    * match response.phone     == payloadUpdate.phone
    * def responseIdStr = karate.toString(response.id)
    * match responseIdStr == userId
    * match response.userStatus == payloadUpdate.userStatus


    Scenario: Actualizar usuario no existente

    * def timestamp = java.lang.System.currentTimeMillis()
    * def username = 'devsu_user_' + timestamp
    * def setup    = call read('classpath:features/api/users/user-setup.feature')
    * def username = setup.username
    * def userId   = setup.userId
    * def payloadUpdate = read('classpath:features/api/users/payloads/user.json')
    * set payloadUpdate.firstName = 'Carlos'
    * set payloadUpdate.lastName = 'Paez'
    * set payloadUpdate.email = 'carlos.paez.updated@devsu.com'
    * set payloadUpdate.phone = '3001234567'
    * set payloadUpdate.username = username
    * set payloadUpdate.id = userId
    * set payloadUpdate.userStatus = 1


    Given url baseUrl + '/user/' + username
    And request payloadUpdate
    When method PUT
    Then status 200

    * match response.code    == 200
    * match response.type    == 'unknown'
    * match response.message == userId

    Given url baseUrl + '/user/' + username
    When method GET
    Then status 200

    * match response.username  == username
    * match response.firstName == payloadUpdate.firstName
    * match response.email     == payloadUpdate.email
    * match response.lastName  == payloadUpdate.lastName
    * match response.phone     == payloadUpdate.phone
    * def responseIdStr = karate.toString(response.id)
    * match responseIdStr == userId
    * match response.userStatus == payloadUpdate.userStatus