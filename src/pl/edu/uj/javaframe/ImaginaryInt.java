package pl.edu.uj.javaframe;

public class ImaginaryInt extends Int{

    public Int imaginary;

    String valueFormat = "\\d+i\\d+";

    @Override
    public Value create(String val) {
        if(val.matches(valueFormat)) {
            super.value = new Int().create(val.split("i")[0]);
            imaginary = (Int) new Int().create(val.split("i")[1]);
            return this;
        }
        else{
            System.out.println("Incorrect input");
            return null;
        }
    }

    @Override
    public Value add(Value v) {

        value = ((Int)value).add(v);

        if(v instanceof ImaginaryInt) {
            imaginary = (Int)imaginary.add(((ImaginaryInt) v).imaginary);
        }
        if(v instanceof ImaginaryDouble){
            imaginary = (Int)imaginary.add(((ImaginaryDouble) v).imaginary);
        }

        return this;

    }

    @Override
    public String toString() {
        return value.toString() + "i" + imaginary.toString();
    }
}
