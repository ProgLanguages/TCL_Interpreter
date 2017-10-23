package models;

import java.util.HashMap;
import java.util.Map;

public class Arreglo {

	private int size;
	private Map<Object, Variable> valsArreglo;
	
	public Arreglo(){
		valsArreglo = new HashMap<>();
		size = 0;
	}
	
	public void insertIndice(Object index, Variable val){
		valsArreglo.put(index, new Variable(val));
		size++;
	}
	
	public void updateIndex(Object index, Variable val){
		valsArreglo.replace(index, val);
	}
	
	public Variable getValue(Object index){
		return valsArreglo.get(index);
	}
	
	public boolean containsKey(Object key){
		return valsArreglo.containsKey(key);
	}
	
	public int getSize(){
		return this.size;
	}
}
