# Mobiiliohjelmointi - Week 4: Jetpack Compose Navigation

Tällä viikolla laajennettiin sovellusta lisäämällä kunnon navigaatio.

## Navigaatio & Arkkitehtuuri
Nyt sovellus on "Single-Activity" -tyylinen, eli meillä on vaan `MainActivity`, joka pyörittää eri ruutuja `NavHostin` avulla.

### Navigointi
- **NavHost**: Tää on se "kontti", joka vaihtaa ruutua sen mukaan mitä linkkiä painetaan.
- **NavController**: Tää hoitaa sen varsinaisen liikkumisen (`navigate("home")`, `navigate("calendar")`).
- **BottomNavigation**: Alapalkki, josta pääsee vaihtaan näkymää (Tehtävät, Kalenteri, Asetukset).

### Jaettu Tila (Shared State)
Mulla on yks ainoa `TaskViewModel`, joka luodaan `MainApp`:n sisällä. Se välitetään sieltä sekä `HomeScreen`:lle että `CalendarScreen`:lle.
- Tän ansiosta jos lisäät taskin "Tehtävät"-sivulla, se näkyy **heti** myös "Kalenteri"-sivulla.
- Molemmat näkymät kuuntelee samaa `StateFlowta`.

## Uudet Näkymät
1.  **HomeScreen**: Vanha tuttu lista.
2.  **CalendarScreen**: Täällä tehtävät on ryhmitelty päivämäärän (`dueDate`) mukaan. Ihan vaan simppeli `LazyColumn`, jossa on otsikot päiville.
3.  **SettingsScreen**: Tää on vielä dummy, tekstinä vaan et täs olis asetukset.

## Dialogit
Lisäys ja muokkaus hoidetaan `AlertDialog`:lla. Se ei oo oma "sivu" navigaatiossa, vaan se lävähtää siihen nykyisen ruudun päälle. Sama dialogi toimii sekä listassa että kalenterissa.
