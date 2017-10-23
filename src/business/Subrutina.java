package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.tclParser.Cuerpo_funcionContext;
import java.util.Iterator;

public class Subrutina {
	List<String> argumentos;
	Cuerpo_funcionContext bloqueInstruccion;
	List<Map<String, Object>> tableVars;
	
	
	public Subrutina(Cuerpo_funcionContext dec, List<String> parametros){
		this.argumentos = parametros;
		this.bloqueInstruccion = dec;
		tableVars = new ArrayList<>();
		tableVars.add(new HashMap<>());
                addArgumentos();
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

    public boolean verifyParams(List<Variable> params) {
        return params.size() == argumentos.size();
    }
    
    public void addVariables(List<Variable> params) {
        Iterator<String> argIt = argumentos.iterator();
        Iterator<Variable> parIt = params.iterator();
        while (argIt.hasNext()) {
            tableVars.get(0).replace(argIt.next(), parIt.next());
        }
    }

    private void addArgumentos() {
        Iterator<String> argIt = argumentos.iterator();
        while (argIt.hasNext()) {
            tableVars.get(0).put(argIt.next(), null);
        }
    }

    public Cuerpo_funcionContext getBloqueInstruccion() {
        return bloqueInstruccion;
    }
	
}
