
# Bank kata # 

Bank account kata focus on these opeations (deposit, withdraw and statment printing)

**Requirements**
* Deposit and Withdrawal
* Account statement (date, amount, balance)
* Statement printing

## User Stories ##

- US 1:  
  In order to save money  
  As a bank client  
  I want to make a deposit in my account  


- US 2:  
  In order to retrieve some or all of my savings  
  As a bank client  
  I want to make a withdrawal from my account  


* US 3:  
	In order to check my operations  
	As a bank client  
	I want to see the history (operation, date, amount, balance) of my operations  


### Solution

This application is designated according to the layered architecture model 
- Repository
- Service 
- Application (Presentation)

All business rules are implemented as service in the service layer witch is independant to the repositroy one.  
The last one have to respect the repository contract defined in repository interfaces. It can use different kind   
of persistence model (data base, in memory, etc...)

The bank program is decoulped to the two others too and it only interact with Service layer.


