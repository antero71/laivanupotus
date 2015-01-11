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

Ohjelma arpoo vastustajan ja pelaajan laivojen paikat omalle ruudukoilleen. Tietokoneen 
ruudukko tulee ylempään osaan näyttöä ja pelaajan ruudukko sen alapuolelle.

## Pelaajan toiminnot

Pelaaja antaa nimensä tulostilastoa varten. Sen jälkeen pelaaja voi suurentaa
ruudukkoa oletuksen 10 x 10 ruudukosta maksimissaan 15 x 15 ruudukkoon.

Kun peli aloitetaan pelaaja valitsee ruudun johon hän "ampuu". (Klikkaa hiirellä jotain
pelialuueen ruutua). Peli joko ilmoittaa
että ohi meni tai osui. Jos osoi, ruutuun ilmaantuu laiva/laivanosa, ruutu jossa X-kirjain ja 
taustaväri punainen. Jos menee ohi, ruutuun tulee o-kirjain. Osuman jälkeen pelaaja saa 
jatkaa kunnes tulee huti. Ampumisen jälkeen pistesaldoa päivitetään, osumasta saa 100 pistettä ja ohiammutusta menettää 50 pistettä. Jos laiva uppoaa siitä saa 1000 pistettä. 

Pelaajan ampumisen jälkeen tietokone
ampuu, jos se osuu, se ampuu uudelleen kunnes menee ohi.

Peli päivittää pelaajan ja tietokoneen pisteitä näytön yläreunan pistenäytölle.

## Laivat

Laivojen pituudet ja määrät ovat
* 1 lentotukialus, 4 ruutua
* 2 risteilijää, 3 ruutua
* 3 hävittäjää, 2 ruutua
* 4 sukellusvenettä, 1 ruutu

## Pistelistaus

Pelaajien pisteiden listaus. Pelaaja saa osumasta 100 pistettä ja menettää 
ohiammutusta 50 pistettä. Upotuksesta saa 1000 pistettä. Paras pistemäärä
voittaa.
