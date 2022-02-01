# ALGO - Rendu du projet final
##### Auteur : Benoit PEGAZ
###### tags: `ESGI`, `ALGO`

## Environnement

Ce projet a été développé en [Java 16](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html), en utilisant la bibliothèque graphique [Swing](https://fr.wikipedia.org/wiki/Swing_(Java)) et la bibliothèque de gestion de graphiques [Graphstream](https://graphstream-project.org/)

## Sujet

- [x] Construire un graphe représentant un réseau de transport existant constitué d’environ 10 à 20 destinations, présentant une structure de type graphe (avec au moins un cycle).
  Construire une application graphique
- [x] Créer une interface graphique contenant:
  - Possibilité de choisir un point de départ.
  - Possibilité de choisir un point d’arrivé.
  - Affichage des itinéraires calculés.
  - Analyse des calculs des itinéraires calculés.
- [x] L'itinéraire sera calculé via au moins 2 algorithmes différents.
- [x] Comparer les résultats de ces algorithmes:
  - En temps de transport prévu pour l’utilisateur et présenterez la complexité
  - En temps d'exécution qu'ont engendrés ces algorithmes par leur calcul.

## Graphe du réseau de transport

Le graphe utilisé dans l'application est une adaptation du réseau de transport [Léman Express](https://www.lemanexpress.ch/fileadmin/user_upload/Plans_reseau_et_multimodal/Plan_reseau_Leman_Express_211212_light.pdf)

![Réseau de transport du Léman Express](https://i.imgur.com/SVUO4xj.jpg)

Le sujet du projet limite le nombre de sommets à 20, le graphe ne contient qu'une partie de l'ensemble des villes desservies et a donc été simplifié.  
Afin de rendre le graphe plus intéressant pour tester l'application, des arêtes ont été ajoutées.

L'adaptation du réseau de transport du [Léman Express](https://www.lemanexpress.ch/fileadmin/user_upload/Plans_reseau_et_multimodal/Plan_reseau_Leman_Express_211212_light.pdf):

![Adaptation du réseau de transport]()

Le graphe représenté dans l'application:

![Graphe présent dans l'application]()

L'affichage du graphe ainsi que le positionnement de ses sommets se font automatiquement au démarrage de l'application.  
Il est donc possible d'avoir un affichage qui diffère du graphe ci-dessus mais les données qui y sont comprises restent inchangées.

## Fonctionnement de l'application

L'application est composée de 3 zones:
- La zone en haut de la fenêtre: Zone de saisie des sommets de départ et d'arrivée
- La zone ancrée à droite: Affichage du graphe, c'est dans cette zone que sera également affichée le plus court chemin permettant d'aller du sommet de départ au sommet d'arrivée
- La zone ancrée à gauche: Zone d'information, c'est dans cette zone que vous pourrez voir les informations liées aux différents algorithmes implémentés

## Calcul du plus court chemin

### Principe général

Ce projet comporte plusieurs algorithmes résolvant le problème du plus court chemin.  
L'idée générale est de trouver un chemin avec un poids minimal, c'est-à-dire trouver un chemin d'un sommet à un autre de façon à ce que la somme des arêtes de ce chemin  soit inférieure au poids des autres chemins

Ces algorithmes utilisent la notion de graphe pondéré afin de calculer le plus court chemin, dans notre cas nous n'avons pas encore de poids à nos arêtes et sommets, par défaut nous allons donc considérer que toutes les arêtes ont un coût de 1.
En faisant ce choix, cela revient à considérer que le plus court chemin calculé est égal au minimum d'arêtes utilisées pour aller d'un sommet A à B

### Algorithmes utilisés







