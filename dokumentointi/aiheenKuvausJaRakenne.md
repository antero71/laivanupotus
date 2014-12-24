#Ohjelman nimi "Laivanupotus"

Peliä pelataan 10-15 x 10-15 ruudukolla. Ruudukkoon laitetaan 
erimittaisia laivoja niin että laivojen etäisyys toisistaan
on vähintään yksi ruutu. Laiva voi olla kiinni reunassa. Laivan
on oltava joko pystyssä tai vaakasuorassa, ei vinottain.

## Käyttäjät

Pelaaja 

## Pelin aloitus

Pelaaja antaa nimensä ja valitsee ruudukon koon, oletuksena 10 x 10 ruutua
maksimissaan 15 x 15 ruutua. 

Ohjelma arpoo vastustajan laivojen paikat omalle ruudukolleen.

## Pelaajan toiminnot

Pelaaja antaa nimensä tulostilastoa varten. Sen jälkeen pelaaja voi suurentaa
ruudukkoa oletuksen 10 x 10 ruudukosta maksimissaan 15 x 15 ruudukkoon.

Pelaaja asettaa laivansa omaan ruudukkoonsa ja sen jälkeen voidaan aloittaa peli.

Kun peli aloitetaan pelaaja valitsee ruudun johon hän "ampuu". Peli joko ilmoittaa
että ohi meni tai osui. Jos osoi, ruutuun ilmaantuu laiva/laivanosa. Ja pelaaja saa 
jatkaa kunnes tulee huti.
Jos osuu niin pelaajan pistesaldoa päivitetään. Pelaajan ampumisen jälkeen tietokone
ampuu, jos se osuu, se ampuu uudelleen kunnes menee ohi.

## Laivat

Laivojen pituudet ja määrät ovat
* 1 lentotukialus, 4 ruutua
* 2 risteilijää, 3 ruutua
* 3 hävittäjää, 2 ruutua
* 4 sukellusvenettä, 1 ruutu

## Pistelistaus

Pelaajien pisteiden listaus. Pelaaja saa osumasta 100 pistettä ja menettää 
ohiammutusta 50 pistettä. Pelaaja voi siis saada loppupisteekseen joko negatiivisen 
tai positiivisen arvon. Paras pistemäärä
voittaa.
