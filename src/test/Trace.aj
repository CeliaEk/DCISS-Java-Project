package test;

public aspect Trace {
	// A completer 
	// faire en sorte qu'un message du type
	// "trace ----> Iterateur construit pour un arbre de taille n"
	// ou n est la taille de l'arbre
	// soit affiche sur la console a chaque construction d'un tel iterateur

	// Quand utiliser la trace ?
		// Des qu'un iterateur d'arbre est cree
	pointcut creation(ItrArbre it):execution(ItrArbre.new(ArbreLexicographique))&&this(it);

	// Advice apres un appel, on affiche la trace
	after(ItrArbre it):creation(it){
		System.out
				.println("trace ----> Iterateur construit pour un arbre de taille "
						+ it.getArbre().size());
	}
}