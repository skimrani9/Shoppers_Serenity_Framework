@echo off
setlocal EnableExtensions

REM Runs all Cucumber scenarios across every feature module in ShoppersRunner.
set ENV=Dev
set BROWSER=chrome
set TAGS=@login or @home or @navigation or @search or @catalog or @product or @bag or @auth or @signup or @checkout or @payment

echo ============================================================
echo  Shoppers Serenity - FULL TEST SUITE
echo ============================================================
echo  Environment : %ENV%
echo  Browser     : %BROWSER%
echo  Tags        : %TAGS%
echo.
echo  Feature modules:
echo    Login, Home, Navigation, Search, Catalog, ProductDetails,
echo    ShoppingBag, Auth, Checkout, Payment
echo ============================================================
echo.

mvn clean verify -Denvironment=%ENV% -Dtags="%TAGS%" -Dwebdriver.driver=%BROWSER%

set EXIT_CODE=%ERRORLEVEL%
echo.
if %EXIT_CODE% EQU 0 (
    echo BUILD SUCCESS - Report: target\site\serenity\index.html
) else (
    echo BUILD FAILED - Exit code: %EXIT_CODE%
    echo See: target\failsafe-reports
)
echo.

endlocal
exit /b %EXIT_CODE%
