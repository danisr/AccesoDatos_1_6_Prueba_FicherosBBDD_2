import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class conexionBBDD {
	Connection conexion;

	public void conexion() {
		System.out.println("Conectando...");
		Properties propiedades = new Properties();
		InputStream entrada = null;

		try {
			entrada = new FileInputStream("configbbdd.ini");
			propiedades.load(entrada);

			String bbdd = propiedades.getProperty("bbdd");
			String user = propiedades.getProperty("user");
			String pwd = propiedades.getProperty("pass");
			String url = "jdbc:mysql://localhost/" + bbdd;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					conexion = DriverManager.getConnection(url, user, pwd);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Conexion establecida");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				System.out.println("No tengo el driver cargado");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<String> selectAlumnos() {
		System.out.println("Lectura de Alumnos");
		ArrayList<String> alumnos = new ArrayList<String>();

		try {
			if (conexion != null) {
				String query = "SELECT alumnos.DNI FROM alumnos";
				PreparedStatement pstmt = conexion.prepareStatement(query);
				ResultSet rset = pstmt.executeQuery();

				while (rset.next()) {
					String dniAlumno = rset.getString("DNI");					

					alumnos.add(dniAlumno);
				}
				pstmt.close();
				rset.close();
			} else {
				System.out.println("Conexion nula");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}

	public ArrayList<Alumno> selectNotasAlumno(int dni) {
		System.out.println("Lectura de notas de Alumnos");
		ArrayList<Alumno> notasAl = new ArrayList<Alumno>();

		try {
			if (conexion != null) {
				String query = "SELECT alumnos.APELLIDO_NOMBRE, asignaturas.NOMBRE, notas.nota FROM alumnos, asignaturas, notas WHERE notas.alumno = alumnos.DNI AND notas.asignatura = asignaturas.CODIGO AND alumnos.DNI = "
						+ dni;
				PreparedStatement pstmt = conexion.prepareStatement(query);
				ResultSet rset = pstmt.executeQuery();

				while (rset.next()) {
					String nombreAlumno = rset.getString("APELLIDO_NOMBRE");
					String nombreAsig = rset.getString("NOMBRE");
					int notaAsig = rset.getInt("nota");

					notasAl.add(new Alumno(nombreAlumno, nombreAsig, notaAsig));
				}
				pstmt.close();
				rset.close();
			} else {
				System.out.println("Conexion nula");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notasAl;
	}

	public ArrayList<String> selectNotasAsignaturas(String asignatura) {
		System.out.println("Lectura de notas de Asignaturas");
		ArrayList<String> notas = new ArrayList();

		int codigo = 0;

		if (asignatura.equals("Física")) {
			codigo = 1;
		} else if (asignatura.equals("Química")) {
			codigo = 2;
		} else if (asignatura.equals("Lengua")) {
			codigo = 3;
		} else if (asignatura.equals("Inglés")) {
			codigo = 4;
		}

		try {
			if (conexion != null) {
				String query = "SELECT asignaturas.NOMBRE, MAX(nota), MIN(nota), ROUND(AVG(nota)) FROM bbdd_escuela.notas, bbdd_escuela.asignaturas WHERE asignaturas.NOMBRE = '"
						+ asignatura + "' AND notas.asignatura = " + codigo;

				PreparedStatement pstmt = conexion.prepareStatement(query);
				ResultSet rset = pstmt.executeQuery();

				while (rset.next()) {
					String nombreAsig = rset.getString("NOMBRE");
					int maxNota = rset.getInt("MAX(nota)");
					String sMaxNota = maxNota + "";
					int minNota = rset.getInt("MIN(nota)");
					String sMinNota = minNota + "";
					int mediaNota = rset.getInt("ROUND(AVG(nota))");
					String sMediaNota = mediaNota + "";

					notas.add(nombreAsig);
					notas.add(sMaxNota);
					notas.add(sMinNota);
					notas.add(sMediaNota);
				}
				pstmt.close();
				rset.close();
			} else {
				System.out.println("Conexion nula");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notas;
	}
}