@Sample_Testing
Feature: Title of your feature
  #I want to use this template for my feature file
#
  #@tag1
  #Scenario: Title of your scenario
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |

      
  #@Test1
  #Scenario: Sample Testing
    #Given user login into Application with User ID and Password
#		When user validate the details
#		And user Logoff from the Application
#		
#		@Gmail_Testing
  #Scenario: Gmail_Testing
    #Given User login into Application with <UserID> and <Password>
#		When User send the mail
#		And User Logoff from the Application
		
#		@Instagram_Testing
  #Scenario: Instagram_Testing
    #Given User login into Instagram Application with <UserID> and <Password>
#		When User send the message
#		And User Logoff from the Instagram Application
		
		
		@Instagram_Testing
  Scenario: Instagram ::: Instagram_Testing
    Given User login into Instagram Application with <UserID> and <Password>
		When User send the message
		And User Logoff from the Instagram Application	
		
		