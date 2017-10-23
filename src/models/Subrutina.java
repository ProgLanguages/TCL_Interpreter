package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.tclParser.Cuerpo_funcionContext;
import java.util.Iterator;

public class Subrutina {
	private List<String> argumentos;
	private Cuerpo_funcionContext bloqueInstruccion;
	private List<Map<String, Object>> tableVars;

	public Subrutina(Cuerpo_funcionContext dec, List<String> parametros){
		this.argumentos = parametros;
		this.bloqueInstruccion = dec;
		tableVars = new ArrayList<>();
	}
	
	public Subrutina(Subrutina s){
		this.argumentos = s.argumentos;
		this.bloqueInstruccion = s.bloqueInstruccion;
                tableVars = new ArrayList<>();
	}        

        public boolean verifyParams(List<Variable> params) {
            return params.size() == argumentos.size();
        }    

        public void removeTable(){
                tableVars.remove(tableVars.size()-1);
        }

        public void addVariables(List<Variable> params) {
            Iterator<String> argumentoIt = argumentos.iterator();
            Iterator<Variable> parametroIt = params.iterator();
            while (argumentoIt.hasNext()) {
                tableVars.get(tableVars.size()-1).replace(argumentoIt.next(), new Variable(parametroIt.next()));
            }
        }
    
	public void addTable(){
		tableVars.add(new HashMap<>());
                if (tableVars.isEmpty())
                    addArgumentos();
	}

        public void addArgumentos() {
            Iterator<String> argIt = argumentos.iterator();
            while (argIt.hasNext()) {
                tableVars.get(tableVars.size()-1).put(argIt.next(), null);
            }
        }
        
	public void removeVariable(String name){
		tableVars.get(tableVars.size()-1).remove(name);
	}

        public Cuerpo_funcionContext getBloqueInstruccion() {
            return bloqueInstruccion;
        }
    
	public Object getVarSwitch(){
		return getLastTable().get("-switch");
	}
	
	public Map<String, Object> getLastTable(){
		return tableVars.get(tableVars.size()-1);
	}
        
	public List<Map<String, Object>> getTables(){
		return this.tableVars;
	}
	
	public void setVariable(String name, Variable var){
            tableVars.get(tableVars.size()-1).put(name, var);
	}
}
