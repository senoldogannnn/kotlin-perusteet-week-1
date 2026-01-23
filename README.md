# Mobiiliohjelmointi - Week 3: MVVM & Jetpack Compose

Täs prokkiksessa harjoteltiin **Model-View-ViewModel (MVVM)** -arkkitehtuuria ja käytettiin Jetpack Composea sekä StateFlowta.

## MVVM-arkkitehtuuri
Ideana on jakaa koodi kolmeen osaan, et se pysyis siistinä ja ois helpompi testata:

1.  **Model (`com.example.week1.model`)**:
    - Tänne tulee kaikki data ja logiikka.
    - `Task.kt`: Ihan perus dataclassi taskeille.
    - `TaskLogic.kt`: Apufunktiot jos tarvii pyöritellä dataa.

2.  **View (`com.example.week1.view`)**:
    - Tää hoitaa vaan sen miltä appi näyttää.
    - `HomeScreen.kt`: Päänäkymä missä listataan hommat.
    - `DetailDialog.kt`: Ikkuna missä voi muokkaa tai poistaa taskin.
    - View vaan **kuuntelee** ViewModelia ja päivittyy sen mukaan.

3.  **ViewModel (`com.example.week1.viewmodel`)**:
    - Tää toimii siltana Modelin ja Viewin välissä.
    - `TaskViewModel.kt`: Täällä pidetään yllä tilaa (`StateFlow`) ja hoidetaan käyttäjän toiminnot (esim. lisäys tai poisto).
    - UI-logiikka pysyy täällä eikä sekoitu näyttöön.

## StateFlow
**StateFlow** on tapa hallita tilaa silleen modernisti.

- `TaskViewModel` tarjoaa ulospäin `StateFlow<List<Task>>` -listan.
- View sit "kollectaa" tätä (`collectAsState()`) ja kuuntelee muutoksia.
- Heti ku listaan tulee muutos ViewModelissa, UI piirtää ittesä uusiks automaattisesti. Ei tarvii mitään manuaalista säätöä.

## Ominaisuudet
- **Lisää taski**: Kirota nimi ja kuvaus, paina nappia.
- **Muokkaa/Poista**: Klikkaa taskia ni aukee dialogi. Siellä voi editoia tekstejä tai heittää roskiin.
- **Merkkaa tehdyks**: Checkboxilla voi ruksia homman valmiiks.
- **Reaktiivinen UI**: Kaikki päivittyy heti ruudulle StateFlown ansiosta.
