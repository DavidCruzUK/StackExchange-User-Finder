# StackExchange User Finder

## Architecture Design Pattern
* MVVM + Repository + (Coroutines) StateFlow

## Modules:
![alt text](https://i.ibb.co/2ScTfmn/Archtecture.png)
* App: Appliation Module + ViewModels
* Repository: Contains Room DataBase where it Saves all data once received from the server
* Service: Retrofit2 And Endpoints to get Users Data
* Entity : Data Model Entities

## Libraries used:
* Kotlin: 1.4.21
* Dagger2: 2.31.2
* RxJava2: 2.2.20
* RxAndroid: 2.1.1
* Retrofit2: 2.9.0
* Coroutines: 1.4.2
* Room: 2.2.6
* Glide: 4.11.0
* JUnit: 4.13.1
* MocKK: 1.10.5

## Supports:
* Minimum SDK: 19 (Android 4.4.1)
* Target SDK: 20