package examenPPOA;

public aspect Singleton {

	private NoeudVide instance = new NoeudVide();
	
	private pointcut appelConstructeurNoeudVide() : call(public NoeudVide.new()) && ! within(Singleton) && within(arbreLexicographique.*);

	NoeudVide around() : appelConstructeurNoeudVide() {
//		System.out.println("Appelant : " + thisJoinPoint.getThis().getClass().getSimpleName());
		return instance;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	}

}
