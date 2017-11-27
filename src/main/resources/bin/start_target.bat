@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\..\..\..\target
for %%i in (*) do set LIB_JARS=!LIB_JARS!;%%i

echo "start"

java  -classpath %LIB_JARS%;"%JAVA_HOME%\lib\tools.jar"  com.test.javahome.GetJavaHome  %*
::start javaw -classpath %LIB_JARS% -Dfile.encoding=UTF-8 -Duser.language=en -Duser.country=US com.test.javahome.GetJavaHome

echo "end "

pause