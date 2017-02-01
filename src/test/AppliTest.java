package test;

import java.util.ArrayList;
import java.util.List;

public class AppliTest {
	
	// Classe de test
	// Modifier/etendre le main a volonte

	public static void main(String[] args) {
		ArbreLexicographique a = new ArbreLexicographique();
		System.out.print(a);
		System.out.println(a.size());
		System.out.println("----------");
		a.add("mot");
		a.add("motus");
		a.add("test");
		a.add("tester");
		a.add("add");
		a.add("ajout");
		System.out.print(a);
		System.out.println(a.size());
		System.out.println("----------");
		System.out.println(a.contains("arbre"));
		System.out.println(a.contains("add"));
		String[] t = a.toArray();
		System.out.println("----------");		
		System.out.println(a.remove("ajout"));
		System.out.println(a.remove("rien"));
		System.out.println("----------");
		System.out.println("Affichage avec un \"foreach\"");
		for (String s : a)
			System.out.println(s);
		System.out.println("----------");
		List<String> l = new ArrayList<String>();
		l.add("un");
		l.add("mot");
		l.add("pour");
		l.add("tester");
		System.out.println("taille avant :"+a.size());
		System.out.println(a.removeAll(l));
		System.out.println("taille apres :"+a.size());
		System.out.print(a);
		for (int i = 0; i < t.length; i ++) {
			a.add(t[i]);
		}
		System.out.println("----------");
		System.out.print(a);
		
		System.out.println("Ajout de test sur l'implementation des Set");
		// test clear
		System.out.println("");
		System.out.println("-- Test clear() --");
		System.out.println("taille avant clear :"+a.size());
		a.clear();
		System.out.println("taille apres clear :"+a.size());
		System.out.println("-- Test clear() --");
		System.out.println("");
		
		// test addAll
		System.out.println("-- Test addAll() --");
		List<String> l2 = new ArrayList<String>();
		l2.add("je");
		l2.add("suis");
		l2.add("etudiant");
		l2.add("a");
		l2.add("grenoble");
		System.out.println("taille avant addAll :"+a.size());
		System.out.println(a.addAll(l2));
		System.out.println("taille apres addAll("+l2.toString()+") :"+a.size());
		System.out.print(a);
		System.out.println("-- Test addAll() --");
		System.out.println("");
		
		// test containsAll
		System.out.println("-- Test containsAll() --");
		List<String> l3 = new ArrayList<String>();
		l3.add("etudiant");
		System.out.println("arbre contient "+l3.toString()+" ? "+ a.containsAll(l3));
		l3.add("grenoble");
		System.out.println("arbre contient "+l3.toString()+" ? "+ a.containsAll(l3));
		l3.add("lyon");
		System.out.println("arbre contient "+l3.toString()+" ? "+ a.containsAll(l3));
		System.out.println("-- Test containsAll() --");
		System.out.println("");
		
		// test retainAll
		System.out.println("-- Test retainAll() --");
		List<String> l4 = new ArrayList<String>();
		l4.add("suis");
		l4.add("grenoble");
		System.out.println("taille avant retainAll :"+a.size());
		System.out.println("arbre contient "+l4.toString()+" ? "+ a.containsAll(l4));
		System.out.println("on lance retainAll sur l4 : " + a.retainAll(l4));
		System.out.println("arbre contient "+l4.toString()+" ? "+ a.containsAll(l4));
		System.out.println("taille apres retainAll :"+a.size());
		System.out.print(a);
		System.out.println("-- Test retainAll() --");
	}

}
