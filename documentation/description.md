# Description

__Topic:__ The purpose of my project is to generate a direct marketing list(s) of contacts who have given an active marketing consent to participate in marketing campaigns. If the customer gives an email address he or she allows emails to be sent during the next two years. There are email, phone, text message and direct home address consents which may have each their own lifespan. In order to limit the case I am going to focus on application logic of 

1) receiving customer contact data, 

2) validate the format and type of data, 

3) compare and 

4) add/update the contact information with the existing customer data and 

5) manage the latest marketing consent of every active customer separately. 

To limit my case further I focus on private customers who have given their emails and/or phone numbers. It is possible that customers have given their name and home address as well, but that is not compulsory in order to maintain a list of phone numbers and emails with active marketing consent.

__Class Diagram__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/Class%20DiagramUpD2.png "Class Diagram (Updated #2")

__Structure of the Software__

Lists of emails and phone numbers are uploaded to the system so that each email and phone number is saved as contacts. 

__Contacts__ from a certain file or channel are collected under single __ContactList__ so as to know where the contact row has been obtained. Before matching contacts with the existing customers, the contacts are parsed and labeled by __Type__ (whether the contact row is email, phone number or unknown).

When comparing the contacts with the existing customers from the __CustomerList__, each contact row is labeled by __State__ (whether the contact row found from the existing customers or not). 

A special __StateKeeper__ collects contact row, Type and State info in a single object so as to add and update customers with the fresh contact data. Finally, the marketing lists can be obtained form the customers who have given an email or phone number consent or both and active consents downloaded from the system for marketing purposes.

An __Operator__ is a medium between DashBoardUI and CustomerList. Operator contains all the methods required in the DashBoardUI.

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


