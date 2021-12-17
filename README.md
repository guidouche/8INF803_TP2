# 8INF803_TP2

Exercice 1:
Pour lancer notre application il faut démarrer le main.py

Les fichiers de base sont déjà présent, si vous relancez le programme ils seront écrasés. 
Vous retrouverez : 
- myFile.json -> contient pour chaque créature la liste des sorts associées.
- invertedIndex.json -> contient pour chaque sort la liste des créatures associées.
- Spell.json -> contient tous les éléments récupérés du TP1 ( Nom,niveau,composante,description,url,classe liées..)
- my.json -> contient pour chaque créature le lien de sa page d'information.
- exo2.db est une table généré pour l'exercice 2, vous y retrouverez toutes les informations présent dans les Json sous forme d'une BDD SqLite.


Un menu se lance lors du lancement du programme, vous allez pouvoir sélectionner les options que vous souhaitez.

1- Parser la page et generer les Json
2- Génère le json d'index inversé ( Créature[Sort]->Sort[Créature] )
3- Génère une base de donnée avec toutes les informations des sorts et des cr éatures
4- Exit le programme
