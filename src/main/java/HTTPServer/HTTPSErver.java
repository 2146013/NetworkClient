package HTTPServer;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class HTTPServer {



    private static HttpServer _instance = new HttpServer();



    private HttpServer(){



    }



    private static HttpServer getInstance(){
        return _instance;
    }



    public static void main(String[] args) throws IOException{
        HttpServer.getInstance().startServer(args);
    }



    public void startServer(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }



        processRequest(clientSocket);



        serverSocket.close();
    }



    public void processRequest(Socket clientSocket) throws IOException{
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        String method = "";
        String path = "";
        String version = "";
        while ((inputLine = in.readLine()) != null) {
            if(!method.isEmpty()){
                String[] requestStrings = inputLine.split(" ");
                method = requestStrings[0];
                path = requestStrings[1];
                version = requestStrings[2];
            }
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }
        String responseMsg = createTextResponse(path);
        out.println(responseMsg);



        out.close();



        in.close();



        clientSocket.close();



    }



    public String createTextResponse(String path){
        Path file = Paths.get("./www" + path);
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n" + "</head>"
                + "<body>"
                + "My Web Site"
                + "</body>"
                + "</html>";
    }
}