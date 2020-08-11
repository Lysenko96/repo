package com.lysenko.lambda;

public class SixTuple<A,B,C,D,E,F> {
    A first;
    B second;
    C third;
    D four;
    E five;
    F six;
    public SixTuple(){

    }
    public SixTuple(A a, B b, C c, D d, E e, F f){
        this.first = a;
        this.second = b;
        this.third = c;
        this.four = d;
        this.five = e;
        this.six = f;
    }

    @Override
    public String toString() {
        return "SixTuple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", four=" + four +
                ", five=" + five +
                ", six=" + six +
                '}';
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public void setThird(C third) {
        this.third = third;
    }

    public void setFour(D four) {
        this.four = four;
    }

    public void setFive(E five) {
        this.five = five;
    }

    public void setSix(F six) {
        this.six = six;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    public D getFour() {
        return four;
    }

    public E getFive() {
        return five;
    }

    public F getSix() {
        return six;
    }
}
