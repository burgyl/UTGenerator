# UTGenerator

Un générateur de tests unitaires.

## Comment l'utiliser

"Classe" et "méthode" permettent d'indiquer de quelle méthode est le test unitaire et dans quelle classe il se trouve.
Pour avoir un ArrayList comme résultat, il faut cocher la case "ArrayList" et choisir le type de l'ArrayList dans "Contient".

Pour les arguments, on peut en avoir un ou deux, la colonne de gauche est pour le premier, celle de droite pour le deuxième. 
Chaque possibilité d'argument est mise dans un des champs.
Les trois boutons remplissent seulement les champs selon une prédifinition, vous pouvez les personnaliser.

Pour avoir un ArrayList en argument, il faut cocher la case "ArrayList" de l'argument. La dernière possibilité d'argument se changera en "Contient" et il faudra mettre à cet endroit le type de l'argument.

Les tests seront générés en appuyant sur "Générer". Une fenêtre s'affichera pour entrer le résultat de chaque possibilité, le premier argument à gauche et le deuxième à droite, il faudra entrer le résultat. Les tests unitaires générés seront affichés à droite, il suffira de les coller dans la classe de test.

## Valeurs spéciales

Pour entrer des strings, il faut simplement les mettre en guillemets.

Pour entrer des ArrayList, il faut cocher la case pour le paramètre/résultat et mettre dans "Contient" le type de l'ArrayList. Il faut mettre toutes les valeurs entre crochets et séparées par des virgules.
