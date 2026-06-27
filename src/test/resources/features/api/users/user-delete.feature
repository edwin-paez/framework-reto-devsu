@Delete @Regression
Feature: PetStore - Eliminar usuario


  Scenario: Eliminar usuario y verificar que ya no existe

    * def setup    = call read('classpath:features/api/users/user-setup.feature')
    * def username = setup.username

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

   #Se valida que el usuario no se pueda eliminar de nuevo

    Given url baseUrl + '/user/' + username
    When method DELETE
    Then status 404