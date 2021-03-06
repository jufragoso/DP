package ed;

/**
 * Grupo: The Team Crocket
 * 
 * @author Juan Luis Fragoso del Rey
 * @author Carlos Rodriguez Nu�ez
 */
public class List<T extends Comparable<T>> {
	/** Puntero al primer elemento de la Lista */
	private Node first;

	/** Puntero al ultimo elemento de la Lista */
	private Node last;

	/** Tama�o de la Lista */
	Integer size = 0;

	public class Node {
		/** Data almacenado en cada nodo */
		private T Data;
		/** Enlace al siguiente elemento */
		private Node next;
		/** Enlace al elemento anterior */
		private Node prev;

		public Node(Node prev, T Data, Node next) {
			this.Data = Data;
			this.next = next;
			this.prev = prev;
		}

		/**
		 * Metodo que devuelve el siguiente nodo de la Lista (para recorridos
		 * con la lista)
		 * 
		 * @return el elemento next
		 */
		public Node next() {
			return next;
		}

		/**
		 * Metodo que devuelve el anterior nodo de la Lista (para recorridos con
		 * la lista)
		 * 
		 * @return el elemento prev
		 */
		public Node prev() {
			return prev;
		}

		/**
		 * Metodo para consultar un Data
		 * 
		 * @return el Data contenido en el nodo actual
		 */
		public T get() {
			return Data;
		}
	}// class Nodo

	/**
	 * Metodo constructor por defecto de la clase Lista
	 */
	public List() {
		first = last = null;
		size = 0;
	}

	/**
	 * Metodo constructor parametrizado de la clase Lista
	 * 
	 * @param Data
	 *            es el nuevo elemento en la lista
	 */
	public List(T Data) {
		addLast(Data);
	}

	/**
	 * Metodo que devuelve el elemento del inicio de la Lista
	 * 
	 * @return el primer elemento
	 */
	public T getFirst() {
		return first.Data;
	}

	/**
	 * Metodo que devuelve el ultimo elemento de la Lista
	 * 
	 * @return el ultimo elemento
	 */
	public T getLast() {
		return last.Data;
	}

	/**
	 * Metodo que devuelve el inicio de la Lista
	 * 
	 * @return first
	 */
	public Node first() {
		return first;
	}

	/**
	 * Metodo que devuelve el final de la Lista
	 * 
	 * @return last
	 */
	public Node last() {
		return last;
	}

	/**
	 * Metodo para comprobar si la lista esta vacia o no
	 * 
	 * @return true si esta vacia o false en caso contrario
	 */
	public boolean estaVacia() {
		return (size == 0);
	}

	/**
	 * Metodo para comprobar el tama�o de la lista
	 * 
	 * @return size tama�o de la lista
	 */
	public Integer size() {
		return size;
	}

	/**
	 * Metodo para consultar un Data almacenado en una posicion
	 * 
	 * @return el Data contenido en el nodo que esta en la posicion pos de la
	 *         lista
	 */
	public T get(Integer pos) {
		T d = null;
		Node iter = first;
		Integer i = 0;
		boolean encontrado = false;
		while (i <= pos && !encontrado && iter != null) {
			if (i == pos) {
				encontrado = true;
				d = iter.Data;
			}
			i++;
			iter = iter.next;
		}
		return d;
	}

	/**
	 * Permite insertar al final de la lista
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 */
	public void addLast(T Data) {
		Node l = last;
		Node nodo = new Node(l, Data, null);
		last = nodo;
		if (l == null)
			first = nodo;
		else
			l.next = nodo;
		size++;
	}

	/**
	 * Permite eliminar el ultimo elemento de la Lista
	 * 
	 */
	public void removeLast() {
		// implementación del método
		if (last != null) {
			last = last.prev();
		}
		// no hay elementos
		if (last == null)
			first = null;
		else
			last.next = null;
		size--;
	}

	/**
	 * Permite eliminar el primer elemento de la Lista
	 * 
	 */
	public void removeFirst() {
		// first apunta al siguient elemento
		if (first != null) {
			first = first.next();
		}

		if (first == null)
			last = null; // list ha quedado vacia
		else
			first.prev = null; // el nuevo prev de first apunta a null
		size--;
	}

	/**
	 * Permite eliminar un dato almacenado en la lista
	 * 
	 * @param dato
	 *            valor que se eliminara de la lista
	 * @return la posicion en la que se almacenaba el dato
	 */
	public int removeDato(T dato) {
		Node aux = first;
		int i = 1;

		while (aux != null) {

			if (aux.Data.equals(dato)) {

				if (aux == first)
					first = first.next;
				else if (aux == last)
					last = last.prev;
				else {
					aux.prev().next = aux.next;
					aux.next().prev = aux.prev;
				}
				size--;
				return i;
			} else {
				aux = aux.next;
				i++;
			}
		}

		return -1;
	}

	/**
	 * Permite insertar de forma ordenada en la lista
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 */
	public void sortedAdd(T Data) {
		Node nodoDato = new Node(null, Data, null);
		Node aux = first;
		boolean enc = false;
		if (!estaVacia()) {
			while (!enc) {
				if (aux.Data.equals(nodoDato.Data)) {
					if (aux.next != null)
						aux = aux.next;
					else {
						last.next = nodoDato;
						nodoDato.prev = last;
						last = nodoDato;
						enc = true;
						size++;
					}

				} else {
					nodoDato.next = aux;
					nodoDato.prev = aux.prev();
					// aux.prev = nodoDato;
					if (aux != first)
						aux.prev().next = aux.prev = nodoDato;
					else {
						first = nodoDato;
						aux.prev = nodoDato;
					}

					enc = true;
					size++;
				}
			}
		} else {
			first = last = nodoDato;
			size++;
		}
	}

	/**
	 * Permite insertar al principio de la lista
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 */
	public void addFirst(T Data) {
		Node nodoDato = new Node(null, Data, null);
		if (!estaVacia()) {
			first.prev = nodoDato;
			nodoDato.next = first;
			first = nodoDato;

		} else
			first = last = nodoDato;
		size++;
	}

	/**
	 * comprueba si un valor existe en la lista
	 * 
	 * @param Data
	 *            valor que se va a comprobar
	 * @return true si el Data existe en la lista o false en caso contrario
	 */
	public boolean contains(T Data) {
		Node nodoDato = first;
		if (!estaVacia()) {
			while (nodoDato != last.next)
				if (nodoDato.Data.equals(Data)) {
					return true;
				} else {
					nodoDato = nodoDato.next;
				}
		}
		return false;
	}

	/**
	 * Permite insertar delante de un valor dado
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 * @param valorbuscar
	 *            valor delante del cual se insertara el nuevo dato
	 */
	public void addBefore(T Data, T valorbuscar) {
		Node nodoDato = new Node(null, Data, null);
		Node aux = first;
		boolean enc = false;
		if (!estaVacia()) {
			while (!enc) {
				if (aux.Data.equals(valorbuscar)) {
					enc = true;
					nodoDato.next = aux;
					if (aux != first) {
						nodoDato.prev = aux.prev();
						aux.prev().next = aux.prev = nodoDato;

					} else {
						aux.prev = nodoDato;
						nodoDato.next = aux;
						first = nodoDato;
					}
					size++;
				} else {
					aux = aux.next;
				}
			}

		}
	}

	/**
	 * Permite insertar detras de un valor dado
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 * @param valorbuscar
	 *            valor detras del cual se insertara el nuevo dato
	 */
	public void addAfter(T Data, T valorbuscar) {
		Node nodoDato = new Node(null, Data, null);
		Node aux = first;
		boolean enc = false;
		if (!estaVacia()) {
			while (!enc) {
				if (aux.Data.equals(valorbuscar)) {
					enc = true;
					nodoDato.prev = aux;
					if (aux.next != null) {
						nodoDato.next = aux.next();
						aux.next().prev = aux.next = nodoDato;

					} else {
						aux.next = nodoDato;
						last = nodoDato;
					}
					size++;
				} else {
					aux = aux.next;
				}
			}

		}
	}

	/**
	 * Permite insertar en orden en una lista
	 * 
	 * @param Data
	 *            contiene el elemento a insertar
	 */
	public void addOrder(T Data) {

		if (!this.estaVacia()) {
			int i = 1;
			boolean enc = false;

			Node auxiliar = first;

			while (!enc) {
				if (Data.compareTo(auxiliar.Data) < 0) {
					this.addIndex(Data, i);
					enc = true;
				} else {
					if (auxiliar.next != null) {
						auxiliar = auxiliar.next;
						i++;
					} else {
						addLast(Data);
						enc = true;
					}
				}
			}

			if (!enc) {
				addLast(Data);
			}

		} else {
			this.addLast(Data);
		}
	}

	/**
	 * Permite insertar delante de una posicion dada
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 * @param index
	 *            posicion delante de la cual se insertara el nuevo dato
	 */
	public void addBeforeIndex(T Data, int index) {
		Node nodoDato = new Node(null, Data, null);
		Node aux = first;
		int i = 1;
		if (!estaVacia()) {
			while (i < index) {
				aux = aux.next;
				i++;
			}
			nodoDato.next = aux;
			if (aux != first) {
				nodoDato.prev = aux.prev();
				aux.prev().next = aux.prev = nodoDato;

			} else {
				aux.prev = nodoDato;
				nodoDato.next = aux;
				first = nodoDato;
			}
			size++;
		}

	}

	/**
	 * Permite insertar detras de una posicion dada
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 * @param index
	 *            posicion detras de la cual se insertara el nuevo dato
	 */
	public void addAfterIndex(T Data, int index) {
		Node nodoDato = new Node(null, Data, null);
		Node aux = first;
		int i = 1;
		if (!estaVacia()) {
			while (i < index) {
				aux = aux.next;
				i++;
			}
			nodoDato.prev = aux;
			if (aux.next != null) {
				nodoDato.next = aux.next();
				aux.next().prev = aux.next = nodoDato;

			} else {
				aux.next = nodoDato;
				last = nodoDato;
			}
			size++;
		}
	}

	/**
	 * Permite insertar en una posicion dada
	 * 
	 * @param Data
	 *            valor que se va a insertar
	 * @param index
	 *            posicion en la cual se insertara el nuevo Data
	 */
	public void addIndex(T Data, int index) {
		Node nodoDato = new Node(null, Data, null);
		Node aux = first;
		int i = 1;
		if (!estaVacia()) {
			while (i < index) {
				aux = aux.next;
				i++;
			}
			nodoDato.next = aux;
			if (aux != first) {
				nodoDato.prev = aux.prev();
				aux.prev().next = aux.prev = nodoDato;

			} else {
				aux.prev = nodoDato;
				nodoDato.next = aux;
				first = nodoDato;
			}
			size++;
		}

	}

	/**
	 * Permite eliminar el Data almacenado en una posición dada
	 * 
	 * @param index
	 *            posicion del Data que se eliminara
	 * @return el dato que esta al inicio de la lista
	 */
	public T removeIndex(int index) {
		int i = 1;
		Node aux = first;
		while (i < index) {
			aux = aux.next;
			i++;
		}
		if (aux != first) {
			if (aux == last) {
				last = aux.prev;
			} else {
				aux.prev().next = aux.next;
				aux.next().prev = aux.prev;
			}
			size--;
			return first.Data;

		} else {
			first = aux.next;
			size--;
			return first.Data;

		}
	}

	/**
	 * Cambia el valor almacenado en una posicion por otro
	 * 
	 * @param index
	 *            posicion del dato que se cambiara
	 * @param Data
	 *            nuevo valor por el que se sustituira el valor que hay
	 *            almacenado actualmente
	 */
	public void set(int index, T Data) {

		Node aux = first;
		int i = 1;
		while (i < index) {
			aux = aux.next;
			i++;
		}
		aux.Data = Data;

	}

	/**
	 * Muestra por pantalla el contenido de la lista
	 * 
	 * @return s string que contiene todos los Datas de la lista concatenados
	 */
	public String toString() {
		String s = new String();
		for (int i = 0; i < size; i++) {
			if (i == size - 1)
				s = s + get(i);
			else
				s = s + get(i) + " ";
		}

		return s;
	}

}
