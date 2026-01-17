# TikTokDownloader

A modern Android app built with Kotlin and Jetpack Compose that demonstrates downloading and playing TikTok videos, local persistence, and a clean Android architecture using Hilt, Room, Retrofit, and Media3 (ExoPlayer).

> This README was generated from the project's Gradle configuration (`app/build.gradle.kts`). Please review and adapt any usage or legal text (especially around downloading content) before publishing.

Table of contents
- Project summary
- Project coordinates
- Features
- Architecture overview
- Major libraries and versions
- Prerequisites
- Quick start (development)
- Build & run (Windows / PowerShell)
- Testing
- Code generation & KSP notes
- Troubleshooting
- Contributing
- License & acknowledgements


Project summary
---------------
TikTokDownloader is an Android application sample that uses modern Android technologies:
- UI: Jetpack Compose
- DI: Hilt (with KSP)
- Persistence: Room (with KSP)
- Networking: Retrofit + OkHttp
- Media playback: AndroidX Media3 (ExoPlayer)
- Serialization: kotlinx.serialization

Use this project as a reference for combining these technologies into a Compose-first Android application.

Project coordinates (from `app/build.gradle.kts`)
- ApplicationId / Namespace: `com.example.tiktokdownloader`
- compileSdk: 36
- minSdk: 24
- targetSdk: 36
- versionCode: 1
- versionName: `1.0`
- JVM target: 11

Features (implemented or intended)
- Download TikTok videos and save metadata locally
- View list of downloaded videos (Room-backed)
- Play videos with Media3 ExoPlayer
- Network requests via Retrofit and OkHttp
- Dependency injection with Hilt
- Compose-based navigation and screens

IMPORTANT: Respect TikTok's Terms of Service and copyright laws. This project is intended for educational purposes and local experimentation only. Do not use it to redistribute copyrighted material without permission.

Architecture overview
---------------------
The app follows a conventional, layered approach:
- UI layer: Jetpack Compose + ViewModel (lifecycle aware)
- Domain layer: Use-cases or ViewModel-driven business logic
- Data layer: Retrofit for remote API calls, Room for local storage
- DI layer: Hilt modules provide network, database, and repository bindings

Project layout (top-level)
- `app/` - Android application module (source, resources, Gradle settings)
- `gradle/` - Gradle version pins and wrapper
- `build.gradle.kts`, `settings.gradle.kts` - root Gradle settings

Major libraries & versions (extracted from build files)
- Room: 2.8.4 (runtime + ksp/annotation processor)
- Retrofit: 3.0.0 (+ converter-gson)
- OkHttp: 4.12.0 (+ logging-interceptor)
- Hilt: 2.57.1 (hilt-android + hilt ksp compiler)
- Media3 (ExoPlayer): 1.2.1 (media3-exoplayer + media3-ui)
- Lifecycle (ViewModel): 2.10.0
- kotlinx-serialization-json: 1.7.3
- Compose: managed via Compose BOM (platform)

Prerequisites
-------------
- Java JDK 11 (Project targets Java 11)
- Android Studio (2022.2+ / Electric Eel or newer recommended)
- Android SDK Platform 36 installed (compileSdk = 36)
- Android device or emulator with API >= 24
- Gradle wrapper is included; no global Gradle necessary

Quick start (first-time setup)
1. Install JDK 11 and Android Studio.
2. Open the project in Android Studio by selecting the repository root.
3. Let Gradle sync. Android Studio may prompt to install missing SDK components for API 36 — accept the prompts.
4. If you use command line, ensure `JAVA_HOME` points to a JDK 11 installation.

Build & run (Windows / PowerShell)

Open PowerShell in the project root (`C:\Users\PMLS\StudioProjects\tiktokdownloader`) and run the following commands:

```powershell
# Clean and assemble debug APK
.\gradlew.bat clean assembleDebug

# Install debug APK to a connected device or emulator
.\gradlew.bat installDebug

# Run connected instrumentation tests
.\gradlew.bat connectedAndroidTest
```

You can also open the project in Android Studio and use the Run button to launch on an emulator or device.

Testing
-------
- Unit tests: `app/src/test/`
- Instrumentation tests / UI tests: `app/src/androidTest/`

Run tests from command line:

```powershell
# Unit tests
.\gradlew.bat test

# Instrumentation tests
.\gradlew.bat connectedAndroidTest
```

Code generation & KSP notes
--------------------------
- This project uses KSP for Room and Hilt code generation; generated sources are under `app/build/generated/` and `app/build/ksp/`.
- If you rename packages or models and see KSP/annotation errors, run a clean build: `.\gradlew.bat clean build` and consider invalidating caches in Android Studio.

Common troubleshooting
----------------------
- Gradle sync fails (Missing compileSdk or SDK components): open SDK Manager and install API 36 or accept prompts in Android Studio.
- Hilt errors (missing generated components): make sure the KSP compiler dependencies are present in `app/build.gradle.kts` and rebuild the project.
- Room migration errors: during development you can opt for destructive migrations, but create proper migration logic before production release.
- JVM crashes (hs_err_pid*.log): logs may indicate native issues, check the stacktrace and ensure you use compatible JDK/NDK versions.

Security & legal considerations
------------------------------
- Do not hardcode keys or credentials in source. Use secure storage or CI secrets for any API keys.
- Downloading videos from TikTok or other platforms may violate terms of service or copyright. Use this app responsibly and legally.

Contributing
------------
1. Fork and create a feature branch.
2. Run unit and instrumentation tests locally.
3. Open a Pull Request with clear description and testing notes.

Suggested next steps / small improvements
- Add a `LICENSE` file to clarify project licensing.
- Add screenshots to `README.md` under a `Screenshots` section.
- Add a sample `.env.example` and document any configuration if you introduce API keys or endpoints.
- Add CI workflow to run lint and tests on pull requests.

License
-------
No license file detected. Add a license (MIT, Apache-2.0, etc.) to clarify reuse and contribution terms.

Maintainers / Contact
---------------------
Add an `AUTHORS` or `MAINTAINERS` file with contact details if this project is to be maintained collaboratively.

Last generated: 2026-01-18
