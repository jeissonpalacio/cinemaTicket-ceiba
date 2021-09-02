Feature: Get All Recovered with consult to the API Recovered

  Background:
    * url 'http://localhost:8080/cineadn/'
  Scenario: ticket create- OK
    Given path 'ticket'
    And request {"idClient":1,"amount":15000.00,"idMovieProjector":1,"idSeats":[7]}
    When method POST
    Then status 200

  Scenario: delete ticket
    Given path 'ticket/16'
    When method DELETE
    Then status 200

  Scenario: put ticket
    Given path 'ticket/16'
    And request {"idClient":1,"amount":15000.00,"idMovieProjector":1,"idSeats":[6]}
    When method PUT
    Then status 200

  Scenario: get movies
    Given path '/movies'
    When method GET
    Then status 200

  Scenario: get movieprojector
    Given path '/movieprojector'
    When method GET
    Then status 200


