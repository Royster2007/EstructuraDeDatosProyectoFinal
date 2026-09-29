/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algortimosordenados;

import java.util.ArrayList;

/**
 *
 * @author VICTUS
 */
public class Selection {
    public static void selectionSort(int[] arreglo){
        int indexMin=0;
        for(int i=0; i<arreglo.length;i++){
            indexMin=i;
            for(int j=i+1;j<arreglo.length;j++){
                if(arreglo[j]<arreglo[indexMin]){
                    indexMin=j;
                }
            }
            swap(arreglo,indexMin,i);
        }
    }
    public static void selectionSort(ArrayList<Integer> arreglo){
        int indexMin=0;
        for(int i=0; i<arreglo.size();i++){
            indexMin=i;
            for(int j=i+1;j<arreglo.size();j++){
                if(arreglo.get(j)<arreglo.get(indexMin)){
                    indexMin=j;
                }
            }
            swap(arreglo,indexMin,i);
        }
    }
    public static void swap(int[] arreglo, int tmp, int tmp2){
        int temp = arreglo[tmp];
        arreglo[tmp] = arreglo[tmp2];
        arreglo[tmp2] = temp;
    }
    public static void swap(ArrayList<Integer> arreglo, int tmp, int tmp2){
        int temp = arreglo.get(tmp);
        arreglo.set(tmp, arreglo.get(tmp2));
        arreglo.set(tmp2,temp);
    }
}
