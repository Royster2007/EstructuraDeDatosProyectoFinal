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
public class Insertion {
    public static void insertionSort(int[] arreglo){
        for(int i=1;i<arreglo.length;i++){
            int index=i;
            while(index>0){
                if(arreglo[index]<arreglo[index-1]){
                  swap(arreglo,index,index-1);
                  index--;
               }else{
                    break;
                } 
            }
        }
    }
    public static void insertionSort(ArrayList<Integer> arreglo){
        for(int i=1;i<arreglo.size();i++){
            int index=i;
            while(index>0){
                if(arreglo.get(index)<arreglo.get(index-1)){
                  swap(arreglo,index,index-1);
                  index--;
               }else{
                    break;
                } 
            }
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
