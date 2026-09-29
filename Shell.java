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
public class Shell {
    //for(int i=0; i<length; i=n+1-1
    //Ordenas grupos, tu escojes cuantos hay y cada llamada vas reduciendo el numero de grupos.
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
    public static void shellSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr.get(i);
                int j = i;
                while (j >= gap && arr.get(j-gap) > temp) {
                    arr.set(j, arr.get(j-gap));
                    j -= gap;
                }
                arr.set(j, temp);
            }
        }
    }
}
