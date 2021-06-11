package Networking;

import javax.management.Query;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejercicio1 {
    public static void main(String... args) throws MalformedURLException {
        URL personalSite = new URL ("https://www.escuelaing.edu.co/es/");
        System.out.println("Protocolo: " + personalSite.getAuthority());
        System.out.println("Authoruty: " + personalSite.getAuthority());
        System.out.println("Host: " + personalSite.getHost());
        System.out.println("Port:" + personalSite.getPort());
        System.out.println("Path: " + personalSite.getPath());
        System.out.println("Query: " + personalSite.getQuery());
        System.out.println("File: "+ personalSite.getFile());
        System.out.println("Ref: " + personalSite.getRef());

    }
}
