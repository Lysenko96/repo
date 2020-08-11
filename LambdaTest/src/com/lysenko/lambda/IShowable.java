package com.lysenko.lambda;

public interface IShowable {
    int getIntResult();
}
interface IShowable2{
    int getIntResult2(int x, int y);
}
interface IShowable6{
    SixTuple<String, Integer, Boolean, Double, Character, Main.MyObject> getResult(String s, Integer n, Boolean f, Double d, Character ch, Main.MyObject object);
}
