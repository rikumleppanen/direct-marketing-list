# Description

__Topic:__ The purpose of my project is to generate a direct marketing list(s) of contacts who have given an active marketing consent to participate in marketing campaigns. If the customer gives an email address he or she allows emails to be sent during the next two years. There are email, phone, text message and direct home address consents which may have each their own lifespan. In order to limit the case I am going to focus on application logic of 

1) receiving customer contact data, 

2) validate the format and type of data, 

3) compare and 

4) add/update the contact information with the existing customer data and 

5) manage the latest marketing consent of every active customer separately. 

To limit my case further I focus on private customers who have given their emails and/or phone numbers. It is possible that customers have given their name and home address as well, but that is not compulsory in order to maintain a list of phone numbers and emails with active marketing consent.

__Class Diagram__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/Class%20DiagramUpD1.png "Class Diagram (Updated #1")

__Structure of the Software__

Lists of emails and phone numbers are uploaded to the system so that each email and phone number is saved as contacts. 

__Contacts__ from a certain file or channel are collected under single __ContactList__ so as to know where the contact row has been obtained. Before matching contacts with the existing customers, the contacts are parsed and labeled by __Type__ (whether the contact row is email, phone number or unknown).

When comparing the contacts with the existing customers from the __CustomerList__, each contact row is labeled by __State__ (whether the contact row found from the existing customers or not). 

A special __StateKeeper__ collects contact row, Type and State info in a single object so as to add and update customers with the fresh contact data. Finally, the marketing lists can be obtained form the customers who have given an email or phone number consent or both and active consents downloaded from the system for marketing purposes.

__Sequence Diagram: Create a New Marketing List__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image1.JPG "Create a New Marketing List")

__Sequence Diagram: refA1 getMaxConsent__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image2.JPG)

__Sequence Diagram: refA2 getConsentList__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image3.JPG)

__Sequence Diagram: refB1 selectActiveConsents__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image4.JPG)


__Users: Random Users who participate for lottery, system and b2c-people__

__Random user__
- can add his/her contact info: email and/or phone number with corresponding marketing consent
- (__maybe later:__ can delist himself or herself from the list, thus he or she is transferred to suppression list.)

__System__
- validates the contact data given by a random user
- compares the contact data with the existing data
- adds a new contact data if the data cannot be matched by email or phone number to the existing contact else updates the existing data with the fresh data
- tracks the history of existing contacts and the dates and mediums of marketing consent individually with timestamps and expiry dates
- if the individual consent is expired, the contact cannot be used in active marketing campaigns but if the contact gives his or her information again, the consent is again valid for the next 2 years.

__B2C-people__
- can obtain a report of the amount of rows in the marketing list by medium, 
- can sort the lists by the most frequently updated contacts 
- can obtain a list of update history of each contact.

__Test cases to ensure customer cards avoid generating duplicates__

A is a single contact row in a contact list

B is a contact with two rows in a contact list

Cases where a new customer is created, since there is no match in the customer list

Test case 1: A: (add an email address)

Test case 2: A: (add a phone number)

Test case 3: B: (add both email address and phone number)


Cases where an existing customer is updated fresh info after the row was not earlier available

Test case 4: B: (add a phone number to the customer who has the same email address)

Test case 5: B: (add a email address to the customer who has the same phone number)


Cases where contact data is matched with customer data and consents are only updated

Test case 6: A: (add a new email consent)

Test case 7: A: (add a new phone number consent)

Test case 8: B: (add both email and phone number consent)


Cases where contact data is partially matched with customer data and the correct consent is added

Test case 9: A: (add an email address consent to customer who has existing email address and phone number)

Test case 10: A: (add a phone number consent to customer who has existing email address and phone number)


Cases where contact data is partially matched so that the customer data and consents have to be updated

Test case 11: B: (update phone number to the customer who has been matched by email address)

Test case 12: B:  (update email address to the customer who has been matched by phone number)


Test cases are tested in the CustomerListTest File.
