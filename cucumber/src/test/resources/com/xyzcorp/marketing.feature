Feature: Walking through a sale with a prospective customer
    Scenario: A prospect inquired online about setting up a local LLM
        Given a prospect
        When they submit fill a form about their needs online
        Then we send an email acknowledging the receipt
    Scenario: A prospect will require a sales point of contact
        Given a prospect
        When they submit fill a form about their needs online
        Then the prospect should be added to the "Prospect Queue" topic
