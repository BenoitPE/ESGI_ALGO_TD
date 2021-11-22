# ALGO TD3 - Arbre binaire de recherche

###### tags: `ESGI`, `ALGO`

## Exercice 1:
#### 1. 
> Un arbre binaire de recherche est un arbre binaire dans lequel chaque nœud possède une clé,
> telle que chaque nœud du sous-arbre gauche ait une clé inférieure ou égale à celle du nœud considéré,
> et que chaque nœud du sous-arbre droit possède une clé supérieure ou égale à celle-ci.
> Les nœuds que l'on ajoute deviennent des feuilles de l'arbre.

Cette arbre est un arbre binaire de recherche.

#### 2.
Les noeuds 5 et 25 existent déjà
![img.png](gitressources/img.png)

#### 3.
Parcours en profondeur infixe : ```3 5 6 8 12 13 19 20 21 25 28 50```. On remarque que lors d'un parcours en profondeur infixe le résultat obtenu est un tableau trié par ordre croissant

##Exercice 2:
#### 1.
```5 10 15 20 25 30 35 40 45 50 55 60 65 70```

#### 3.
Nous pouvons constater que le temps de recherche dans un ABR (arbre binaire de recherche) est inférieur au temps de recherche dans une liste. Cela est dû à la méthode de parcours dans un ABR, la récurrence amène à une complexité de forme $\frac{n}{n^2+1}$

#### 4.
Nous pouvons constater que le temps écoulé pour trier un ABR avec un parcours infixe est inférieur au temps écoulé pour trier une liste avec Collections.sort().
Nous pouvons également constater que le temps de tri de l'ABR varie en fonction de la forme de l'arbre, le cas au mieux correspond à un arbre équilibré, le cas au pire est lorsque l'arbre est complètement déséquilibré et ressemble à une liste chaînée