package test;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ItrArbre implements Iterator<String> {

	private ArbreLexicographique arbre; // l'arbre sur lequel on itere
	private String liste; // liste des mots de l'arbre
	private int i; // indice du debut du prochain next() dans liste
	private String dernier; // dernier next() retourne, null si aucun next() n'a encore ete effectue
	// il n'est pas nécessaire d'ajouter des attributs supplémentaires
	
	public ItrArbre(ArbreLexicographique a) {
		this.arbre = a;
		this.liste = arbre.toString();
		this.i = 0;
		this.dernier = null;
	}

	// usefull to get arbre size in the trace aspect.
	public ArbreLexicographique getArbre() {
		return arbre;
	}
	
	@Override
	public boolean hasNext() {
		// check if "i" is less than the size of arbre.
		return (i < arbre.size());
	}

	@Override
	public String next() {
		// On cherche le suivant, s'il n'y en a pas = exception
		if (!hasNext())
			throw new NoSuchElementException();
		// S'il existe : return le dernier element parcouru
		String[] tableauDeMots = liste.split("\n");
		dernier = tableauDeMots[i];
		i++;
		return dernier;
	}

	@Override
	public void remove() {
		// On supprime le dernier element, s'il n'y en a pas = exception
		if (dernier == null)
			throw new IllegalStateException();
		// S'il existe, on le supprime, return void.
		arbre.suppr(dernier);
		liste = arbre.toString();
		i--;
		dernier = null;
	}
}
