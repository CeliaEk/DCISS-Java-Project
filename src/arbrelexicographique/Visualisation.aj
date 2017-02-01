package arbrelexicographique;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

public privileged aspect Visualisation {

	declare parents : ArbreLexicographique implements TreeModel;
	private DefaultTreeModel ArbreLexicographique.treeModel;
	private JTree ArbreLexicographique.vue;

	declare parents : NoeudAbstrait implements TreeNode;
	private DefaultMutableTreeNode NoeudAbstrait.treeNode;


	/* 
	 * ----------------------Implémentation de l'interface TreeModel-------------------------
	 * */
	public void ArbreLexicographique.setVue(JTree jt){
		//modifier la valeur de l'attribut vue
		this.vue = jt;
	}
	
	public void ArbreLexicographique.addTreeModelListener(TreeModelListener l){
	//Adds a listener for the TreeModelEvent posted after the tree changes.
		this.treeModel.addTreeModelListener(l);
	}
	
	public Object ArbreLexicographique.getChild(Object parent, int index){
		return this.treeModel.getChild(parent, index);
		//Returns the child of parent at index index in the parent's child array.
	}
	public int ArbreLexicographique.getChildCount(Object parent){
		return this.treeModel.getChildCount(parent);
	//Returns the number of children of parent.
	}
	
	public int	ArbreLexicographique.getIndexOfChild(Object parent, Object child){
		return this.treeModel.getIndexOfChild(parent, child);
	//Returns the index of child in parent.
	}
	public Object ArbreLexicographique.getRoot(){
		return this.treeModel.getRoot();
	//Returns the root of the tree.
	}
	
	public boolean	ArbreLexicographique.isLeaf(Object node){
		return this.treeModel.isLeaf(node);
		//Returns true if node is a leaf.
	}
	public void ArbreLexicographique.removeTreeModelListener(TreeModelListener l){
		//Removes a listener previously added with addTreeModelListener.
		this.treeModel.removeTreeModelListener(l);
	}
	
	public void ArbreLexicographique.valueForPathChanged(TreePath path, Object newValue){
		this.treeModel.valueForPathChanged(path, newValue);
	}
	
	
/* ------------------------Implémentation de l'interface TreeNode----------------------------- */
	public Enumeration<TreeNode> NoeudAbstrait.children(){
	return this.treeNode.children();
	//Returns the children of the receiver as an Enumeration.
	}
	
	public boolean NoeudAbstrait.getAllowsChildren(){
	return this.treeNode.getAllowsChildren();
	//Returns true if the receiver allows children.
	}

	public TreeNode NoeudAbstrait.getChildAt(int childIndex){
	return this.treeNode.getChildAt(childIndex);
	//Returns the child TreeNode at index childIndex.
	}

	public int NoeudAbstrait.getChildCount(){
	return treeNode.getChildCount();
	//Returns the number of children TreeNodes the receiver contains.
	}

	public int NoeudAbstrait.getIndex(TreeNode node){
	return treeNode.getIndex(node);
	//Returns the index of node in the receivers children.
	}

	public TreeNode NoeudAbstrait.getParent(){
	return treeNode.getParent();
	//Returns the parent TreeNode of the receiver.
	}

	public boolean NoeudAbstrait.isLeaf(){
	return treeNode.isLeaf();
	//Returns true if the receiver is a leaf.
	}
	
	
/* --------------------------------------Pointcuts & Advices ----------------------------------------*/
	
	//Création d'un nouveau TreeModel quand un nouvel ArbreLexicographique est instancié.
	pointcut initArbre(ArbreLexicographique a) : target(a) && execution(ArbreLexicographique.new());
	after (ArbreLexicographique a) : initArbre(a){
		a.treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
	}
	
	//Lorsque la racine est modifiée.
	pointcut modifR(ArbreLexicographique a) : target(a) && set(NoeudAbstrait ArbreLexicographique.entree);
	
	//modification de l'arbre lorsque la racine change lors de l'ajout d'un mot
	pointcut modifAjout(ArbreLexicographique a) : target(a) && modifR(ArbreLexicographique) && withincode(boolean ArbreLexicographique.ajout(String));
	after(ArbreLexicographique a) : modifAjout(a){
		if(a.entree.treeNode != null){
		((DefaultMutableTreeNode) a.treeModel.getRoot()).insert(a.entree.treeNode, 0);
		((DefaultTreeModel)a.treeModel).reload(); //on rafraichit l'arbre après le changement
		}
	}
	
	//modification de l'arbre lorsque que la racine change lors de la suppression d'un mot
	pointcut modifSuppr(ArbreLexicographique a) : target(a) && modifR(ArbreLexicographique) && withincode(boolean ArbreLexicographique.suppr(String));
	before(ArbreLexicographique a) : modifSuppr(a){
		((DefaultMutableTreeNode)a.getRoot()).remove(a.entree.treeNode);
	}
	after(ArbreLexicographique a) : modifSuppr(a){
		if(a.entree != new NoeudVide()){
			((DefaultMutableTreeNode) a.treeModel.getRoot()).insert(a.entree.treeNode, 0);
		}
		((DefaultTreeModel)a.treeModel).reload(); //on rafraichit l'arbre après le changement
	}
	
	pointcut initNoeudAbstrait(NoeudAbstrait n) : target(n) && execution(NoeudAbstrait.new(NoeudAbstrait));
	after(NoeudAbstrait n) : initNoeudAbstrait(n){
		n.treeNode = new DefaultMutableTreeNode();
	}
	
	//A la création d'un nouveau noeud, on crée un nouveau TreeNode qui possède un frere, un fils et un char
	pointcut initNoeud(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : target(n) && args(frere, fils, val) && execution(Noeud.new(NoeudAbstrait, NoeudAbstrait, char));
	after(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : initNoeud(n, frere,  fils,  val){
		n.treeNode = new DefaultMutableTreeNode(Character.toString(val));
		n.treeNode.add(fils.treeNode);
	}
	
	//modification d'un frere
	pointcut modifFrere(NoeudAbstrait n) : target(n) && set(NoeudAbstrait NoeudAbstrait.frere);
		
	//modification d'un fils
	pointcut modifFils(Noeud n, NoeudAbstrait n1) : this(n) && target(n1) && set(NoeudAbstrait Noeud.fils);
		
	//modification d'un fils lors de l'ajout
	pointcut modifFilsajout(Noeud n) : target(n) && modifFils(Noeud, NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait.ajout(String));
	after(Noeud n) : modifFilsajout(n){
		((DefaultMutableTreeNode) n.treeNode).insert(n.fils.treeNode, 0);
	}
	
	//Modification d'un frere lors de l'ajout
	pointcut modifFrereajout(NoeudAbstrait n, NoeudAbstrait nouveauFrere) :
	target(n) && set(NoeudAbstrait NoeudAbstrait.frere) && args(nouveauFrere) && withincode(NoeudAbstrait NoeudAbstrait+.ajout(String));
	after(NoeudAbstrait n, NoeudAbstrait nf) : modifFrereajout(n, nf){
		if(n.getParent() != null){
			((DefaultMutableTreeNode) n.treeNode.getParent()).insert(nf.treeNode,0);
		}
	}
		
		//modification d'un fils dans la méthode suppr
	pointcut modifFilsSuppr(Noeud n) : target(n) &&  modifFils(Noeud, NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.suppr(String));
	before(Noeud n) : modifFilsSuppr(n){
		if(n.fils.treeNode.getParent() != null) n.treeNode.remove(n.fils.treeNode);
		}
		after(Noeud n) : modifFilsSuppr(n){
			if(n.fils != new NoeudVide()) n.treeNode.insert(n.fils.treeNode, 0);
		}
		
		//modification d'un frere dans la méthode suppr
	pointcut modifFrereSuppr(NoeudAbstrait n) : target(n) && modifFrere(NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.suppr(String));
	before(NoeudAbstrait n) : modifFrereSuppr(n){
			((DefaultMutableTreeNode)n.treeNode.getParent()).remove(n.frere.treeNode);
		}
	after(NoeudAbstrait n) : modifFrereSuppr(n){
		if(n.frere != new NoeudVide())
			((DefaultMutableTreeNode) n.treeNode.getParent()).insert(n.frere.treeNode, 0);
		}
		

}


