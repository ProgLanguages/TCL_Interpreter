package models;

public class Variable {

	private int tipo;
	private Object valor;
	
	public Variable(int tipo, Object valor){
		this.tipo = tipo;
		this.valor = valor;
	}

	public Variable(Variable newVar){
		this.tipo = newVar.getTipo();
		this.valor = newVar.getValor();
	}
	
	@Override
	public String toString() {
		return "Variable [tipo=" + tipo + ", valor=" + valor + "]";
	}
	
	public Object getValor() {
		return  valor;
	}
	
	public int getTipo() {
		return  tipo;
	}
	
	public void setTipo(int t) {
		tipo = t;
	}
	
	public String valorToString(){
		return valor.toString();
	}
	
	public void setValor(Object valor){
		this.valor = valor;
	}
	
	
}
