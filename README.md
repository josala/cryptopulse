# Cryptopulse

Android MVVM architectured app, structured in gradle modules for demo purposes.
Uses Koin dependency injection framework for it's simplicity and coroutines to execute async tasks such as database fetching or network requests.

## Modules

- App: Contains App class for Koin modules injection and MainActivity that handles all navigation using single activity pattern
- Core: Contains common dependencies for other modules, such as Routing interface for navigation
- Database: Module for creating and accessing Room database
- Network: Module for Retrofit network clients and BaseService to handle network Responses
- Features: Each feature contains needed gradle module dependencies and business layer unit testing
- Feature.CoinmarketCap: Populates list of latest cryptocurrencies/tokens retrieved from coinmarketcap api and shows and navigates to a detail where data can be stored on database
- Feature.Favorites: Shows a list of all stored crypto/tokens in the database