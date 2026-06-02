# PowerShell: quote the entire -D argument (e.g. "-Dtags=..."), not only the value.
# Avoid -Dcucumber.* on the CLI — PowerShell treats -Dcucumber as a PowerShell parameter.
param(
    [string]$Environment = "Dev",
    [string]$Tags = "@login",
    [string]$Browser = "chrome"
)

Write-Host "Running Shoppers Serenity tests..."
Write-Host "Environment: $Environment"
Write-Host "Tags: $Tags"
Write-Host "Browser: $Browser"

mvn clean verify `
    "-Denvironment=$Environment" `
    "-Dtags=$Tags" `
    "-Dwebdriver.driver=$Browser" `
    "-Dheadless.mode=false"
