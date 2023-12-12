Feature: A customer wants to get a rid to their hotel
    Scenario: An existing customer arrives at the airport and
    is needing a ride to the hotel, and they already
    have a hotel that is booked.
        Given an existing ride customer
        When that customer arrives at the TPA airport
        And they already have a "Hilton Beachside Hotel" booked
        And they booked a ride on our mobile application
        And they are waiting at the closet arrival door C5
        Then we will make a request to ride services with the location
    Scenario: A existing customer arrives at the airport and
    is needing a ride to the hotel, and they do not have a hotel
    booked, they should provide an address to event
        Given an existing customer
        And that customer arrives at the TPA airport
        And they do not have a hotel booked
        When the customer click on "Find Ride" on the application
        Then the application well ask for an address of the venue that they want to be close to
    Scenario: A existing customer arrives at the airport and
    is needing a ride to the hotel, and they do not have a hotel
    booked, but they have address event added
        Given an existing customer
        When that customer arrives at the TPA airport
        And they do not have a hotel booked
        And they booked a ride on our mobile application
        And they selected a venue that they want to be close to
        And they are waiting at the closet arrival door C5
        Then we well find available hotels that have vacancy
        And we will list options for available hotels with their prices
