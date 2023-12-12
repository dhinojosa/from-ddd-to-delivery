Feature: A customer wishes to make a claim
    Scenario: A customer makes a claim
    within 90 days after an auto-accident.
        Given an existing customer
        When that customer files a claim
        And the claim is within 90 days of the auto accident
        Then the claim will be flagged as SUBMITTED
