package classes;

import java.util.HashMap;
import java.util.Map;

public class Arreglo {

	int size;
	Map<Object, Variable> valsArreglo;
	
	public Arreglo(){
		valsArreglo = new HashMap<>();
		size = 0;
	}
	
	public Variable getValue(Object index){
		return valsArreglo.get(index);
	}
	
	public boolean containsKey(Object key){
		return valsArreglo.containsKey(key);
	}
	
}
