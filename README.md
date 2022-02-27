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

Le graphe utilisé dans l'application est une adaptation du réseau de transport du [Léman Express](https://www.lemanexpress.ch/fileadmin/user_upload/Plans_reseau_et_multimodal/Plan_reseau_Leman_Express_211212_light.pdf)

![Réseau de transport du Léman Express](ressources/reseau_leman_express.jpg)

Le sujet du projet limite le nombre de sommets à 20, le graphe ne contient qu'une partie de l'ensemble des villes desservies et a donc été simplifié.  
Afin de rendre le graphe plus intéressant pour tester l'application, des arêtes ont également été ajoutées.

Le graphe représenté dans l'application:

![Graphe présent dans l'application](ressources/graphe_leman_express.png)

L'affichage du graphe ainsi que le positionnement de ses sommets se font automatiquement au démarrage de l'application.

> ⚠️ Il est donc possible d'avoir un affichage qui diffère du graphe ci-dessus mais les données qui y sont comprises restent inchangées.

## Fonctionnement de l'application

L'application est composée de 2 fenêtres:
- La fenêtre principale, contient:
  - Une zone de saisie des sommets de départ et d'arrivée, et bouton permettant d'accéder à la fenêtre de détails
  - L'affichage du graphe, c'est sur ce graphe que sera également affichée le plus court chemin
- La fenêtre de détails: Fenêtre d'information, c'est dans cette fenêtre que vous pourrez comparer les différents algorithmes implémentés

![img.png](ressources/fenetre_details.png)

Sélectionnez un sommet de départ et de destination, si un chemin existe entre ces 2 sommets il est affiché, il est alors possible d'afficher la fenêtre de détails en cliquant sur le bouton "Détails"

## Calcul du plus court chemin

### Principe général

Ce projet comporte plusieurs algorithmes **résolvant le problème du plus court chemin**.  
L'idée générale est de trouver un chemin avec un **poids minimal**, c'est-à-dire trouver un chemin d'un sommet à un autre de façon à ce que la somme des arêtes de ce chemin  soit inférieure au poids des autres chemins

Ces algorithmes utilisent la notion de graphe pondéré afin de calculer le plus court chemin, dans notre cas les poids correspondent aux distances entre les différents sommets

## Algorithmes implémentés

### Parcours en profondeur modifié

Le parcours en profondeur modifié renvoie un chemin entre deux sommets s'il existe. Cet algorithme ne renvoie pas nécessairement le chemin le plus court.

Cet algorithme exécute un **parcours en profondeur** depuis le sommet de départ, si cet algo parvient à atteindre le sommet de destination il s'arrête pour retourner le chemin inverse trouvé.  
Comme le chemin obtenu n'est pas comparé avec les autres chemins possibles, nous ne pouvons pas affirmer que le chemin obtenu est le plus court chemin.

Cet algorithme a été ajouté à la liste des algorithmes implémentés afin de montrer que trouver un chemin existant a une complexité bien inférieure à trouver le plus court chemin

*Complexité:*  
|V| : le nombre de sommets  
|E| : le nombre d'arêtes  
Coût au pire: O(|V| + |E|)

---

### Algorithme de Dijkstra
L'**algorithme de Dijkstra** est l'algorithme le plus connu résolvant le problème du plus court chemin.  
Cet algorithme fonctionne exclusivement avec un graphe contenant des arêtes de poids positifs.  
Fondé sur un parcours en largeur classiquen cet algorithme permet de calculer tous les plus courts chemins d'un sommet de départ vers tous les autres sommets.

A la fin de l'exécution de l'algorithme de Dijkstra, on obtient pour chaque sommet sa distance par rapport au sommet source, et le sommet parent permettant de retrouver le chemin inverse.

De nombreux dérivés de l'algorithme de Dijkstra existent, et d'autres algorithmes se basent dessus tel que l'algorithme A*.


*Complexité:*  
|V| : le nombre de sommets  
|E| : le nombre d'arêtes  
Coût au pire: O(|V| log |V| + |E|)

---

### Algorithme de Bellman-Ford
L'algorithme de Bellman-Ford est également un algorithme permettant de résoudre le problème du plus court chemin.

De la même façon que l'algorithme de Dijkstra, l'algorithme de Bellman-Ford permet de calculer tous les plus courts chemins d'un sommet de départ vers tous les autres sommets.  
Contrairement à l'algorithme de Dijkstra, celui de Bellman-Ford accepte les graphes de poids négatifs (mais sans cycle négatif).


*Complexité:*  
|V| : le nombre de sommets  
|E| : le nombre d'arêtes  
Coût au pire: O(|V||E|)