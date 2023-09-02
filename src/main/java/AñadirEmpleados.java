import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AñadirEmpleados {
    public static void main(String[] args) {
        List<Empleado> listaEmpleados = new ArrayList<>();
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
}