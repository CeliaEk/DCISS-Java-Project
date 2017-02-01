package arbrelexicographique;

public class ArbreLexicographique {
	NoeudAbstrait entree;
	

	public ArbreLexicographique() {
		entree = new NoeudVide();
	}

	public String toString() {
		return entree.toString("");
	}

	public boolean contient(String s) {
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		return entree.prefixe(s);
	}

	public int nbMots() {
		return entree.nbMots();
	}

	public boolean ajout(String s) {
		try {
			entree = entree.ajout(s);
		} catch (ArbreLexicographiqueException ale) {
			return false;
		}
		return true;
	}

	public boolean suppr(String s) {
		try {
			entree = entree.suppr(s);
		} catch (ArbreLexicographiqueException ale) {
			return false;
		}
		return true;
	}

	public boolean estVide() {
		return entree instanceof NoeudVide;
	}
	
}
