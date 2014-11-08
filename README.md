find your artist
==============

Tout d'abord, je suis partie sur l'idée d'un projet en C#/.NET.
Il s'agissait de récupérer des données dans un tableau croisé dynamique,
accessible grâce au langage de requêtes MDX et à la mise en place d'un webservice.
Cependant, il n'est possible d'utiliser un projet .NET autrement que via Visual Studio.
Finalement, je me suis rendue compte assez tard que mon application ne pourrait 
être accessible via les machines Ubuntu de l'école, comme spécifié dans les exigences.

J'ai donc choisi de faire une application web permettant de répertorier des données,
en l'occurrence des salons de tatouage.
Il s'agit de choisir une ville ou un style de tatouage (par rapport aux tatoueurs y exerçant),
et les salons correspondant aux critères s'affichent sur une Google Map.

Find your Artist est développée en Java JEE, sur la base d'un modèle MVC,
et plus précisément le pattern DAO. Le principe est de séparer la couche modèle
en une couche métier et une couche données, afin de mieux distinguer les traitements.
Le framework Spring a permis de définir l'architecture de manière standardisée.
L'application utilise le classique framework de persistance Hibernate afin de gérer
les correspondances entre les objets.
Concernant la gestion des dépendances, c'est Maven qui est utilisé via Eclipse.
Enfin, le framework JQuery est aussi utilisé afin de simplifier les instructions Javascript.
L'affichage de la map est géré par l'API Maps v3.

En fonction des critères de recherche de l'utilisateur, l'application génère les coordonnées
de latitude et longitude des adresses provenant de la base de données.
