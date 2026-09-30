package algortimosordenados;
import java.util.ArrayList;
import static algortimosordenados.BubbleSorting.burbuja;
import static algortimosordenados.Merge.mergeSort;
import static algortimosordenados.Insertion.insertionSort;
import static algortimosordenados.Selection.selectionSort;
import static algortimosordenados.Shell.shellSort;
import static algortimosordenados.Quick.quickSort;
import static algortimosordenados.Merge.mergeSortInsert;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.List;

public class AlgortimosOrdenados {
    private static final Map<String, String> BIG_O = Map.of(
        "MergeSort", "O(n log n) todos los casos",
        "Burbuja", "Mejor O(n) | Prom/Peor O(n2)",
        "Insertion", "Mejor O(n) | Prom/Peor O(n2)",
        "Selection", "O(n2) todos los casos",
        "ShellSort", "Mejor O(n log n) | Peor O(n2)",
        "QuickSort", "Mejor/Prom O(n log n) | Peor O(n2)"
    );
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
            System.out.println();
            System.out.println("Elige una opcion:");
            System.out.println("1. 100 elementos aleatorios");
            System.out.println("2. 50,000 elementos aleatorios");
            System.out.println("3. 100,000 elementos aleatorios");
            System.out.println("4. 100,000 elementos restringidos entre 1 y 5");
            System.out.println("5. Ingrese el numero de elementos");
            System.out.println("6. Reto extra(ingresar tiempo limite)");

            int opcion = leerEnteroValido(sc, "Ingresa el numero de tu opcion (1-6):");

            switch (opcion) {
                case 1: ejecutarPruebas(100, false); break;
                case 2: ejecutarPruebas(50000, false); break;
                case 3: ejecutarPruebas(100000, false); break;
                case 4: ejecutarPruebas(100000, true); break;
                case 5: int n = leerEnteroValido(sc, "Ingresa el numero de elementos");
                        ejecutarPruebas(n,false);break;
                case 6: int segundos= leerEnteroValido(sc, "Cuantos segundos quieres darle a cada implementacion?");
                        modoDesafio(segundos);
                        break;
                default:
                    System.out.println("Opcion invalida, debe ser 1, 2, 3 o 4.");
                    continue;
            }

            System.out.println();
            System.out.println("Quieres correr otra prueba? (1 = si, 2 = no)");
            int respuesta = leerEnteroValido(sc, "Ingresa 1 o 2:");
            seguir = (respuesta == 1);
        }

        System.out.println("Programa terminado.");
        sc.close();
    }

    public static int leerEnteroValido(Scanner sc, String mensaje) {
        int valor = -1;
        boolean valido = false;
        while (!valido) {
            System.out.println(mensaje);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                if (valor > 0) {
                    valido = true;
                } else {
                    System.out.println("Debe ser un numero entero positivo.");
                }
            } else {
                System.out.println("Eso no es un numero entero valido.");
                sc.next();
            }
        }
        return valor;
    }

    public static void ejecutarPruebas(int n, boolean restringido) {
        ConcurrentHashMap<String, Double> resultados = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Boolean> ordenados = new ConcurrentHashMap<>();
        Random rand = new Random();

        int[] arregloOG = new int[n];
        ArrayList<Integer> listaOG = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int insert = restringido ? (rand.nextInt(5) + 1) : rand.nextInt();
            arregloOG[i] = insert;
            listaOG.add(insert);
        }

        Thread[] hilos = new Thread[14];

        hilos[0] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            mergeSort(copia);
            long fin = System.nanoTime();
            resultados.put("MergeSort (Arreglo)", (fin - inicio) / 1_000_000.0);
            ordenados.put("MergeSort (Arreglo)", estaOrdenado(copia));
        });
        hilos[1] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            mergeSort(copia);
            long fin = System.nanoTime();
            resultados.put("MergeSort (Lista)", (fin - inicio) / 1_000_000.0);
            ordenados.put("MergeSort (Lista)", estaOrdenado(copia));
        });
        hilos[2] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            burbuja(copia);
            long fin = System.nanoTime();
            resultados.put("Burbuja (Arreglo)", (fin - inicio) / 1_000_000.0);
            ordenados.put("Burbuja (Arreglo)", estaOrdenado(copia));
        });
        hilos[3] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            burbuja(copia);
            long fin = System.nanoTime();
            resultados.put("Burbuja (Lista)", (fin - inicio) / 1_000_000.0);
            ordenados.put("Burbuja (Lista)", estaOrdenado(copia));
        });
        hilos[4] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            insertionSort(copia);
            long fin = System.nanoTime();
            resultados.put("Insertion (Arreglo)", (fin - inicio) / 1_000_000.0);
            ordenados.put("Insertion (Arreglo)", estaOrdenado(copia));
        });
        hilos[5] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            insertionSort(copia);
            long fin = System.nanoTime();
            resultados.put("Insertion (Lista)", (fin - inicio) / 1_000_000.0);
            ordenados.put("Insertion (Lista)", estaOrdenado(copia));
        });
        hilos[6] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            selectionSort(copia);
            long fin = System.nanoTime();
            resultados.put("Selection (Arreglo)", (fin - inicio) / 1_000_000.0);
            ordenados.put("Selection (Arreglo)", estaOrdenado(copia));
        });
        hilos[7] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            selectionSort(copia);
            long fin = System.nanoTime();
            resultados.put("Selection (Lista)", (fin - inicio) / 1_000_000.0);
            ordenados.put("Selection (Lista)", estaOrdenado(copia));
        });
        hilos[8] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            shellSort(copia);
            long fin = System.nanoTime();
            resultados.put("ShellSort (Arreglo)", (fin - inicio) / 1_000_000.0);
            ordenados.put("ShellSort (Arreglo)", estaOrdenado(copia));
        });
        hilos[9] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            shellSort(copia);
            long fin = System.nanoTime();
            resultados.put("ShellSort (Lista)", (fin - inicio) / 1_000_000.0);
            ordenados.put("ShellSort (Lista)", estaOrdenado(copia));
        });
        hilos[10] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            quickSort(copia, 0, copia.length - 1);
            long fin = System.nanoTime();
            resultados.put("QuickSort (Arreglo)", (fin - inicio) / 1_000_000.0);
            ordenados.put("QuickSort (Arreglo)", estaOrdenado(copia));
        });
        hilos[11] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            quickSort(copia, 0, copia.size() - 1);
            long fin = System.nanoTime();
            resultados.put("QuickSort (Lista)", (fin - inicio) / 1_000_000.0);
            ordenados.put("QuickSort (Lista)", estaOrdenado(copia));
        });
        hilos[12] = new Thread(() -> {
            int[] copia = arregloOG.clone();
            long inicio = System.nanoTime();
            mergeSortInsert(copia);
            long fin = System.nanoTime();
            resultados.put("MergeSort (Arreglo) SinInsert", (fin - inicio) / 1_000_000.0);
            ordenados.put("MergeSort (Arreglo) SinInsert", estaOrdenado(copia));
        });
        hilos[13] = new Thread(() -> {
            ArrayList<Integer> copia = new ArrayList<>(listaOG);
            long inicio = System.nanoTime();
            mergeSortInsert(copia);
            long fin = System.nanoTime();
            resultados.put("MergeSort (Lista) SinInsert", (fin - inicio) / 1_000_000.0);
            ordenados.put("MergeSort (Lista) SinInsert", estaOrdenado(copia));
        });
        for (Thread h : hilos) {
            h.start();
        }
        for (Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<Map.Entry<String, Double>> lista = new ArrayList<>(resultados.entrySet());
        lista.sort((a, b) -> Double.compare(a.getValue(), b.getValue()));

        System.out.println();
        System.out.println("RESULTADOS DE ORDENAMIENTO");
        System.out.println("Elementos: " + n + (restringido ? " (restringido 1-5)" : ""));
        System.out.println();
        System.out.printf("%-5s %-30s %-15s %-10s %-40s%n", "Pos.", "Algoritmo", "Tiempo (s.ms)", "Ordeno?", "Big O");

        int posicion = 1;
        for (Map.Entry<String, Double> entrada : lista) {
            String nombre = entrada.getKey();
            boolean ok = ordenados.get(nombre);
            String algoritmo = nombre.split(" ")[0];
            String bigO = BIG_O.get(algoritmo);
            System.out.printf("%-5d %-30s %-15.4f %-10s %-40s%n", posicion, nombre, entrada.getValue(), ok ? "Si" : "No", bigO);
            posicion++;
        }

        System.out.println();
        System.out.println("Implementacion con menor tiempo: " + lista.get(0).getKey());
    }
    public static void modoDesafio(int segundos){
    ConcurrentHashMap<String, Integer> colecciones = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Double> promedios = new ConcurrentHashMap<>();
    long limiteNs = segundos * 1_000_000_000L;
    
    int tamañoColeccion = 1000;
    Thread[] hilos = new Thread[14];

    hilos[0] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            mergeSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("MergeSort (Arreglo)", contador);
        promedios.put("MergeSort (Arreglo)", tiempoTotalMs / contador);
    });

    hilos[1] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            mergeSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("MergeSort (Lista)", contador);
        promedios.put("MergeSort (Lista)", tiempoTotalMs / contador);
    });

    hilos[2] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            burbuja(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("Burbuja (Arreglo)", contador);
        promedios.put("Burbuja (Arreglo)", tiempoTotalMs / contador);
    });

    hilos[3] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            burbuja(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("Burbuja (Lista)", contador);
        promedios.put("Burbuja (Lista)", tiempoTotalMs / contador);
    });

    hilos[4] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            insertionSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("Insertion (Arreglo)", contador);
        promedios.put("Insertion (Arreglo)", tiempoTotalMs / contador);
    });

    hilos[5] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            insertionSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("Insertion (Lista)", contador);
        promedios.put("Insertion (Lista)", tiempoTotalMs / contador);
    });

    hilos[6] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            selectionSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("Selection (Arreglo)", contador);
        promedios.put("Selection (Arreglo)", tiempoTotalMs / contador);
    });

    hilos[7] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            selectionSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("Selection (Lista)", contador);
        promedios.put("Selection (Lista)", tiempoTotalMs / contador);
    });

    hilos[8] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            shellSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("ShellSort (Arreglo)", contador);
        promedios.put("ShellSort (Arreglo)", tiempoTotalMs / contador);
    });

    hilos[9] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            shellSort(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("ShellSort (Lista)", contador);
        promedios.put("ShellSort (Lista)", tiempoTotalMs / contador);
    });

    hilos[10] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            quickSort(datos, 0, datos.length - 1);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("QuickSort (Arreglo)", contador);
        promedios.put("QuickSort (Arreglo)", tiempoTotalMs / contador);
    });

    hilos[11] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            quickSort(datos, 0, datos.size() - 1);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("QuickSort (Lista)", contador);
        promedios.put("QuickSort (Lista)", tiempoTotalMs / contador);
    });

    hilos[12] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            int[] datos = new int[tamañoColeccion];
            for (int i = 0; i < tamañoColeccion; i++) datos[i] = rand.nextInt();
            long inicio = System.nanoTime();
            mergeSortInsert(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("MergeSort (Arreglo) SinInsert", contador);
        promedios.put("MergeSort (Arreglo) SinInsert", tiempoTotalMs / contador);
    });

    hilos[13] = new Thread(() -> {
        Random rand = new Random();
        int contador = 0;
        double tiempoTotalMs = 0;
        long inicioGlobal = System.nanoTime();
        while (System.nanoTime() - inicioGlobal < limiteNs) {
            ArrayList<Integer> datos = new ArrayList<>();
            for (int i = 0; i < tamañoColeccion; i++) datos.add(rand.nextInt());
            long inicio = System.nanoTime();
            mergeSortInsert(datos);
            long fin = System.nanoTime();
            tiempoTotalMs += (fin - inicio) / 1_000_000.0;
            contador++;
        }
        colecciones.put("MergeSort (Lista) SinInsert", contador);
        promedios.put("MergeSort (Lista) SinInsert", tiempoTotalMs / contador);
    });

    for (Thread h : hilos) h.start();
    for (Thread h : hilos) {
        try { h.join(); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    List<Map.Entry<String, Integer>> lista = new ArrayList<>(colecciones.entrySet());
    lista.sort((a, b) -> Integer.compare(b.getValue(), a.getValue())); // mayor a menor

    System.out.println();
    System.out.println("MODO DESAFIO - Limite: " + segundos + " segundos, Tamano por coleccion: " + tamañoColeccion);
    System.out.printf("%-30s %-15s %-20s%n", "Algoritmo", "Colecciones", "Tiempo promedio (ms)");
    for (Map.Entry<String, Integer> entrada : lista) {
        String nombre = entrada.getKey();
        System.out.printf("%-30s %-15d %-20.4f%n", nombre, entrada.getValue(), promedios.get(nombre));
    }
}

    public static Boolean estaOrdenado(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            if (arreglo[i] > arreglo[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static Boolean estaOrdenado(ArrayList<Integer> arreglo) {
        for (int i = 0; i < arreglo.size() - 1; i++) {
            if (arreglo.get(i) > arreglo.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}