set class=com.gestion.livre.service.WebServiceGestionLivre
set clpth=./war/WEB-INF/classes
set resourcedir=./war
set outsourcedir=./src
set outdir=./war/WEB-INF/classes
set JDK_HOME=C:\Program Files\Java\jdk1.7.0_67
"%JDK_HOME%\bin\wsgen" -cp "%clpth%" -wsdl -keep -r "%resourcedir%" -d "%outdir%" -s "%outsourcedir%" %class%
pause