package com.leon;

import java.util.*;
public class TestGeneric <T> {
	T t1;
	public static void main(String [] argv) {
		Vector<String> vs = new Vector<String>();
		vs.add("1111");
		
		String s1 = vs.get(0);
		System.out.println("-----:"+s1);
		
		vs.add("123");
		s1 = vs.get(1);
		System.out.println("-----:"+s1);
		
		TestGeneric tg = new TestGeneric<Integer>();
		
		Animal fish = new Animal("Fish");
		Mammal ape = new Mammal("Ape");
		
		
		//Animal me = tg.testGenericMethodDefine(fish);
		//String him = new String("him");
		String me =  tg.testGenericMethodDefine(new String("him:"));
		
		String a1 = ifThenElse(true, "S1", "S2");
		
		System.out.println("resutl:"+a1);
		
	}

	 public static <S> S testGenericMethodDefine(S s){
	     return s;
	 }
		
	 public static <K> K ifThenElse(boolean b, K first, K second) {
		 return b ? first : second;
	 }
	 
}

class Animal {
	String name;
	public Animal(String name) {
		this.name = name;
	}
	public Animal () {
		
	}
}
class Mammal extends Animal {
	public Mammal() {
		
	}
	
	public Mammal(String name) {
		this.name = name;
	}
}