import accidentes.*;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    // Listas globales
    static ArrayList<Dueno> duenos = new ArrayList<>();
    static ArrayList<Marca> marcas = new ArrayList<>();
    static ArrayList<Carro> carros = new ArrayList<>();
    static ArrayList<Incidente> incidentes = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializar datos de ejemplo
        inicializarDatos();

        boolean running = true;
        while (running) {
            System.out.println("\n----- Sistema de Accidentes de Carros ------");
            System.out.println("1. Registrar dueno");
            System.out.println("2. Registrar marca");
            System.out.println("3. Registrar carro");
            System.out.println("4. Registrar incidente");
            System.out.println("5. Consultas");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            int option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1: registrarDueno(); break;
                case 2: registrarMarca(); break;
                case 3: registrarCarro(); break;
                case 4: registrarIncidente(); break;
                case 5: menuConsultas(); break;
                case 0: running = false; break;
                default: System.out.println("Opcion invalida.");
            }
        }
        System.out.println("Gracias por usar el programa.");
    }

    // Datos iniciales
    private static void inicializarDatos() {
        Marca toyota = new Marca(1, "Toyota", "Japon");
        Marca ford = new Marca(2, "Ford", "EEUU");
        Marca chevrolet = new Marca(3, "Chevrolet", "EEUU");
        Marca nissan = new Marca(4, "Nissan", "Japon");
        marcas.add(toyota);
        marcas.add(ford);
        marcas.add(chevrolet);
        marcas.add(nissan);

        Dueno d1 = new Dueno(1001, "Lucia", "Torres", "3201234567");
        Dueno d2 = new Dueno(1002, "Carlos", "Ramirez", "3109876543");
        Dueno d3 = new Dueno(1003, "Maria", "Gomez", "3001122334");
        Dueno d4 = new Dueno(1004, "Andres", "Martinez", "3004455667");
        Dueno d5 = new Dueno(1005, "Paola", "Diaz", "3225566778");
        duenos.add(d1);
        duenos.add(d2);
        duenos.add(d3);
        duenos.add(d4);
        duenos.add(d5);

        Carro c1 = new Carro("KHT321", "SUV", 2018); c1.setMarca(toyota); d1.vincularCarro(c1);
        Carro c2 = new Carro("JDF456", "Sedan", 2020); c2.setMarca(ford); d2.vincularCarro(c2);
        Carro c3 = new Carro("XYZ987", "Pickup", 2017); c3.setMarca(chevrolet); d3.vincularCarro(c3);
        Carro c4 = new Carro("LMN345", "Hatchback", 2021); c4.setMarca(nissan); d4.vincularCarro(c4);
        Carro c5 = new Carro("PQR678", "SUV", 2019); c5.setMarca(toyota); d5.vincularCarro(c5);
        carros.add(c1); carros.add(c2); carros.add(c3); carros.add(c4); carros.add(c5);

        Incidente i1 = new Incidente(101, "Choque", new Date(), d1.getTelefono()); d1.agregarIncidente(i1);
        Incidente i2 = new Incidente(102, "Volcamiento", new Date(), d2.getTelefono()); d2.agregarIncidente(i2);
        Incidente i3 = new Incidente(103, "Atropello", new Date(), d3.getTelefono()); d3.agregarIncidente(i3);
        Incidente i4 = new Incidente(104, "Choque", new Date(), d4.getTelefono()); d4.agregarIncidente(i4);
        Incidente i5 = new Incidente(105, "Incendio", new Date(), d5.getTelefono()); d5.agregarIncidente(i5);
        incidentes.add(i1); incidentes.add(i2); incidentes.add(i3); incidentes.add(i4); incidentes.add(i5);

        System.out.println("Datos iniciales cargados.");
    }

    // Registrar dueno
    private static void registrarDueno() {
        System.out.print("Cedula: ");
        long cedula = Long.parseLong(sc.nextLine());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        Dueno d = new Dueno(cedula, nombre, apellido, telefono);
        duenos.add(d);
        System.out.println("Dueno registrado: " + d);
    }

    // Registrar marca
    private static void registrarMarca() {
        System.out.print("ID: ");
        long id = Long.parseLong(sc.nextLine());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Pais: ");
        String pais = sc.nextLine();

        Marca m = new Marca(id, nombre, pais);
        marcas.add(m);
        System.out.println("Marca registrada: " + m);
    }

    // Registrar carro
    private static void registrarCarro() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Anio: ");
        int anio = Integer.parseInt(sc.nextLine());

        Carro c = new Carro(placa, modelo, anio);

        // Se imprime lista para seleccionar
        System.out.println("Seleccione la marca:");
        for (int i = 0; i < marcas.size(); i++) {
            System.out.println((i + 1) + ". " + marcas.get(i).getNombre());
        }
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (idx >= 0 && idx < marcas.size()) {
            c.setMarca(marcas.get(idx));
        }

        // Se imprime lista para seleccionar
        System.out.println("Seleccione el dueno:");
        for (int i = 0; i < duenos.size(); i++) {
            System.out.println((i+1) + ". " + duenos.get(i).getNombre());
        }
        int idxDueno = Integer.parseInt(sc.nextLine()) - 1;
        if (idxDueno >= 0 && idxDueno < duenos.size()) {
            duenos.get(idxDueno).vincularCarro(c);
        }

        carros.add(c);
        System.out.println("Carro registrado: " + c);
    }

    // Registrar incidente
    private static void registrarIncidente() {
        System.out.print("Codigo: ");
        long codigo = Long.parseLong(sc.nextLine());
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();

        // Se imprime lista para seleccionar
        System.out.println("Seleccione el dueno:");
        for (int i = 0; i < duenos.size(); i++) {
            System.out.println((i+1) + ". " + duenos.get(i).getNombre());
        }
        int idxDueno = Integer.parseInt(sc.nextLine()) - 1;
        if (idxDueno >= 0 && idxDueno < duenos.size()) {
            Dueno d = duenos.get(idxDueno);
            Incidente inc = new Incidente(codigo, tipo, new Date(), d.getTelefono());
            d.agregarIncidente(inc);
            incidentes.add(inc);
            System.out.println("Incidente registrado: " + inc);
        }
    }

    // Menu de consultas
    private static void menuConsultas() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Consultas ---");
            System.out.println("1. La marca mas vendida");
            System.out.println("2. La marca de carros con mas incidentes");
            System.out.println("3. El pais de origen mas comun y cuantos carros tiene");
            System.out.println("4. Mostrar los incidentes de cada dueno");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: consultaMarcaMasVendida(); break;
                case 2: consultaMarcaMasIncidentes(); break;
                case 3: consultaPaisMasComun(); break;
                case 4: consultaIncidentesPorDueno(); break;
                case 0: running = false; break;
                default: System.out.println("Opcion invalida.");
            }
        }
    }

    // 1. Marca mas vendida
    private static void consultaMarcaMasVendida() {
        if (carros.isEmpty()) {
            System.out.println("No hay carros registrados.");
            return;
        }
        String marcaMasVendida = "";
        int maximo = 0;
        for (Marca m : marcas) {
            int contador = 0;
            for (Carro c : carros) {
                if (c.getMarca() != null && c.getMarca().getNombre().equals(m.getNombre())) {
                    contador++;
                }
            }
            if (contador > maximo) {
                maximo = contador;
                marcaMasVendida = m.getNombre();
            }
        }
        System.out.println("La marca mas vendida es: " + marcaMasVendida + " con " + maximo + " carros.");
    }

    // 2. Marca con mas incidentes
    private static void consultaMarcaMasIncidentes() {
        if (incidentes.isEmpty()) {
            System.out.println("No hay incidentes registrados.");
            return;
        }

        String marcaMasIncidentes = "";
        int maximo = 0;

        for (Marca m : marcas) {
            int contador = 0;
            for (Dueno d : duenos) {
                // recorremos cada incidente del dueno
                for (Incidente i : d.getIncidentes()) {
                    // cada incidente debe estar ligado a un carro
                    for (Carro c : d.getCarros()) {
                        if (c.getMarca() != null && c.getMarca().getNombre().equals(m.getNombre())) {
                            // sumamos SOLO UNA VEZ el incidente para esa marca
                            contador++;
                            break; // salimos del bucle de carros para no duplicar
                        }
                    }
                }
            }
            if (contador > maximo) {
                maximo = contador;
                marcaMasIncidentes = m.getNombre();
            }
        }

        System.out.println("La marca con mas incidentes es: " + marcaMasIncidentes + " con " + maximo + " incidentes.");
    }

    // 3. Pais de origen mas comun
    private static void consultaPaisMasComun() {
        if (marcas.isEmpty()) {
            System.out.println("No hay marcas registradas.");
            return;
        }
        String paisMasComun = "";
        int maximo = 0;
        for (Marca m1 : marcas) {
            int contador = 0;
            for (Marca m2 : marcas) {
                if (m1.getPais().equals(m2.getPais())) {
                    contador += m2.getCarros().size();
                }
            }
            if (contador > maximo) {
                maximo = contador;
                paisMasComun = m1.getPais();
            }
        }
        System.out.println("El pais mas comun es: " + paisMasComun + " con " + maximo + " carros.");
    }

    // 4. Incidentes por dueno
    private static void consultaIncidentesPorDueno() {
        if (duenos.isEmpty()) {
            System.out.println("No hay duenos registrados.");
            return;
        }
        for (Dueno d : duenos) {
            System.out.println("\nDueno: " + d.getNombre() + " " + d.getApellido());
            if (d.getIncidentes().isEmpty()) {
                System.out.println("  Sin incidentes registrados.");
            } else {
                for (Incidente i : d.getIncidentes()) {
                    System.out.println("  - " + i);
                }
            }
        }
    }
}
