package com.lysenko.lambda;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Main {
    static int x = 13;
    // tuple method
    //static int y = 23;
    //static int x = 3;
    public Main(){
        show();
        new L2().show2();
        new L2().show3();
        System.out.println(func());
    }
   static class MyObject{
        String s;
        int a;
        MyObject(String string, int number){
            this.s = string;
            this.a = number;
            //System.out.println(toString());
        }

        @Override
        public String toString() {
            return "MyObject{" +
                    "s='" + s + '\'' +
                    ", a=" + a +
                    '}';
        }
    }

    public static void main(String[] args) {
        new Main();
    }
        static TwoTuple<String,Integer> f(){
            return new TwoTuple<String, Integer>("Anton",24);
        }

        static SixTuple<String, Integer, Boolean, Double, Character, MyObject> func(){
        return new SixTuple<>("text", 23, false, 3.4, 'z', new MyObject("myObj", 44));
    }

        void show(){
        int y = 23;
        x = 1;
        //lambda
        IShowable showable = () -> {
            return x + y;
        };
//        System.out.println(showable.getIntResult());
//        System.out.println(x);
        System.out.println(showable.getIntResult());
        // tuple invoke
        System.out.println(f().toString());
        System.out.println(f().first);
        System.out.println(f().second);
        String s = "";
            System.out.println("tuple");
            SixTuple<String, Integer, Boolean, Double, Character, MyObject> sixTuple = new SixTuple<>();
          /*  IShowable6 showable6 = () -> {
                return new SixTuple<>("text1", 232, true, 4.3, 'y', new MyObject("Obj123", 322));
            };*/

            sixTuple.setFirst("te");
            sixTuple.setSecond(42);
            sixTuple.setThird(true);
            sixTuple.setFour(3.5);
            sixTuple.setFive('u');
            sixTuple.setSix(new MyObject("obj24124", 333));
            IShowable6 showable6 =  (a,b,c,d,e,f) -> sixTuple;
            System.out.println(showable6.getResult(sixTuple.getFirst(), sixTuple.getSecond(), sixTuple.getThird(), sixTuple.getFour(), sixTuple.getFive(),sixTuple.getSix()));
        }

}
class L2{

    void show2() {
        IShowable2 showable2 = (x, y) -> {
            if (y == 0)
                return 0;
            else
                return x / y;
        };
        System.out.println("y not 0: "+showable2.getIntResult2(22,2));
        System.out.println("y = 0: "+showable2.getIntResult2(22,0));
        int sum = L2.calculate("+").getIntResult2(3,5);
        System.out.println("sum: "+sum);
        int difference = L2.calculate("-").getIntResult2(20,13);
        System.out.println("dif: "+difference);
    }
    void show3(){
        BinaryOperator<Double> divide = (x,y) -> x/y;
        System.out.println(divide.apply(20d,10d));
        System.out.println(divide.apply(22d,12d));
        Function<Integer, String> convert = x ->  String.valueOf(Character.toChars(x));
        System.out.println("symbol: "+convert.apply(122));
    }
     static IShowable2 calculate(String sign){
        if(sign.equals("+")){
            return (x,y) -> x + y;
        }
        else if(sign.equals("-")){
            return (x,y) -> x - y;
        }
        return null;
    }
}
