@web
Feature: Accessibility of various websites

  Scenario: inquisitum.co.uk meets accessibility AA WCAG standards
    Given the "inquisitum" login page is loaded
    And axe has been injected
    When an axe scan is executed for AA WCAG level
    Then no violations should occur

  Scenario: the example of a good website from WAI meets AA accessibility WCAG standards
    Given the "good WAI example" login page is loaded
    And axe has been injected
    When an axe scan is executed for AA WCAG level
    Then no violations should occur

  Scenario: inquisitum.co.uk meets accessibility A WCAG standards
    Given the "inquisitum" login page is loaded
    And axe has been injected
    When an axe scan is executed for A WCAG level
    Then no violations should occur

  Scenario: the example of a good website from WAI meets A accessibility WCAG standards
    Given the "good WAI example" login page is loaded
    And axe has been injected
    When an axe scan is executed for A WCAG level
    Then no violations should occur
