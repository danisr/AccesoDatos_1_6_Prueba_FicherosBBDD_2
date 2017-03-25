import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

public class Fichero {
	FileWriter fichero = null;
	PrintWriter pw = null;
	Properties propiedades = new Properties();
	InputStream entrada = null;

	public void escribirNotasAlumno(ArrayList<Alumno> notasAlumno) {
		System.out.println("Escribiendo Notas de Alumno en fichero");
		try {
			entrada = new FileInputStream("configfichero.ini");
			propiedades.load(entrada);

			String ruta = propiedades.getProperty("ruta");
			fichero = new FileWriter(ruta);
			pw = new PrintWriter(fichero);

			int j = 1;
			pw.println("ALUMNO: " + notasAlumno.get(j).getNombreAlumno());
			int asig = 1;
			for (int i = 0; i < notasAlumno.size(); i++) {
				pw.println("ASIGNATURA " + asig + "º: " + notasAlumno.get(i).getNombreAsignatura() + " = "
						+ notasAlumno.get(i).getNota());
				asig++;
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void escribirNotasAsignaturas(ArrayList<String> notas) {
		System.out.println("Escribiendo Notas de Asignaturas en fichero");
		try {
			entrada = new FileInputStream("configfichero.ini");
			propiedades.load(entrada);

			String ruta = propiedades.getProperty("ruta");
			fichero = new FileWriter(ruta);
			pw = new PrintWriter(fichero);

			for (int i = 0; i < notas.size(); i++) {
				pw.print("ASIGNATURA: " + notas.get(i) + "\nNota Máxima:" + notas.get(++i) + " Nota Mínima:" + notas.get(++i)
						+ " Nota Media:" + notas.get(++i));
				pw.println();
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}