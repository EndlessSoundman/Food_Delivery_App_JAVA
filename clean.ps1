# PowerShell script to clean compiled files
Write-Host "========================================"
Write-Host "Cleaning compiled files..."
Write-Host "========================================"
Write-Host ""

# Remove all .class files from source directory
Write-Host "Removing .class files from source directory..."
Get-ChildItem -Path src -Include *.class -Recurse -ErrorAction SilentlyContinue | Remove-Item -Verbose -ErrorAction SilentlyContinue

# Remove build directory if it exists
if (Test-Path build) {
    Write-Host "Removing build directory..."
    Remove-Item -Path build -Recurse -Force
    Write-Host "Build directory removed."
}

Write-Host ""
Write-Host "========================================"
Write-Host "Clean completed!"
Write-Host "========================================"
Write-Host ""
