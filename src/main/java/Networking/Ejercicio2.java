package Networking;

import java.io.*;
import java.net.URL;

public class Ejercicio2 {
    public static void main(String[] args) throws Exception {
        URL google = new URL("https://www.google.com/?hl=es");
        try { BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()));
            String filename = "Miejercicio";
            PrintWriter outputFile = new PrintWriter("C:\\Users\\danie\\Desktop\\ARSW\\NetworkClient\\"+ filename+".html");
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null){
                System.out.println(inputLine);
                outputFile.println(inputLine);
            }
            reader.close();
            outputFile.close();
        }   catch (IOException x){
            System.err.println(x);
        }
    }
}
