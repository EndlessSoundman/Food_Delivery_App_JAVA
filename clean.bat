@echo off
echo ========================================
echo Cleaning compiled files...
echo ========================================
echo.

REM Remove all .class files from source directory
echo Removing .class files from source directory...
for /r src %%f in (*.class) do (
    del /q "%%f" 2>nul
    if exist "%%f" (
        echo Deleted: %%f
    )
)

REM Remove build directory if it exists
if exist build (
    echo Removing build directory...
    rmdir /s /q build
    echo Build directory removed.
)

echo.
echo ========================================
echo Clean completed!
echo ========================================
echo.
