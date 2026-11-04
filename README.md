[![NoviIndraF](https://circleci.com/gh/NoviIndraF/PantauCovid19.svg?style=shield)](https://circleci.com/gh/NoviIndraF/PantauCovid19)

# PantauCovid19 – COVID-19 Tracker for Indonesia (Android)

PantauCovid19 ("monitor COVID-19") is an Android app written in **Kotlin** that shows COVID-19 statistics for Indonesia and each province. It is built as a **modular Clean Architecture** project with an offline-first repository, an encrypted local database and a dynamic feature module for saved provinces.

> **Data source status:** the app reads from `https://apicovid19indonesia-v2.vercel.app/api/`, which is no longer online (it returns HTTP 504). A fresh install shows the error view; to run the app again, point `baseUrl` in `core/.../di/CoreModule.kt` to another API with the same response format.

## Features

- **Splash screen** with a Lottie animation.
- **Home:** national totals (positive, treated, recovered, deaths) and the list of provinces, with search by province name.
- **National detail:** daily changes, suspect/probable cases (ODP/PDP) and specimen totals.
- **Province detail:** cases, treated, recovered and deaths, split by gender and age group; save the province.
- **Map:** provinces on a Mapbox map.
- **Saved provinces:** a dynamic feature module (`:favorite`) opened with the deep link `pantaucovid19://favorite`.
- **Offline first:** data is cached in Room and shown while the network request runs (`NetworkBoundResource`).
- **Security:** the Room database is encrypted with SQLCipher, and OkHttp uses certificate pinning.

## Modules

```
app/        Activities and ViewModels: splash, home, homeDetail, provinceDetail, map
core/       Shared data and domain layers
  data/
    source/remote/   Retrofit ApiService, RemoteDataSource, responses
    source/local/    Room (CovidDatabase, CovidDao), LocalDataSource, entities
    CovidRepository, NetworkBoundResource, Resource
  domain/
    model/           Indonesia, Province
    repository/      ICovidRepository
    usecase/         CovidUseCase, CovidInteractor
  di/                Koin modules (database, network, repository)
  utils/             DataMapper, AppExecutors
favorite/   Dynamic feature module: saved provinces
```

## Tech stack

- Kotlin, coroutines and Flow, LiveData, View Binding
- Koin for dependency injection
- Retrofit, Gson and OkHttp (logging interceptor, certificate pinning)
- Room with SQLCipher
- Mapbox Maps SDK (annotation plugin), Lottie, Shimmer
- Firebase Crashlytics
- CircleCI build

## Getting started

Requirements: Android Studio with **JDK 11** (the project uses Android Gradle Plugin 4.2.2 and Gradle 6.x), Android SDK 30.

1. Open the project in Android Studio.
2. The Mapbox token is in `app/src/main/res/values/strings.xml` (`mapbox_access_token`). Replace it with your own if needed.
3. `app/google-services.json` belongs to the original Firebase project. Replace it with your own to use Crashlytics.
4. Run the `app` configuration on a device or emulator (Android 7.0+).

A release APK built in 2021 is in `app/release/app-release.apk`.
