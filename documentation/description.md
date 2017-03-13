Topic: The purpose of my project is to generate a direct marketing list of contacts who have given an active marketing consent to participate in marketing campaigns. In order to limit the case I am going to focus on application logic of 1) receiving customer contact data, 2) validate the format and type of data, 3) compare and 4) add/update the contact information and 5) manage the latest marketing consent of every active contact separately. To limit my case further I focus on private customers who have given their emails and/or phone numbers. It is possible that customers have given their name and home address as well, but that is not compulsory in order to maintain a list of phone numbers and emails with active marketing consent.

Viel� varmuudeksi suomeksi:

Aihe: Henkil�asiakkaiden markkinointilupaj�rjestelm�n sovelluslogiikka, joka k�sittelee asiakkaiden antamat yhteystiedot, validoi niiden kirjoitusasun, tarkistaa, onko vastaavilla tiedoilla annettu aikaisemmin lupaa ja yhdist�� ja t�ydent�� tuoreet tiedot luvista, mik�li tiedot on yhdistett�viss� jo olemassa olevaan asiakkaaseen joko s�hk�postiosoitteen tai puhelinnumeron perusteella.

J�rjestelm�n ajatus piilee siin�, ett� siihen saattaa kerty� saman asiakkaan yhteystiedot useaan eri otteeseen ajan mittaan. Tapausesimerkkin� voi olla verkossa olevat arvonnat, joihin k�ytt�j� antaa s�hk�postiosoitteensa lis�ksi palveluntarjoajalle s�hk�postimarkkinointiluvan 2 vuodeksi. Pit�m�ll� kirjaa k�ytt�j�n osallistumiskerroista sek� markkinointiluvista saadaan vahva signaali k�ytt�j�n aktiivisuudesta sek� oikeus hy�dynt�� yhteystietoja markkinoinnissa.  EU:ssa vaatimukset markkinointilupien aktiiviseen ker��miseen ja ker�ttyjen lupien seurantaan tiukkenevat ensi vuonna: http://www.marketingtechnews.net/news/2016/aug/02/european-gdpr-regulations-set-turn-email-marketing-consent-its-head/

Ajan mittaan puhelinnumero tai s�hk�postiosoite saattavat vaihtua, mutta mik�li jompikumpi tiedoista on edelleen sama, niin tiedot yhdistet��n aikaisemmin saatuihin tietoihin ja asiakkaan luvat p�ivittyv�t tuoreimman luvan mukaisiksi. 

Asiakas saattaa antaa vain esimerkiksi puhelinnumeronsa tai s�hk�postiosoitteensa. Samalla h�n antaa m��r�aikaiset markkinointiluvat kyseisiin kanaviin, eli jos h�n antoi puhelinnumeron, h�n antoi my�s puhelinmarkkinointiluvan. Jos h�n antoi s�hk�postiosoitteen, h�n antoi my�s s�hk�postimarkkinointiluvan. Lupapuoli on laajennettavissa my�s kotiosoitteeseen, jolloin asiakas on antanut luvan l�hett�� postia.

Miten projekti rajataan riitt�v�sti?
Projekti rajataan henkil�asiakkaisiin, jotka antavat minimiss��n joko s�hk�postiosoitteen tai puhelinnumeron ja maksimissaan n�iden lis�ksi nimens� ja kotiosoitteensa. Projektissa ei oteta erityisesti kantaa, millaisella lomakkeella yhteystiedot annetaan tai miss� tiedot annetaan, vaan olennaista on yhteystietojen validointi, yhdistely ja lupien ja yhteystietojen tuoreuden varmistaminen ja seuranta. Projektissa ei my�sk��n oteta kantaan siihen, miten yhteystiedot ker�t��n j�rjestelm�st� markkinointitoimenpiteit� varten, ellei aika t�h�n riit�. Yhteystiedot tallennetaan Asiakas-olioihin ja Javan listoihin, eik� tietokantoihin.

Taustaa: https://ico.org.uk/media/for-organisations/documents/1555/direct-marketing-guidance.pdf 

Users: Random users, system and b2c-people

Random user
- can add his/her contact info: email and/or phone number with corresponding marketing consent

System:
validates the contact data given by a random user
compares the contact data with the existing data
adds a new contact data if the data cannot be matched by email or phone number to the existing contact else updates the existing data with the fresh data
tracks the history of existing contacts and the dates and mediums of marketing consent individually

B2C-people
can obtain a report of the amount of rows in the marketing list, 
can sort the lists by the most frequently updated contacts 
can obtain a list of update history of each contact.
