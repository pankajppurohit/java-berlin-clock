Story: The Berlin Clock

Meta:
@scope interview

Narrative:
    As a clock enthusiast
    I want to tell the time using the Berlin Clock
    So that I can increase then number of ways that I can read the time

Scenario: Midnight
When the time is 00:00:00
Then the clock should look like
Y
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Middle of the afternoon
When the time is 13:17:01
Then the clock should look like
O
RROO
RRRO
YYROOOOOOOO
YYOO

Scenario: Just before midnight
When the time is 23:59:59
Then the clock should look like
O
RRRR
RRRO
YYRYYRYYRYY
YYYY

Scenario: Midnight
When the time is 24:00:00
Then the clock should look like
Y
RRRR
RRRR
OOOOOOOOOOO
OOOO

Scenario: Null time
    When the time is null
    Then the clock should throw an error "Invalid time format"

Scenario: Empty time value
    When the time is ""
    Then the clock should throw an error "Invalid time format"

Scenario: Invalid time format (with dots instead of colons)
    When the time is 23.34.34
    Then the clock should throw an error "Invalid time format"

Scenario: Invalid hour value (greater than 24)
    When the time is 25:30:45
    Then the clock should throw an error "Invalid time format"

Scenario: Invalid minute value (greater than 59)
    When the time is 12:60:30
    Then the clock should throw an error "Invalid time format"

Scenario: Invalid second value (greater than 59)
    When the time is 12:30:60
    Then the clock should throw an error "Invalid time format"

Scenario: Invalid time format (non-numeric value)
    When the time is "abc:def:ghi"
    Then the clock should throw an error "Invalid time format"



