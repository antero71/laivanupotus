#Ohjelman nimi "Laivanupotus"

Peliä pelataan 10 x 10 ruudukolla. Ruudukkoon laitetaan 
erimittaisia laivoja niin että laivojen etäisyys toisistaan
on vähintään yksi ruutu. Laiva voi olla kiinni reunassa. Laivan
on oltava joko pystyssä tai vaakasuorassa, ei vinottain.

## Käyttäjät

Pelaaja ja avustaja

## Avustajan toiminnot

Avustaja valitsee ruudukon vieressä olevasta laivalistasta laivan ja 
asettaa sen pelialueelle, siten että sen etäisyys toisista laivoista 
on vähintään 1 ruutu. Avustaja jatkaa kunnes kaikki laivat on aseteltu
pelialueelle.

## Pelaajan toiminnot

Pelaaja valitsee ruudun johon hän "ampuu". Peli joko ilmoittaa
että ohi meni tai osui. Jos osoi, ruutuun ilmaantuu laiva/laivanosa.

## Laivat

Laivojen pituudet ja määrät ovat
* 1 lentotukialus, 4 ruutua
* 2 risteilijää, 3 ruutua
* 3 hävittäjää, 2 ruutua
* 4 sukellusvenettä, 1 ruutu

## Optio 1: Laivojen paikkojen arvonta

Sovellus arpoo laivojen paikat

## Optio 2: Pistelistaus

Pelaajien pisteiden listaus, joka ammus lasketaan ja 
vähiten ammuksia kuluttanut on kärjessä.
Eli paras mahdollinen tulos on 10 pistettä, eli jokainen laiva
on uponnut, ja yhtään ylimääräistä ammusta ei ole ammuttu.
