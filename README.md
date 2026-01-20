# Kotlin Perusteet - Week 2 Tehtävä

Tässä on mun Week 2 harkka. Laajensin sitä ekaa prokkista ja lisäsin ViewModelin ja paremman UI:n.

## Mitä tässä on?
- **TaskViewModel**: Tää hoitaa nyt listan datat, ettei ne katoa.
- **LazyColumn**: Käytin tätä listaukseen, koska se on tehokkaampi.
- **Filtteröinti**: Lisäsin napit millä saa näkyviin kaikki tai vaan tehdyt.
- **Toiminnot**: 
  - Voi lisätä uusia juttuja.
  - Voi ruksia tehdyksi.
  - Voi järjestää pvm mukaan.

## Miten State toimii?
Composessa homma toimii silleen, että kun data (State) muuttuu, niin UI piirtää itsensä uusiks. Mä siirsin nää tilat `TaskViewModel`:iin (state hoisting), jotta UI-koodi pysyy siistinä ja vaan näyttää asiat.

## ViewModel vs remember
- **remember**: Tää on ok pikkujutuille, mut jos kääntää näyttöä nii nää tiedot katoaa.
- **ViewModel**: Tää on parempi tämmöselle datalle, koska se pysyy hengissä vaikka config muuttuis (esim. ruudun kääntö). Siks käytin tätä tehtävälistaan.

## Tekijä
Senol Dogan

