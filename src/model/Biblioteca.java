package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Biblioteca {

    private String nombre;

    private HashSet<Libro> listaLibros;
    private HashSet<Bibliotecario> bibliotecarios;
    private TreeSet <Estudiante> listaEstudiantes;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        listaLibros = new HashSet<>();
        listaEstudiantes = new TreeSet<>();
        bibliotecarios = new HashSet<>();
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


        // Inicializar Estudiantes
        getListaEstudiantes().add(new Estudiante("Juan", "Pérez", "12345"));
        getListaEstudiantes().add(new Estudiante("María", "Gómez", "67890"));
        getListaEstudiantes().add(new Estudiante("Carlos", "López", "54321"));
        getListaEstudiantes().add(new Estudiante("Ana", "Martínez", "98765"));
        getListaEstudiantes().add(new Estudiante("Pedro", "García", "13579"));
        getListaEstudiantes().add(new Estudiante("Laura", "Rodríguez", "24680"));
        getListaEstudiantes().add(new Estudiante("Luis", "Fernández", "56789"));
        getListaEstudiantes().add(new Estudiante("Sofía", "Hernández", "11223"));
        getListaEstudiantes().add(new Estudiante("David", "Díaz", "33445"));
        getListaEstudiantes().add(new Estudiante("Elena", "Sánchez", "55667"));
        getListaEstudiantes().add(new Estudiante("Miguel", "Torres", "77889"));
        getListaEstudiantes().add(new Estudiante("Carmen", "Romero", "99001"));
        getListaEstudiantes().add(new Estudiante("Javier", "Ortega", "22334"));
        getListaEstudiantes().add(new Estudiante("Isabel", "Luna", "44556"));
        getListaEstudiantes().add(new Estudiante("Raúl", "Ramírez", "66778"));
        getListaEstudiantes().add(new Estudiante("Valentina", "Vargas", "88990"));
        getListaEstudiantes().add(new Estudiante("Andrés", "Jiménez", "11222"));
        getListaEstudiantes().add(new Estudiante("Paula", "Silva", "33444"));
        getListaEstudiantes().add(new Estudiante("Roberto", "Paz", "55666"));
        getListaEstudiantes().add(new Estudiante("Camila", "Lara", "77888"));
        // inicializar bibliotecario
        getBibliotecarios().add(new Bibliotecario("jhojan","gil","110"));
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

    public TreeSet<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(TreeSet<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public void añadirEstudiante(Estudiante estudiante) {
        Estudiante estudiante1 = listaEstudiantes.stream().filter(estudiante2 -> estudiante2.getNombre().equals(estudiante.getNombre())).findFirst().orElse(null);
        if (estudiante1 == null){
            listaEstudiantes.add(estudiante);

        }
    }

    public boolean actualizarEstudiante(String id, String nombre, String apellido, String id1) {
    Estudiante estudianteSeleccionado = listaEstudiantes.stream().filter(estudiante -> estudiante.getId().equals(id)).findFirst().orElse(null);


    if (estudianteSeleccionado != null){
        estudianteSeleccionado.setNombre(nombre);
        estudianteSeleccionado.setApellido(apellido);
        estudianteSeleccionado.setId(id1);
        return true;
    }
    else {
        return  false;
    }

    }

    public boolean eliminarEstudiante(Estudiante estudianteSeleccionado) {
        if (estudianteSeleccionado != null&& listaEstudiantes.stream().filter(estudiante -> estudiante.getId().equals(estudianteSeleccionado.getId())).findFirst().orElse(null)!= null){
            listaEstudiantes.remove(estudianteSeleccionado);

            return true;
        }
        else {
            return false;
        }
    }

    public HashSet<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public void setBibliotecarios(HashSet<Bibliotecario> bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }

    public boolean eliminarBibliotecario(Bibliotecario bibliotecarioSeleccionado) {
        if (bibliotecarioSeleccionado != null&& bibliotecarios.stream().filter(estudiante -> estudiante.getId().equals(bibliotecarioSeleccionado.getId())).findFirst().orElse(null)!= null){
            bibliotecarios.remove(bibliotecarioSeleccionado);

            return true;
        }
        else {
            return false;
        }
    }

    public void añadirBibliotecario(Bibliotecario bibliotecario) {
        Bibliotecario bibliotecario1 = this.bibliotecarios.stream().filter(estudiante2 -> estudiante2.getNombre().equals(bibliotecario.getNombre())).findFirst().orElse(null);
        if (bibliotecario1 == null){
            bibliotecarios.add(bibliotecario);
        }
    }

    public boolean actualizarBibliotecario(String id, String nombre, String apellido, String id1) {
        Bibliotecario bibliotecarioSeleccionado = bibliotecarios.stream().filter(estudiante -> estudiante.getId().equals(id)).findFirst().orElse(null);


        if (bibliotecarioSeleccionado != null){
            bibliotecarioSeleccionado.setNombre(nombre);
            bibliotecarioSeleccionado.setApellido(apellido);
            bibliotecarioSeleccionado.setId(id1);
            return true;
        }
        else {
            return  false;
        }

    }

    public HashSet <Libro> getListaLibrosPrestados() {
        Iterator<Libro> libroIterator = listaLibros.iterator();
        HashSet<Libro> librosPrestados = new HashSet<>();
        while (libroIterator.hasNext()){
            Libro libro = libroIterator.next();
            if (libro.getEstado()== EstadoLibro.PRESTADO){
                librosPrestados.add(libro);
            }
        }
        return librosPrestados;
    }

    public void devolverPrestamo(Libro libroSeleccionado, String codigoPrestamo) {



    }


    public Estudiante buscarEstudiante(String mensaje, Libro libroSeleccionado) {
        Iterator<Estudiante> estudianteIterator = listaEstudiantes.iterator();
        while (estudianteIterator.hasNext()){
            Estudiante estudiante = estudianteIterator.next();
            if (estudiante.getId().equals(mensaje)){
                return estudiante;
            }

        }
    return null;
    }
}
