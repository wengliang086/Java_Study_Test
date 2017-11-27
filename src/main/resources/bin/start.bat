@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

java  -classpath ..\conf;%LIB_JARS%;"%JAVA_HOME%\lib\tools.jar"  com.hoolai.basejavaproject.Main  %*

pause