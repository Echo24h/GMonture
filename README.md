# Plugin Minecraft pour EcoCraft : Ajout de montures avec contrôle par les flèches du clavier

<p align="center">
<img src="https://github.com/Echo24h/Echo24h/blob/main/EcoCraft_logo_transparent.png" width="100"/>
</p>

Ce plugin Minecraft a été créé pour le serveur EcoCraft. Il ajoute des montures au jeu et permet de les contrôler à l'aide des flèches du clavier. Le plugin est entièrement configurable grâce au fichier de configuration fourni, qui permet de modifier les prix dans la boutique, les statistiques des montures, les permissions nécessaires pour l'achat, etc.

<p align="center" display="flex">

<img src="https://github.com/Echo24h/Echo24h/blob/main/gmonture_capture_2.png" height="250"/>

<img src="https://github.com/Echo24h/Echo24h/blob/main/gmonture_capture_1.png" height="250"/>

</p>

Les montures sont également disponibles dans un menu dédié et dans le menu de la boutique. Le plugin prend également en compte les différents mondes où les montures ne sont pas disponibles.

Enfin, le plugin affiche des messages en cas d'erreur ou de succès, tels que des erreurs de permission, des montures désactivées dans un monde spécifique, des montures blessées, un manque de place pour appeler une monture, ou encore un message de succès lors de l'achat d'une monture.

Le fichier de configuration fourni permet de personnaliser le plugin pour s'adapter aux besoins spécifiques du serveur EcoCraft.

Dans l'ensemble, ce plugin offre aux joueurs de Minecraft sur le serveur EcoCraft une expérience de jeu plus immersive et plus variée en ajoutant de nouveaux éléments tels que des montures terrestres et volantes. Les joueurs peuvent personnaliser leur expérience de jeu en achetant différentes montures dans la boutique et en configurant leurs statistiques pour s'adapter à leur style de jeu spécifique.

## Config

Voici une explication rapide des différentes sections du fichier de configuration :

* `disable_worlds`: Cette section permet de spécifier les mondes où les montures ne sont pas disponibles.

* `dead_delay`: Cette section permet de définir le délai en secondes après la mort de la monture avant qu'elle ne puisse être rappelée.

* `montures`: Cette section est la plus importante, car elle permet de définir les différents types de montures disponibles dans le jeu. Chaque monture est définie par plusieurs paramètres tels que l'entité associée à la monture, le nom de la monture, la permission requise pour l'achat, le prix d'achat, la vitesse de déplacement, la hauteur de saut, et bien plus encore.

* `messages`: Cette section permet de définir les différents messages qui seront affichés aux joueurs lorsqu'ils interagissent avec les montures dans le jeu. Les messages peuvent être modifiés pour s'adapter aux besoins spécifiques du serveur, et incluent des messages d'erreur (comme un manque de permission ou un manque d'argent) et des messages de succès (comme l'achat réussi d'une monture).


