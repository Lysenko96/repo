package com.example.lysenko;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Anagram {
    private static List<Character> list = new LinkedList<>();
    private String reverseMyInputText;

    private String[] inputText(String inputText) {
        String[] inputTextWords = null;

        inputText = inputText.toLowerCase(); // reverse toUpperCase symbols in input text

        inputTextWords = new String[inputText.length()];
        inputTextWords = inputText.split(" ");
        return inputTextWords;
    }

    private void reverseSymbolsInWord(List<Character> latinSymbols, List<Integer> indexNotLatinSymbols, List<Character> symbolsWord) {
        List<Character> reverseSymbolsWord = new LinkedList<>();
        for(int reverseIndex = latinSymbols.size()-1; reverseIndex >=0; reverseIndex--) {
            reverseSymbolsWord.add(latinSymbols.get(reverseIndex));
        }
        for(Integer indexSymbolNotLatin : indexNotLatinSymbols) {
            reverseSymbolsWord.add(indexSymbolNotLatin, symbolsWord.get(indexSymbolNotLatin));
        }
        for(int indexOutput = 0; indexOutput < reverseSymbolsWord.size(); indexOutput++) {

            list.add(reverseSymbolsWord.get(indexOutput));
        }
        list.add(' ');
    }

    private boolean isASCIISymbol(int[] indexASCII, int indexSymbol) {
        if(indexASCII[indexSymbol] >= 97 && indexASCII[indexSymbol] <=122)
            return true;
        return false;
    }

    private List<Character> reverseInputText(String[] inputTextWords){

        char[] symbolsInWord;
        for(int countSymbolsWord = 0; countSymbolsWord < inputTextWords.length; countSymbolsWord++) {
            symbolsInWord = new char[inputTextWords[countSymbolsWord].length()];
            symbolsInWord = inputTextWords[countSymbolsWord].toCharArray();
            int[] indexASCII = new int[symbolsInWord.length];
            List<Character> latinSymbols = new LinkedList<>();
            List<Integer> indexNotLatinSymbols = new LinkedList<>();
            List<Character> symbolsWord = new LinkedList<>();
            for(int indexSymbol = 0; indexSymbol < symbolsInWord.length; indexSymbol++) {
                indexASCII[indexSymbol] = (int) symbolsInWord[indexSymbol];
                if(isASCIISymbol(indexASCII, indexSymbol)){
                    latinSymbols.add((char) indexASCII[indexSymbol]);
                    symbolsWord.add((char) indexASCII[indexSymbol]);
                }
                else {
                    indexNotLatinSymbols.add(indexSymbol);
                    symbolsWord.add((char) indexASCII[indexSymbol]);
                }
            }
            reverseSymbolsInWord(latinSymbols,indexNotLatinSymbols,symbolsWord);
        }
        return list;
    }

    private void reverseInputTextInString(List<Character> newReverseSymbolsWord) {
        String myReverseInputString = "";
        for(Character charsInWord : list){
            myReverseInputString += charsInWord;
        }
        reverseMyInputText = myReverseInputString;
    }


    public String reverseText(String text) {
        reverseInputTextInString(reverseInputText(inputText(text)));
        return reverseMyInputText;
    }
}

public class Main {
    static Anagram anagram = new Anagram();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        System.out.println(anagram.reverseText(text));
        scanner.close();
    }
}
