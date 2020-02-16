package model.data.structure;

import model.data_exception.SeparateChainingHashST_ArgumentoIlegalException;

/**
 *  The {@code SeparateChainingHashST} class represents a symbol table of generic
 *  key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be {@code null}â€”setting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a separate chaining hash table. It requires that
 *  the key type overrides the {@code equals()} and {@code hashCode()} methods.
 *  The expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 *  operation is constant, subject to the uniform hashing assumption.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For other implementations, see {@link ST}, {@link BinarySearchST},
 *  {@link SequentialSearchST}, {@link BST}, {@link RedBlackBST}, and
 *  {@link LinearProbingHashST},
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
// Una tabla de simbolos implementada con una Hash Table de encadenamiento separado
public class SeparateChainingHashST<K,V>
{
	private static final int INIT_CAPACITY = 4;
	
	private int n; // numero de las parejas llave-valor
	private int m; // size de la table hash
	private ListaLlaveValorSecuencial<K,V>[] st; //arreglo de la lista encadenada de la tabla de simbolos
	
	
	// inicializa una tabla de simbolos vacia
	public SeparateChainingHashST()
	{
		this(INIT_CAPACITY);
	}
	
	
	// crea la tabla de hash de encadenamiento separado con m posiciones(slots) iniciales
	public SeparateChainingHashST(int m)
	{
		this.m = m;
		st = (ListaLlaveValorSecuencial<K, V>[]) new ListaLlaveValorSecuencial[m];
		
		for (int i = 0; i < m; i++) 
		{
			st[i] = new ListaLlaveValorSecuencial<K,V>();
		}
	}
	
	 
	// redimensiona la tabla de hash hasta tener el numero de cadenas dado.
	// rehashing todas las llaves
	private void resize(int cadenas)
	{
		SeparateChainingHashST<K, V> temp = new SeparateChainingHashST<K,V>(cadenas);
		for (int i = 0; i < m; i++)
		{
			for(K key: st[i].llaves())
			{
				temp.insertar(key, st[i].darValor(key));
			}
			
		}
		
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}
	
	
	
	
	//Devuelve la posicioÌ�n de 0 a m-1 indicada para guardar la llave en la tabla.
	private int hash(K llave)
	{
		return (llave.hashCode() & 0x7fffffff) % m;
	}
	
	
	//Devuelve el numero de llaves en la tabla
	public int darTamanio()
	{
		return n;
	}
	
	//Devuelve si la tabla esta vacia o no
	public boolean estaVacia()
	{
		return darTamanio() == 0;
	}
	
	
	//devuelve si la llave existe en la tabla
	// si la llave es null genera una excepciÃ³n 
	public boolean tieneLlave(K llave)
	{
		if(llave == null)
			try 
			{
				throw new SeparateChainingHashST_ArgumentoIlegalException ("El argumento es null");
			} 
		catch (SeparateChainingHashST_ArgumentoIlegalException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return darValor(llave) !=null;
	}
	
	// Devuelve el valor asociado a la llave, devuelve null si no existe la llave en la tabla.
	// si la llave ingresada es null genera una excepciÃ³n 
	public V darValor(K llave)
	{
		if(llave == null)
			try {
				throw new SeparateChainingHashST_ArgumentoIlegalException("El argumento es null");
			} catch (SeparateChainingHashST_ArgumentoIlegalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		int i = hash(llave);
		return st[i].darValor(llave);
	}
	
	/**
	 * inserta la llave y el valor en la tabla. Si el valor es null y la llave existe previamente
	 *  debe eliminar el elemento de la tabla. 
	 *  Si la llave existe debe reemplazar el valor asociado a la llave. 
	 *  Recuerde reducir las posiciones de la tabla o aumentarlas (rehash) 
	 *  dependiendo del factor de carga de la tabla. (n/m)
	 * @param llave
	 * @param valor
	 */
	public void insertar(K llave, V valor)
	{
		if(llave == null)
			try {
				throw new SeparateChainingHashST_ArgumentoIlegalException("El primer argumento ingresado es null");
			} catch (SeparateChainingHashST_ArgumentoIlegalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(valor == null)
		{
			delete(llave);
			return;
		}
		
		if(n >= 10*m) resize(2*m); // dobla el tamanno de la tabla si su longitud promedio es >=10
		
		int i = hash(llave);
		if(!st[i].tieneLlave(llave)) n++;
		st[i].insertar(llave, valor);
		
	
	}
	
	
	
	
	public void delete(K llave)
	{
		if(llave == null)
			try 
			{
				throw new SeparateChainingHashST_ArgumentoIlegalException("El argumento a borrar es null");
			} 
			catch (SeparateChainingHashST_ArgumentoIlegalException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		int i = hash(llave);
		if(st[i].tieneLlave(llave)) n--;
		st[i].delete(llave);
		
		// reduce el tamanno de la tabala si su longitud promedio de la lista <=2
		if(m > INIT_CAPACITY && n <= 2*m ) resize(m/2);
	}
	
	
	
	// Devuelve un iterador sobre las llaves de la tabla
	public Iterable<K> llaves()
	{
		Cola<K> queue = new Cola<K>();
		
		for (int i = 0; i < m; i++)
		{
			for(K key : st[i].llaves())
			{
				queue.enqueue(key);
			}
		}
		
		return queue;		
	}

	//Devuelve la longitud de cada una de las listas que pertenecen a la tabla de hash.
	public int[] darLongitudLista()
	{		
		int[] longitudresp = new int[m];
	
		for (int i = 0; i < m; i++) 
		{
			int tama = st[i].darTamanio();
			longitudresp[i] = tama;
			
		}

		return longitudresp;
	}
	
	// Devuelve el numero de filas de la tabla Hash, no el numero de parejas que contiene
	public int darNumeroFilasHT()
	{
		return m;
	}
	
	
	public ListaLlaveValorSecuencial<K, V> darListaSecuencia(K key)
	{
		int llave = hash(key);
		return st[llave];
	}
}
