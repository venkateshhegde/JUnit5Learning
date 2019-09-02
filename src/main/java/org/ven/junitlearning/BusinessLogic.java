package org.ven.junitlearning;

public class BusinessLogic {

    public int add(int a, int b){return a+b;}
    public int[] add(int[] a, int[] b){
        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            ret[i] = a[i] + b[i];
        }
        return ret;
    }

}
