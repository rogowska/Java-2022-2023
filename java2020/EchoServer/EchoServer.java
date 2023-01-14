import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            String[] exp = inputLine.split("\\s");
            if (exp.length != 3) {
                out.println("Wrong format of expression, please use: \"number operator number\", exiting.");
            }
            Double[] numbers = new Double[2];
            String operator = exp[1];
            try {
                numbers[0] = Double.valueOf(exp[0]);
                numbers[1] = Double.valueOf(exp[2]);
            } catch (NumberFormatException e) {
                out.println("NumberFormatException, exiting.");
                out.close();
                in.close();
                clientSocket.close();
                serverSocket.close();
            }
            if (operator.equals("+")) {
                out.println(numbers[0] + numbers[1]);
            } else if (operator.equals("-")) {
                out.println(numbers[0] - numbers[1]);
            } else if (operator.equals("*")) {
                out.println(numbers[0] * numbers[1]);
            } else if (operator.equals("/")) {
                if (numbers[1] == 0) {
                    out.println("Division by 0, please enter another expression");
                }
                Double result = numbers[0] / numbers[1];
                out.println(result);
            } else {
                out.println("Wrong operator");
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }
}