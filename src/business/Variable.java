package business;

public class Variable {

	int tipo;
	Object valor;
	
	public Variable(int tipo, Object valor){
		this.tipo = tipo;
		this.valor = valor;
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
