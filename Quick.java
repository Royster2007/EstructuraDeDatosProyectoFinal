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
public class Quick {
    public static void quickSort(int[] arr, int inicio, int fin) {
        if(inicio>=fin) return;
        int pivote = arr[inicio+(fin-inicio)/2];
        int menosPivote = inicio, i = inicio, masPivote = fin;
        
        while(i<=masPivote){
            if(arr[i]<pivote){
                intercambiar(arr,menosPivote,i);
                menosPivote++;
                i++;
            } else if(arr[i]>pivote){
                intercambiar(arr,i,masPivote);
                masPivote--;
            }else{
                i++;
            }
        }
        quickSort(arr,inicio,menosPivote-1);
        quickSort(arr,masPivote+1,fin);
    }
    public static void quickSort(ArrayList<Integer> arr, int inicio, int fin) {
        if(inicio>=fin)return;
        int pivote = arr.get(inicio+(fin-inicio)/2);
        int menosPivote = inicio, i = inicio, masPivote = fin;
        
        while(i<=masPivote){
            if(arr.get(i)<pivote){
                intercambiar(arr,menosPivote,i);
                menosPivote++;
                i++;
            } else if(arr.get(i)>pivote){
                intercambiar(arr,i,masPivote);
                masPivote--;
            }else{
                i++;
            }
        }
        quickSort(arr,inicio,menosPivote-1);
        quickSort(arr,masPivote+1,fin);
    }
    private static void intercambiar(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void intercambiar(ArrayList<Integer> arr, int a, int b){
        int temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b,temp);
    }
}
