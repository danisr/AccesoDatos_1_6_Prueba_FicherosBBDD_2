import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Intermediario {
	Scanner teclado;
	conexionBBDD conexion;
	Fichero fichero;

	public Intermediario() {
		teclado = new Scanner(System.in);
		conexion = new conexionBBDD();
		fichero = new Fichero();
	}

	public void ejecucion() {
		conexion.conexion();
		int op = 0;
		boolean salir = false;

		while (!salir) {
			System.out.println(".......................... \n" + ".  1 Listado de Notas del Alumno\n"
					+ ".  2 Informe de Notas de las Asignaturas\n" + ".  3 Salir \n" + "..........................");
			try {
				System.out.print("Opción: ");
				op = teclado.nextInt();
				System.out.println("OPCION SELECCIONADA: " + op);

				switch (op) {
				case 1:
					alumnos();
					break;
				case 2:
					teclado.nextLine();
					System.out.print("Introduzca asignatura (Lengua, Inglés, Física, Química): ");
					String asignatura = teclado.nextLine();
					notasAsignatura(asignatura);
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("Opcion invalida: marque un numero de 1 a 3");
					break;
				}
			} catch (Exception e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 1 a 3");
				e.printStackTrace();
				teclado.next();
			}
		}
	}

	public void alumnos() {
		ArrayList<String> alumnos = conexion.selectAlumnos();

		boolean isValido = false;
		int dniTeclado = 0;
		while (!isValido) {
			System.out.print("Introduzca su DNI: ");
			dniTeclado = teclado.nextInt();

			String sDni = dniTeclado + "";
			for (int i = 0; i < alumnos.size(); i++) {
				if (alumnos.get(i).equals(sDni)) {
					isValido = true;
				}
			}
			if (!isValido) {
				System.out.println("El DNI no existe");
			}
		}
		notasAlumno(dniTeclado);
	}

	public void notasAlumno(int dni) {
		ArrayList<Alumno> notasAlumno = conexion.selectNotasAlumno(dni);
		fichero.escribirNotasAlumno(notasAlumno);
	}

	public void notasAsignatura(String asignatura) {
		ArrayList<String> notas = conexion.selectNotasAsignaturas(asignatura);
		fichero.escribirNotasAsignaturas(notas);
	}
}