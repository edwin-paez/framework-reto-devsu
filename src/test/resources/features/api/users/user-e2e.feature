@E2E @Regression
Feature: PetStore - Ciclo de vida completo de usuario (CRUD)

  Scenario: Flujo completo CRUD con validación de consistencia de datos

    * def timestamp = java.lang.System.currentTimeMillis()
    * def username  = 'devsu_user_' + timestamp

    * def payload = read('classpath:features/api/users/payloads/user.json')
    * set payload.username = username

    Given url baseUrl + '/user'
    And request payload
    When method POST
    Then status 200

    * match response.code    == 200
    * match response.type    == 'unknown'
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

    * def payloadUpdate = read('classpath:features/api/users/payloads/user.json')
    * set payloadUpdate.firstName = 'Marco'
    * set payloadUpdate.lastName = 'Lopez'
    * set payloadUpdate.email = 'marco.lopez.updated@devsu.com'
    * set payloadUpdate.phone = '3003214567'
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

    * match response.username    == payloadUpdate.username
    * match response.firstName   == payloadUpdate.firstName
    * match response.lastName    == payloadUpdate.lastName
    * match response.email       == payloadUpdate.email
    * match response.phone       == payloadUpdate.phone
    * match response.userStatus  == payloadUpdate.userStatus
    * def responseIdStr = karate.toString(payloadUpdate.id)
    * match responseIdStr == userId


    Given url baseUrl + '/user/' + username
    When method DELETE
    Then status 200

    * match response.code    == 200
    * match response.type    == 'unknown'
    * match response.message == username


    Given url baseUrl + '/user/' + username
    When method GET
    Then status 404

    * match response.code    == 1
    * match response.type    == 'error'
    * match response.message == 'User not found'
