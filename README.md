# Mobiiliohjelmointi - Week 5: Weather App (Retrofit + OpenWeather API)

Tällä viikolla rakennettiin sääsovellus, joka hakee dataa OpenWeatherMap API:sta.

## Teknologiat & Ratkaisut

### Retrofit & JSON
- **Retrofit**: Hoitaa HTTP-pyynnöt verkkoon. Se luo `WeatherApi`-rajapinnan toteutuksen taustalla.
- **Gson**: Toimii konvertterina, joka muuttaa API:sta tulevan JSON-vastauksen suoraan Kotlinin data-luokiksi (`WeatherResponse`).

### Coroutines
- API-haku tehdään `suspend`-funktiolla (`getWeather`).
- Kutsu käynnistetään `ViewModel`issa `viewModelScope.launch` -blokissa. Tämä varmistaa, että verkkoliikenne tapahtuu taustasäikeessä (IO), eikä jumita käyttöliittymää (Main Thread).

### UI Tila (State Management)
- **ViewModel**: Pitää yllä sovelluksen tilaa (`WeatherUiState`).
- `WeatherUiState` on `sealed interface`, jolla on kolme tilaa:
    1.  `Idle`: Odottaa syötettä.
    2.  `Loading`: Haku käynnissä (näytetään latausympyrä).
    3.  `Success`: Data saapui (näytetään sää).
    4.  `Error`: Jotain meni pieleen (näytetään virheviesti).
- **Compose**: `WeatherScreen` kuuntelee tilaa (`collectAsState`) ja päivittää näkymän automaattisesti tilan muuttuessa.

### API Key Turvallisuus
- API-avainta **ei** ole kovakoodattu koodiin.
- Se on tallennettu `local.properties` -tiedostoon, jota ei laiteta Gitiin.
- Build-vaiheessa Gradle lukee sen ja luo `BuildConfig.OPEN_WEATHER_API_KEY` -vakion, jota koodi käyttää.
