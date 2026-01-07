# Viikkotehtävä 1: Domain + Kotlin + Compose

Tässä on mun eka Kotlin-harkka. Tein tän Android Studiolla ja Composella.

## Mitä tää tekee?
Tää appi näyttää listan mun tehtävistä (Tasks). Siel on otsikko, kuvaus, pvm ja priority.

## Tietomalli (Domain)
- `Task`: Perus data class missä on id, title, desc, jne.
- `mockTasks`: Yksinkertainen lista tehtäviä (kovakoodattu), jotta näkee jotain ruudulla.

## Funktiot (Logic)
Tein nää funktiot `TaskLogic.kt` tiedostoon:
- `addTask`: Lisää uuden taskin listaan.
- `toggleDone`: Vaihtaa `done` statuksen (tehty/ei tehty).
- `filterByDone`: Suodattaa listaa.
- `sortByDueDate`: Järjestää pvm mukaan (tätä käytetään `HomeScreen`:ssä).

## Miten ajaa?
Avaa Android Studio ja paina Play-nappia. Pitäis näkyä lista taskeista.
