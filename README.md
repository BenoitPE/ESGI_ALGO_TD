# ALGO TD2

###### tags: `ESGI`, `ALGO`

## Exercice 1:
![](https://i.imgur.com/OCkELsq.png =x250)

:warning: Je ne suis pas sûr des réponses que j'ai fourni :warning:
1. Quel tableau à une dimension permet de stocker cet arbre en mémoire ?
>`[20, 5, 25, 3, 12, 21, 28, 8, 13, 6]`


2. Cet arbre est-il un tas ? Si ce n’est pas le cas, est-il suffisant de tamiser cet arbre pour qu’il devienne un tas ? Quel serait le tableau ainsi obtenu ?

:thought_balloon: Rappel : `Tamisage` : Opération permettant de construire un tas à partir d’un arbre binaire quelconque. Cela s’effectue par déplacement et inversion de nœuds.
> Ce n'est pas un tas car il viole la propriété de tas :
Un noeud de valeur 12 ne devrait pas être fils d'un noeud de valeur 5  
> Tamiser permettrait de transformer cet arbre en tas  
> Tableau obtenu : `[3, 5, 6, 8, 12, 13, 20, 21, 25, 28]`

3.  Même question avec l’arbre représenté par le tableau suivant :
>Ancien tableau:  
>`20 2 35 50 12 885 9 7`  
>Nouveau tableau:  
`2 7 9 12 20 35 50 885`

## Exercice 2
1.
>Ancien tableau:  
>`25 40 2 65 10`  
>Nouveau tableau:  
>`2 10 25 40 65`

2.
>Ancien tableau:  
>`10 50 1 25 35 15`  
>Nouveau tableau:  
>`1 10 15 25 35 50`  


