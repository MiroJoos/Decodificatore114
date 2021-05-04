import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.io.IOException;

/**
 * Questa classe rappresenta un analizzatore di file.
 *
 * @author Miro Joos
 * @version 2021.05.04
 */
public class Decodificatore{

	/**
	 * Questo metodo stampa a terminale la frequenza con la quale i caratteri sono presenti nel testo.
	 *
	 * @param caratteri Contiene 95 caratteri di utf-8 partendo dallo spazio (carattere numero 0020 in hex).
	 * @param contatoreCaratteri Contiene il numero della frequenza dei caratteri nel file.
	 */
	public static void printFrequnzaCarattere(char[] caratteri, int[] contatoreCaratteri, double[] percentuale){
		for (int i = 0 ; i < caratteri.length ; i++) {
			System.out.println("'" + caratteri[i] + "': " + contatoreCaratteri[i]+"': "+ percentuale[i]);
		}
    }
    
    /**
     * Questo metodo si occcupa di prendere il percorso inserito dall'utente.
     * @param args Contiene la stringa che determina il percorso
     * @return Contiene il percorso
     * @throws IndexOutOfBoundsException Eccezione in caso di mancante stringa.
     */
    public static Path trovaPercorso(String[] args) throws IndexOutOfBoundsException{
        try{
            return Paths.get(args[0]);
        }catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static char[] genereteAvaibleChar(){
    	//contiene tutti i caratteri, 95 poichè 127-32=95. (nella tabella asci le lettere e i segni partono dal 32).
        //char[] caratteri = new char[95];
		
		//contiene tutti i caratteri MINUSCOLI
		char[] caratteri = new char[25];

		//Riempimento dell'array caratteri di caratteri partendo dal carattere 65 di utf-8.
		int count = 97;
		for (int i = 0 ; i < caratteri.length ; i++) {
			caratteri[i] = (char)count;
			count++;
		}
        count = 0;

        return caratteri;
    }

    public static double calculatePercentage(int obtained, int total) {
    	//System.out.println("primo: "+obtained);
    	//System.out.println("diviso: "+total);
        return obtained * 100 / total;
    }

    public static void trovaPercentualeCaratteri(Path percorso, char[] caratteri, int[] contatoreCaratteri, long start){
    	int count = 0;
    	int caratteriTotali = 0;

    	//blocco try catch per gestire eccezioni di lettura.
		try{
			List<String> lines = Files.readAllLines(percorso);

			//For per leggere tutto il file
			for (String line : lines) {
				line.toLowerCase();
				//For per percorrere una riga
				for (int i = 0; i < line.length() ; i++) {

					//For per controllo lettera e aggiunta frequenza
					for (int j = 0 ;  j < caratteri.length ; j++) {
						if (line.charAt(count) == caratteri[j]) {
							contatoreCaratteri[j]++;
						}
					}
					if(line.charAt(count) != 32){
						caratteriTotali++;						
					}
					count++;
				}
				count = 0;
			}


			//Frequenza caratteri
			System.out.println("Caratteri totali: "+caratteriTotali);
			double[] percentuale = new double[caratteri.length];
			for (int i = 0; i < contatoreCaratteri.length; i++){
				percentuale[i] = calculatePercentage(contatoreCaratteri[i], caratteriTotali);
			}

			printFrequnzaCarattere(caratteri, contatoreCaratteri, percentuale);
            System.out.println();
            
			//Numero di righe presenti all'interno del file
			//System.out.println("Il file contiene " + Files.lines(percorso).count() + " righe");
			//System.out.println();
            
            //Fine misurazione del tempo e calcolo del tempo impiegato (in millisecondi)
			System.out.println("Parse time: " + (System.currentTimeMillis() - start) + " ms");
		}catch(IOException ex){
			System.out.println("Error reading <file>");
		}
    }

    public static void decodifica(char[] caratteri, int[] contatoreCaratteri, double[] percentuale){
    	char[] caratteriDecodificati = new char[25];

    	for (int i = 0; i < percentuale.length ;i++ ) {
    		if (percentuale[i] > 11.40 && percentuale[i] <= 11.75){
    			caratteriDecodificati[i] = 'a';
    		}else if (percentuale[i] >= 0.66 && percentuale[i] <= 0.93){
    			caratteriDecodificati[i] = 'b';
    		}else if (percentuale[i] > 3.90 && percentuale[i] <= 4.70){
    			caratteriDecodificati[i] = 'c';
    		}else if (percentuale[i] > 3.40 && percentuale[i] <= 3.90){
    			caratteriDecodificati[i] = 'd';
    		}else if (percentuale[i] > 11.75){
    			caratteriDecodificati[i] = 'e';
    		}else if (percentuale[i] >= 0.94 && percentuale[i] <= 1){
    			caratteriDecodificati[i] = 'f';
    		}else if (percentuale[i] > 1.57 && percentuale[i] <= 1.80){
    			caratteriDecodificati[i] = 'g';
    		}else if (percentuale[i] > 1 && percentuale[i] <= 1.57){
    			caratteriDecodificati[i] = 'h';
    		}else if (percentuale[i] > 10 && percentuale[i] < 11.40){
    			caratteriDecodificati[i] = 'i';
    		}else if (percentuale[i] > 6.40 && percentuale[i] <= 6.70){
    			caratteriDecodificati[i] = 'l';
    		}else if (percentuale[i] > 2.25 && percentuale[i] <= 2.80){
    			caratteriDecodificati[i] = 'm';
    		}else if (percentuale[i] > 6.70 && percentuale[i] < 8){
    			caratteriDecodificati[i] = 'n';
    		}else if (percentuale[i] > 8 && percentuale[i] < 10){
    			caratteriDecodificati[i] = 'o';
    		}else if (percentuale[i] > 3.02 && percentuale[i] <= 3.40){
    			caratteriDecodificati[i] = 'p';
    		}else if (percentuale[i] >= 0.51 && percentuale[i] <= 0.65){
    			caratteriDecodificati[i] = 'q';
    		}else if (percentuale[i] > 6 && percentuale[i] <= 6.40){
    			caratteriDecodificati[i] = 'r';
    		}else if (percentuale[i] > 4.70 && percentuale[i] <= 5){
    			caratteriDecodificati[i] = 's';
    		}else if (percentuale[i] > 5 && percentuale[i] <= 6){
    			caratteriDecodificati[i] = 't';
    		}else if (percentuale[i] > 2.80 && percentuale[i] <= 3.02){
    			caratteriDecodificati[i] = 'u';
    		}else if (percentuale[i] > 1.80 && percentuale[i] <= 2.25){
    			caratteriDecodificati[i] = 'v';
    		}else if(percentuale[i] >= 0 && percentuale[i] <= 0.50){
    			caratteriDecodificati[i] = 'z';
    		}
    	}


    	//rimpiazzare caratteri

    }
	/**
     * Metodo main.
	 * Questo metodo "preleva" ìl percorso del file che l'utente assegna tramite linea di comando (args).
	 * @param args Array di stringhe che contiene il percorso fornito dall'utente.
	 */
	public static void main(String[] args) {
		//Inizio misurazione del tempo
		long start = System.currentTimeMillis();

        //Path del file inserita dall'utente
        Path percorso = trovaPercorso(args);
        if (percorso == null){
            System.out.println("Usage: java CharCounter <fileName>");
            return;
        }
        
        //contiene i caratteri validi
        char[] caratteri = genereteAvaibleChar();
        System.out.println("caratteri.length: " + caratteri.length);

		//contiene il numero di frequenza dei caratteri associato a ogni carattere
		int[] contatoreCaratteri = new int[caratteri.length];

		//
		trovaPercentualeCaratteri(percorso, caratteri, contatoreCaratteri, start);
	}
}