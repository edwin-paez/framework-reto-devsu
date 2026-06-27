@PurchaseFlow @Smoke @Regression
Feature: Flujo de compra exitosa en SauceDemo

  @CP-001 @Smoke
  Scenario Outline: Compra exitosa de dos productos

    Given un usuario autenticado con credenciales:
      | <username> | <password> |
    When agrega 2 productos al carrito
    And inicia el proceso de checkout
    And completa la compra
    Then el sistema muestra el mensaje de confirmación de compra

    Examples:
    
      | username      | password     |
      | standard_user | secret_sauce |