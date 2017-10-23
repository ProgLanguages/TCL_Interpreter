package business;

public class Error {

	public static final String ERR_STRING = "string";
	public static final String ERR_INT = "integer";
	public static final String ERR_DOUBLE = "double";
	
	public static void printError(String mensaje, String loc){
		System.err.printf("<%s> Error semantico:  %s\n", loc, mensaje);
		System.exit(-1);
	}
	
	public static String variableNotDeclared(String name){
		return "la variable '" + name + "' no ha sido declarada.";
	}
	
	public static String arrayWithoutKey(String nameVar, String index){
		return "el arreglo '" + nameVar + "' no tiene la llave '" + index + "'.";
	}
	
	public static String variableNotArray(String nameVar){
		return "la variable '" + nameVar + "' no es un arreglo.";
	}
	
	public static String variableIsArray(String nameVar){
		return "la variable '" + nameVar + "' es un arreglo.";
	}
	
	public static String repeatedFunction(String nameFunc){		
		return "la subrutina '" + nameFunc + "' ya ha sido declarada.";
	}
	
	public static String incompatibleData(String wanted, String found){		
		return "tipos de datos incompatibles. Se esperaba: " + wanted + "; se encontro: " + found + ".";
	}
}
