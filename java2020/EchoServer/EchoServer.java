import java.io.*;
import java.net.*;

public class EchoServer {

    static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isOperator(String str){
        if (str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*") ){
           return true;
        }
        return false;
    }
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
            if(exp.length == 3){
                String operator = exp[1];
                if (isOperator(operator)){
                    String arg01 = exp[0];
                    String arg02 = exp[2];
                    if (isDouble(arg01) && isDouble(arg02)){
                        Double[] numbers = new Double[2];
                        numbers[0] = Double.valueOf(exp[0]);
                        numbers[1] = Double.valueOf(exp[2]);
                        if (operator.equals("+")) {
                            out.println(numbers[0] + numbers[1]);
                        } else if (operator.equals("-")) {
                            out.println(numbers[0] - numbers[1]);
                        } else if (operator.equals("*")) {
                            out.println(numbers[0] * numbers[1]);
                        } else if (operator.equals("/")) {
                            if(numbers[1] == 0){
                                out.println("Division by 0, please enter another expression");
                            }
                            else{
                                Double result = numbers[0] / numbers[1];
                                out.println(result);
                            }
                        }
                    }else{
                        out.println("NumberFormatException, please use digit as arguments, e.g.: 5.1 * 3");
                    }
                }
                else{
                    out.println("Wrong operator, please use: + - * /");
                }
            }else{
                out.println("Wrong format of expression, please use: \"number operator number\", e.g.: 4.2 / 3");
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }
}