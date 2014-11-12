set class=com.gestion.livre.service.WebServiceGestionLivre
set clpth=./war/WEB-INF/classes;./war/WEB-INF/lib/appengine-api-1.0-sdk-1.9.15.jar
set resourcedir=./war
set outsourcedir=./src
set outdir=./war/WEB-INF/classes
set JDK_HOME=C:\Program Files\Java\jdk1.7.0_51
"%JDK_HOME%\bin\wsgen" -cp "%clpth%" -wsdl -keep -r "%resourcedir%" -d "%outdir%" -s "%outsourcedir%" %class%
pause