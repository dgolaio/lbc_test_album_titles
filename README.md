# LBC Test Album Titles

This project is an example of how to consume an API to fetch a list of albums, store them locally using Room, and display them in an Android interface using Jetpack Compose. The architecture used in the project is based on the **MVVM** and 
**Clean Architecture** patterns.

# Check Out LBC_Explanation_Document For more Info

## Technologies Used

- **Kotlin**: Main programming language.
- **Jetpack Compose**: Library for building UIs declaratively.
- **Room Database**: Local data persistence on the device.
- **Hilt**: Dependency injection.
- **Retrofit**: HTTP client for making API requests.
- **Moshi**: Library for JSON conversion.
- **Kotlin Coroutines**: Asynchronous task management.
- **Flow**: For reactive data flow between the UI and the repository.
- **JUnit and Mockito**: For unit testing.

## Project Structure

The project follows the **MVVM + Clean Architecture** pattern. The responsibilities are divided as follows:

- **Model**: Defines the data and objects to be handled.
- **ViewModel**: Interacts with the domain layer and prepares data for the UI.
- **Use Case**: Business logic that orchestrates interactions between the data layer and the ViewModel.
- **Repository**: Responsible for fetching data from the API and the local database.
- **API Service**: Defines the API requests.
- **DAO**: Access to the local database using Room.
- **Database**: Room Database configuration.

## Min API
API 24


## Architecture Details

The project follows a **Clean Architecture** with separate layers for data, domain, and presentation:
	•	**Presentation Layer**: Managed by ViewModel and uses Jetpack Compose for the UI.
	•	**Domain Layer**: Contains the use cases that hold the business logic.
	•	**Data Layer**: Contains the repository, API service, Room DAO, and database implementation.

## Testing

Unit tests are written using JUnit and Mockito. The repository class is tested for both local (Room) and remote (API) data fetching.

## TODOs : 
Make Better Error Handling Functionalities 

## License

This project is licensed under the MIT License.
