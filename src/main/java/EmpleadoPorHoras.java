class EmpleadoPorHoras extends Empleado {
    private double salarioPorHora;
    private double horasTrabajadas;

    public EmpleadoPorHoras(String nombre, double salarioPorHora, double horasTrabajadas) {
        super(nombre, 0); // El salario base no se usa en este caso
        this.salarioPorHora = salarioPorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getSalarioPorHora() {
        return salarioPorHora;
    }

    public void setSalarioPorHora(double salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSalario() {
        return salarioPorHora * horasTrabajadas;
    }
}