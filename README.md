
#  Weather Forecast System

A weather forecast web application built using Java and Spring Boot that fetches real-time weather information and provides a five-day forecast for cities around the world.

##  Features

- Search for weather by city name
- Display current temperature
- View humidity levels
- Display feels-like temperature
- Show wind speed
- View a five-day weather forecast
- Responsive user interface for desktop and mobile devices

##  Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Thymeleaf
- HTML5 and CSS3
- Maven
- Open-Meteo Weather API

##  Prerequisites

Before running the application, make sure you have:

- Java JDK 21
- Git
- VS Code or another Java IDE
- An internet connection

##  Installation and Setup

### 1. Clone the repository

```bash
git clone https://github.com/Abhiraj0779/Weather_Forecast_System.git
```

### 2. Open the project folder

```bash
cd Weather_Forecast_System
```

### 3. Run the application on Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### 4. Open the application

Visit the following URL in your browser:

http://localhost:8080/

The application loads Pune weather by default. Enter another city name in the search box to check its weather.

##  Weather API

This project uses the Open-Meteo API to retrieve geographical coordinates and weather forecast data. No API key is required for normal non-commercial use.

- Geocoding API: https://geocoding-api.open-meteo.com/
- Weather Forecast API: https://open-meteo.com/

##  Project Structure

```text
Weather_Forecast_System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── WeatherForecastSystem/
│   │   │       ├── WeatherforecastsystemApplication.java
│   │   │       ├── WeatherController.java
│   │   │       └── WeatherService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           └── index.html
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Project Objective

The objective of this project is to demonstrate Java backend development, REST API integration, JSON data processing, and web application development using Spring Boot.

## Author

**Abhiraj Dagde**

##  License

This project is intended for educational and learning purposes.
