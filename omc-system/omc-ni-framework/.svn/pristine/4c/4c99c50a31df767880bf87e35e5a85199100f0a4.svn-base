@echo off
title OMC-NI-FrameWork for SSDX NBI

if "%OS%" == "Windows_NT" setlocal
set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
pushd %DIRNAME%..
set HOME=%~dp0

if "%JAVA_HOME%"  == "" set JAVA_HOME=%HOME%\jre
if exist "%JAVA_HOME%\bin\java.exe" goto JAVA_HOME_DONE
echo JAVA_HOME is not set.  Unexpected results may occur.
echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
goto END

:JAVA_HOME_DONE
set JAVA_EXEC=%JAVA_HOME%\bin\java.exe
set CLASSPATH=""
if not exist "%HOME%\lib" goto CLASSPATH_DONE
for %%i in (%HOME%\lib\*.jar) do call :APPEND_CLASSPATH %%i


:CLASSPATH_DONE
set MAIN_CLASS=com.ltln.modules.ni.omc.framework.start.OMCMain
set JAVA_OPTS=-Xms128m -Xmx1024m -Duser.dir=%HOME%

rem set JAVA_OPTS=%JAVA_OPTS% -Djava.security.auth.login.config=%HOME%\conf\auth.conf

rem set JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y %JAVA_OPTS%

rem set ARGS= -Duser.dir=%HOME%envconf
goto RUN

:APPEND_CLASSPATH
set CLASSPATH=%CLASSPATH%;%1
goto :eof

:RUN
echo ===============================================================================
echo.
echo   OMC-NI-FrameWork for SSDX NBI
echo.
echo   HOME: %HOME%
echo.
echo   JAVA: %JAVA_EXEC%
echo.
echo   JAVA_OPTS: %JAVA_OPTS%
echo.
echo   CLASSPATH: %CLASSPATH%
echo.
echo.  MAIN_CLASS: %MAIN_CLASS%
echo. 
echo.  ARGS: %ARGS%
echo ===============================================================================
echo.

:RESTART
"%JAVA_EXEC%" %JAVA_OPTS% ^
	-cp "%CLASSPATH%" ^
	%MAIN_CLASS% %ARGS% %*

if ERRORLEVEL 10 goto RESTART

:END
if "%OS%" == "Windows_NT" endlocal
if "%NOPAUSE%" == "" pause
@echo on