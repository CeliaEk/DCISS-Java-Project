package test;

import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public aspect ArbreCollection {
	// A completer : faire en sorte que la classe ArbreLexicographique
	// implemente les interfaces Iterable<String>, Collection<String> et Set<String>

	// On d�clare qu'ArbreLexicographique implemente les interfaces
	declare parents : (ArbreLexicographique) implements Iterable<String>;
	declare parents : (ArbreLexicographique) implements Collection<String>;
	declare parents : (ArbreLexicographique) implements Set<String>;

	// ...
	
	public <T> T[] ArbreLexicographique.toArray(T[] t) {
		// ...
		String[] tableauArbre = this.toArray();
		
		// indication pour cette methode : l'instruction suivante permet d'affecter a t un nouveau tableau de taille 100 qui est exactement du meme type que T.
			t = (T[])java.lang.reflect.Array.newInstance(t.getClass().getComponentType(), tableauArbre.length);
		// ...
		for (int i = 0; i < t.length; i++)
			t[i] = (T) tableauArbre[i];
		return t;
	}

	// ...
	// verifie l'ajoute 1 mot
	public boolean ArbreLexicographique.add(String mot) {
		return this.ajout((String) mot);
	}

	// Ajoute une collection de mots.
	public boolean ArbreLexicographique.addAll(Collection<? extends String> mots) {
		// pour chaque mot e la collection ...
		for (String mot : mots) {
			// on l'ajoute. return false si ajout bug
			if (!this.ajout(mot))
				return false;
		}
		return true;
	}

	// supprime tout les mots d'un arbre
	public void ArbreLexicographique.clear() {
		// on transforme l'arbre en tableau de mots ...
		String[] tableauArbre = this.toArray();
		// ... puis on supprime chaque mot
		for (String mot : tableauArbre)
			this.remove(mot);
	}

	// verifie si l'arbre contient un mot.
	public boolean ArbreLexicographique.contains(Object mot) {
		return this.contient((String) mot);
	}

	// verifie si l'arbre contient l'integralite de la collection de mot en parametre
	public boolean ArbreLexicographique.containsAll(
			Collection<? extends String> mots) {
		// pour chaque mots
		for (String mot : mots) {
			// s'il n'est pas contenu : return false
			if (!this.contains(mot))
				return false;
		}
		// si on arrive ici, c'est que tous les mots sont presents
		return true;
	}
	
	public Iterator<String> ArbreLexicographique.iterator() {
		return new ItrArbre(this);
	}

	// verifie la suppression d'un mot
	public boolean ArbreLexicographique.remove(Object mot) {
		return this.suppr((String) mot);
	}

	// verifie la suppression d'une collection de mot
	public boolean ArbreLexicographique.removeAll(
			Collection<? extends String> mots) {
		// pour chaque mots
		for (String mot : mots) {
			// si le mot est present, on le retire
			if (this.contains(mot)) {
				// s'il n'y a pas eu de suppresion, on return false
				if (!this.remove(mot))
					return false;
			}
		}
		// si on arrive ici, c'est que tous les mots ont ete supprime
		return true;
	}

	// donne l'intersection de l'arbre courant avec la collection en parametre
	public boolean ArbreLexicographique.retainAll(
			Collection<? extends String> collectionMots) {
		// stock l'arbre sous forme de tableau
		String[] tableauArbre = this.toArray();
		// pour chaque mot de l'arbre
		for (int i = 0; i < tableauArbre.length; i++) {
			// on regarde si le mot n'est pas dans la liste en parametre
			if (!(collectionMots.contains(tableauArbre[i]))) {
				// alors on le supprime + test si erreur lors de la suppression
				if (!this.remove(tableauArbre[i]))
					return false;
			}
		}
		// si on arrive ici : l'arbre ne contient plus que les mots de la collection et on return true
		return true;
	}

	// renvoie l'arbre sous forme de tableau
	public String[] ArbreLexicographique.toArray() {
		// on recupere l'arbre sous forme de string que l'on decoupe avec le separateur
		return this.toString().split("\n");
	}
}
