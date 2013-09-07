package txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import javax.swing.JOptionPane;


public class Lectura {

    public static void main(String[] args) {
    	StringBuilder retornar=new StringBuilder(); //va acumulando todo
        String linea;	//contiene la linea
        File archivo=new File("C:\\leer.txt");
        FileReader fileReader=null; //abre el archivo en disco
        BufferedReader br=null; //buffer de todo el archivo
        int contadorLinea=0;
        
        try{
        	fileReader=new FileReader(archivo);
        	br=new BufferedReader(fileReader);
	        while ((linea = br.readLine()) != null){
	        	System.out.println(linea);
	            retornar.append(linea);
	            retornar.append("\n");
	        }
	    fileReader.close();
        JOptionPane.showMessageDialog(null, retornar);
        }
        catch ( IOException e){
            JOptionPane.showMessageDialog(null, "ERROR \nCree un archivo 'leer.txt' en C: ");
        }
    }
}
