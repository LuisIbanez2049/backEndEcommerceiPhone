package com.example.iphonedropp.models.utils;

import java.util.Random;

public class GenerateOrderNumber {

//    public static void main(String[] args){
//        System.out.println("El numero generado es: " + generateNumberCard());
//    }

    public static String generateNumberCard() {
        Random random = new Random();
        String number = "";
        for (int i = 0; i < 4; i++) {
            number += random.nextInt((9999 - 1000) + 1) + 1000;
        }
        return number;
    }

}
