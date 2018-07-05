package com.amitesh.skiing;

//importing resort class
com.amitesh.skiing.SkiResort;

public class App {
    public static void main( String[] args ) {
      SkiResort skiResort = new SkiResort(args[0], args[1], args[2]);
      System.out.println(skiResort.toString());
    }
}
