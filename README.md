# StudyFlow

## Purpose

StudyFlow is a mobile academic productivity application designed for tertiary students. The application helps students organise their academic information in one place and provides a foundation for managing modules, academic tasks and personal study information.

The prototype was developed using Kotlin and Jetpack Compose in Android Studio. Firebase Authentication is used for user registration and login, while a custom REST API connects the Android application to an online PostgreSQL database.

## Main Purpose of the Prototype

The prototype demonstrates the core functionality and technical architecture of StudyFlow.

The current prototype allows users to:

- Register an account
- Log into the application
- Log out
- View a dashboard
- View modules
- Add modules
- Edit modules
- Delete modules
- View their account email
- Change their display name through Settings

More advanced features such as assignments, assessments, calendar functionality, offline synchronisation, push notifications, gamification, statistics, multilingual support and Google SSO are planned for the full application and are not part of the current prototype.

## Application Design

StudyFlow uses a modern Android architecture based on Jetpack Compose.

The main screens are:

1. Splash Screen
2. Login Screen
3. Register Screen
4. Dashboard
5. Modules
6. Add/Edit Module
7. Settings

The main navigation flow is:

Splash
↓
Login
↓
Dashboard
├── Modules
│   ├── Add Module
│   └── Edit/Delete Module
└── Settings

The application uses separate packages for screens, navigation, models, data access and ViewModels. This separation makes the application easier to maintain and extend.

## Technologies Used

- Kotlin
- Android Studio
- Jetpack Compose
- Navigation Compose
- Firebase Authentication
- Retrofit
- ASP.NET Core Web API
- Entity Framework Core
- PostgreSQL
- Supabase
- Render
- GitHub
- Docker

## Data Stored Online

### Firebase

Firebase Authentication stores the authentication information for registered StudyFlow users.

Firebase is responsible for:

- User email
- Firebase user ID (UID)
- Authentication credentials
- User profile information such as display name

The Android application does not directly store user passwords in the StudyFlow PostgreSQL database.

### REST API

The StudyFlow REST API is a custom backend created using ASP.NET Core and C#.

The API acts as the communication layer between the Android application and the PostgreSQL database.

The Android application sends HTTP requests to the API using Retrofit.

The API processes these requests and communicates with the database using Entity Framework Core.

### PostgreSQL Database

The StudyFlow database is hosted online through Supabase.

The current database contains a `Modules` table containing information such as:

- Module ID
- Module name
- Module code
- Lecturer
- Colour

The database allows module information to persist online rather than only being stored on the Android device.

## REST API Connection

The Android application communicates with the REST API over HTTPS.

The basic data flow is:

Android Application
↓
Retrofit
↓
Render
↓
ASP.NET Core REST API
↓
Entity Framework Core
↓
Supabase PostgreSQL

Example API operations include:

- `GET /api/Modules`
- `GET /api/Modules/{id}`
- `POST /api/Modules`
- `PUT /api/Modules/{id}`
- `DELETE /api/Modules/{id}`

## GitHub Usage

GitHub was used as the version control platform for the StudyFlow Android application.

The repository is used to:

- Store the Android source code
- Track development changes
- Maintain the project history
- Provide a central backup of the source code
- Organise the project for development and submission
- Support integration with the backend development workflow

GitHub allows changes to be committed throughout development rather than keeping only one final copy of the project.

The Android repository is separate from the backend repository so that the mobile application and REST API can be developed and maintained independently.

## Project Structure

The Android application follows a structured architecture similar to:

com.studyflow.app
├── data
│   ├── api
│   ├── auth
│   └── repository
├── model
├── navigation
├── ui
│   ├── screens
│   └── theme
└── viewmodel

This structure separates the user interface from business logic and data access.

## Future Development

Future versions of StudyFlow are planned to include:

- Assignments and tasks
- Tests and examinations
- Calendar and timetable
- Grade tracking
- Pomodoro/focus timer
- Study statistics
- Study streaks and gamification
- Offline storage using Room
- Data synchronisation
- Push notifications
- Google SSO
- English, isiXhosa and Afrikaans language support
