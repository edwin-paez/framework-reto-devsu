@ignore
Feature: Helper - Crear usuario de prueba (reutilizable)

  Scenario: Crear usuario base para reutilizar en otros escenarios
  
    * def timestamp = java.lang.System.currentTimeMillis()
    * def username = 'devsu_user_' + timestamp
    * def payload = read('classpath:features/api/users/payloads/user.json')
    * set payload.username = username

    Given url baseUrl + '/user'
    And request payload
    When method POST
    Then status 200

    * match response.message == '#notnull'
    * def userId = response.message
