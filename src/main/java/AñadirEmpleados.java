import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AñadirEmpleados {
    private static final String ARCHIVO_EMPLEADOS = "C:\\Users\\esteb\\OneDrive\\Desktop\\empleados.txt";

    public static void main(String[] args) {
        List<Empleado> listaEmpleados = leerEmpleadosDeArchivoTXT();

        Scanner scanner = new Scanner(System.in);

        int numEmpleados = obtenerNumero("Ingrese el número de empleados a agregar: ", scanner);

        for (int i = 0; i < numEmpleados; i++) {
            System.out.println("Empleado #" + (i + 1));
            String nombre = obtenerTexto("Nombre: ", scanner);
            int tipo = obtenerNumero("Tipo de empleado (1: Asalariado, 2: Por horas): ", scanner);

            if (tipo == 1) {
                double salarioBase = obtenerDoble("Salario base: ", scanner);
                listaEmpleados.add(new EmpleadoAsalariado(nombre, salarioBase));
            } else if (tipo == 2) {
                double salarioPorHora = obtenerDoble("Salario por hora: ", scanner);
                double horasTrabajadas = obtenerDoble("Horas trabajadas: ", scanner);
                listaEmpleados.add(new EmpleadoPorHoras(nombre, salarioPorHora, horasTrabajadas));
            }
        }

        // Guardar todos los empleados (incluyendo los existentes) en el archivo de texto
        guardarEmpleadosEnArchivoTXT(listaEmpleados);

        System.out.println("\nCálculo de salarios:\n");

        for (Empleado empleado : listaEmpleados) {
            System.out.println("Nombre: " + empleado.nombre);
            System.out.println("Salario: " + empleado.calcularSalario());
            System.out.println("=========================");
        }
    }

    private static int obtenerNumero(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    private static double obtenerDoble(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        return scanner.nextDouble();
    }

    private static String obtenerTexto(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        return scanner.next();
    }

    private static List<Empleado> leerEmpleadosDeArchivoTXT() {
        List<Empleado> empleados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_EMPLEADOS))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    String tipo = partes[1];
                    double salario = Double.parseDouble(partes[2]);

                    if (tipo.equals("Asalariado")) {
                        empleados.add(new EmpleadoAsalariado(nombre, salario));
                    } else if (tipo.equals("Por Horas")) {
                        empleados.add(new EmpleadoPorHoras(nombre, salario, 0)); // Horas trabajadas no se almacenan en el archivo
                    }
                }
            }

            System.out.println("Los datos de empleados se han leído desde el archivo de texto.");
        } catch (IOException e) {
            System.err.println("Error al leer los datos de empleados desde el archivo de texto.");
            e.printStackTrace();
        }

        return empleados;
    }

    private static void guardarEmpleadosEnArchivoTXT(List<Empleado> empleados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_EMPLEADOS))) {
            for (Empleado empleado : empleados) {
                String tipo = empleado instanceof EmpleadoAsalariado ? "Asalariado" : "Por Horas";
                writer.println(empleado.nombre + "," + tipo + "," + empleado.calcularSalario());
            }

            System.out.println("Los datos de empleados se han guardado en el archivo de texto.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos de empleados en el archivo de texto.");
            e.printStackTrace();
        }
    }
}