package classes;

import java.util.ArrayList;
import java.util.List;

import classes.tclParser.Cuerpo_funcionContext;

public class Subrutina {
	List<String> parametros;
	Cuerpo_funcionContext bloqueInstruccion;
	
	public Subrutina(Cuerpo_funcionContext dec){
		this.parametros = new ArrayList<>();
		this.bloqueInstruccion = dec;
	}
	
	public void insertParametro(String param){
		parametros.add(param);
	}
	
}
