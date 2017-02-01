package arbrelexicographique;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public aspect Serialisation {

	declare parents : ArbreLexicographique implements Serializable;

	public void ArbreLexicographique.sauve(String nomFichier){
		//Ecrire l'arbre dans un fichier
		File f = new File(nomFichier);
		
		try {
		      // Ouverture d'un flux en lecture depuis le fichier de nom fichier 
		        BufferedWriter out1 = new BufferedWriter(new FileWriter(nomFichier));
		        out1.write(this.toString());
		        out1.flush();
		        out1.close();
		       
		      } catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("erreur" + e.getMessage());
			}
	}
	
	public void ArbreLexicographique.charge(String nomFichier){
	      try {
	      // Ouverture d'un flux en lecture depuis le fichier de nom fichier 
	       BufferedReader in = new BufferedReader(new FileReader(nomFichier));
	       Scanner s = new Scanner(in);
	       while(s.hasNext()){
	    	   this.ajout(s.nextLine());
	       }
	       in.close();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	       
	}
	       
	       
	       
//	        int c;
//	        String mot="";
//	        while ((c = in1.read()) != -1) {
//	        	if((char)c!='\n'){
//	        		mot = mot + (char)c;
//	        	}else{ 
//	        		this.ajout(mot);
//	        	}
//	        }
	        

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	}

}
