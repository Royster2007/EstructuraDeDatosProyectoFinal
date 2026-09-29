/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algortimosordenados;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author VICTUS
 */
public class Merge {
    //Divides arreglo hasta que longitud sea 1
    //Unes de forma ordenada
    //Comparas los dos arreglos que estas uniendo viendo cual es el mayo. Asumes que lo que estas comparando ya esta ordenado
    public static int[] mergeSort(int[] arreglo){
        if(arreglo.length<=1){
            return arreglo;
        }
        if(arreglo.length<=15){
            Insertion.insertionSort(arreglo);
            return arreglo;
        }
        int mid=arreglo.length/2;
        int[] left = Arrays.copyOfRange(arreglo, 0, mid);
        int[] right = Arrays.copyOfRange(arreglo, mid, arreglo.length);
        left=mergeSort(left);
        right=mergeSort(right);
        int l=0 ,r=0,a=0;
        if(left[left.length-1]>right[0]){
            while(l<left.length&&r<right.length){
                if(left[l]>right[r]){
                    arreglo[a]=right[r];
                    a++;
                    r++;
                }else{
                    arreglo[a]=left[l];
                    a++;
                    l++;
                }
            }
        }
        while(l<left.length){
            arreglo[a]=left[l];
            a++;
            l++;
        }
        while(r<right.length){
            arreglo[a]=right[r];
            a++;
            r++;
        }
        return arreglo;
    }
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> arreglo){
        if(arreglo.size()<=1){
            return arreglo;
        }
        if(arreglo.size()<=15){
            Insertion.insertionSort(arreglo);
            return arreglo;
        }
        int mid=arreglo.size()/2;
        ArrayList<Integer> left=new ArrayList<>(arreglo.subList(0,mid));
        ArrayList<Integer> right=new ArrayList<>(arreglo.subList(mid, arreglo.size()));
        
        left=mergeSort(left);
        right=mergeSort(right);
        int l=0 ,r=0,a=0;
        if(left.getLast()>right.getFirst()){
            while(l<left.size()&&r<right.size()){
                if(left.get(l)>right.get(r)){
                    arreglo.set(a,right.get(r));
                    a++;
                    r++;
                }else{
                    arreglo.set(a,left.get(l));
                    a++;
                    l++;
                }
            }
        }

        while(l<left.size()){
            arreglo.set(a,left.get(l));
            a++;
            l++;
        }
        while(r<right.size()){
            arreglo.set(a,right.get(r));
            a++;
            r++;
        }
        return arreglo;
    }
}
