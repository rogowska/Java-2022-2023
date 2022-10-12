import java.util.ArrayList;
import java.util.List;

public class Pesel {
   private String peselNumber;

    public Pesel(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public boolean compare(Pesel peselNumber) {
        boolean flag;
        flag = this.peselNumber.equals(peselNumber.getPeselNumber());
        if(flag){
        System.out.println("PESEL numbers are equal.");}
        else{
            System.out.println("PESEL number are not equal.");
        }
        return flag;
    }

    static boolean check(Pesel peselNumber) {

        boolean flag = false;

        if(!peselNumber.getPeselNumber().matches("\\d{11}")){
            System.out.println("PESEL number is incorrect. It should contain eleven digits.");
            return flag;
        }
        String[] peselSplit = peselNumber.getPeselNumber().split("");
        List<Integer> numeric = new ArrayList<Integer>();

        for (String element : peselSplit) {
            numeric.add(Integer.parseInt(element));
        }

        int verification = numeric.get(0) + numeric.get(1)*3 + numeric.get(2)*7 + numeric.get(3)*9 + numeric.get(4) + numeric.get(5)*3 + numeric.get(6)*7 + numeric.get(7)*9 + numeric.get(8) + numeric.get(9)*3 + numeric.get(10);
        verification = verification % 10;
        if(verification == 0){
            System.out.print("PESEL is correct.");
            flag = true;
            }
            else{
            System.out.print("PESEL is incorrect.");
            }
            return flag;
    }
}