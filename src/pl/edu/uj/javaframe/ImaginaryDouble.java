package pl.edu.uj.javaframe;

public class ImaginaryDouble extends MyDouble{

    public MyDouble imaginary;
    String valueFormat = "\\d+i\\d+";

    @Override
    public Value create(String val) {
        if(val.matches(valueFormat)){
        super.value = new MyDouble().create(val.split("i")[0]);
        imaginary = (MyDouble) new MyDouble().create(val.split("i")[1]);
        return this;}
        else{
            System.out.println("Incorrect input");
            return null;
        }
    }

    @Override
    public Value add(Value v) {

        value = ((MyDouble)value).add(v);

        if(v instanceof ImaginaryInt) {
            imaginary = (MyDouble)imaginary.add(((ImaginaryInt) v).imaginary);
        }
        if(v instanceof ImaginaryDouble){
            imaginary = (MyDouble)imaginary.add(((ImaginaryDouble) v).imaginary);
        }

        return this;

    }

    @Override
    public String toString() {
        return value.toString() + "i" + imaginary.toString();
    }
}
