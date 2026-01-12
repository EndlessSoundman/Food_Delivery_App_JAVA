@echo off
echo ========================================
echo Building Food Delivery App...
echo ========================================
echo.

REM Create build directory if it doesn't exist
if not exist build\classes mkdir build\classes

REM Compile all Java files to the build directory
echo Compiling Java source files...
javac -d build\classes -sourcepath src\main\java src\main\java\Main.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo Build successful!
    echo ========================================
    echo Compiled classes are in: build\classes
    echo Run with: java -cp build\classes Main
    echo.
) else (
    echo.
    echo ========================================
    echo Build failed! Check for compilation errors.
    echo ========================================
    echo.
)
