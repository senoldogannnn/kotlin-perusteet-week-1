# Mobiiliohjelmointi - Week 6: Room Database Integration (Weather App Caching)

Tällä viikolla sääsovellukseen lisättiin **Room**-tietokanta, joka toimii välimuistina (caching). Tämä vähentää verkkoliikennettä ja mahdollistaa sovelluksen käytön (osittain) ilman nettiyhteyttä.

## Arkkitehtuuri & Tietovirta
Sovellus noudattaa Google suosittelemaa arkkitehtuuria:
`UI` -> `ViewModel` -> `Repository` -> (`Local Database` + `Remote API`)

1.  **UI (WeatherScreen)**: Pyytää säätä ViewModelilta.
2.  **ViewModel (WeatherViewModel)**: Välittää pyynnön Repositorylle.
3.  **Repository (WeatherRepository)**: Päättää mistä data haetaan (Välimuisti vs Verkko).
4.  **Local (Room)**: Tallentaa säätiedot pysyvästi laitteelle.
5.  **Remote (Retrofit)**: Hakee tuoreimman sään OpenWeatherMap:sta.

## Välimuistilogiikka (Caching Logic)
Repository tarkistaa ensin onko kaupungin sää tallennettu tietokantaan.
- **Jos data on tallella JA tuoretta (< 30 min vanhaa)**: Palautetaan data suoraan tietokannasta. API-kutsua ei tehdä.
- **Jos data on vanhaa (> 30 min) TAI puuttuu**: Tehdään API-kutsu, tallennetaan uusi data tietokantaan ja palautetaan se käyttäjälle.

## Teknologiat
- **Room**: SQL-tietokanta (Entity, DAO, Database).
- **Retrofit + Gson**: Verkkoyhteydet.
- **Coroutines & Flow**: Asynkroninen datankäsittely.
- **ViewModel & LiveData/StateFlow**: UI:n tilanhallinta.
