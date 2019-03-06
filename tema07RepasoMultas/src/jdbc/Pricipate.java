package jdbc;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Pricipate {
	public static int setInteger(String cadena) {
		System.out.println(cadena);
		return new Scanner(System.in).nextInt();
	}

	public static String setString(String cadena) {
		System.out.println(cadena);
		return new Scanner(System.in).nextLine();
	}

	public static void altaMultas(Session session) {
		Transaction transaction = session.beginTransaction();
		System.out.println("\t\t**--** ALTA MULTAS **--**");
		Multas multas = new Multas();
		session.save(multas);
		transaction.commit();
		System.out.println("\nMulta dado de alta con exito.\n");
	}

	public static void menuMultas() {
		System.out.println("\t\t***---*** MENU MULTAS ***---***");
		System.out.println("Pulsa 1 para dar de alta a una multa.");
		System.out.println("Pulsa 2 para dar de baja a una multa.");
		System.out.println("Pulsa 3 para modificar a una multa.");
		System.out.println("Pulsa 4 para motrar todos los multa.");
		System.out.println("Pulsa 5 para salirte al menu Principal.");
	}

	public static void showMenuMultas(Session session) {
		int opcion;

		do {
			menuMultas();
			opcion = setInteger("Introduce la opcion que desees.");

			switch (opcion) {
			case 1: {
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				break;
			}
			case 5: {
				System.out.println("Te has salido del menu de multas. Hasta pronto ma nigga.");
				break;
			}
			default: {
				System.out.println("Error en la opcion. Intentalo de nuevo.\n\n");
				break;
			}
			}
		} while (opcion != 5);
	}

	/**
	 * Zona conductores
	 * 
	 * @param session
	 */

	public static void altaConductor(Session session) {
		Transaction transaction = session.beginTransaction();
		System.out.println("\t\t**--** ALTA CONDUCTOR **--**");
		Conductor conductor = new Conductor(setString("Nif del conductor:"), setString("Nombre"),
				setString("Apellidos:"), setString("Direccion:"), setString("Poblacion:"), setString("Provincia_"),
				setInteger("Telefono:"));
		session.save(conductor);
		transaction.commit();
		System.out.println("\nConductor dado de alta con exito.\n");
	}

	public static void bajaConductor(Session session) {
		Transaction transaction = session.beginTransaction();
		System.out.println("\t\t**--** BAJA CONDUCTOR **--**");
		Conductor conductor = new Conductor();
		try {
			conductor = (Conductor) session.get(Conductor.class, setString("DNI del Conductor a da de baja:"));
			session.delete(conductor);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("No existe ningun condutor con ese ID.");
			e.printStackTrace();
		}
		System.out.println("\nConductor dado de baja con exito.\n");
	}

	public static void modificarConductor(Session session) {
		System.out.println("\t\t**--** MODIFICAR CONDUCTOR **--**");
		Conductor conductor = new Conductor();
		try {
			Transaction transaction = session.beginTransaction();
			conductor = (Conductor) session.get(Conductor.class, setString("DNI del Conductor:"));
			System.out.println("\n\tIntroduce los nuevos datos:\n");

			conductor.setNif(setString("Nif del conductor:"));
			conductor.setNombre(setString("Nombre"));
			conductor.setApellidos(setString("Apellidos:"));
			conductor.setDireccion(setString("Direccion:"));
			conductor.setPoblacion(setString("Poblacion:"));
			conductor.setProvincia(setString("Provincia:"));
			conductor.setTelefono(setInteger("Telefono:"));

			session.update(conductor);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("No existe ningun condutor con ese ID.");
			e.printStackTrace();
		}
		System.out.println("\nConductor modificado con exito.\n");
	}

	public static void getAllConductores(Session session) {

		Query query = session.createQuery("from  Conductor");

		List<Conductor> listaConductores = query.list();
		Iterator<Conductor> iter = listaConductores.iterator();

		Conductor conductor = new Conductor();
		while (iter.hasNext()) {
			conductor = iter.next();
			System.out.println(conductor.toString());
		}
	}

	public static void getAllConductoresCacerenios(Session session) {
		String provincia = setString("introduce la provincia:");
		System.out.println("Conductores de " + provincia);

		Query query = session.createQuery("from  Conductor where provincia=?");
		query.setString(0, provincia);

		List<Conductor> listaConductores = query.list();
		Iterator<Conductor> iter = listaConductores.iterator();

		Conductor conductor = new Conductor();
		while (iter.hasNext()) {
			conductor = iter.next();
			System.out.println(conductor.toString());
		}
	}

	public static void menuConductores() {
		System.out.println("\n\t\t***---*** MENU CONDUCTORES ***---***");
		System.out.println("Pulsa 1 para dar de alta a un conductor.");
		System.out.println("Pulsa 2 para dar de baja a uun conductor.");
		System.out.println("Pulsa 3 para modificar a un conductor.");
		System.out.println("Pulsa 4 para motrar todos los conductores.");
		System.out.println("Pulsa 5 para motrar todos los conductores que sean de una determinada provincia.");
		System.out.println("Pulsa 6 para salirte al menu Principal.\n\n");
	}

	public static void showMenuConductores(Session session) {
		int opcion;

		do {
			menuConductores();
			opcion = setInteger("Introduce la opcion que desees.");

			switch (opcion) {
			case 1: {
				altaConductor(session);
				break;
			}
			case 2: {
				bajaConductor(session);
				break;
			}
			case 3: {
				modificarConductor(session);
				break;
			}
			case 4: {
				getAllConductores(session);
				break;
			}
			case 5: {
				getAllConductoresCacerenios(session);
				break;
			}
			case 6: {
				System.out.println("Te has salido del menu de conductores. Hasta pronto ma nigga.");
				break;
			}
			default: {
				System.out.println("Error en la opcion. Intentalo de nuevo.\n\n");
				break;
			}
			}
		} while (opcion != 6);
	}

	/**
	 * Zona vehiculos
	 * 
	 * @param session
	 */

	public static void altaVehiculos(Session session) {
		Transaction transaction = session.beginTransaction();
		System.out.println("\t\t**--** ALTA VEHICULOS **--**");
		Vehiculo vehiculo = new Vehiculo();

		Query query = session.createQuery("from Conductor where nif=?");
		query.setString(0, setString("DNI del conductor:"));

		Conductor conductor = (Conductor) query.list().get(0);

		vehiculo = new Vehiculo(setString("Matricula"), conductor, setString("Marca:"), setString("Modelo:"),
				setInteger("CV:"));

		session.save(vehiculo);
		transaction.commit();

		System.out.println("\nVehiculo dado de alta con exito.\n");
	}

	public static void bajaVehiculos(Session session) {
		Transaction transaction = session.beginTransaction();
		System.out.println("\t\t**--** BAJA VEHICULOS **--**");
		Vehiculo vehiculo = new Vehiculo();
		try {
			vehiculo = (Vehiculo) session.get(Vehiculo.class, setString("Matricula del Vehiculo:"));
			session.delete(vehiculo);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("No existe ningun vehiculo con esa matricula.");
			e.printStackTrace();
		}
		System.out.println("\nVehiculos dado de baja con exito.\n");
	}

	public static void modificarVehiculos(Session session) {
		System.out.println("\t\t**--** MODIFICAR VEHICULOS **--**");
		Vehiculo vehiculo = new Vehiculo();
		Transaction transaction = session.beginTransaction();
		vehiculo = (Vehiculo) session.get(Vehiculo.class, setString("Matricula del Vehiculo:"));
		System.out.println("\n\tIntroduce los nuevos datos:\n");

		vehiculo.setMarca(setString("Marca del Vehiculo:"));
		vehiculo.setModelo(setString("Modelo del vehiculo:"));
		vehiculo.setCv(setInteger("CV:"));
		session.update(vehiculo);
		transaction.commit();

		System.out.println("\nVehiculos modificado con exito.\n");
	}

	public static void getAllVehiculos(Session session) {

		Query query = session.createQuery("from Vehiculo");

		List<Vehiculo> listaVehiculo = query.list();
		Iterator<Vehiculo> iter = listaVehiculo.iterator();

		Vehiculo vehiculo = new Vehiculo();
		while (iter.hasNext()) {
			vehiculo = iter.next();
			System.out.println(vehiculo);
		}
	}

	public static void getAllVehiculosCacerenios(Session session) {
		String provincia = setString("introduce la provincia:");
		System.out.println("Conductores de " + provincia);

		Query query = session.createQuery("from Vehiculo where conductor.provincia=?");
		query.setString(0, provincia);

		List<Vehiculo> listaConductores = query.list();
		Iterator<Vehiculo> iter = listaConductores.iterator();

		Vehiculo conductor = new Vehiculo();
		while (iter.hasNext()) {
			conductor = iter.next();
			System.out.println(conductor.toString());
		}
	}

	public static void menuVehiculos() {
		System.out.println("\n\t\t***---*** MENU VEHICULOS ***---***");
		System.out.println("Pulsa 1 para dar de alta a un Vehiculos.");
		System.out.println("Pulsa 2 para dar de baja a uun Vehiculosr.");
		System.out.println("Pulsa 3 para modificar a un Vehiculos.");
		System.out.println("Pulsa 4 para motrar todos los Vehiculos.");
		System.out.println("Pulsa 5 para motrar todos los Vehiculos que sean de una determinada provincia.");
		System.out.println("Pulsa 6 para salirte al menu Principal.\n\n");
	}

	public static void showMenuVehiculos(Session session) {
		int opcion;

		do {
			menuVehiculos();
			opcion = setInteger("Introduce la opcion que desees.");

			switch (opcion) {
			case 1: {
				altaVehiculos(session);
				break;
			}
			case 2: {
				bajaVehiculos(session);
				break;
			}
			case 3: {
				modificarVehiculos(session);
				break;
			}
			case 4: {
				getAllVehiculos(session);
				break;
			}
			case 5: {
				getAllVehiculosCacerenios(session);
				break;
			}
			case 6: {
				System.out.println("Te has salido del menu de Vehiculos. Hasta pronto ma nigga.");
				break;
			}
			default: {
				System.out.println("Error en la opcion. Intentalo de nuevo.\n\n");
				break;
			}
			}
		} while (opcion != 6);
	}

	public static void menuPrincipal() {
		System.out.println("\t\t***---*** MENU PRINCIPAL ***---***");
		System.out.println("Pulsa 1 para ir a la tabla de Multa.");
		System.out.println("Pulsa 2 para ir a la tabla de Conductor.");
		System.out.println("Pulsa 3 para ir a la tabla de Vehiculo.");
		System.out.println("Pulsa 4 para salir del programa.\n\n");
	}

	public static void showMenuPrincipal(Session session) {
		int opcion;

		do {
			menuPrincipal();
			opcion = setInteger("Introduce la opcion que desees.");

			switch (opcion) {
			case 1: {
				showMenuMultas(session);
				break;
			}
			case 2: {
				showMenuConductores(session);
				break;
			}
			case 3: {
				showMenuVehiculos(session);
				break;
			}
			case 4: {
				System.out.println("Te has salido del programa. Hasta pronto ma nigga.");
				break;
			}
			default: {
				System.out.println("Error en la opcion. Intentalo de nuevo.\n\n");
				break;
			}
			}
		} while (opcion != 4);
	}

	public static void main(String[] args) {
		org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

		SessionFactory factory = SessionFactoryUtil.getSessionFactory();
		Session session = factory.openSession();

		showMenuPrincipal(session);

		session.close();
		factory.close();

	}

}
