# Description

__Topic:__ The purpose of my project is to generate a direct marketing list(s) of contacts who have given an active marketing consent to participate in marketing campaigns. If the customer gives an email address he or she allows emails to be sent during the next two years. There are email, phone, text message and direct home address consents which may have each their own lifespan. In order to limit the case I am going to focus on application logic of 

1) receiving customer contact data, 

2) validate the format and type of data, 

3) compare and 

4) add/update the contact information and 

5) manage the latest marketing consent of every active contact separately. 

To limit my case further I focus on private customers who have given their emails and/or phone numbers. It is possible that customers have given their name and home address as well, but that is not compulsory in order to maintain a list of phone numbers and emails with active marketing consent.

__Class Diagram__

![Alt text](https://github.com/rikumleppanen/direct-marketing-list/blob/master/documentation/Class%20Diagram.png "Class Diagram")

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

Vielä varmuudeksi suomeksi - kirjoitan englanniksi myöhemmin tärkeimpine osineen:

Aihe: Henkilöasiakkaiden markkinointilupajärjestelmän sovelluslogiikka, joka käsittelee asiakkaiden antamat yhteystiedot, validoi niiden kirjoitusasun, tarkistaa, onko vastaavilla tiedoilla annettu aikaisemmin lupaa ja yhdistää ja täydentää tuoreet tiedot luvista, mikäli tiedot on yhdistettävissä jo olemassa olevaan asiakkaaseen joko sähköpostiosoitteen tai puhelinnumeron perusteella.

Järjestelmän ajatus piilee siinä, että siihen saattaa kertyä saman asiakkaan yhteystiedot useaan eri otteeseen ajan mittaan. Tapausesimerkkinä voi olla verkossa olevat arvonnat, joihin käyttäjä antaa sähköpostiosoitteensa lisäksi palveluntarjoajalle sähköpostimarkkinointiluvan 2 vuodeksi. Pitämällä kirjaa käyttäjän osallistumiskerroista sekä markkinointiluvista saadaan vahva signaali käyttäjän aktiivisuudesta sekä oikeus hyödyntää yhteystietoja markkinoinnissa.  EU:ssa vaatimukset markkinointilupien aktiiviseen keräämiseen ja kerättyjen lupien seurantaan tiukkenevat ensi vuonna: http://www.marketingtechnews.net/news/2016/aug/02/european-gdpr-regulations-set-turn-email-marketing-consent-its-head/

Ajan mittaan puhelinnumero tai sähköpostiosoite saattavat vaihtua, mutta mikäli jompikumpi tiedoista on edelleen sama, niin tiedot yhdistetään aikaisemmin saatuihin tietoihin ja asiakkaan luvat päivittyvät tuoreimman luvan mukaisiksi. 

Asiakas saattaa antaa vain esimerkiksi puhelinnumeronsa tai sähköpostiosoitteensa. Samalla hän antaa määräaikaiset markkinointiluvat kyseisiin kanaviin, eli jos hän antoi puhelinnumeron, hän antoi myös puhelinmarkkinointiluvan. Jos hän antoi sähköpostiosoitteen, hän antoi myös sähköpostimarkkinointiluvan. Lupapuoli on laajennettavissa myös kotiosoitteeseen, jolloin asiakas on antanut luvan lähettää postia.

Miten projekti rajataan riittävästi?
Projekti rajataan henkilöasiakkaisiin, jotka antavat minimissään joko sähköpostiosoitteen tai puhelinnumeron ja maksimissaan näiden lisäksi nimensä ja kotiosoitteensa. Projektissa ei oteta erityisesti kantaa, millaisella lomakkeella yhteystiedot annetaan tai missä tiedot annetaan, vaan olennaista on yhteystietojen validointi, yhdistely ja lupien ja yhteystietojen tuoreuden varmistaminen ja seuranta. Projektissa ei myöskään oteta kantaan siihen, miten yhteystiedot kerätään järjestelmästä markkinointitoimenpiteitä varten, ellei aika tähän riitä. Yhteystiedot tallennetaan Asiakas-olioihin ja Javan listoihin, eikä tietokantoihin.

Taustaa: https://ico.org.uk/media/for-organisations/documents/1555/direct-marketing-guidance.pdf 

