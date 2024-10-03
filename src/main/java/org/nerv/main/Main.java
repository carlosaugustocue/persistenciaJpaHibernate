package org.nerv.main;


import org.nerv.domain.model.*;
import org.nerv.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static EmpleadoRepository empleadoRepo = new EmpleadoRepository();
    private static DepartamentoRepository departamentoRepo = new DepartamentoRepository();
    private static TareaRepository tareaRepo = new TareaRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 5) {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crudEmpleado();
                    break;
                case 2:
                    crudDepartamento();
                    break;
                case 3:
                    crudTarea();
                    break;
                case 4:
                    listarEmpleados();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. CRUD Empleados");
        System.out.println("2. CRUD Departamentos");
        System.out.println("3. CRUD Tareas");
        System.out.println("4. Ver todos los Empleados");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    // CRUD Empleados
    private static void crudEmpleado() {
        System.out.println("\n--- CRUD Empleados ---");
        System.out.println("1. Crear Empleado");
        System.out.println("2. Leer Empleado por ID");
        System.out.println("3. Actualizar Empleado por ID");
        System.out.println("4. Eliminar Empleado por ID");
        System.out.print("Selecciona una opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                crearEmpleado();
                break;
            case 2:
                leerEmpleado();
                break;
            case 3:
                actualizarEmpleado();
                break;
            case 4:
                eliminarEmpleado();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void crearEmpleado() {
        System.out.println("\n--- Crear Empleado ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Fecha de contratación (YYYY-MM-DD): ");
        Date fechaContratacion = new Date();  // Aquí puedes implementar el parseo de fecha

        System.out.print("Estado (ACTIVO, INACTIVO, VACACIONES): ");
        EstadoEmpleado estado = EstadoEmpleado.valueOf(scanner.nextLine().toUpperCase());

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setFechaContratacion(fechaContratacion);
        empleado.setEstado(estado);

        empleadoRepo.crear(empleado);
        System.out.println("Empleado creado exitosamente.");
    }

    private static void leerEmpleado() {
        System.out.println("\n--- Leer Empleado ---");
        System.out.print("ID del Empleado: ");
        Long id = Long.parseLong(scanner.nextLine());

        Empleado empleado = empleadoRepo.leer(id);
        if (empleado != null) {
            System.out.println("ID: " + empleado.getId());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Fecha de Contratación: " + empleado.getFechaContratacion());
            System.out.println("Estado: " + empleado.getEstado());
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }

    private static void actualizarEmpleado() {
        System.out.println("\n--- Actualizar Empleado ---");
        System.out.print("ID del Empleado: ");
        Long id = Long.parseLong(scanner.nextLine());

        Empleado empleado = empleadoRepo.leer(id);
        if (empleado != null) {
            System.out.print("Nuevo nombre (deja en blanco para no cambiar): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                empleado.setNombre(nuevoNombre);
            }

            System.out.print("Nuevo estado (ACTIVO, INACTIVO, VACACIONES): ");
            String nuevoEstado = scanner.nextLine();
            if (!nuevoEstado.isEmpty()) {
                empleado.setEstado(EstadoEmpleado.valueOf(nuevoEstado.toUpperCase()));
            }

            empleadoRepo.actualizar(empleado);
            System.out.println("Empleado actualizado exitosamente.");
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }

    private static void eliminarEmpleado() {
        System.out.println("\n--- Eliminar Empleado ---");
        System.out.print("ID del Empleado: ");
        Long id = Long.parseLong(scanner.nextLine());

        Empleado empleado = empleadoRepo.leer(id);
        if (empleado != null) {
            empleadoRepo.eliminar(empleado);
            System.out.println("Empleado eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }

    // CRUD Departamentos
    private static void crudDepartamento() {
        System.out.println("\n--- CRUD Departamentos ---");
        System.out.println("1. Crear Departamento");
        System.out.println("2. Leer Departamento por ID");
        System.out.println("3. Actualizar Departamento por ID");
        System.out.println("4. Eliminar Departamento por ID");
        System.out.print("Selecciona una opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                crearDepartamento();
                break;
            case 2:
                leerDepartamento();
                break;
            case 3:
                actualizarDepartamento();
                break;
            case 4:
                eliminarDepartamento();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void crearDepartamento() {
        System.out.println("\n--- Crear Departamento ---");
        System.out.print("Nombre del departamento: ");
        String nombre = scanner.nextLine();

        Departamento departamento = new Departamento();
        departamento.setNombre(nombre);

        departamentoRepo.crear(departamento);
        System.out.println("Departamento creado exitosamente.");
    }

    private static void leerDepartamento() {
        System.out.println("\n--- Leer Departamento ---");
        System.out.print("ID del Departamento: ");
        Long id = Long.parseLong(scanner.nextLine());

        Departamento departamento = departamentoRepo.leer(id);
        if (departamento != null) {
            System.out.println("ID: " + departamento.getId());
            System.out.println("Nombre: " + departamento.getNombre());
        } else {
            System.out.println("No se encontró ningún departamento con ese ID.");
        }
    }

    private static void actualizarDepartamento() {
        System.out.println("\n--- Actualizar Departamento ---");
        System.out.print("ID del Departamento: ");
        Long id = Long.parseLong(scanner.nextLine());

        Departamento departamento = departamentoRepo.leer(id);
        if (departamento != null) {
            System.out.print("Nuevo nombre del departamento: ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                departamento.setNombre(nuevoNombre);
            }

            departamentoRepo.actualizar(departamento);
            System.out.println("Departamento actualizado exitosamente.");
        } else {
            System.out.println("No se encontró ningún departamento con ese ID.");
        }
    }

    private static void eliminarDepartamento() {
        System.out.println("\n--- Eliminar Departamento ---");
        System.out.print("ID del Departamento: ");
        Long id = Long.parseLong(scanner.nextLine());

        Departamento departamento = departamentoRepo.leer(id);
        if (departamento != null) {
            departamentoRepo.eliminar(departamento);
            System.out.println("Departamento eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún departamento con ese ID.");
        }
    }

    // CRUD Tareas
    private static void crudTarea() {
        System.out.println("\n--- CRUD Tareas ---");
        System.out.println("1. Crear Tarea");
        System.out.println("2. Leer Tarea por ID");
        System.out.println("3. Actualizar Tarea por ID");
        System.out.println("4. Eliminar Tarea por ID");
        System.out.print("Selecciona una opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                crearTarea();
                break;
            case 2:
                leerTarea();
                break;
            case 3:
                actualizarTarea();
                break;
            case 4:
                eliminarTarea();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void crearTarea() {
        System.out.println("\n--- Crear Tarea ---");
        System.out.print("Descripción de la tarea: ");
        String descripcion = scanner.nextLine();

        System.out.print("ID del empleado asignado: ");
        Long empleadoId = Long.parseLong(scanner.nextLine());

        Empleado empleado = empleadoRepo.leer(empleadoId);
        if (empleado != null) {
            Tarea tarea = new Tarea();
            tarea.setDescripcion(descripcion);
            tarea.setEmpleado(empleado);

            tareaRepo.crear(tarea);
            System.out.println("Tarea creada exitosamente.");
        } else {
            System.out.println("Empleado no encontrado, no se puede asignar la tarea.");
        }
    }

    private static void leerTarea() {
        System.out.println("\n--- Leer Tarea ---");
        System.out.print("ID de la Tarea: ");
        Long id = Long.parseLong(scanner.nextLine());

        Tarea tarea = tareaRepo.leer(id);
        if (tarea != null) {
            System.out.println("ID: " + tarea.getId());
            System.out.println("Descripción: " + tarea.getDescripcion());
            System.out.println("Empleado asignado: " + tarea.getEmpleado().getNombre());
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID.");
        }
    }

    private static void actualizarTarea() {
        System.out.println("\n--- Actualizar Tarea ---");
        System.out.print("ID de la Tarea: ");
        Long id = Long.parseLong(scanner.nextLine());

        Tarea tarea = tareaRepo.leer(id);
        if (tarea != null) {
            System.out.print("Nueva descripción (deja en blanco para no cambiar): ");
            String nuevaDescripcion = scanner.nextLine();
            if (!nuevaDescripcion.isEmpty()) {
                tarea.setDescripcion(nuevaDescripcion);
            }

            tareaRepo.actualizar(tarea);
            System.out.println("Tarea actualizada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID.");
        }
    }

    private static void eliminarTarea() {
        System.out.println("\n--- Eliminar Tarea ---");
        System.out.print("ID de la Tarea: ");
        Long id = Long.parseLong(scanner.nextLine());

        Tarea tarea = tareaRepo.leer(id);
        if (tarea != null) {
            tareaRepo.eliminar(tarea);
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID.");
        }
    }

    // Listar todos los empleados
    private static void listarEmpleados() {
        System.out.println("\n--- Lista de Empleados ---");
        // Implementar lógica para listar todos los empleados (si es necesario)
    }
}
