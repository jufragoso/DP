package laberinto;
import ed.Arbol;
import ed.List;

/**
 * Grupo: The Team Crocket
 * @author Juan Luis Fragoso del Rey
 * @author Carlos Rodriguez Nu�ez
 *
 */

/**
 * Tipo enumerado que representa los 3 posibles estados en los que puede estar la puerta.
 */


public class Puerta {

	/**
	 * Tipo enumerado que representa los 3 posibles estados en los que puede estar la puerta.
	 */
	public enum Estado {
		no_configuada, abierta, cerrada;

		public String toString() {
			return this.name();
		}
	};
	
	/** Almacena las llaves probadas para abrir la puerta */
	private Arbol<Llave> probadas;
	/** Almacena la combinacion de la cerradura */
	private Arbol<Llave> cerradura;

	/** Contiene la combinacion de la puerta de manera consistente */
	private List<Llave> combinacion;

	/** Indica el estado en el que se encuentra la puerta */
	private Estado estadoPuerta;

	/** Altura a la que se abre la puerta */
	private int altura_Apertura;

	/**
	 * @return la altura a la que se abre la puerta
	 */
	public int getAltura_Apertura() {
		return altura_Apertura;
	}

	/**
	 * @param altura_Apertura
	 *            contiene la altura a la qe se abre la puerta
	 */
	public void setAltura_Apertura(int altura_Apertura) {
		this.altura_Apertura = altura_Apertura;
	}

	/**
	 * Metodo constructor por defecto de la clase Puerta
	 */
	public Puerta() {
		probadas = new Arbol<Llave>();
		combinacion = new List<Llave>();
		cerradura = new Arbol<Llave>();
		estadoPuerta = Estado.no_configuada;
	}

	/**
	 * @param llave
	 *            es la llave que se prueba en la combinacion
	 * @return true si la puerta se abre, false en caso contrario
	 */
	public boolean abrirPuerta(Llave llave) {
		if (!probadas.pertenece(llave)) {
			if (cerradura.pertenece(llave)) {
				probadas.insertar(llave);
				cerradura.borrar(llave);
				if (cerradura.profundidad() < this.altura_Apertura
						&& cerradura.numeroNodosInternos() >= cerradura
								.numeroHojas()) {
					estadoPuerta = Estado.abierta;
					return true;
				}
			}
		} 
		return false;
	}

	/**
	 * @return true si el estado de la puerta es abierta, false en caso
	 *         contrario
	 */
	public boolean estaAbierta() {
		return (estadoPuerta == Estado.abierta);
	}

	/**
	 * Cambia el estado de la puerta a cerrada, inicializa las llaves probadas e
	 * inicializa de nuevo la cerradura
	 */
	public void cerrarPuerta() {
		probadas = new Arbol<Llave>();

		cerradura = new Arbol<Llave>();
		for (int i = 0; i < combinacion.size(); i++) {
			cerradura.insertar(combinacion.get(i));
		}

		estadoPuerta = Estado.cerrada;

	}

	/**
	 * @return el arbol probadas
	 */
	public Arbol<Llave> getProbadas() {
		return probadas;
	}

	/**
	 * Inicializa el arbol probadas con el arbol pasado por parametro
	 * @param probadas
	 * 				arbol que contiene una serie de llaves probadas
	 */
	public void setProbadas(Arbol<Llave> probadas) {
		this.probadas = probadas;
	}
	
	/**
	 * @return arbol de cerradura
	 */
	public Arbol<Llave> getCerradura(){
		return cerradura;
	}
	
	/**
	 * Inicializa el arbol de cerradura con el arbol pasado por parámetro
	 * @param cerradura
	 * 				arbol que contiene una serie de llaves que forman la cerradura
	 */
	public void setCerradura (Arbol<Llave> cerradura){
		this.cerradura = cerradura;
	}
	
	/**
	 * @return El estado de la puerta
	 */
	public Estado getEstadoPuerta (){
		return this.estadoPuerta;
	}
	
	/**
	 * @param estadoPuerta
	 * 					Estado en el que se encontrará la puerta
	 */
	public void setEstadoPuerta (Estado estadoPuerta){
		this.estadoPuerta = estadoPuerta;
	}
	
	/**
	 * @return Combinacion de la puerta
	 */
	public List<Llave> getCombinacion() {
		return combinacion;
	}
	
	/**
	 * @param combinacion
	 * 				Combinacion de la puerta
	 */
	public void setCombinacion(List<Llave> combinacion){
		this.combinacion = combinacion;
	}
	
	/**
	 * Introduce las llaves del array en la lista y cambia el estado de la
	 * puerta a abierta
	 * 
	 * @param combinacion
	 *            contiene las llaves que compondran la cerradura
	 */
	public void configurarPuerta(Llave[] combinacion) {
		for (int i = 0; i < combinacion.length; i++) {
			Llave dato = combinacion[i];
			this.combinacion.addLast(dato);

		}
		estadoPuerta = Estado.abierta;
	}

	/**
	 * Muestra por pantalla si la puerta esta abierta o no.
	 */
	public void comprobar() {
		if (estaAbierta()) {
			System.out.println("La puerta esta abierta");
		} else {
			System.out.println("La puerta NO esta abierta");
			;
		}
	}

	public String toString() {
		return "(puerta:" + estadoPuerta + ":" + altura_Apertura + ":"
				+ cerradura + ":" + probadas + ")";
	}
}
