package model.data.structure;

/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/


import java.util.Iterator;
import java.util.NoSuchElementException;

import model.api.ILista;
import model.data_exception.Cola_ColaVaciaException;


/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a static nested class for
 *  linked-list nodes. See {@link LinkedQueue} for the version from the
 *  textbook that uses a non-static nested class.
 *  See {@link ResizingArrayQueue} for a version that uses a resizing array.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this queue
 */

public class Cola<T> implements Iterable<T> , ILista<T>
{
    private Nodo<T> primero;    // inicio de la cola
    private Nodo<T> ultimo;     // final de la cola
    private int n;               // numero de elementos de la cola

    
    // Clase nodo anidada
    public static class Nodo<T> 
    {
        private T item;
        private Nodo<T> next;
        
        public Nodo<T> darSiguiente(){
    		return next;
    		}
        
        public T darElemento(){
        		return item;
        }
        
    }
    

    /**
     * Inicializa una cola vacia
     */
    public Cola() 
    {
        primero = null;
        ultimo = null;
        n = 0;
    }

    
    /**
     * Retorna true si la cola esta vacia
     *
     * @return true si esta cola esta vacia
     */
    public boolean estaVacia() 
    {
        return primero == null;
    }
    

    /**
     * Retorna el numero de los items en esta cola
     *
     * @return tamannio de la cola
     */
    public int size() {
        return n;
    }

    
    /**
     * Retorna el elemento agregado recientemente a esta cola
     *
     * @return Elemento agregado recientemente
     * @throws ListaCola_ColaVaciaException si esta cola esta vacia
     */
    public T peek() {
        if (estaVacia())
			try 
        		{
				throw new Cola_ColaVaciaException("Cola vacia");
			} 
        		catch (Cola_ColaVaciaException e) {
				e.printStackTrace();
			}
        
        return primero.item;
    }

    
    /**
     * Agrega el elemento a la cola.
     *
     * @param  elemento que va a ser agregado a la cola
     */
    public void enqueue(T pT) 
    {
        Nodo<T> antiguo = ultimo;
        ultimo = new Nodo<T>();
        ultimo.item = pT;
        ultimo.next = null;
        if (estaVacia()) primero = ultimo;
        else           antiguo.next = ultimo;
        n++;
    }
    

    /**
     * Remueve y retorna el primer item de la cola
     *
     * @return el primer item de la cola
     * @throws ListaCola_ColaVaciaException si la cola esta vacia
     */
    public T dequeue() {
        if (estaVacia())
			try 
        		{
				throw new Cola_ColaVaciaException("La cola esta vacia");
			} catch (Cola_ColaVaciaException e) 
        		{
				e.printStackTrace();
			}
        T item = primero.item;
        primero = primero.next;
        n--;
        if (estaVacia()) ultimo = null;  
        return item;
    }

    
    
    /**
     * Retorna una representación en String de esta cola
     *
     * @return la secuencia de los items en orden FIFO, seprada por espacios.
     */
    public String toString() 
    {
        StringBuilder s = new StringBuilder();
        
        for (T item : this) 
        {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 

    
    /**
     * Retorna un iterador que itera sobre los items de esta cola en orden FIFO
     * 
     * @return iterador que itera sobre los items de esta cola en orden FIFO
     */
    public Iterator<T> iterator()  
    {
        return new ListIterator<T>(primero);  
    }

    private class ListIterator<T> implements Iterator<T> 
    {
        private Nodo<T> current;

        public ListIterator(Nodo<T> first) 
        {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next; 
            return item;
        }
    }
     
    
    public Nodo<T> darPrimero()
    {
		return primero;
	}
    
    public Nodo<T> darUltimo()
    {
		return ultimo;
	}
 
    

    
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
