@echo off
mvn clean verify -Denvironment=Dev -Dtags="@login and @positive" -Dwebdriver.driver=chrome
