# NewsFeed App

App that display a news feed using data from CBC News API 📰

## Description

 The app displays a list of news from the CBC API. Each item in the list has a title, date and picture related to the news story. The app allows filtering news stories by type and checks for internet connection. The app also has offline persistance data. This application was made with an MVVM Architecture, using Kotlin coroutines for making the API Requests to get the news list. 
 
### Features
* Repository and datasource were used as part of the model in the MVVM architechture,
as a way to keep the app modularized and easier to maintain
* Kotlin Coroutines were used to make API requests and to access app database
* Made use of Viewmodel, Livedata and DataBinding to make interaction with view easier and to retain data
* Dependency injection was used to help manage dependencies 
* Room was used to save news list in database
* Made use of Viewmodel, Livedata and DataBinding to make interaction with view easier and to retain data 

### Built With

* [Kotlin](http://kotlinlang.org/) - Programming Language
* [Koin](https://insert-koin.io) - Dependency Injection Framework
  * Used to help decouple code and to reduce boilerplate code by injecting 
  repositories, database, viewmodel and retrofit interfaces
* [Glide](https://bumptech.github.io/glide/) - Image Loading Framework
  * This library was used to load the images from src links obtained from a GET request
* [Retrofit](https://square.github.io/retrofit/) - REST Client Library
  * Retrofit was used as a way to make the GET Request to the API quicker
  and more efficient
* [Jackson](https://github.com/FasterXML/jackson-core) - JSON Parser
  * This parser was used to parse JSON into an objects
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - ORM 
  * A persistance library that is used to map objects to database
* [Lottie](https://lottiefiles.com/) - Animation Library
  * Animation library used for the app's splashscreen
* [Mockito](https://site.mockito.org/) & [JUnit](https://junit.org/junit4/) - Test Libraries
  * Libraries used for mocking objects and unit tests
 
## Author

[![Thais Lins](https://avatars.githubusercontent.com/thaislins?s=100)<br /><sub>Thais Lins</sub>](https://github.com/thaislins) 
