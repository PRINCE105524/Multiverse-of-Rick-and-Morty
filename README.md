# Multiverse-of-Rick-and-Morty
"Multiverse of Rick &amp; Morty" is an Android app that utilizes the Rick and Morty API to provide users with an immersive journey through the multiverse of the popular animated series, "Rick and Morty." Explore various characters from the show and discover character details. You're welcome to explore its rich universe.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Libraries Used](#libraries-used)
- [Contributing](#contributing)

## Features

- Fetches a list of characters from the Rick and Morty API.
- Displays character information including name, image, and status.
- Allows users to click on a character to view detailed information.
- Utilizes MVVM architectural pattern for clean separation of concerns.
- Implements dependency injection to improve app performance and reduce boilerplate code.
- Includes unit test data and test environment for ViewModel and repository components.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Android Studio installed on your development machine. recommended -> Android Studio Giraffe
- A GitHub account (optional) for version control.

## Getting Started

To build and run the application, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/PRINCE105524/Multiverse-of-Rick-and-Morty.git

2. Open the project in Android Studio.
3. Build the project by clicking on the "Build" menu and selecting "Build Project."
4. Connect an Android device or use an emulator.
5. Run the app by clicking the "Run" button in Android Studio.

## Project Structure

The project follows the MVVM (Model-View-ViewModel) architectural pattern and is organized into the major following packages:

di: Dependency Injection modules (Dagger Hilt) for managing dependencies.

ui: User interface components, including activities.

viewmodels: ViewModel classes for handling UI-related logic.

utils: Utility classes and helper functions.

models: mainly contain data class

api: API methods for fetching data from the remote sources.

repository: Repository for characterlist and characterdetails. 

## Libraries Used

This project makes use of the following libraries and tools:

Retrofit: HTTP client for making API requests.

ViewModel: Part of Android Architecture Components for managing UI-related data.

LiveData: Part of Android Architecture Components for observing data changes.

Dagger (Hilt): A dependency injection framework.

JUnit: For unit testing.

Glide: Image Loading and Caching

and more...

## Contributing

If you'd like to contribute to this project, please follow these guidelines:

Fork the project.
Create a new branch for your feature or bug fix.
Make your changes and write tests if necessary.
Push your changes to your fork.
Create a pull request to the main repository.

## Thanks. 



   
