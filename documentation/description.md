# Description

__Topic:__ The purpose of my project is to generate a direct marketing list(s) of contacts who have given an active marketing consent to participate in marketing campaigns. If the customer gives an email address he or she allows emails to be sent during the next two years. There are email, phone, text message and direct home address consents which may have each their own lifespan. In order to limit the case I am going to focus on application logic of 

1) receiving customer contact data, 

2) validate the format and type of data, 

3) compare and 

4) add/update the contact information and 

5) manage the latest marketing consent of every active contact separately. 

To limit my case further I focus on private customers who have given their emails and/or phone numbers. It is possible that customers have given their name and home address as well, but that is not compulsory in order to maintain a list of phone numbers and emails with active marketing consent.

__Class Diagram__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/Class%20DiagramUpD1.png "Class Diagram (Updated #1")

__Sequence Diagram: Create a New Marketing List__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image1.jpg "Create a New Marketing List")

__Sequence Diagram: refA1 getMaxConsent__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image2.jpg)

__Sequence Diagram: refA2 getConsentList__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image3.jpg)

__Sequence Diagram: refB1 selectActiveConsents__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/image4.jpg)


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



