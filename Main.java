package com.lysenko.integerdivision;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Integer> listRemainder = new ArrayList<>();
	private static ArrayList<Integer> listMultiply = new ArrayList<>();
	private static ArrayList<Integer> listQuotient = new ArrayList<>();
	private static ArrayList<Integer> listNewNumber = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String number = scn.nextLine();
		int divider = scn.nextInt();
		int count = 0; // количесто цифр в числе после взятия неполного чатсного
		int d = 0; // строка с числами делимого в число
		String s1 = ""; // строка со всеми числами делимого
		char[] numbers = number.toCharArray();
		d = Integer.parseInt(numbers[0] + "");
		for (int i = 0; i < numbers.length; i++) {
			s1 += String.valueOf(numbers[i]);
			d = Integer.parseInt(s1);
			count++;
			//System.out.println("divider: " + divider);
			int quotient = d / divider; // остаток от 1-го неполного частного
			//System.out.println("quotient: " + quotient);
			int multiply = quotient * divider;
			//System.out.println("multipay: " + multiply);
			int remainder = d - multiply;
			//System.out.println("remainder: " + remainder);
			listQuotient.add(quotient);
			listMultiply.add(multiply);
			listRemainder.add(remainder);
			i = count;
			while (i < number.length()) {
				String newNumber = String.valueOf(remainder) + Integer.parseInt(numbers[i] + "");
				//System.out.println("newNumber: " + newNumber);
				int quotient2 = Integer.parseInt(newNumber) / divider;
				//System.out.println("quotient2: " + quotient2);
				multiply = quotient2 * divider;
				//System.out.println("multipay: " + multiply);
				remainder = Integer.parseInt(newNumber) - multiply;
				//System.out.println("newRemainder: " + remainder);
				listQuotient.add(quotient2);
				listMultiply.add(multiply);
				listNewNumber.add(Integer.parseInt(newNumber));
				listRemainder.add(remainder);
				i++;
			}

		}
		StringBuilder str = new StringBuilder();
		//System.out.println();
		//System.out.println("number: " + number);
		//System.out.print("listQuotient: ");
		for (int i = 0; i < listQuotient.size(); i++) {
			//System.out.print(listQuotient.get(i));
			str.append(listQuotient.get(i));
		}
		//System.out.println();
		//System.out.print("listMultiply: ");
		for (int i = 0; i < listMultiply.size(); i++) {
			//System.out.print(listMultiply.get(i) + " ");
		}
		//System.out.println();
		//System.out.print("listNewNumber: ");
		for (int i = 0; i < listNewNumber.size(); i++) {
			//System.out.print(listNewNumber.get(i) + " ");
		}
		//System.out.println();
		//System.out.print("listRemainder: ");
		System.out.println();
		System.out.println("_" + number + "|" + divider);
		System.out.println(" " + listMultiply.get(0) + "    |-----");
		System.out.println(" -    |"+ str);
		for(int i = 0; i < listNewNumber.size(); i++) {
			StringBuilder b = new StringBuilder();
			System.out.println(b.append("") + "_" + listNewNumber.get(i));
			System.out.println(" " + listMultiply.get(i+1));
			System.out.println(" --");
			}
		System.out.print("   "+listRemainder.get(listRemainder.size() - 1));
	}
	
	
}
