@echo off
mvn clean verify -Denvironment=Dev -Dtags="@login" -Dwebdriver.driver=chrome -Dheadless.mode=false
