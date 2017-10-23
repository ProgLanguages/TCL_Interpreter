package visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.antlr.v4.runtime.tree.TerminalNode;

import business.Arreglo;
import business.Constants;
import business.Error;
import business.Subrutina;
import business.Variable;
import classes.tclBaseVisitor;
import classes.tclParser;
import classes.tclParser.AgrupContext;
import classes.tclParser.Asig_forContext;
import classes.tclParser.AsignacionContext;
import classes.tclParser.Case2Context;
import classes.tclParser.Case2_funcionContext;
import classes.tclParser.Case2_loopContext;
import classes.tclParser.Case_funcionContext;
import classes.tclParser.Case_loopContext;
import classes.tclParser.Cuerpo_funcionContext;
import classes.tclParser.Cuerpo_loopContext;
import classes.tclParser.Dec_forContext;
import classes.tclParser.DeclaracionContext;
import classes.tclParser.Declaracion_funcionContext;
import classes.tclParser.Default_funcionContext;
import classes.tclParser.Default_loopContext;
import classes.tclParser.Else_funcionContext;
import classes.tclParser.ElseifContext;
import classes.tclParser.Elseif_funcionContext;
import classes.tclParser.Elseif_loopContext;
import classes.tclParser.Exp_addContext;
import classes.tclParser.Exp_andContext;
import classes.tclParser.Exp_igContext;
import classes.tclParser.Exp_mulContext;
import classes.tclParser.Exp_orContext;
import classes.tclParser.Exp_potContext;
import classes.tclParser.Exp_relContext;
import classes.tclParser.Exp_unaContext;
import classes.tclParser.GetsContext;
import classes.tclParser.If_funcionContext;
import classes.tclParser.If_loopContext;
import classes.tclParser.IncrementoContext;
import classes.tclParser.IndiceContext;
import classes.tclParser.InicioContext;
import classes.tclParser.Inicio_caseContext;
import classes.tclParser.Inicio_elseifContext;
import classes.tclParser.Inicio_ifContext;
import classes.tclParser.Inicio_switchContext;
import classes.tclParser.Param_funcContext;
import classes.tclParser.PutsContext;
import classes.tclParser.R_breakContext;
import classes.tclParser.R_caseContext;
import classes.tclParser.R_continueContext;
import classes.tclParser.R_defaultContext;
import classes.tclParser.R_elseContext;
import classes.tclParser.R_forContext;
import classes.tclParser.R_ifContext;
import classes.tclParser.R_returnContext;
import classes.tclParser.R_switchContext;
import classes.tclParser.Switch_funcionContext;
import classes.tclParser.Switch_loopContext;
import classes.tclParser.TermContext;
import classes.tclParser.ValorContext;

public class VisitorTCL<T> extends tclBaseVisitor<T> {

    List<Map<String, Object>> tables = new ArrayList<>();
    Map<String, Subrutina> tableFunctions = new HashMap<>();
    Subrutina funcActual = null;
    Variable returnValue = null;
    
    boolean hasToBreak;
    boolean hasToContinue;

    public T visitInicio(InicioContext ctx) {
        tables.add(new HashMap<>());
        return super.visitChildren(ctx);
    }

    public T visitDeclaracion_funcion(Declaracion_funcionContext ctx) {
        if (ctx.IDENTIFICADOR() != null) {

            String nameId = ctx.IDENTIFICADOR().getText();
            // Si ya existia una funcion con el mismo nombre
            if (tableFunctions.containsKey(nameId)) {
                String msj = Error.repeatedFunction(nameId);
                int line = ctx.IDENTIFICADOR().getSymbol().getLine();
                int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine() + 1;
                Error.printError(msj, line, col);
                return null;
            } else {
                tableFunctions.put(nameId, new Subrutina(ctx.cuerpo_funcion()));

                /*
				 * Hay que obetener los argumentos, y ponerle esos argumentos a
				 * la funcion
				 */
			}
		}
		return super.visitDeclaracion_funcion(ctx);
	}

	// Falta manejar lo del scope en las funciones
	@Override
	public T visitIf_funcion(If_funcionContext ctx) {
		int result = (int) (((Variable) visitInicio_if(ctx.inicio_if())).getValor());
		if (result == 1) {
			funcActual.setTable();
			visitCuerpo_funcion(ctx.cuerpo_funcion());
			funcActual.removeTable();
			return null;
		} else {
			return visitElseif_funcion(ctx.elseif_funcion());
		}
	}

	@Override
	public T visitElseif_funcion(Elseif_funcionContext ctx) {
		if (ctx.else_funcion() != null) {
			return visitElse_funcion(ctx.else_funcion());
		} else {
			int result = (int) (((Variable) visitInicio_elseif(ctx.inicio_elseif())).getValor());
			if (result == 1) {
				funcActual.setTable();
				visitCuerpo_funcion(ctx.cuerpo_funcion());
				funcActual.removeTable();
				return null;
			} else {
				return visitElseif_funcion(ctx.elseif_funcion());
			}
		}
	}

	@Override
	public T visitElse_funcion(Else_funcionContext ctx) {
		if (ctx.inicio_else() != null) {
			funcActual.setTable();
			visitCuerpo_funcion(ctx.cuerpo_funcion());
			funcActual.removeTable();			
		}
		return null;
	}

	@Override
	public T visitSwitch_funcion(Switch_funcionContext ctx) {
		Variable var = (Variable) visitInicio_switch(ctx.inicio_switch());

		String nameVar = "-switch";
		funcActual.setVariable(nameVar, var);	
		visitCase_funcion(ctx.case_funcion());
		funcActual.removeTable();
		return null;
	}

	@Override
	public T visitCase_funcion(Case_funcionContext ctx) {
		int valor = (int)((Variable)visitInicio_case(ctx.inicio_case())).getValor();
		Variable temp = (Variable) funcActual.getVarSwitch();
		if(valor == (int)temp.getValor()){
			funcActual.setTable();
			visitCuerpo_funcion(ctx.cuerpo_funcion());
			funcActual.removeTable();
		} else {
			if(ctx.case2_funcion() != null){
				return visitCase2_funcion(ctx.case2_funcion());				
			} 
		}		
		return null;
	}
	
	@Override
	public T visitCase2_funcion(Case2_funcionContext ctx) {
		if(ctx.default_funcion() != null){
			return visitDefault_funcion(ctx.default_funcion());
		} else {
			int valor = (int) ((Variable)visitInicio_case(ctx.inicio_case())).getValor();
			Variable temp = (Variable) funcActual.getVarSwitch();
			if(valor == (int) temp.getValor()){
				funcActual.setTable();
				visitCuerpo_funcion(ctx.cuerpo_funcion());
				funcActual.removeTable();
			} else {
				if(ctx.case2_funcion() != null){
					return visitCase2_funcion(ctx.case2_funcion());
				} 
			}		
			return null;			
		}
	}
	
	@Override
	public T visitDefault_funcion(Default_funcionContext ctx) {
		funcActual.setTable();
		visitCuerpo_funcion(ctx.cuerpo_funcion());
		funcActual.removeTable();		
		return null;
	}
	
	@Override
	public T visitCuerpo_funcion(Cuerpo_funcionContext ctx) {		
		if(returnValue != null){
			return null;
		}
		
		if(ctx.r_return() != null){
			returnValue = (Variable)visitR_return(ctx.r_return());
			return null;
		} else {
			return visitChildren(ctx);
		}
	}
	
	@Override
	public T visitR_return(R_returnContext ctx) {
		if(ctx.value_return() != null){
			return (T) visitAsignacion(ctx.value_return().asignacion());
		} else {
			return (T) new Variable(Constants.INT, 0);
		}
	}
	
    /*//////////////////////////////////////////////////////////////////////////
    _   ___ 
   | | | __|
   | | | _| 
   |_| |_|  

/////////////////////////////////////////////////////////////////////////*/
	
	@Override
	public T visitR_if(R_ifContext ctx) {
		int result = (int) (((Variable) visitInicio_if(ctx.inicio_if())).getValor());
		if (result != 0) {
			tables.add(new HashMap<>());
			visitCuerpo_inst(ctx.cuerpo_inst());
			tables.remove(tables.size() - 1);
			return null;
		} else {
			return visitElseif(ctx.elseif());
		}
	}

	@Override
	public T visitElseif(ElseifContext ctx) {
		if (ctx.r_else() != null) {
			return visitR_else(ctx.r_else());
		} else {
			int result = (int) (((Variable) visitInicio_elseif(ctx.inicio_elseif())).getValor());
			if (result != 0) {
				tables.add(new HashMap<>());
				visitCuerpo_inst(ctx.cuerpo_inst());
				tables.remove(tables.size()-1);
				return null;
			} else {
				return visitElseif(ctx.elseif());
			}
		}
	}
	
	@Override
	public T visitR_else(R_elseContext ctx) {
		if (ctx.inicio_else() != null) {
			tables.add(new HashMap<>());
			visitCuerpo_inst(ctx.cuerpo_inst());
			tables.remove(tables.size()-1);
		}
		return null;
	}
	
    /*//////////////////////////////////////////////////////////////////////////
    __   _   _   _   _____    ___  _  _ 
  /' _/ | | | | | | |_   _|  / _/ | || |
  `._`. | 'V' | | |   | |   | \__ | >< |
  |___/ !_/ \_! |_|   |_|    \__/ |_||_|

/////////////////////////////////////////////////////////////////////////*/	
	
	@Override
	public T visitR_switch(R_switchContext ctx) {
		Variable var = (Variable) visitInicio_switch(ctx.inicio_switch());
		Map<String, Object> tempTable = tables.get(tables.size() - 1);

		String nameVar = "-switch";
		tempTable.put(nameVar, var);
		visitR_case(ctx.r_case());
		tempTable.remove(nameVar);
		return null;
	}
	
	@Override
	public T visitR_case(R_caseContext ctx) {
		int valor = (int)((Variable)visitInicio_case(ctx.inicio_case())).getValor();
		Variable temp = (Variable)tables.get(tables.size()-1).get("-switch");
		if(valor == (int)temp.getValor()){
			tables.add(new HashMap<>());
			visitCuerpo_inst(ctx.cuerpo_inst());
			tables.remove(tables.size()-1);
		} else {
			if(ctx.case2() != null){
				return visitCase2(ctx.case2());				
			} 
		}		
		return null;
	}
	
	@Override
	public T visitCase2(Case2Context ctx) {
		if(ctx.r_default() != null){
			return visitR_default(ctx.r_default());
		} else {
			int valor = (int) ((Variable)visitInicio_case(ctx.inicio_case())).getValor();
			Variable temp = (Variable) tables.get(tables.size()-1).get("-switch");
			if(valor == (int) temp.getValor()){
				tables.add(new HashMap<>());
				visitCuerpo_inst(ctx.cuerpo_inst());
				tables.remove(tables.size()-1);
			} else {
				if(ctx.case2() != null){
					return visitCase2(ctx.case2());
				} 
			}		
			return null;			
		}		
	}
	
	@Override
	public T visitR_default(R_defaultContext ctx) {
		tables.add(new HashMap<>());
		visitCuerpo_inst(ctx.cuerpo_inst());
		tables.remove(tables.size()-1);		
		return null;
	}
	/*
		 ___    __    ___ 
		| __|  /__\  | _ \
		| _|  | \/ | | v /
		|_|    \__/  |_|_\
	 */
		
	@Override
	public T visitR_for(R_forContext ctx) {
		tables.add(new HashMap<>());
		
		visitDec_for(ctx.inicio_for().dec_for());		
		Variable expresion = (Variable) visitExpresion(ctx.inicio_for().expresion());
		
		
		if(expresion.getTipo() != Constants.INT){
			String msj = "";
			if(expresion.getTipo() == Constants.DOUBLE){
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
			} else {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
			}
			int line = ctx.inicio_for().expresion().getStart().getLine();
			int col = ctx.inicio_for().expresion().getStart().getCharPositionInLine()+1;
			Error.printError(msj, line, col);
		}
		

		Variable varToIncr = (Variable)visitIdentificador(ctx.inicio_for().IDENTIFICADOR(), null);
		Variable incr = (Variable) visitIncremento(ctx.inicio_for().incremento());		
		while((int)expresion.getValor() != 0){
			hasToBreak = false;
			hasToContinue = false;
			visitCuerpo_loop(ctx.cuerpo_loop());
			if(hasToBreak){
				hasToBreak = false;
				break;
			}
			if(hasToContinue)
				hasToBreak = false;
			int newVal = (int)varToIncr.getValor() + (int)incr.getValor();
			varToIncr.setValor(newVal);
			expresion = (Variable) visitExpresion(ctx.inicio_for().expresion());
		}			
					
		tables.remove(tables.size()-1);
		return null;
	}
	
	@Override
	public T visitIncremento(IncrementoContext ctx) {
		if(ctx.VALOR_ENTERO() != null){
			return (T) new Variable(Constants.INT, Integer.parseInt(ctx.VALOR_ENTERO().getText()));
		} else {
			return (T) new Variable(Constants.INT, 1);
		}
	}
	
	@Override
	public T visitDec_for(Dec_forContext ctx) {
		String nameVar = ctx.IDENTIFICADOR().getText();

		Variable temp = (Variable)valueID(nameVar);
		Variable newValue = (Variable) visitAsig_for(ctx.asig_for());
		
		if(newValue.getTipo() != Constants.INT){
			String msj = "";
			if(newValue.getTipo() == Constants.DOUBLE){
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
			} else {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
			}
			int line = ctx.asig_for().getStart().getLine();
			int col = ctx.asig_for().getStart().getCharPositionInLine()+1;
			Error.printError(msj, line, col);
		}
		
		Map<String, Object> tempTable;
		if (temp == null) { // Si se cumple la variable no existía
			tempTable = tables.get(tables.size() - 1);
			tempTable.put(nameVar, newValue);
		} else {			
			temp.setValor(newValue.getValor());
		}
		
		return (T)newValue;
	}
	
	@Override
	public T visitAsig_for(Asig_forContext ctx) {
		if(ctx.VALOR_ENTERO() != null){
			return (T) new Variable(Constants.INT, Integer.parseInt(ctx.VALOR_ENTERO().getText()));
		} else if(ctx.IDENTIFICADOR() != null){
			return visitIdentificador(ctx.IDENTIFICADOR(), ctx.indice());
		} else {
			return visitExpresion(ctx.expr().expresion());
		}
	}
	
	
	
	@Override
	public T visitInicio_case(Inicio_caseContext ctx) {
		return (T) new Variable(Constants.INT, Integer.parseInt(ctx.VALOR_ENTERO().getText()));
	}

	@Override
	public T visitInicio_switch(Inicio_switchContext ctx) {
		String nameVar = ctx.IDENTIFICADOR().getText();
		Variable indice = (Variable) visitIndice(ctx.indice());
		Variable result;
		result = (Variable)visitIdentificador(ctx.IDENTIFICADOR(), ctx.indice());
		
		// se hace un error de tipo, siempre debe ser INT
		if (result.getTipo() != Constants.INT) { 
			String msj = "";
			if (result.getTipo() == Constants.DOUBLE) {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
			} else {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
			}
			int line = ctx.IDENTIFICADOR().getSymbol().getLine();
			int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine();
			Error.printError(msj, line, col);
			return null;
		} else {
			return (T) result;
		}
	}

	@Override
	public T visitInicio_if(Inicio_ifContext ctx) {
		Variable result = (Variable) visitExpresion(ctx.expresion());

		if (result.getTipo() != Constants.INT) {
			String msj = "";
			if (result.getTipo() == Constants.DOUBLE) {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
			} else if (result.getTipo() == Constants.STRING) {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
			}
			int line = ctx.expresion().getStart().getLine();
			int col = ctx.expresion().getStart().getCharPositionInLine() + 1;
			Error.printError(msj, line, col);
			return null;
		} else {
			int res = (int) result.getValor();
			if (res != 0) {
				return (T) new Variable(Constants.INT, 1);
			} else {
				return (T) new Variable(Constants.INT, 0);
			}
		}
	}

	@Override
	public T visitInicio_elseif(Inicio_elseifContext ctx) {
		Variable result = (Variable) visitExpresion(ctx.expresion());

		if (result.getTipo() != Constants.INT) {
			String msj = "";
			if (result.getTipo() == Constants.DOUBLE) {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
			} else if (result.getTipo() == Constants.STRING) {
				msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
			}
			int line = ctx.expresion().getStart().getLine();
			int col = ctx.expresion().getStart().getCharPositionInLine() + 1;
			Error.printError(msj, line, col);
			return null;
		} else {
			int res = (int) result.getValor();
			if (res != 0) {
				return (T) new Variable(Constants.INT, 1);
			} else {
				return (T) new Variable(Constants.INT, 0);
			}
		}
	}

	
	@Override
	public T visitDeclaracion(DeclaracionContext ctx) {
		String nameVar = ctx.IDENTIFICADOR().getText();
		Variable indice = null;
		if (ctx.indice().val_indice() != null) { // Verifica que haya un indice
			indice = (Variable) visitIndice(ctx.indice());
		}

		Object temp = valueID(nameVar);
		Variable newValue = (Variable) visitAsignacion(ctx.asignacion());
		Map<String, Object> tempTable;
		if (temp == null) { // Si se cumple la variable no existía
			if (indice != null) { // si se cumple se esta creando un nuevo
									// arreglo
				tempTable = tables.get(tables.size() - 1);
				Arreglo newArreglo = new Arreglo();
				newArreglo.insertIndice(indice.getValor(), newValue);
				tempTable.put(nameVar, newArreglo);
			} else { // si no se esta creando una nueva variable
				tempTable = tables.get(tables.size() - 1);
				tempTable.put(nameVar, newValue);
			}
		} else {
			if (indice != null) { // si se cumple se puede actualizar o un nuevo
									// indice
				// si se estan intentando acceder a una que no es arreglo ->
				// ERROR
				if (temp.getClass().getName().equals("business.Variable")) {
					String msj = Error.variableNotArray(nameVar);
					int line = ctx.IDENTIFICADOR().getSymbol().getLine();
					int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine();
					Error.printError(msj, line, col);
					return null;
				}

				Arreglo arr = (Arreglo) temp;
				if (arr.containsKey(indice.getValor())) { // se actualiza valor
															// de indice
					arr.updateIndex(indice.getValor(), newValue);
				} else { // si no se crea un nuevo indice
					arr.insertIndice(indice.getValor(), newValue);
				}
			} else {
				// se cambia a variable si la que existia era arreglo
				if (temp.getClass().getName().equals("business.Arreglo")) {
					tempTable = tables.get(tables.size() - 1);
					tempTable.remove(nameVar);
					tempTable.put(nameVar, newValue);
				} else { // se actualiza el valor de la variable
					Variable cur = (Variable) temp;
					cur.setValor(newValue.getValor());
				}
			}
		}

		return null;
	}

	@Override
	public T visitPuts(PutsContext ctx) {
		Variable val;
		val = (Variable) visitAsignacion(ctx.asignacion());
		System.out.println(val.getValor());
		return null;
	}

	public T visitAsignacion(AsignacionContext ctx) {
		if (ctx.valor() != null) { // Es un valor string, double o int
			return visitValor(ctx.valor());
		} else if (ctx.agrup() != null) { // Es una agrupacion por cor.
											// cuadrados
			return visitAgrup(ctx.agrup());
		} else { // Si no es un identificador
			return visitIdentificador(ctx.IDENTIFICADOR(), ctx.indice());
		}
	}

	public T visitIndice(IndiceContext ctx) {
		if (ctx.val_indice() != null) { // Si se tiene un valor entre los
										// parentesis
			if (ctx.val_indice().valor() != null) { // Si es de tipo valor el
													// indice
				return (T) visitValor(ctx.val_indice().valor());
			} else { // Si no, es de tipo agrupacion
				return (T) visitAgrup(ctx.val_indice().agrup());
			}
		}
		return null;
	}

	public T visitParam_func(Param_funcContext ctx) {
		List<Variable> params = new ArrayList<>();
		if (ctx.aux_param() != null) {
			if (ctx.aux_param().asignacion() != null) {
				params.add((Variable) visitAsignacion(ctx.aux_param().asignacion()));
			} else if (ctx.aux_param().expr() != null) {
				params.add((Variable) visitExpresion(ctx.aux_param().expr().expresion()));

			}
			params.addAll((List<Variable>) visitParam_func(ctx.aux_param().param_func()));

		}
		return (T) params;
	}

	public T visitAgrup(AgrupContext ctx) {
		if (ctx.aux_agrup().expr() != null) { // Si dentro de la agrupacion es
												// expr
			return (T) visitExpresion(ctx.aux_agrup().expr().expresion());
		} else if (ctx.aux_agrup().param_func() != null) { // si dentro de agrup
															// hay llamado a
															// funcion
			String nameFunc = ctx.aux_agrup().IDENTIFICADOR().getText();

			List<Variable> params = (List<Variable>) visitParam_func(ctx.aux_agrup().param_func());
    
			/*
			 * En esta parte hay que verificar primero que el numero de
			 * parametros sea correcto Luego hay que pasar a ejecutar la funcion
			 * y retornar ese valor devuelto
             */
            return (T) new Variable(Constants.INT, 0);
        } else if (ctx.aux_agrup().aux_array() != null) { // Si hay dentro una
            // accion de array
            String command = ctx.aux_agrup().aux_array().getStart().getText();
            String nameId = ctx.aux_agrup().aux_array().IDENTIFICADOR().getText();
            Object temp = valueID(nameId);

            if (command.equals("size")) { // Realiza la accion de 'size'
                if (temp == null) { // Si la variable no existe -> ERROR
                    String msj = Error.variableNotDeclared(nameId);
                    int line = ctx.aux_agrup().aux_array().IDENTIFICADOR().getSymbol().getLine();
                    int col = ctx.aux_agrup().aux_array().IDENTIFICADOR().getSymbol().getCharPositionInLine() + 1;
                    Error.printError(msj, line, col);
                    return null;
                }

                // Si variable no es un arreglo -> ERROR
                if (!temp.getClass().getName().equals("business.Arreglo")) {
                    String msj = Error.variableNotArray(nameId);
                    int line = ctx.aux_agrup().aux_array().IDENTIFICADOR().getSymbol().getLine();
                    int col = ctx.aux_agrup().aux_array().IDENTIFICADOR().getSymbol().getCharPositionInLine() + 1;
                    Error.printError(msj, line, col);
                    return null;
                } else {
                    Arreglo arr = (Arreglo) temp;
                    return (T) new Variable(Constants.INT, arr.getSize());
                }
            } else { // es la accion de 'exists'
                // variable existe y es un arreglo
                if (temp != null && temp.getClass().getName().equals("business.Arreglo")) {
                    return (T) new Variable(Constants.INT, 1);
                } else {
                    return (T) new Variable(Constants.INT, 0);
                }
            }

        } else if (ctx.aux_agrup().gets() != null) { // Se va a gets
            return visitGets(ctx.aux_agrup().gets());
        }
        return null;
    }

    public T visitValor(ValorContext ctx) {
        if (ctx.VALOR_DOUBLE() != null) { // Mira si es un double
        	return (T) new Variable(Constants.DOUBLE, Double.parseDouble(ctx.VALOR_DOUBLE().getText()));
        } else if (ctx.VALOR_ENTERO() != null) { // Mira si es un Entero
            return (T) new Variable(Constants.INT, Integer.parseInt(ctx.VALOR_ENTERO().getText()));
        }

        // Hace la parte del String - Hay que mejorar, funciona regular
        String temp = ctx.VALOR_STRING().getText();
        String[] words = temp.substring(1, temp.length() - 1).split(" ");
        StringBuilder result = new StringBuilder("");
        temp = "";
        Object res;
        int colTemp = 0;
        for (String word : words) {
            if (!word.isEmpty() && word.charAt(0) == '$') {
                temp = word.substring(1);
                res = valueID(temp);
                if (res != null) {
                    Variable val = (Variable) res;
                    result.append(val.getValor().toString() + " ");
                } else {
                    String msj = Error.variableNotDeclared(temp);
                    int line = ctx.VALOR_STRING().getSymbol().getLine();
                    int col = ctx.VALOR_STRING().getSymbol().getCharPositionInLine();
                    col += 2 + colTemp;
                    Error.printError(msj, line, col);
                }
            } else {
                result.append((String) word + " ");
            }
            colTemp += word.length() + 1;
        }
        return (T) new Variable(Constants.STRING, result.toString());
    }

    public T visitGets(GetsContext ctx) {
        Scanner input = new Scanner(System.in);
        String result = input.nextLine();
        input.close();

        if (result.matches("'-'?[0-9]+")) { // si hace match con la regex es un
            // INT
            return (T) new Variable(Constants.INT, Integer.parseInt(result));
        } else if (result.matches("-?[0-9]+.[0-9]+")) { // si no, es un Double
            return (T) new Variable(Constants.DOUBLE, Double.parseDouble(result));
        } else { // si no es un string
            return (T) new Variable(Constants.STRING, result);
        }
    }
    
    /*//////////////////////////////////////////////////////////////////////////
                            _   _   _  _   _   _     ___ 
                           | | | | | || | | | | |   | __|
                           | 'V' | | >< | | | | |_  | _| 
                           !_/ \_! |_||_| |_| |___| |___|
    
     /////////////////////////////////////////////////////////////////////////*/
    
    @Override
    public T visitInicio_while(tclParser.Inicio_whileContext ctx) {
        return null;
    }

    @Override
    public T visitR_while(tclParser.R_whileContext ctx) {
        return null;
    }

    
    
    @Override
    public T visitIf_loop(If_loopContext ctx) {
    	int result = (int) (((Variable) visitInicio_if(ctx.inicio_if())).getValor());
    	if(result != 0){
    		tables.add(new HashMap<>());
    		visitCuerpo_loop(ctx.cuerpo_loop());
    		tables.remove(tables.size()-1);
    	} else {
    		visitElseif_loop(ctx.elseif_loop());
    	}
    	return null;
    }
    
    @Override
    public T visitElseif_loop(Elseif_loopContext ctx) {
    	if(ctx.else_loop() != null){
    		return visitElse_loop(ctx.else_loop());
    	} else {
    		int result = (int) (((Variable) visitInicio_elseif(ctx.inicio_elseif())).getValor());
    		if(result != 0){
    			tables.add(new HashMap<>());
    			visitCuerpo_loop(ctx.cuerpo_loop());
    			tables.remove(tables.size()-1);
    		} else {
    			visitElseif_loop(ctx.elseif_loop());
    		}    		
    	}
    	
    	return null;
    }
    
    @Override
    public T visitCuerpo_loop(Cuerpo_loopContext ctx) {
    	if(hasToBreak || hasToContinue){
    		return null;
    	}
    	
    	if(ctx.r_break() != null){
    		return visitR_break(ctx.r_break());
    	} else if(ctx.r_continue() != null){
    		return visitR_continue(ctx.r_continue());
    	} else {
    		return visitChildren(ctx);
    	}
    	
    }
    
    @Override
    public T visitSwitch_loop(Switch_loopContext ctx) {
		Variable var = (Variable) visitInicio_switch(ctx.inicio_switch());
		Map<String, Object> tempTable = tables.get(tables.size() - 1);

		String nameVar = "-switch";
		tempTable.put(nameVar, var);
		visitCase_loop(ctx.case_loop());
		tempTable.remove(nameVar);
		return null;
    }
    
    @Override
    public T visitCase_loop(Case_loopContext ctx) {
		int valor = (int)((Variable)visitInicio_case(ctx.inicio_case())).getValor();
		Variable temp = (Variable)tables.get(tables.size()-1).get("-switch");
		if(valor == (int)temp.getValor()){
			tables.add(new HashMap<>());
			visitCuerpo_loop(ctx.cuerpo_loop());
			tables.remove(tables.size()-1);
		} else {
			if(ctx.case2_loop() != null){
				return visitCase2_loop(ctx.case2_loop());				
			} 
		}		
		return null;
    }
    
    @Override
    public T visitCase2_loop(Case2_loopContext ctx) {
		if(ctx.default_loop() != null){
			return visitDefault_loop(ctx.default_loop());
		} else {
			int valor = (int) ((Variable)visitInicio_case(ctx.inicio_case())).getValor();
			Variable temp = (Variable) tables.get(tables.size()-1).get("-switch");
			if(valor == (int) temp.getValor()){
				tables.add(new HashMap<>());
				visitCuerpo_loop(ctx.cuerpo_loop());
				tables.remove(tables.size() - 1);
			} else {
				if(ctx.case2_loop() != null){
					return visitCase2_loop(ctx.case2_loop());
				} 
			}		
			return null;			
		}	
    }
    
    @Override
    public T visitDefault_loop(Default_loopContext ctx) {
		tables.add(new HashMap<>());
		visitCuerpo_loop(ctx.cuerpo_loop());
		tables.remove(tables.size()-1);		
		return null;
    }
    
    @Override
    public T visitR_break(R_breakContext ctx) {
    	hasToBreak = true;    	
    	return null;
    }
    
    @Override
    public T visitR_continue(R_continueContext ctx) {
    	hasToContinue = true;
    	return null;
    }
    
    /*//////////////////////////////////////////////////////////////////////////
        ___  __   __  ___   ___   ___    __   _    __    __  _   ___    __ 
       | __| \ \_/ / | _,\ | _ \ | __| /' _/ | |  /__\  |  \| | | __| /' _/
       | _|   > , <  | v_/ | v / | _|  `._`. | | | \/ | | | ' | | _|  `._`.
       |___| /_/ \_\ |_|   |_|_\ |___| |___/ |_|  \__/  |_|\__| |___| |___/
    
     /////////////////////////////////////////////////////////////////////////*/
    
    public T visitExp_or(Exp_orContext ctx) {
        Variable var1, var2 = (Variable) visitExp_and(ctx.exp_and()), var3;
        if (ctx.exp_or() != null) {
            var1 = (Variable) visitExp_or(ctx.exp_or());
            var3 = new Variable(Constants.INT, null);
            if (var1.getTipo() == Constants.INT && var2.getTipo() == Constants.INT) {
                var3.setValor((!((Integer) var1.getValor()).equals(0) || !((Integer) var2.getValor()).equals(0))
                        ? (Object) 1 : (Object) 0);
            } else {
                String tipo;
                int line, col;
                if (var1.getTipo() != Constants.INT) {
                    line = ctx.exp_or().getStart().getLine();
                    col = ctx.exp_or().getStart().getCharPositionInLine();
                    tipo = (var1.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_STRING;
                } else {
                    line = ctx.exp_and().getStart().getLine();
                    col = ctx.exp_and().getStart().getCharPositionInLine();
                    tipo = (var2.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_STRING;
                }
                String msj = Error.incompatibleData(Error.ERR_INT, tipo);
                Error.printError(msj, line, col + 1);
            }
            return (T) var3;
        }
        return (T) var2;
    }

    public T visitExp_and(Exp_andContext ctx) {
        Variable var1, var2 = (Variable) visitExp_ig(ctx.exp_ig()), var3;
        if (ctx.exp_and() != null) {
            var1 = (Variable) visitExp_and(ctx.exp_and());
            var3 = new Variable(Constants.INT, null);
            if (var1.getTipo() == Constants.INT && var2.getTipo() == Constants.INT) {
                var3.setValor((!((Integer) var1.getValor()).equals(0) && !((Integer) var2.getValor()).equals(0))
                        ? (Object) 1 : (Object) 0);
            } else {
                String tipo;
                int line, col;
                if (var1.getTipo() != Constants.INT) {
                    line = ctx.exp_and().getStart().getLine();
                    col = ctx.exp_and().getStart().getCharPositionInLine();
                    tipo = (var1.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_STRING;
                } else {
                    line = ctx.exp_ig().getStart().getLine();
                    col = ctx.exp_ig().getStart().getCharPositionInLine();
                    tipo = (var2.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_STRING;
                }
                String msj = Error.incompatibleData(Error.ERR_INT, tipo);
                Error.printError(msj, line, col + 1);
            }
            return (T) var3;
        }
        return (T) var2;
    }

    public T visitExp_ig(Exp_igContext ctx) {
        Variable var1, var2 = (Variable) visitExp_rel(ctx.exp_rel()), var3;
        if (ctx.exp_ig() != null) {
            var1 = (Variable) visitExp_ig(ctx.exp_ig());

            var3 = new Variable(Constants.INT, null);
            String op = ctx.op_ig().getText();
            if (op.equals("ne") || op.equals("eq")) {
                if (var1.getTipo() == Constants.STRING && var2.getTipo() == Constants.STRING) {
                    if (op.equals("eq")) {
                        var3.setValor((((String) var1.getValor()).equals((String) var2.getValor())) ? (Object) 1
                                : (Object) 0);
                    } else {
                        var3.setValor((((String) var1.getValor()).equals((String) var2.getValor())) ? (Object) 0
                                : (Object) 1);
                    }
                } else {
                    String tipo;
                    int line, col;
                    if (var1.getTipo() != Constants.STRING) {
                        tipo = (var1.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_INT;
                        line = ctx.exp_ig().getStart().getLine();
                        col = ctx.exp_ig().getStart().getCharPositionInLine();
                    } else {
                        tipo = (var2.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_INT;
                        line = ctx.exp_rel().getStart().getLine();
                        col = ctx.exp_rel().getStart().getCharPositionInLine();
                    }
                    String msj = Error.incompatibleData(Error.ERR_STRING, tipo);
                    Error.printError(msj, line, col + 1);
                }
            } else {
                String msj = null;
                if (var1.getTipo() == Constants.STRING || var2.getTipo() == Constants.STRING) {
                    msj = Error.incompatibleData(Error.ERR_INT + ", " + Error.ERR_DOUBLE, Error.ERR_STRING);
                    int line = (var1.getTipo() == Constants.STRING) ? ctx.exp_ig().getStart().getLine()
                            : ctx.exp_rel().getStart().getLine(),
                            col = (var1.getTipo() == Constants.STRING) ? ctx.exp_ig().getStart().getCharPositionInLine()
                            : ctx.exp_rel().getStart().getCharPositionInLine();
                    Error.printError(msj, line, col + 1);
                }
                if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                    if (op.equals("==")) {
                        Double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                                v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                        var3.setValor(((v1).equals(v2)) ? (Object) 1 : (Object) 0);
                    } else {
                        Double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                                v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                        var3.setValor(((v1).equals(v2)) ? (Object) 0 : (Object) 1);
                    }
                } else {
                    if (op.equals("==")) {
                        var3.setValor((((Integer) var1.getValor()).equals((Integer) var2.getValor())) ? (Object) 1
                                : (Object) 0);
                    } else {
                        var3.setValor((((Integer) var1.getValor()).equals((Integer) var2.getValor())) ? (Object) 0
                                : (Object) 1);
                    }
                }
            }
            return (T) var3;
        }
        return (T) var2;
    }

    public T visitExp_rel(Exp_relContext ctx) {
        Variable var1, var2 = (Variable) visitExp_add(ctx.exp_add()), var3;
        if (ctx.exp_rel() != null) {
            var1 = (Variable) visitExp_rel(ctx.exp_rel());
            var3 = new Variable(Constants.INT, null);
            String op = ctx.op_rel().getText();

            if (var1.getTipo() == Constants.STRING && var2.getTipo() != Constants.STRING) {
                String tipo = (var2.getTipo() == Constants.DOUBLE) ? Error.ERR_DOUBLE : Error.ERR_INT;
                int line = ctx.exp_add().getStart().getLine(),
                        col = ctx.exp_add().getStart().getCharPositionInLine();

                String msj = Error.incompatibleData(Error.ERR_STRING, tipo);
                Error.printError(msj, line, col + 1);
            } else if (var2.getTipo() == Constants.STRING && var1.getTipo() != Constants.STRING) {
                int line = ctx.exp_add().getStart().getLine(),
                        col = ctx.exp_add().getStart().getCharPositionInLine();

                String msj = Error.incompatibleData(Error.ERR_INT + ", " + Error.ERR_DOUBLE, Error.ERR_STRING);
                Error.printError(msj, line, col + 1);
            }
            if (op.equals(">=")) {
                if (var1.getTipo() == Constants.STRING && var2.getTipo() == Constants.STRING) {
                    var3.setValor((((String) var1.getValor()).compareTo((String) var2.getValor()) >= 0) ? (Object) 1
                            : (Object) 0);
                } else if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                    double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                            v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                    var3.setValor((v1 >= v2) ? (Object) 1 : (Object) 0);
                } else {
                    var3.setValor(((Integer) var1.getValor() >= (Integer) var2.getValor()) ? (Object) 1 : (Object) 0);
                }
            } else if (op.equals("<=")) {
                if (var1.getTipo() == Constants.STRING && var2.getTipo() == Constants.STRING) {
                    var3.setValor((((String) var1.getValor()).compareTo((String) var2.getValor()) <= 0) ? (Object) 1
                            : (Object) 0);
                } else if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                    double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                            v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                    var3.setValor((v1 <= v2) ? (Object) 1 : (Object) 0);
                } else {
                    var3.setValor(((Integer) var1.getValor() <= (Integer) var2.getValor()) ? (Object) 1 : (Object) 0);
                }
            } else if (op.equals("<")) {
                if (var1.getTipo() == Constants.STRING && var2.getTipo() == Constants.STRING) {
                    var3.setValor((((String) var1.getValor()).compareTo((String) var2.getValor()) < 0) ? (Object) 1
                            : (Object) 0);
                } else if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                    double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                            v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                    var3.setValor((v1 < v2) ? (Object) 1 : (Object) 0);
                } else {
                    var3.setValor(((Integer) var1.getValor() < (Integer) var2.getValor()) ? (Object) 1 : (Object) 0);
                }
            } else if (op.equals(">")) {
                if (var1.getTipo() == Constants.STRING && var2.getTipo() == Constants.STRING) {
                    var3.setValor((((String) var1.getValor()).compareTo((String) var2.getValor()) > 0) ? (Object) 1
                            : (Object) 0);
                } else if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                    double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                            v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                    var3.setValor((v1 > v2) ? (Object) 1 : (Object) 0);
                } else {
                    var3.setValor(((Integer) var1.getValor() > (Integer) var2.getValor()) ? (Object) 1 : (Object) 0);
                }
            }
            return (T) var3;
        }
        return (T) var2;
    }

    public T visitExp_add(Exp_addContext ctx) {
        Variable var1, var2 = (Variable) visitExp_mul(ctx.exp_mul()), var3;
        if (ctx.exp_add() != null) {
            var1 = (Variable) visitExp_add(ctx.exp_add());
            ;

            String msj = null;
            if (var1.getTipo() == Constants.STRING || var2.getTipo() == Constants.STRING) {
                msj = Error.incompatibleData(Error.ERR_INT + ", " + Error.ERR_DOUBLE, Error.ERR_STRING);
            }
            if (msj != null) {
                int line = (var1.getTipo() == Constants.STRING) ? ctx.exp_add().getStart().getLine()
                        : ctx.exp_mul().getStart().getLine(),
                        col = (var1.getTipo() == Constants.STRING) ? ctx.exp_add().getStart().getCharPositionInLine()
                        : ctx.exp_mul().getStart().getCharPositionInLine();
                Error.printError(msj, line, col + 1);
            }

            var3 = new Variable(-1, null);
            char op = ctx.op_add().getText().charAt(0);
            if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                        v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                if (op == '+') {
                    var3.setValor((Object) (v1 + v2));
                } else {
                    var3.setValor((Object) (v1 - v2));
                }
                var3.setTipo(Constants.DOUBLE);
            } else {
                if (op == '+') {
                    var3.setValor((Object) ((Integer) var1.getValor() + (Integer) var2.getValor()));
                } else {
                    var3.setValor((Object) ((Integer) var1.getValor() - (Integer) var2.getValor()));
                }
                var3.setTipo(Constants.INT);
            }
            return (T) var3;
        }
        return (T) var2;
    }

    public T visitExp_mul(Exp_mulContext ctx) {
        Variable var1, var2 = (Variable) visitExp_pot(ctx.exp_pot()), var3;
        if (ctx.exp_mul() != null) {
            var1 = (Variable) visitExp_mul(ctx.exp_mul());
            char op = ctx.op_mul().getText().charAt(0);
            if (op == '%') {
                String msj = null;
                if (var1.getTipo() == Constants.STRING) {
                    msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
                } else if (var1.getTipo() == Constants.DOUBLE) {
                    msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
                } else if (var2.getTipo() == Constants.STRING) {
                    msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING);
                } else if (var2.getTipo() == Constants.DOUBLE) {
                    msj = Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
                }
                if (msj != null) {
                    int line = (var1.getTipo() == Constants.DOUBLE || var1.getTipo() == Constants.STRING)
                            ? ctx.exp_mul().getStart().getLine() : ctx.exp_pot().getStart().getLine(),
                            col = ((var1.getTipo() == Constants.DOUBLE || var1.getTipo() == Constants.STRING)
                            ? ctx.exp_mul().getStart().getCharPositionInLine()
                            : ctx.exp_pot().getStart().getCharPositionInLine());
                    Error.printError(msj, line, col + 1);
                }

                var3 = new Variable(Constants.INT, (Object) ((int) var1.getValor() % (int) var2.getValor()));
            } else {
                String msj = null;
                if (var1.getTipo() == Constants.STRING || var2.getTipo() == Constants.STRING) {
                    msj = Error.incompatibleData(Error.ERR_INT + ", " + Error.ERR_DOUBLE, Error.ERR_STRING);
                }
                if (msj != null) {
                    int line = (var1.getTipo() == Constants.STRING) ? ctx.exp_mul().getStart().getLine()
                            : ctx.exp_pot().getStart().getLine(),
                            col = (var1.getTipo() == Constants.STRING)
                            ? ctx.exp_mul().getStart().getCharPositionInLine()
                            : ctx.exp_pot().getStart().getCharPositionInLine();
                    Error.printError(msj, line, col + 1);
                }

                var3 = new Variable(-1, null);
                if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                    double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor()
                            : (int) var1.getValor()),
                            v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor()
                            : (int) var2.getValor());
                    if (op == '*') {
                        var3.setValor((Object) (v1 * v2));
                    } else {
                        var3.setValor((Object) (v1 / v2));
                    }
                    var3.setTipo(Constants.DOUBLE);
                } else {
                    if (op == '*') {
                        var3.setValor((Object) ((int) var1.getValor() * (int) var2.getValor()));
                    } else {
                        var3.setValor((Object) ((int) var1.getValor() / (int) var2.getValor()));
                    }
                    var3.setTipo(Constants.INT);
                }
            }
            return (T) var3;
        }
        return (T) var2;
    }

    public T visitExp_pot(Exp_potContext ctx) {
        Variable var1, var2 = (Variable) visitExp_una(ctx.exp_una()), var3;
        if (ctx.exp_pot() != null) {
            var1 = (Variable) visitExp_pot(ctx.exp_pot());

            String msj = null;
            if (var1.getTipo() == Constants.STRING || var2.getTipo() == Constants.STRING) {
                msj = Error.incompatibleData(Error.ERR_INT + ", " + Error.ERR_DOUBLE, Error.ERR_STRING);
                int line = (var1.getTipo() == Constants.STRING) ? ctx.exp_pot().getStart().getLine()
                        : ctx.exp_una().getStart().getLine(),
                        col = (var1.getTipo() == Constants.STRING) ? ctx.exp_pot().getStart().getCharPositionInLine()
                        : ctx.exp_una().getStart().getCharPositionInLine();
                Error.printError(msj, line, col + 1);
            }

            var3 = new Variable(-1, null);

            if (var1.getTipo() == Constants.DOUBLE || var2.getTipo() == Constants.DOUBLE) {
                double v1 = ((var1.getTipo() == Constants.DOUBLE) ? (double) var1.getValor() : (int) var1.getValor()),
                        v2 = ((var2.getTipo() == Constants.DOUBLE) ? (double) var2.getValor() : (int) var2.getValor());
                var3.setValor((Object) Math.pow(v1, v2));
                var3.setTipo(Constants.DOUBLE);
            } else {
                var3.setValor((Object) (int) Math.pow((int) var1.getValor(), (int) var2.getValor()));
                var3.setTipo(Constants.INT);
            }
            return (T) var3;
        }
        return (T) var2;

    }

    public T visitExp_una(Exp_unaContext ctx) {
        if (ctx.op_una() != null) {
            char op = ctx.op_una().getText().charAt(0);
            Variable var = (Variable) visitExp_una(ctx.exp_una());
            int line = ctx.exp_una().getStart().getLine(), col = ctx.exp_una().getStart().getCharPositionInLine();
            if (op == '!') {
                if (var.getTipo() != Constants.INT) {
                    String msj = (var.getTipo() == Constants.STRING)
                            ? Error.incompatibleData(Error.ERR_INT, Error.ERR_STRING)
                            : Error.incompatibleData(Error.ERR_INT, Error.ERR_DOUBLE);
                    Error.printError(msj, line, col + 1);
                }
                var.setValor(((Integer) var.getValor() == 0) ? (Object) 1 : (Object) 0);
            } else {
                if (var.getTipo() == Constants.STRING) {
                    String msj = Error.incompatibleData(Error.ERR_INT + ", " + Error.ERR_DOUBLE, Error.ERR_STRING);
                    Error.printError(msj, line, col + 1);
                } else if (var.getTipo() == Constants.INT) {
                    var.setValor((Object) (-(Integer) var.getValor()));
                } else {
                    var.setValor((Object) (-(Double) var.getValor()));
                }
            }
            return (T) var;
        } else {
            return visitTerm(ctx.term());
        }
    }

    public T visitTerm(TermContext ctx) {
        if (ctx.IDENTIFICADOR() != null) {
            return visitIdentificador(ctx.IDENTIFICADOR(), ctx.indice());
        } else if (ctx.agrup() != null) {
            return visitAgrup(ctx.agrup());
        } else if (ctx.valor() != null) {
            return visitValor(ctx.valor());
        } else if (ctx.exp_or() != null) {
            return visitExp_or(ctx.exp_or());
        }
        return null;
    }

    private T visitIdentificador(TerminalNode id_ctx, IndiceContext idx_ctx) {
        String nameVar = id_ctx.getText();
        Variable indice = null;
        if (idx_ctx != null) { // Se mira si existe un indice
            indice = (Variable) visitIndice(idx_ctx);
        }

        Object temp = valueID(nameVar);
        if (temp == null) { // Si se cumple, la cariable no existe
            String msj = Error.variableNotDeclared(nameVar);
            int line = id_ctx.getSymbol().getLine();
            int col = id_ctx.getSymbol().getCharPositionInLine();
            Error.printError(msj, line, col);
            return null;
        } else {
            if (indice != null) { // Si existe algun indice

                // Es una variable y se esta pasando como arreglo -> ERROR
                if (temp.getClass().getName().equals("business.Variable")) {
                    String msj = Error.variableNotArray(nameVar);
                    int line = id_ctx.getSymbol().getLine();
                    int col = id_ctx.getSymbol().getCharPositionInLine();
                    Error.printError(msj, line, col);
                    return null;
                }

                Arreglo arr = (Arreglo) temp;
                // Se mira si el indice existe, si no, es un error
                if (arr.containsKey(indice.getValor())) {
                    return (T) arr.getValue(indice.getValor());
                } else {
                    String msj = Error.arrayWithoutKey(nameVar, indice.valorToString());
                    int line = id_ctx.getSymbol().getLine();
                    int col = id_ctx.getSymbol().getCharPositionInLine();
                    Error.printError(msj, line, col);
                    return null;
                }
            } else {
                // Si se esta llamando a una variable pero es un arreglo ->
                // ERROR
                if (temp.getClass().getName().equals("business.Arreglo")) {
                    String msj = Error.variableIsArray(nameVar);
                    int line = id_ctx.getSymbol().getLine();
                    int col = id_ctx.getSymbol().getCharPositionInLine();
                    Error.printError(msj, line, col);
                    return null;
                } else {
                    return (T) (Variable) temp;
                }
            }
        }
    }

    String getTipo(int tipo) {
        switch (tipo) {
            case Constants.INT:
                return "INTEGER";
            case Constants.DOUBLE:
                return "DOUBLE";
            case Constants.STRING:
                return "STRING";
            default:
                return "Seras mamon";
        }
    }

    /*
	 * Funcion se encarga de mirar si la variable existe en alguna de las tablas
	 * Si no existe retorna null y si existe retorna la Variable que corresponda
     */
    public T valueID(String id) {
        List<Map<String, Object>> ts = tables;
        if (funcActual != null) {
            ts = funcActual.getTables();
        }
        for (Map<String, Object> table : ts) {
            if (table.containsKey(id)) {
                return (T) table.get(id);
            }
        }
        return null;
    }

}

    /*//////////////////////////////////////////////////////////////////////////
                            ___   ___    __     ___
                           | _,\ | _ \  /__\   / _/
                           | v_/ | v / | \/ | | \__
                           |_|   |_|_\  \__/   \__/
    
     /////////////////////////////////////////////////////////////////////////*/