# StudyFlow

Link to the final part below:
https://youtu.be/WNgabVEP_7Y

Link to the prototype functionality down below:
https://youtu.be/ml3ZqknexKM

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

<img width="957" height="435" alt="Screenshot 2026-11-06 141105" src="https://github.com/user-attachments/assets/73e2bf93-4bd6-4543-a3fe-cf89f895aa90" />


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

 Innovative features introduced after the prototype
The latest development phase focused on extending the original prototype beyond basic academic module management.

The following features represent the major enhancements.

**Google Single Sign-On:**
Google Single Sign-On was added using Firebase Authentication and Android Credential Manager.

This allows users to authenticate using an existing Google account instead of having to enter their email address and password manually.

The feature improves convenience by reducing the steps required to access the application.

Firebase handles the authentication process and verifies the Google credentials.


**Push notification functionality:**

<img width="720" height="1600" alt="Push Notifications" src="https://github.com/user-attachments/assets/0795ef2d-d834-427e-a67e-ae45e3fd774b" />


Firebase Cloud Messaging was introduced to provide the foundation for real-time notifications.

The Android application now includes:
- A Firebase messaging service.
- A notification channel for StudyFlow.
- Android notification permission handling for Android 13 and newer.
- Notification display logic for incoming Firebase messages.
This creates the foundation for future academic notifications, including assignment reminders, module announcements and important updates.

The Android receiving implementation has been built, but an end-to-end notification test and backend notification sending integration still need to be completed. Therefore, fully operational real-time academic notifications should not yet be claimed.


**Multilingual support:
**

<img width="720" height="1600" alt="Settings" src="https://github.com/user-attachments/assets/65e60e38-04ec-4c31-ba88-4015a05e3f7e" />


Multilingual support was introduced to improve the accessibility of StudyFlow.

The original application uses English. Afrikaans translations have been added using Android string resources, and AppCompat locale management has been introduced as the language-switching mechanism.

The language selector is still being debugged, and the translations have not yet been connected throughout the application's Compose screens.

isiXhosa is also a planned language, but its resource directory and translations have not yet been completed.


**Testing and verification**

##Username Change##
<img width="720" height="1600" alt="Username Change " src="https://github.com/user-attachments/assets/9d894399-4638-4266-8cc0-1e830ad108e8" />

##Dashboard##
<img width="720" height="1600" alt="Dashboard" src="https://github.com/user-attachments/assets/74111e05-9b25-433e-9363-7051665cd980" />

##Google SSO##
<img width="720" height="1600" alt="Google SSO" src="https://github.com/user-attachments/assets/7b9a84c8-0d6d-4ae2-a765-89df367d2f5b" />

##Login Page##
<img width="720" height="1600" alt="Login Page " src="https://github.com/user-attachments/assets/069e8dd1-760a-4bc2-8b6c-07da2efb045d" />


Testing was performed incrementally during development to ensure that new features did not unnecessarily affect existing application functionality.

The following table summarises the known testing status.
Feature	Testing status
Email/password registration	Previously tested successfully
Email/password login	Previously tested successfully
Google sign-in	Tested successfully
Module CRUD	Previously tested successfully
Display name updates	Previously tested successfully
Firebase messaging service	Builds successfully
Notification permission	Tested successfully
Actual push notification delivery	Not yet confirmed
Room database	Dependencies synced; implementation not fully tested
Afrikaans language switching	Not working yet
isiXhosa translations	Not implemented
GitHub Actions	Workflow execution not confirmed

Further testing should include:
- Testing authentication on physical Android devices.
- Testing notification delivery in both foreground and background states.
- Testing offline module creation, editing and deletion.
- Testing synchronisation after an internet connection is restored.
- Testing language selection and persistence after restarting the application.
- Testing API authentication and access restrictions.
- Testing the GitHub Actions build and test workflows.


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

## Conclusion:

StudyFlow was developed to address the need for a centralised academic organisation platform for tertiary students.
The original prototype established the core functionality, including user authentication, academic module management, profile settings and communication with a cloud-hosted backend.
Subsequent development expanded the application through Google Single Sign-On, Firebase Cloud Messaging infrastructure, local database development and multilingual support.
The use of Kotlin, Jetpack Compose, Firebase, ASP.NET Core, PostgreSQL and GitHub provides a foundation for continued development and maintenance.
GitHub provides source-code management and version history, while GitHub Actions offers an opportunity to introduce automated building and testing into the development process.
Overall, StudyFlow has progressed from a basic academic management prototype towards a more comprehensive mobile application. Completing the remaining offline, notification and localisation work will further improve its usability, accessibility and reliability.
