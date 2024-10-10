# Bottle Deposit

This is a Kotlin Multiplatform project that simulates a bottle deposit machine.

The project is composed of a backend in the `/server` module, desktop and web GUIs written in Compose in the `/composeApp` module,
and shared code in the `/shared` module.

## Running the code

Start the backend with the command `./gradlew :server:run`.

Start the native GUI with the command `./gradlew :composeApp:run`.

Start the web GUI with the command `./gradlew :composeApp:wasmJsBrowserRun`. A URL to the GUI will be printed when it's ready.
This is a work-in-progress, and will probably not function correctly.