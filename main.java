/*public class main
{
public static void main (String[] args)
{
System.out.println ("hello world");
}
}*/


import java.util.Date;

public class calcpi {

	public static void main(String[] args) {
		int i;
		UDChar pi, frac;
		java.lang.String StrPI="PI="; 
		Date now = new Date();
		System.out.println(now);
		 
		pi = new UDChar(2);
		frac = new UDChar(2);
		for (i=1; i <3000; i++){
			frac.mulEntiere(i);
			frac.divEntiere(2*i +1);
			pi.add(frac);
		}
        now = new Date();
		System.out.println(now);
		System.out.println("PI="+ pi);
		System.out.println("frac="+ frac);	
	}
}
/**********************fichier UDChar.java**************************/
/**

 
 @author cyberfastfred
 
 Created on 23 janv. 2005
 
 implémentation basique orientée pour le calcul de pi à 100 décimale
 
 La série calculant pi n'ajoutant que des nombre fractionnaires de plus en plus petit
 
 Les grands nombres sont uniquement positifs
 
/

public class UDChar  {
	/*nombre de chiffres*/
	public static final int PRECISION = 1000;

	protected char [] chiffres = new char [PRECISION];
	/* 10 chiffres avant la virgule PRECISION-10 chiffres après */
	
	/*créé un grand décimal à partir d'un int

	 
 
	 
/   	

    public UDChar(int entier){
    	int i;
        /*

         
 Initiailisation de la partie entière
         
/

         for (i=9; i>=0; i--){
         	int chiffre;
         	chiffre = entier % 10;
         	chiffres[i] = intToChiffre(chiffre);

         	entier /=10;
         }
         /*mise à 0 des décimales*/
         for (i=10; i<PRECISION; i++) chiffres[i]='0';
    }
    
	/*

	 
 Ajoute l'operande Ultradecmal
	 
/

	public void add(UDChar operande) {
		int i, somme, retenue;
		retenue = 0;
		for(i = PRECISION - 1; i >= 0; i--){
			somme = chiffreToInt(chiffres[i])
			      + chiffreToInt(operande.chiffres[i])
				  + retenue;
			retenue = somme / 10;
			somme %= 10;
			chiffres[i] = intToChiffre(somme);
		}
	}

	/*

	 
 Soustrait l'opérande UltraDecimal
	 
/

	public void sub(UDChar operande) {
		int i, retrait, retenue;
		retenue = 0;
		for(i = PRECISION - 1; i >= 0; i--){
			retrait = 10 + chiffreToInt(chiffres[i])
			      - chiffreToInt(operande.chiffres[i])
				  - retenue;
			retenue = 1 - (retrait / 10);
			retrait %= 10;
			chiffres[i] = intToChiffre(retrait);
		}
		
	}

	/*

	 
 multiplication d'un UD par un entier
	 
 suffisant pour le calcul de séries simples
	 
/

	public void mulEntiere(int operande){
		UDChar partiel, original;
		int i, chiffre, operandeSuiv;
		original = this.dupl();
		
		/* remise à zero de l'UD */
		for (i=0; i<PRECISION; i++) chiffres[i]='0';
		
		operandeSuiv = operande;
		for (i=0; i <10 ;i++){
			partiel = original.dupl();
			chiffre = operandeSuiv % 10;
			operandeSuiv /=10;
			partiel.mulUnit(chiffre);
			this.add(partiel);
			original.shiftLeft(1);
		}
	}

	/*

	 
 multiplication d'un UD par un entier
	 
 suffisant pour le calcul de séries simples
	 
/

	public void divEntiere(int operande){
		int i, reste, result;
		reste = 0;
		result = 0;
		for (i = 0; i <PRECISION; i++){
			reste = reste * 10 + chiffreToInt(chiffres[i]);
			result = reste / operande;
			reste = reste % operande;
			chiffres[i] = intToChiffre(result);
		}
    	
	}

	/*

	 
 Renvoie une chaine cotenant le nombre représenté 
	 
/

	public String toString() {
		String str= new String("");
		int i;
		for(i=0; i<10; i++){
			str = str + chiffres[i];
		}
		str = str +",";
		for(i=10; i<PRECISION; i++){
			if (i%80==0)str=str+"\n";
			str = str + chiffres[i];
		}
		return str;
	}
	/*

	 
 clone un UD
	 
/

	private UDChar dupl(){
		UDChar ud = new UDChar(0);
		ud.add(this); /* pas optimal mais facile */
		return ud;
	}
	
	/*

	 
 decalage à droite
	 
 equivalent à multiplication par 10 puissance pas
	 
/

	private void shiftRight(int pas){
		int i;
		if (pas <= 0) return;
		if (pas > PRECISION) pas = PRECISION;
		for (i = pas; i <PRECISION; i++){
			chiffres[i] = chiffres[i-pas];
		}
		for (i = 0; i < pas; i++){
			chiffres[i] = '0';
		}
	}
	
	/*

	 
 decalage à gauche
	 
 equivalent à multiplication par 10 puissance pas
	 
/

	private void shiftLeft(int pas){
		int i;
		if (pas <= 0) return;
		if (pas > PRECISION) pas = PRECISION;
		for (i = 0; i < PRECISION-pas; i++){
			chiffres[i] = chiffres[i+pas];
		}
		for (i = PRECISION-pas; i <PRECISION; i++){
			chiffres[i] = '0';
		}
	}
	
	/*

	 
 convertie un chiffre sous forme de char en entier
	 
/

	private int chiffreToInt(char chiffre){
		switch(chiffre){
			case '0':
				return(0);
			case '1':
				return(1);
			case '2':
				return(2);
			case '3':
				return(3);
			case '4':
				return(4);
			case '5':
				return(5);
			case '6':
				return(6);
			case '7':
				return(7);
			case '8':
				return(8);
			case '9':
				return(9);
			default:
				return(0);
		}
	}
	
	/*

	 
 Convertie un entier de 0 à 9 en char
	 
/

	private char intToChiffre(int chiffre){
		switch(chiffre){
			case 0:
			  return('0');
			case 1:
				  return('1');
			case 2:
				  return('2');
			case 3:
				  return('3');
			case 4:
				  return('4');
			case 5:
				  return('5');
			case 6:
				  return('6');
			case 7:
				  return('7');
			case 8:
				  return('8');
			case 9:
				  return('9');
			default:
				return('0');
		}
	}
	
	/*

	 
 multiplie par un chiffre de 0 à 9
	 
/

	private void mulUnit(int operande){
		int chiffre, i, resultat, retenue;
		chiffre = operande%10;
		retenue = 0;
		for(i = PRECISION - 1; i >= 0; i--){
			resultat = chiffreToInt(chiffres[i])

				  
 chiffre

				  + retenue;
			retenue = resultat / 10;
			resultat %= 10;
			chiffres[i] = intToChiffre(resultat);
		}
	}

}
