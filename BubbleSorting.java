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
public class BubbleSorting {
    
    public static void burbuja(int[] arreglo) {
        for (int i = 0; i < arreglo.length-1; i++) {
            boolean flag=false;
            for (int j = 0; j < arreglo.length- i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    flag=true;
                    swap(arreglo,j,j+1);
                    
                }
            }
            if(!flag)
                return;
        }
    }
    
    public static void burbuja(ArrayList<Integer> arreglo) {
        for (int i = 0; i < arreglo.size()-1; i++) {
            boolean flag=false;
            for (int j = 0; j < arreglo.size()- i - 1; j++) {
                if(arreglo.get(j)>arreglo.get(j+1)){
                    flag=true;
                    swap(arreglo,j,j+1);
                }
            }
            if(!flag)
                return;
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
