package business;

public class Error {
	
	public static void printError(String mensaje, int line, int col){
		System.err.printf("<%d:%d> Error semantico:  %s\n", line,col, mensaje);
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
}
