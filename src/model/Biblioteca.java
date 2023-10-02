package model;

import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Biblioteca {

    private String nombre;

    private HashSet<Libro> listaLibros;
    private TreeSet<Estudiante> listaEstudiantes;
    private HashMap<String, Prestamo> listaPrestamos;
    private HashSet<Bibliotecario> bibliotecarios;


    public Biblioteca(String nombre) {
        this.nombre = nombre;
        listaLibros = new HashSet<>();
        listaPrestamos = new HashMap<>();
        listaEstudiantes = new TreeSet<>();
        bibliotecarios = new HashSet<>();
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        getListaLibros().add(new Libro("Cien años de soledad", new Autor("Gabriel", "García Márquez", "1927-2014"),Tema.ACCION));
        getListaLibros().add(new Libro("1984", new Autor("George", "Orwell", "1903-1950"),Tema.NOVELA));
        getListaLibros().add(new Libro("Matar un ruiseñor", new Autor("Harper", "Lee", "1926-2016"),Tema.COMEDIA));
        getListaLibros().add(new Libro("El Gran Gatsby", new Autor("F. Scott", "Fitzgerald", "1896-1940"),Tema.POESIA));
        getListaLibros().add(new Libro("El señor de los anillos", new Autor("J.R.R.", "Tolkien", "1892-1973"),Tema.COMEDIA));
        getListaLibros().add(new Libro("En busca del tiempo perdido", new Autor("Marcel", "Proust", "1871-1922"),Tema.ROMANCE));
        getListaLibros().add(new Libro("Don Quijote de la Mancha", new Autor("Miguel de", "Cervantes", "1547-1616"),Tema.TERROR));
        getListaLibros().add(new Libro("Crimen y castigo", new Autor("Fiodor", "Dostoievski", "1821-1881"),Tema.COMEDIA));
        getListaLibros().add(new Libro("Moby Dick", new Autor("Herman", "Melville", "1819-1891"),Tema.ROMANCE));
        getListaLibros().add(new Libro("Orgullo y prejuicio", new Autor("Jane", "Austen", "1775-1817"),Tema.NOVELA));
        getListaLibros().add(new Libro("Clean Code: A Handbook of Agile Software Craftsmanship", new Autor("Robert C.", "Martin", "1952-"),Tema.COMEDIA));
        getListaLibros().add(new Libro("The Pragmatic Programmer: Your Journey to Mastery", new Autor("Andrew", "Hunt", ""),Tema.ACCION));
        getListaLibros().add(new Libro("Design Patterns: Elements of Reusable Object-Oriented Software", new Autor("Erich", "Gamma", ""),Tema.ACCION));
        getListaLibros().add(new Libro("Introduction to the Theory of Computation", new Autor("Michael S.", "Sipser", "1954-"),Tema.ROMANCE));
        getListaLibros().add(new Libro("JavaScript: The Good Parts", new Autor("Douglas", "Crockford", "1955-"),Tema.TERROR));

        // Inicializar Estudiantes
        getListaEstudiantes().add(new Estudiante("Jhojan", "Gil", "1234"));
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
        listaEstudiantes.add(estudiante);
    }

    public boolean actualizarEstudiante(String idBusqueda, String nombre, String apellido, String id) {

        Iterator<Estudiante> estudianteIterator = listaEstudiantes.iterator();

        while (estudianteIterator.hasNext()){
            Estudiante estudiante = estudianteIterator.next();
            if (estudiante.getId().equals(idBusqueda)){
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setId(id);
                return true;
            }
        }
        return false;

    }

    public boolean eliminarEstudiante(Estudiante estudianteSeleccionado) {

        Iterator<Estudiante> estudianteIterator = listaEstudiantes.iterator();

        while (estudianteIterator.hasNext()){
            Estudiante estudiante = estudianteIterator.next();
            if (estudiante.getId().equals(estudianteSeleccionado.getId())){
                listaEstudiantes.remove(estudiante);
                return true;
            }
        }
        return false;
    }

    public HashSet<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public void setBibliotecarios(HashSet<Bibliotecario> bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }

    public boolean eliminarBibliotecario(Bibliotecario bibliotecarioSeleccionado) {
        Iterator<Bibliotecario> bibliotecarioIterator = bibliotecarios.iterator();

        while (bibliotecarioIterator.hasNext()){
            Bibliotecario bibliotecario = bibliotecarioIterator.next();
            if (bibliotecario.getId().equals(bibliotecarioSeleccionado.getId())){
                bibliotecarios.remove(bibliotecario);
                return true;
            }
        }
        return false;
    }

    public void añadirBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarios.add(bibliotecario);
    }

    public boolean actualizarBibliotecario(String idBusqueda, String nombre, String apellido, String id) {

        Iterator<Bibliotecario> bibliotecarioIterator = bibliotecarios.iterator();

        while (bibliotecarioIterator.hasNext()){
            Bibliotecario bibliotecario = bibliotecarioIterator.next();
            if (bibliotecario.getId().equals(idBusqueda)){
                bibliotecario.setNombre(nombre);
                bibliotecario.setApellido(apellido);
                bibliotecario.setId(id);
                return true;
            }
        }
        return false;
    }



    public Estudiante buscarEstudiante(String id) {
        Iterator<Estudiante> iterator = listaEstudiantes.iterator();
        Estudiante estudiante = null;
        while (iterator.hasNext()) {
            Estudiante est = iterator.next();
            if(est.getId().equals(id)){
                estudiante = est;
            }
        }
        return estudiante;
    }



    public Prestamo prestarLibro(Estudiante estudiante, Libro libro) {
            Prestamo prestamo = null;
            if(libro.getEstado().equals(EstadoLibro.NO_PRESTADO)){
                prestamo = new Prestamo(new DetallePrestamo(LocalDate.now(),libro,estudiante));
                libro.setEstado(EstadoLibro.PRESTADO);
                listaPrestamos.put(prestamo.getCodigo(), prestamo);
                //System.out.println(listaPrestamos.get(prestamo.getCodigo()).getNombre());
            }
            return prestamo;

    }

    public Prestamo buscarPrestamo(String id) {
        Iterator<Map.Entry<String, Prestamo>> listIterator = listaPrestamos.entrySet().iterator();

        while (listIterator.hasNext()){
            Map.Entry<String, Prestamo> entry = listIterator.next();
            String key = entry.getKey();
            if (key.equals(id)){
               Prestamo prestamo = entry.getValue();
                return prestamo;
            }

        }

        return null;
    }

    public void devolverLibro(Libro libro,Estudiante estudiante) {

        Iterator<Map.Entry<String, Prestamo>> listIterator = listaPrestamos.entrySet().iterator();

        while (listIterator.hasNext()){
            Map.Entry<String, Prestamo> entry = listIterator.next();
            String key = entry.getKey();
            Prestamo value = entry.getValue();
            if (value.getDetallePrestamo().getLibroPrestado().getNombre().equals(libro.getNombre()));{
                listaPrestamos.remove(value,key);
                libro.setEstado(EstadoLibro.NO_PRESTADO);
            }
        }
    }

    public HashSet<Libro> buscarPrestamoEstudiante(Estudiante estudiante) {

        Iterator<Map.Entry<String, Prestamo>> listIterator = listaPrestamos.entrySet().iterator();
        HashSet<Libro> libroEstudiante = new HashSet<>();

        while (listIterator.hasNext()){
            Map.Entry<String, Prestamo> entry = listIterator.next();
            Prestamo prestamo = entry.getValue();
            if (prestamo.getDetallePrestamo().getEstudiante()==estudiante&&prestamo.getDetallePrestamo().getLibroPrestado().getEstado()==EstadoLibro.PRESTADO){
                libroEstudiante.add(prestamo.getDetallePrestamo().getLibroPrestado());
            }
        }
        return libroEstudiante;
    }
    public Libro crearLibro(String nombreLibro, Tema generoLibro, String autorLibro, LocalDate fechaPublicacion) {
        Libro libro = new Libro(nombreLibro,new Autor(autorLibro,"",""),generoLibro);
        listaLibros.add(libro);
        return libro;
    }

    public boolean actualizarLibro(Libro libroSeleccionado,Libro newLibro) {
        Iterator<Libro> libroIterator = listaLibros.iterator();

        while (libroIterator.hasNext()){
            Libro libro = libroIterator.next();
            if (libro.getNombre().equals(libroSeleccionado.getNombre())){
                libro.setNombre(newLibro.getNombre());
                libro.setAutor(newLibro.getAutor());
                libro.setEstado(newLibro.getEstado());
                libro.setFechaPublicacion(newLibro.getFechaPublicacion());
                libro.setTemario(newLibro.getTemario());
                return true;
            }
        }
        return false;
    }
    public HashSet<Libro> mostrarNombreLibrosConBaseAOrdenacion() {
        for (Libro libro : listaLibros) {
            System.out.println(libro.getNombre());
        }
        return listaLibros;
    }
    public List<Libro> ordenarLibrosPorNombreYFecha() {
        List<Libro> librosOrdenados = new ArrayList<>(listaLibros);

        List<Libro> listaResultante = new ArrayList<>();

        Iterator<Libro> iterator = librosOrdenados.iterator();

        while (iterator.hasNext()) {
            Libro libro1 = iterator.next();
            Autor autorDelLibro1 = libro1.getAutor();

            // Validar si el autor del libro 1 es nulo
            if (autorDelLibro1 != null) {
                String nombreDelAutor1 = autorDelLibro1.getNombre();
                // Almacenar el primer libro en la lista resultante
                listaResultante.add(libro1);

                // Buscar otros libros con el mismo autor y agregarlos a la lista resultante
                while (iterator.hasNext()) {
                    Libro libro2 = iterator.next();
                    Autor autorDelLibro2 = libro2.getAutor();

                    // Validar si el autor del libro 2 es nulo
                    if (autorDelLibro2 != null) {
                        String nombreDelAutor2 = autorDelLibro2.getNombre();

                        // Si los autores son iguales, agregar el libro a la lista resultante
                        if (nombreDelAutor1.equals(nombreDelAutor2)) {
                            listaResultante.add(libro2);
                        } else {
                            // Si los autores son diferentes, detener la búsqueda
                            break;
                        }
                    }
                }
            }
        }

        // Ordenar la lista resultante por fecha de publicación
        listaResultante.sort(new Comparator<Libro>() {
            @Override
            public int compare(Libro libro1, Libro libro2) {
                LocalDate fecha1 = libro1.getFechaPublicacion();
                LocalDate fecha2 = libro2.getFechaPublicacion();

                // Validar si las fechas son nulas
                if (fecha1 == null && fecha2 == null) {
                    return 0; // Ambas fechas son nulas, considerar iguales
                } else if (fecha1 == null) {
                    return 1; // fecha1 es nula, considerar libro1 mayor
                } else if (fecha2 == null) {
                    return -1; // fecha2 es nula, considerar libro2 mayor
                }

                return fecha1.compareTo(fecha2);
            }
        });

        return listaResultante;
    }
    public boolean eliminarLibro(String nombre) {
        // Itera a través de la lista de libros
        Iterator<Libro> iterador = listaLibros.iterator();
        while (iterador.hasNext()) {
            Libro libro = iterador.next();
            if (libro.getNombre().equals(nombre)) {
                // Si el nombre coincide, elimina el libro y devuelve true
                iterador.remove();
                return true;
            }
        }
        // Si no se encontró el libro, devuelve false
        return false;
    }

}
