package model.data.structure;

/**
 *  The {@code SequentialSearchST} class represents an (unordered)
 *  symbol table of generic key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  The class also uses the convention that values cannot be {@code null}. Setting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a singly-linked list and sequential search.
 *  It relies on the {@code equals()} method to test whether two keys
 *  are equal. It does not call either the {@code compareTo()} or
 *  {@code hashCode()} method. 
 *  The <em>put</em> and <em>delete</em> operations take linear time; the
 *  <em>get</em> and <em>contains</em> operations takes linear time in the worst case.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
// Implementación de una tabla de simbolos con una
// busqueda secuencial, en una lista encadenada
// de parejas llave-valor.
public class ListaLlaveValorSecuencial <K,V>
{
	private int n; // numero de las parejas llave-valor
	private Nodo primero; // la lista enlazada de las parejas llave-valor
	
	
	
	// clase Nodo anidada
		private class Nodo
		{
			private K key;
			private V val;
			private Nodo next;
			
			public Nodo(K pLlave, V pValor, Nodo next)
			{
				this.key = pLlave;
				this.val = pValor;
				this.next = next;
			}
		}
	
	
	
	// crea la lista vacia
	public ListaLlaveValorSecuencial()
	{
	}
	
	// devuelve el numero de llaves en la lista
	public int darTamanio()
	{
		return n;
	}
	
	// devuelve si la lista esta vacia o no.
	public boolean estaVacia()
	{
		return darTamanio() == 0;
	}
	
	
	// comprueba si la tabla de simbolos contiene la llave dada.
	public boolean tieneLlave(K llave)
	{
		return darValor(llave) !=null;
	}
	
	// devuelve el valor asociado a la llave, devuelve null si no existe la llave en la lista
	public V darValor(K llave)
	{
		for(Nodo x = primero; x !=null; x = x.next)
		{
			if(llave.equals(x.key))
			{
				return x.val;
			}
		}
		return null;
	}
	
	
	// Inserta en la lista la llave y el valor. Si el valor es null y la llave existe debe
	// eleminar el nodo de la lista.
	// Si la llave no existe debe añadir a la lista un nuevo nodo.
	// Si la llave existe debe reemplazar el valor del nodo.
	public void insertar(K llave, V valor)
	{
		if(valor == null)
		{
			delete(llave);
			return;
		}
		
		for(Nodo x= primero; x !=null;x= x.next)
		{
			if(llave.equals(x.key))
			{
				x.val = valor;
				return;
			}
		}
		
		primero = new Nodo(llave, valor, primero);
		n++;
	}
	
	
	//remueve la llave y el valor asociado de la tabla de simbolos 
	public void delete(K key)
	{
		primero = delete(primero, key);
	}
	
	// elimina la llave en la lista enlazada empezando  por el nodo x
	// Advertencia: la llamada de la funcion puede ser demasiado larga si la lista es larga 
	private Nodo delete(Nodo x,K key)
	{
		if(x == null) return null;
		if(key.equals(x.key))
		{
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
	
	
	
	//Devuleve un iterador sobre las llaves
	public Iterable<K> llaves()
	{
		Cola<K> queue = new Cola<K>();
		
		for(Nodo x = primero; x !=null; x = x.next)
		{
			queue.enqueue(x.key);
		}
		
		return queue;
	}
	

	
	
}
