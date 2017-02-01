package examenPPOA;

public class ArbreLexicographique {
	private NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = new NoeudVide();
	}

	public boolean isEmpty() {
		return entree instanceof NoeudVide;
	}

	public int size() {
		return entree.nbMots();
	}

	public boolean contient(String s) {
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		return entree.prefixe(s);
	}

	public boolean ajout(String s) {
		try {
			entree = entree.ajout(s);
			return true;
		} catch (ModificationImpossibleException mie) {
			return false;
		}
	}

	public boolean suppr(String s) {
		try {
			entree = entree.suppr(s);
			return true;
		} catch (ModificationImpossibleException mie) {
			return false;
		}
	}

	public String toString() { // éléments séparés par \n
		return entree.toString("");
	}

	public static void main(String[] args) {
		ArbreLexicographique arbre = new ArbreLexicographique();
		System.out.println(arbre.ajout("mot"));
		System.out.println(arbre.suppr(""));
		
		System.out.println(arbre.ajout("wic"));
		System.out.println(arbre.ajout("wax"));
		System.out.println(arbre.ajout("wifi"));
		System.out.println(arbre.ajout("mode"));
		System.out.println(arbre.ajout("motus"));
		System.out.println(arbre.ajout("motul"));
		System.out.println(arbre.ajout("wic"));
		
		System.out.print(">>>>>>" + arbre + "<<<<<<<<");
		
		System.out.println(arbre.suppr("motul"));
		System.out.println(arbre.suppr("mot"));
		System.out.println(arbre.suppr("motul"));
		System.out.println(arbre.suppr("motif"));

		System.out.println();
		System.out.print(">>>>>>" + arbre + "<<<<<<<<");

	


	}

}
