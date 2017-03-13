/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.marketing.list;

import java.util.ArrayList;

/**
 *
 * @author 490097
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        System.out.println(add(2,4));
    }
    public static int add(int a, int b) {
        int sum = 0;
        sum = a + b;
        return sum;
    }
    
}
