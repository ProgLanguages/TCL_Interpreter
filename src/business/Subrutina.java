package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.tclParser.Cuerpo_funcionContext;

public class Subrutina {
	List<String> parametros;
	Cuerpo_funcionContext bloqueInstruccion;
	List<Map<String, Object>> tableVars;
	
	
	public Subrutina(Cuerpo_funcionContext dec){
		this.parametros = new ArrayList<>();
		this.bloqueInstruccion = dec;
		tableVars = new ArrayList<>();
		tableVars.add(new HashMap<>());
	}
	
	public void insertParametro(String param){
		parametros.add(param);
	}
	
	public void setTable(){
		tableVars.add(new HashMap<>());
	}
	
	public void removeTable(){
		tableVars.remove(tableVars.size()-1);
	}
	
	public List<Map<String, Object>> getTables(){
		return this.tableVars;
	}
	
}
