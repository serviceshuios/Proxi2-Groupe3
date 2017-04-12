
Ce document a pour but de vous expliquer les démarches à effectuer afin de pouvoir déployer le fichier ProxyBanque.war 
en toute simplicité afin de pouvoir l'installer et l'exécuter dans un environnement Windows ayant pour serveur d'application TomCat.

Un fichier Web archive (.war) contient tous les composants d'une application web de manière organisée et hiérarchisée.Il a pour 
but de déployer l'application web sur l'environnement dans lequel va évoluer l'application.

REMARQUE PRELIMINAIRE :le programme d'installation requiert au moins la version 1.5 du kit de développement (JRE ou JDK) de la plate-forme Java 2 , 
Standard Edition. Si vous utilisez une version antérieure, la procédure d'installation ne configurera pas correctement le fichier WAR 
de l'application utilisateur. L'installation semblera réussir, mais vous rencontrerez des erreurs lorsque vous tenterez de démarrer 
l'application utilisateur. Les étapes 0 à 3 vous indiquent la procédure a effectuer pour installer la JRE de la plateforme JAVA.


Etape 0 : Système d'exploitation et type de système

=> Pour connaitre votre système d'exploitation, vous devez vous rendre dans votre "Poste de travail" ou "Ordinateur".
=> Faire un clic droit dans la fenêtre à coté de votre disque dur et cliquez sur "Propriétés".
=> Vous pouvez maintenant lire dans "type de système" la configuration de votre système ( Exemple : Système d'exploitation 64 Bits)
=> Mémorisez votre système d'exploitation et votre nombre de bits et passez à l'étape suivante.

Etape 1 : Télechargement du JRE 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
=> Maintenant clicker sous download sur la version qui vous intéresse et qui dépend de votre système d'exploitation et de votre nombre de bits que vous avez trouvé dans l'étape 0.
=> Une fois le télechargement terminé passer à l'étape 2.

Etape 2 : Installation du JRE

=> Lancer l'executable et suivre l'assistant d'installation jusqu'à la fin.

Etape 3 : Ajouter les variables d'environnement 

=> Aller dans Ordinateur
=> Clic droit "propriété"
=> Paramètre système avancé
=> Cliquez sur "variable d'environnement"
=> Dans les variables d'utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du répertoire d'installation du JRE.
=> Si la variable n'existe pas, la créer, et ajouter le chemin comme précedemment.  

Vous pouvez vérifier si les variables d'environnements ont été correctement paramétrées en lancant la commande "java -version" dans l'invite de commande. 
L'invite de commande doit indiquer le numéro de version de votre JRE. Sinon veuillez refaire les étapes précedantes.

Etape 4 : installer la base de données fournie

=> Utilisez le Systeme de Gestion de Base de Données MySQL via phpMyAdmin
=> Le login utilisateur par défaut doit être root
=> Il ne doit pas y avoir de mot de passe
=> Le port utilisé doit etre 3306
=> créez une nouvelle base appelée bddproxibanque
=> Cliquez sur import
=> Selectionnez le fichier BddProxiBanque.sql puis ouvrir
=> Cliquez sur Exécuter
=> Votre base de données est installée


Etape 5 : installer un serveur TomCat 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://tomcat.apache.org/download-90.cgi
=> Maintenant cliquez sur la version Core qui vous intéresse et qui dépend de votre système d'exploitation et de votre nombre de bits que vous avez trouvé dans l'étape 0.
=> Cliquez sur "variable d'environnement"
=> Dans les variables d'utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du répertoire d'installation du JRE.
=> Si la variable n'existe pas, la créer, et ajouter le chemin comme précedemment.


Etape 6 : déployer l'application dans TomCat

=> Démarrer Tomcat (bin/startup.bat)
=> La méthode la plus simple est d'ajouter les services dans le même conteneur de Tomcat. 
=> Les fichiers de configuration sont déjà configurés pour le paramètre du port 8080. 
=> Vous devez vérifier si votre système hôte est spécifié dans les fichiers de configuration et laisser le numéro de port tel quel. 
=> Copiez les fichiers extraits pour chaque service dans le conteneur de l'application Tomcat sous <installdir>\tomcats\tomcat-9.0.0.M19_repository\webapps
=> Redémarrez Tomcat

Etape 6 : utiliser l'application

=> Allez sur l'url suivante : localhost:8080/ProxyBanque/ 
=> Maintenant votre programme est lancé.

=> Pour consulter la documentation (javadoc) de l'application, double-cliquez sur le fichier index.html se trouvant dans le repertoire "doc" 
livré avec le dossier ProxyBanque.  
