package GestionContactos;

import java.io.*;

public class ManejoArchivos {

    public static void crearArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el archivo");

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void escribirArchivo(String nombreArchivo, String identificacion, String nombre, String apellido) {
        try {
            File archivo = new File(nombreArchivo);
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(identificacion + " " + nombre + " " + apellido);
            salida.close();
            System.out.println("Se ha escrito al archivo");

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void anexarArchivo(String nombreArchivo, String identificacion, String nombre, String apellido) {
        try {
            File archivo = new File(nombreArchivo);
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(identificacion + " " + nombre + " " + apellido);
            salida.close();
            System.out.println("Se ha anexado informacion al archivo");

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void leerArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while (lectura != null) {
                System.out.println("contacto = " + lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static boolean eliminarContacto(String nombreArchivo, String identificacion) {
        try {
            File archivo = new File(nombreArchivo);
            File archivoTemp = new File(nombreArchivo + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            PrintWriter pw = new PrintWriter(new FileWriter(archivoTemp));

            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                // Verificar si la identificación coincide
                if (partes.length >= 1 && partes[0].equals(identificacion)) {
                    encontrado = true;
                    continue;
                }
                pw.println(linea);
            }

            br.close();
            pw.close();
            archivo.delete();
            archivoTemp.renameTo(archivo);
            return encontrado;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean modificarContacto(String nombreArchivo, String identificacion, String nombre, String apellido) {
        try {
            File archivo = new File(nombreArchivo);
            File archivoTemp = new File(nombreArchivo + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            PrintWriter pw = new PrintWriter(new FileWriter(archivoTemp));

            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                //Verifica si la identificación coincide
                if (partes.length >= 1 && partes[0].equals(identificacion)) {
                    encontrado = true;
                    // Modificar la línea con los nuevos datos
                    linea = identificacion + " " + nombre + " " + apellido;
                }
                pw.println(linea);
            }

            br.close();
            pw.close();
            archivo.delete();
            archivoTemp.renameTo(archivo);
            return encontrado;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean verificarContactoExistente(String nombreArchivo, String identificacion) {
        try {
            File archivo = new File(nombreArchivo);
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));

            String linea = entrada.readLine();
            while (linea != null) {
                String[] partes = linea.split(" ");
                String identificacionExistente = partes[0];

                //Verifica si la identificación coincide 
                if (identificacionExistente.equals(identificacion)) {
                    entrada.close();
                    return true; // El contacto ya existe
                }
                linea = entrada.readLine();
            }

            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
