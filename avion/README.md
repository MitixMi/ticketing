# si necessaire recompilé le projet 
Allé dans \com\example\
Executé la commande javac -d . FrontControllerServlet.java MySession.java UserDataStore.java ../model/*.java ../controller/*.java ../annotation/*.java ../dao/*.java ../database/*.java ../filters/*.java ../models/*.java

on aura alors les classe compilé d'avion dans com/example/avion

Ensuite allé vers Tomcat et créer le dossier "avion" ou n'importe quel autre nom dans webapp
Voici la structure que l'on devrait avoir 
avion
|
|_public
|   |_mettre ici les pages dans public
|_WEB-INF
     |
     |_classes
     |    |_avion
     |_lib
     |  |_FrontControllerServlet.jar(pas necessaire si on a deja mis les classes compilé avion dans classes)
     |_views
     |  |_mettre ici les views 
     |_web.xml