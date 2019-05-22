package com.arv;

import java.util.Scanner;

public class split {
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		String x= in.nextLine();
		
		for(int i=0;i<x.length();i++)
			System.out.println(x.charAt(i)+" ");
		
	}
}
