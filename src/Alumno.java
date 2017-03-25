public class Alumno {
	private String nombreAlumno;
	private String nombreAsignatura;
	private int nota;

	public Alumno(String nombreAlumno, String nombreAsignatura, int nota) {
		this.nombreAlumno = nombreAlumno;
		this.nombreAsignatura = nombreAsignatura;
		this.nota = nota;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}