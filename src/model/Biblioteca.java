package model;

import java.util.HashSet;

public class Biblioteca {

    private String nombre;

    private HashSet<Libro> listaLibros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        listaLibros = new HashSet<>();
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        getListaLibros().add(new Libro("Cien años de soledad", new Autor("Gabriel", "García Márquez", "1927-2014")));
        getListaLibros().add(new Libro("1984", new Autor("George", "Orwell", "1903-1950")));
        getListaLibros().add(new Libro("Matar un ruiseñor", new Autor("Harper", "Lee", "1926-2016")));
        getListaLibros().add(new Libro("El Gran Gatsby", new Autor("F. Scott", "Fitzgerald", "1896-1940")));
        getListaLibros().add(new Libro("El señor de los anillos", new Autor("J.R.R.", "Tolkien", "1892-1973")));
        getListaLibros().add(new Libro("En busca del tiempo perdido", new Autor("Marcel", "Proust", "1871-1922")));
        getListaLibros().add(new Libro("Don Quijote de la Mancha", new Autor("Miguel de", "Cervantes", "1547-1616")));
        getListaLibros().add(new Libro("Crimen y castigo", new Autor("Fiodor", "Dostoievski", "1821-1881")));
        getListaLibros().add(new Libro("Moby Dick", new Autor("Herman", "Melville", "1819-1891")));
        getListaLibros().add(new Libro("Orgullo y prejuicio", new Autor("Jane", "Austen", "1775-1817")));
        getListaLibros().add(new Libro("Clean Code: A Handbook of Agile Software Craftsmanship", new Autor("Robert C.", "Martin", "1952-")));
        getListaLibros().add(new Libro("The Pragmatic Programmer: Your Journey to Mastery", new Autor("Andrew", "Hunt", "")));
        getListaLibros().add(new Libro("Design Patterns: Elements of Reusable Object-Oriented Software", new Autor("Erich", "Gamma", "")));
        getListaLibros().add(new Libro("Introduction to the Theory of Computation", new Autor("Michael S.", "Sipser", "1954-")));
        getListaLibros().add(new Libro("JavaScript: The Good Parts", new Autor("Douglas", "Crockford", "1955-")));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashSet<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(HashSet<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
}
