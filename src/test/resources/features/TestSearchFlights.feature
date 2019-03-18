Feature: Search flights

  Scenario Outline: Search Cheapest flight
    Given I visit https://www.phptravels.net/
    And I click Flights
    And I select <tripType>, <from> - <to>
    And I select departure date <daysFromToday>
    And I select return date <daysFromDeparture>
    And I select <adult> adult, <child> child
    When I click Search button
    And I filter by <carriers>
    Then I click book now on cheapest price
    And I am taken to booking page

    Examples:

      | tripType | from            | to              | daysFromToday | daysFromDeparture | adult | child | carriers                               |
      | ROUND    | London City Arp | Dubai Intl Arpt | 14            | 14                | 2     | 2     | LATAM Airlines Colombia, Qatar Airways |