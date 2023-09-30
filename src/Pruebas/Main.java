package Pruebas;

import model.Estudiante;

import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        // Creating an empty TreeSet by
        // declaring an object of TreeSet class
        TreeSet<Estudiante> ts = new TreeSet<Estudiante>();

        // Adding elements to above object of TreeSet class
        // Using add() method
        ts.add(new Estudiante("roberto","bolaños","123"));
        ts.add(new Estudiante("carlos","perez","1004827192"));
        ts.add(new Estudiante("juan","valencia","1231231"));

        // Create an Iterator over the TreeSet
        Iterator<Estudiante> iterator = ts.iterator();

        // Display message for better readability
        System.out.print("TreeSet: ");

        // Looping over the TreeSet values
        while (iterator.hasNext()){

            System.out.println(iterator.next().getId());
        }

        TreeSet<String> treeSet = new TreeSet<>();

        // Agregar elementos al TreeSet
        treeSet.add("Manzana");
        treeSet.add("Banana");
        treeSet.add("Naranja");

        // Obtener un Iterator para el TreeSet
        Iterator<String> iterator2 = treeSet.iterator();

        // Recorrer el TreeSet utilizando el Iterator
        while (iterator2.hasNext()) {
            String elemento = iterator2.next();
            System.out.println(elemento);
        }

    }
}
