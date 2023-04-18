package es.unican.is2.ListaOrdenada.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenada.ListaOrdenada;

class ListaOrdenadaTest<E> {
	
	

	@Test
	void add() {
		
		ListaOrdenada<Integer> tmp = new ListaOrdenada<Integer>();
		final ListaOrdenada<Integer> tmp2 = tmp;
		tmp.add(1);
		tmp.add(8);
		tmp.add(5);
		
		int get0 = tmp.get(0);
		int get1 = tmp.get(1);
		int get2 = tmp.get(2);
		
		assertTrue(get0 == 8);
		assertTrue(get1 == 5);
		assertTrue(get2 == 1);
		
		//assertThrows(IndexOutOfBoundsException.class, () -> {tmp2.add(null);});
			
	}
	
	@Test
	void get() {
		
		ListaOrdenada<Integer> tmp = new ListaOrdenada<Integer>();
		final ListaOrdenada<Integer> tmp2 = tmp;
		
		//Fuera de los limites
		assertThrows(IndexOutOfBoundsException.class, () -> {tmp2.get(-10);});
		assertThrows(IndexOutOfBoundsException.class, () -> {tmp2.get(10);});
		tmp.add(5);
		int get = tmp.get(0);
		assertTrue(get == 5);
		
	}
	
	@Test
	void size() {
		
		int repeticiones = 10;
		
		ListaOrdenada<Integer> tmp = new ListaOrdenada<Integer>();
		for (int i = 0; i < repeticiones; i++) {
			tmp.add(5);
		}
		int size = tmp.size();
		assertTrue(size == repeticiones);
		
	}
	
	@Test
	void clear() {
		
		ListaOrdenada<Integer> tmp = new ListaOrdenada<Integer>();
		
		tmp.add(5);
		tmp.add(8);
		tmp.add(1);
		
		tmp.clear();
		int size = tmp.size();
		assertTrue(size == 0);
		
	}
	
	@Test
	void remove() {
		
		ListaOrdenada<Integer> tmp = new ListaOrdenada<Integer>();
		
		tmp.add(1);
		tmp.add(5);
		tmp.add(8);
		tmp.add(10);
		
		tmp.remove(1);
		int size = tmp.size();
		assertTrue(size == 3);
		int get1 = tmp.get(1);
		assertTrue(get1 == 5);	
		assertThrows(IndexOutOfBoundsException.class, () -> {tmp.remove(10);});
		assertThrows(IndexOutOfBoundsException.class, () -> {tmp.remove(-10);});
		
		
	}

}
