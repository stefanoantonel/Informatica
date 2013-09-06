package txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Lectura {

    public static void main(String[] args) {
        try{
        String retornar="";
        String linea="";
        
        
        BufferedReader br=new BufferedReader(new FileReader("leer.txt"));
        br.read();
        while ((linea = br.readLine()) != null){
            retornar += linea + "\n";
        }
        JOptionPane.showMessageDialog(null, retornar);
        br.close();
        }
        catch ( IOException e){
            JOptionPane.showMessageDialog(null, "ERROR \nCree un archivo 'leer.txt' en la carpeta donde ejecuta el archivo");
        }
    }
}
