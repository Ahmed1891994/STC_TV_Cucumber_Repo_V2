@STCPlans
Feature: STC Plans Tests

Background:
	Given User is in STC Plan URL
  
 Scenario Outline: Verify Stc Tv Plan Prices
    Given User Chooses <country>
    Then Verify prices for each plan for <country>
      
 Examples:
			|country|
			|"KSA"|
#			|"Kuwait"|
#			|"Bahrain"|
			
 #Scenario Outline: Verify Stc Tv Plan Type
 #   Given User Chooses <country>
 #   Then Verify Plan Type for <country>
      
 #Examples:
	#		|country|
	#		|"KSA"|
	#		|"Kuwait"|
	#		|"Bahrain"|
			
 #Scenario Outline: Verify Stc Tv Plan Currency
 #   Given User Chooses <country>
 #   Then Verify Currency for each plan for <country>
    
 #Examples:
#			|country|
#			|"KSA"|
#			|"Kuwait"|
#			|"Bahrain"|
