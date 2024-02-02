package com.example.Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class RestAPIFacade {

    private static APIQuery movieSearcher = new MovieAPI();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(env.PORT.getValue()));
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;

            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;
            String outputLine;
            URL urlWithTitle = null;
            boolean search = false;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("GET") && !inputLine.contains("favicon")) {
                    String[] url = inputLine.split(" ");
                    urlWithTitle = new URL("http://localhost:35000" + url[1]);
                    if (url[1].contains("/movie?movie=")) {
                        search = true;
                    }
                    break;
                }
                if (!in.ready()) {
                    break;
                }
            }

            if (search) {
                getMovieData(out, urlWithTitle);
            } else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type:text/html; charset=ISO-8859-1\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "  <head>\r\n" + //
                        "    <title>Movie Data App</title>\r\n" + //
                        "    <meta charset=\"UTF-8\" />\r\n" + //
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + //
                        "  </head>\r\n" + //
                        "  <body>\r\n" + //
                        "    <h1>Welcome, please use the following form to obtain the data of a movie</h1>\r\n" + //
                        "    <form action=\"/movie\">\r\n" + //
                        "      <label for=\"name\">Movie title:</label>\r\n" + //
                        "      <br />\r\n" + //
                        "      <input type=\"text\" id=\"title\" placeholder=\"Type the title of the movie...\" />\r\n"
                        + //
                        "      <br />\r\n" + //
                        "      <input type=\"button\" value=\"Submit\" onclick=\"getMovieData()\" />\r\n" + //
                        "    </form>\r\n" + //
                        "    <br />\r\n" + //
                        "    <div id=\"movieData\">\r\n" + //
                        "\r\n" + //
                        "    </div>\r\n" + //
                        "\r\n" + //
                        "    <script>\r\n" + //
                        "      function getMovieData() {\r\n" + //
                        "        let nameVar = document.getElementById(\"title\").value;\r\n" + //
                        "        const xhttp = new XMLHttpRequest();\r\n" + //
                        "        xhttp.onload = function () {\r\n" + //
                        "          document.getElementById(\"movieData\").innerHTML = this.responseText;\r\n" + //
                        "        };\r\n" + //
                        "        xhttp.open(\"GET\", \"/movie?movie=\" + nameVar);\r\n" + //
                        "        xhttp.send();\r\n" + //
                        "        document.getElementById(\"title\").value = \"\";\r\n" + //
                        "      }\r\n" + //
                        "    </script>\r\n" + //
                        "  </body>\r\n" + //
                        "</html>";

                out.println(outputLine);
            }

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * This auxiliar method search the movie with the given URL and send the
     * response to the user who request the data
     * 
     * @param out          The writer to send the response to the usar
     * @param urlWithTitle The URL created with the name of the movie to search in
     *                     the cache
     */
    private static void getMovieData(PrintWriter out, URL urlWithTitle) {
        // Search the movie with the API
        String movieData = null;
        String movieTitle = null;
        try {
            movieTitle = urlWithTitle.getQuery().replace("movie=", "");
            if (movieTitle == null)
                throw new NullPointerException();
            movieData = movieSearcher.queryMovie(movieTitle);
        } catch (NullPointerException nullE) {
            movieData = "";
        }

        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html; charset=ISO-8859-1\r\n"
                + "\r\n"
                + movieData;

        out.println(response);
    }
}