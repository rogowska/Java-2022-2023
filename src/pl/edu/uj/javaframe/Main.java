package pl.edu.uj.javaframe;

public class Main {

    public static void main(String[] args) {

        MyDataFrame df = new MyDataFrame(new Class[] {ImaginaryDouble.class, Int.class}, new String[] {"kol1","kol2"});

        df.addRow(new String[]{"12i3","23"});
        df.addRow(new String[]{"1244i4","23"});
        df.addRow(new String[]{"1244i6","2553"});
        df.addRow(new String[]{"12i9","2300"});

        ImaginaryDouble im1 = (ImaginaryDouble) new ImaginaryDouble().create("12i3");
        ImaginaryDouble im2 = (ImaginaryDouble) new ImaginaryDouble().create("5i3");
        ImaginaryDouble im3 = (ImaginaryDouble) new ImaginaryDouble().create("5");

        new ImaginaryInt().create("12i4").add(new Int().create("10"));
        new Int().create("10").add(new ImaginaryInt().create("12i4"));
        new ImaginaryInt().create("12i4").add(new ImaginaryInt().create("10i10"));

        MyDouble re = (MyDouble) new MyDouble().create("5");

        im1.print();
        System.out.println();
        im2.print();
        System.out.println();
        re.add(im2).print();
        System.out.println();
        System.out.println("------------------------------");
        df.print();

        /* Testowanie dodawania i tworzenia liczb
        new ImaginaryInt().create("10i10").add(new ImaginaryInt().create("1i1")).print();
        System.out.println();
        new ImaginaryInt().create("11i11").add(new ImaginaryDouble().create("1i1")).print();
        System.out.println();
        new ImaginaryInt().create("12i13").add(new Int().create("1")).print();
        System.out.println();
        new ImaginaryInt().create("13i14").add(new MyDouble().create("1")).print();
        System.out.println();
        System.out.println("------------------------------");

        new ImaginaryDouble().create("20i20").add(new ImaginaryDouble().create("1i1")).print();
        System.out.println();
        new ImaginaryDouble().create("21i21").add(new ImaginaryInt().create("1i1")).print();
        System.out.println();
        new ImaginaryDouble().create("22i23").add(new Int().create("1")).print();
        System.out.println();
        new ImaginaryDouble().create("23i24").add(new MyDouble().create("1")).print();
        System.out.println();
        System.out.println("------------------------------");

        new MyDouble().create("30").add(new ImaginaryDouble().create("1i31")).print();
        System.out.println();
        new MyDouble().create("31").add(new ImaginaryInt().create("1i32")).print();
        System.out.println();
        System.out.println("------------------------------");

        new Int().create("40").add(new ImaginaryDouble().create("1i41")).print();
        System.out.println();
        new Int().create("41").add(new ImaginaryInt().create("1i42")).print();
        System.out.println();
        System.out.println("------------------------------"); */






        }


}