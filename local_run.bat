@echo off
setlocal

set ENV=Dev
set TAGS=@login
set BROWSER=chrome

echo Running Shoppers Serenity tests...
echo Environment: %ENV%
echo Tags: %TAGS%
echo Browser: %BROWSER%

mvn clean verify -Denvironment=%ENV% -Dtags=%TAGS% -Dwebdriver.driver=%BROWSER%

endlocal
