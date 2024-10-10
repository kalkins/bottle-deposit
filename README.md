# Bottle Deposit

This is a Kotlin Multiplatform project that simulates a bottle deposit machine.

The project is composed of a backend in the `/server` module, desktop and web GUIs written in Compose in the `/composeApp` module,
and shared code in the `/shared` module.

## Running the code

Start the backend with the command `./gradlew :server:run`. An experimental WebAssembly GUI is available at `http://localhost:12001/`.

Start the desktop GUI with the command `./gradlew :composeApp:run`.
