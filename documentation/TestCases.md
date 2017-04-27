# Test cases to ensure customer cards avoid generating duplicates

A is a single contact row in a contact list

B is a contact with two rows in a contact list

__Cases where a new customer is created, since there is no match in the customer list__

Test case 1: A: (add an email address)

Test case 2: A: (add a phone number)

Test case 3: B: (add both email address and phone number)


__Cases where an existing customer is updated fresh info after the row was not earlier available__

Test case 4: B: (add a phone number to the customer who has the same email address)

Test case 5: B: (add a email address to the customer who has the same phone number)


__Cases where contact data is matched with customer data and consents are only updated__

Test case 6: A: (add a new email consent)

Test case 7: A: (add a new phone number consent)

Test case 8: B: (add both email and phone number consent)


__Cases where contact data is partially matched with customer data and the correct consent is added__

Test case 9: A: (add an email address consent to customer who has existing email address and phone number)

Test case 10: A: (add a phone number consent to customer who has existing email address and phone number)


__Cases where contact data is partially matched so that the customer data and consents have to be updated__

Test case 11: B: (update phone number to the customer who has been matched by email address)

Test case 12: B:  (update email address to the customer who has been matched by phone number)


Test cases are tested in the CustomerListTest File.
