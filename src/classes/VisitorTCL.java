package classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import classes.tclParser.AgrupContext;
import classes.tclParser.AsignacionContext;
import classes.tclParser.Declaracion_funcionContext;
import classes.tclParser.Exp_addContext;
import classes.tclParser.Exp_andContext;
import classes.tclParser.Exp_igContext;
import classes.tclParser.Exp_mulContext;
import classes.tclParser.Exp_orContext;
import classes.tclParser.Exp_potContext;
import classes.tclParser.Exp_relContext;
import classes.tclParser.Exp_unaContext;
import classes.tclParser.GetsContext;
import classes.tclParser.IndiceContext;
import classes.tclParser.Param_funcContext;
import classes.tclParser.PutsContext;
import classes.tclParser.ValorContext;

public class VisitorTCL<T> extends tclBaseVisitor<T> {

    public final static int INT = 0;
    public final static int DOUBLE = 1;
    public final static int STRING = 2;

    ArrayList<Hashtable<String, Object>> tables = new ArrayList<>();
    Hashtable<String, Subrutina> tableFunctions = new Hashtable<>();

    @Override
    public T visitDeclaracion_funcion(Declaracion_funcionContext ctx) {
        if (ctx.IDENTIFICADOR() != null) {

            String nameId = ctx.IDENTIFICADOR().getText();
            if (tableFunctions.containsKey(nameId)) {                         // ERROR, Redefine variable
                int line = ctx.IDENTIFICADOR().getSymbol().getLine();
                int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine() + 1;
                String msj = Error.repeatedFunction(nameId);
                Error.printError(msj, line, col);
                return null;
            } else {                                                        // Define nueva variable
                tableFunctions.put(nameId, new Subrutina(ctx.cuerpo_funcion()));

                /*
				 * Hay que obetener los argumentos, y ponerle esos argumentos a la funcion
                 */
            }
        }
        return super.visitDeclaracion_funcion(ctx);
    }

    @Override
    public T visitPuts(PutsContext ctx) {
        System.out.println(((Variable) visitAsignacion(ctx.asignacion())).valor);
        return null;
    }

    @Override
    public T visitAsignacion(AsignacionContext ctx) {
        if (ctx.valor() != null) {
            return visitValor(ctx.valor());
        } else if (ctx.agrup() != null) {
            return visitAgrup(ctx.agrup());
        }

        String nameVar = ctx.IDENTIFICADOR().getText();
        Variable indice = null;
        boolean flag = false;
        if (ctx.indice() != null) {
            flag = true;
            indice = (Variable) visitIndice(ctx.indice());
        }

        Object temp = valueID(nameVar);
        if (temp == null) {
            String msj = Error.variableNotDeclared(nameVar);
            int line = ctx.IDENTIFICADOR().getSymbol().getLine();
            int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine();
            Error.printError(msj, line, col);
            return null;
        } else {
            if (flag) {
                if (temp.getClass().getName().equals("Variable")) {
                    String msj = Error.variableNotArray(nameVar);
                    int line = ctx.IDENTIFICADOR().getSymbol().getLine();
                    int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine();
                    Error.printError(msj, line, col);
                    return null;
                }

                Arreglo arr = (Arreglo) temp;
                if (arr.containsKey(indice.valor)) {
                    return (T) arr.getValue(indice.getValor());
                } else {
                    String msj = Error.arrayWithoutKey(nameVar, indice.valorToString());
                    int line = ctx.IDENTIFICADOR().getSymbol().getLine();
                    int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine();
                    Error.printError(msj, line, col);
                    return null;
                }
            } else {
                if (temp.getClass().getName().equals("Arreglo")) {
                    String msj = Error.variableIsArray(nameVar);
                    int line = ctx.IDENTIFICADOR().getSymbol().getLine();
                    int col = ctx.IDENTIFICADOR().getSymbol().getCharPositionInLine();
                    Error.printError(msj, line, col);
                    return null;
                } else {
                    return (T) (Variable) temp;
                }
            }
        }
    }

    @Override
    public T visitIndice(IndiceContext ctx) {
        if (ctx.val_indice().valor() != null) {
            return (T) visitValor(ctx.val_indice().valor());
        }
        return (T) visitAgrup(ctx.val_indice().agrup());
    }

    @Override
    public T visitParam_func(Param_funcContext ctx) {
        List<Variable> params = new ArrayList<>();
        if (ctx.aux_param() != null) {                                    // Lectura de parámetros
            if (ctx.aux_param().asignacion() != null) {
                params.add((Variable) visitAsignacion(ctx.aux_param().asignacion()));
            } else if (ctx.aux_param().expr() != null) {
                params.add((Variable) visitExpresion(ctx.aux_param().expr().expresion()));
            }
            params.addAll((List<Variable>) visitParam_func(ctx.aux_param().param_func()));
        }
        return (T) params;
    }

    @Override
    public T visitAgrup(AgrupContext ctx) {
        if (ctx.aux_agrup().expr() != null) {
            return (T) visitExpresion(ctx.aux_agrup().expr().expresion());
        } else if (ctx.aux_agrup().param_func() != null) {
            String nameFunc = ctx.aux_agrup().IDENTIFICADOR().getText();            // Reconocimiento y ejecución de función

            List<Variable> params = (List<Variable>) visitParam_func(ctx.aux_agrup().param_func());

            /*
			 * En esta parte hay que verificar primero que el numero de parametros sea correcto
			 * Luego hay que pasar a ejecutar la funcion y retornar ese valor devuelto
             */
            return (T) new Variable(INT, 0);
        } else if (ctx.aux_agrup().aux_array() != null) {

            /*
			 * Manejar bien lo de los arreglos
             */
            return null;
        } else if (ctx.aux_agrup().gets() != null) {
            return visitGets(ctx.aux_agrup().gets());
        }
        return null;
    }

    @Override
    public T visitValor(ValorContext ctx) {
        if (ctx.VALOR_DOUBLE() != null) {
            return (T) new Variable(DOUBLE, Double.parseDouble(ctx.VALOR_DOUBLE().getText()));
        } else if (ctx.VALOR_ENTERO() != null) {
            return (T) new Variable(INT, Integer.parseInt(ctx.VALOR_ENTERO().getText()));
        }

        String aux = ctx.VALOR_STRING().getText();                      // Lectura de String y reemplazo de ids
        String[] words = aux.substring(1, aux.length() - 2).split(" ");
        StringBuilder result = new StringBuilder("");
        T res;
        int colTemp = 0;
        for (String word : words) {
            if (!word.isEmpty() && word.charAt(0) == '$') {           // Verifica si es id
                aux = word.substring(1);
                res = valueID(aux);
                if (res != null) {
                    result.append(res.toString());
                } else {
                    String msj = Error.variableNotDeclared(aux);
                    int line = ctx.VALOR_STRING().getSymbol().getLine();
                    int col = ctx.VALOR_STRING().getSymbol().getCharPositionInLine();
                    col += 2 + colTemp;
                    Error.printError(msj, line, col);
                }
            } else {
                result.append((String) word);
            }
            result.append(" ");
            colTemp += word.length() + 1;
        }
        result.deleteCharAt(result.length() - 1);
        return (T) new Variable(STRING, result.toString());
    }

    @Override
    public T visitGets(GetsContext ctx) {
        Scanner input = new Scanner(System.in);
        String result = input.nextLine();
        input.close();
        if (result.matches("'-'?[0-9]+")) {
            return (T) new Variable(INT, Integer.parseInt(result));
        } else if (result.matches("-?[0-9]+.[0-9]+")) {
            return (T) new Variable(DOUBLE, Double.parseDouble(result));
        } else {
            return (T) new Variable(STRING, result);
        }
    }

    public T valueID(String id) {
        for (Hashtable<String, Object> table : tables) {
            if (table.containsKey(id)) {
                return (T) table.get(id);
            }
        }
        return null;
    }

    // *********************************************************************** 
    @Override
    public T visitExp_or(Exp_orContext ctx) {
        Variable var1, var2 = (Variable) visitExp_and(ctx.exp_and()), var3;
        if (ctx.exp_or() != null) {
            var1 = (Variable) visitExp_or(ctx.exp_or());
            var3 = new Variable(INT, null);
            if (var1.tipo == INT && var2.tipo == INT) {
                var3.valor = (!((Integer) var1.valor).equals(0) || !((Integer) var2.valor).equals(0)) ? (Object) 1 : (Object) 0;
            } else;
// Error, se esperaba ENTERO                        
            return (T) var3;
        }
        return (T) var2;
    }

    @Override
    public T visitExp_and(Exp_andContext ctx) {
        Variable var1, var2 = (Variable) visitExp_ig(ctx.exp_ig()), var3;
        if (ctx.exp_and() != null) {
            var1 = (Variable) visitExp_and(ctx.exp_and());
            var3 = new Variable(INT, null);
            if (var1.tipo == INT && var2.tipo == INT) {
                var3.valor = (!((Integer) var1.valor).equals(0) && !((Integer) var2.valor).equals(0)) ? (Object) 1 : (Object) 0;
            } else;
// Error, se esperaba ENTERO                        
            return (T) var3;
        }
        return (T) var2;
    }

    @Override
    public T visitExp_ig(Exp_igContext ctx) {
        Variable var1, var2 = (Variable) visitExp_rel(ctx.exp_rel()), var3;
        if (ctx.exp_ig() != null) {
            var1 = (Variable) visitExp_ig(ctx.exp_ig());

            var3 = new Variable(INT, null);
            String op = ctx.OP_IG().getText();
            if (op.equals("ne") || op.equals("eq")) {
                if (var1.tipo == STRING && var2.tipo == STRING) {
                    if (op.equals("eq")) {
                        var3.valor = (((String) var1.valor).equals((String) var2.valor)) ? (Object) 1 : (Object) 0;
                    } else {
                        var3.valor = (((String) var1.valor).equals((String) var2.valor)) ? (Object) 0 : (Object) 1;
                    }
                } else;
// Error, se esperaba STRING
            } else {
                if (var1.tipo == STRING); else if (var2.tipo == STRING); // Error, se esperaba tipo numérico, se encontró STRING
                else if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                    if (op.equals("==")) {
                        var3.valor = (((Double) var1.valor).equals((Double) var2.valor)) ? (Object) 1 : (Object) 0;
                    } else {
                        var3.valor = (((Double) var1.valor).equals((Double) var2.valor)) ? (Object) 0 : (Object) 1;
                    }
                } else if (op.equals("==")) {
                    var3.valor = (((Integer) var1.valor).equals((Integer) var2.valor)) ? (Object) 1 : (Object) 0;
                } else {
                    var3.valor = (((Integer) var1.valor).equals((Integer) var2.valor)) ? (Object) 0 : (Object) 1;
                }
            }
            return (T) var3;
        }
        return (T) var2;
    }

    @Override
    public T visitExp_rel(Exp_relContext ctx) {
        Variable var1, var2 = (Variable) visitExp_add(ctx.exp_add()), var3;
        if (ctx.exp_rel() != null) {
            var1 = (Variable) visitExp_rel(ctx.exp_rel());

            var3 = new Variable(INT, null);
            String op = ctx.OP_REL().getText();
            if (op.equals(">=")) {
                if (var1.tipo == STRING && var2.tipo == STRING) {
                    var3.valor = (0 < ((String) var1.valor).compareTo((String) var2.valor)) ? (Object) 0 : (Object) 1;
                } else if (var1.tipo == STRING); else if (var2.tipo == STRING); // Error, String no se puede comparar con otro tipo de dato
                else if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                    var3.valor = ((Double) var1.valor >= (Double) var2.valor) ? (Object) 1 : (Object) 0;
                } else {
                    var3.valor = ((Integer) var1.valor >= (Integer) var2.valor) ? (Object) 1 : (Object) 0;
                }
            } else if (op.equals("<=")) {
                if (var1.tipo == STRING && var2.tipo == STRING) {
                    var3.valor = (0 > ((String) var1.valor).compareTo((String) var2.valor)) ? (Object) 0 : (Object) 1;
                } else if (var1.tipo == STRING); else if (var2.tipo == STRING); // Error, String no se puede comparar con otro tipo de dato
                else if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                    var3.valor = ((Double) var1.valor <= (Double) var2.valor) ? (Object) 1 : (Object) 0;
                } else {
                    var3.valor = ((Integer) var1.valor <= (Integer) var2.valor) ? (Object) 1 : (Object) 0;
                }
            } else if (op.equals("<")) {
                if (var1.tipo == STRING && var2.tipo == STRING) {
                    var3.valor = (0 < ((String) var1.valor).compareTo((String) var2.valor)) ? (Object) 1 : (Object) 0;
                } else if (var1.tipo == STRING); else if (var2.tipo == STRING); // Error, String no se puede comparar con otro tipo de dato
                else if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                    var3.valor = ((Double) var1.valor < (Double) var2.valor) ? (Object) 1 : (Object) 0;
                } else {
                    var3.valor = ((Integer) var1.valor < (Integer) var2.valor) ? (Object) 1 : (Object) 0;
                }
            } else if (op.equals(">")) {
                if (var1.tipo == STRING && var2.tipo == STRING) {
                    var3.valor = (0 > ((String) var1.valor).compareTo((String) var2.valor)) ? (Object) 1 : (Object) 0;
                } else if (var1.tipo == STRING); else if (var2.tipo == STRING); // Error, String no se puede comparar con otro tipo de dato
                else if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                    var3.valor = ((Double) var1.valor > (Double) var2.valor) ? (Object) 1 : (Object) 0;
                } else {
                    var3.valor = ((Integer) var1.valor > (Integer) var2.valor) ? (Object) 1 : (Object) 0;
                }
            }
            return (T) var3;
        }
        return (T) var2;
    }

    @Override
    public T visitExp_add(Exp_addContext ctx) {
        Variable var1, var2 = (Variable) visitExp_mul(ctx.exp_mul()), var3;
        if (ctx.exp_add() != null) {
            var1 = (Variable) visitExp_add(ctx.exp_add());;

//  Error, se encontró STRING, se esperaba valor numérico
            if (var1.tipo == STRING); else if (var2.tipo == STRING);

            var3 = new Variable(-1, null);
            char op = ctx.OP_ADD().getText().charAt(0);
            if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                if (op == '+') {
                    var3.valor = (Object) ((Double) var1.valor + (Double) var2.valor);
                } else {
                    var3.valor = (Object) ((Double) var1.valor - (Double) var2.valor);
                }
                var3.tipo = DOUBLE;
            } else {
                if (op == '+') {
                    var3.valor = (Object) ((Integer) var1.valor + (Integer) var2.valor);
                } else {
                    var3.valor = (Object) ((Integer) var1.valor - (Integer) var2.valor);
                }
                var3.tipo = INT;
            }
            return (T) var3;
        }
        return (T) var2;
    }

    @Override
    public T visitExp_mul(Exp_mulContext ctx) {
        Variable var1, var2 = (Variable) visitExp_pot(ctx.exp_pot()), var3;
        if (ctx.exp_mul() != null) {
            var1 = (Variable) visitExp_mul(ctx.exp_mul());

            char op = ctx.OP_MUL().getText().charAt(0);
            if (op == '%') {
                if (var1.tipo != INT); else if (var2.tipo == INT);
//  Error, se esperaba valor entero
                var3 = new Variable(INT, (Object) ((Integer) var1.valor % (Integer) var2.valor));
            } else {
                if (var1.tipo == STRING); else if (var2.tipo == STRING);
//  Error, se esperaba valor numérico se encontró STRING
                var3 = new Variable(-1, null);

                if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                    if (op == '*') {
                        var3.valor = (Object) ((Double) var1.valor * (Double) var2.valor);
                    } else {
                        var3.valor = (Object) ((Double) var1.valor / (Double) var2.valor);
                    }
                    var3.tipo = DOUBLE;
                } else {
                    if (op == '/') {
                        var3.valor = (Object) ((Integer) var1.valor * (Integer) var2.valor);
                    } else {
                        var3.valor = (Object) ((Integer) var1.valor / (Integer) var2.valor);
                    }
                    var3.tipo = INT;
                }
            }
            return (T) var3;
        }
        return (T) var2;
    }

    @Override
    public T visitExp_pot(Exp_potContext ctx) {
        Variable var1, var2 = (Variable) visitExp_una(ctx.exp_una()), var3;
        if (ctx.exp_pot() != null) {
            var1 = (Variable) visitExp_pot(ctx.exp_pot());

            if (var1.tipo == STRING); else if (var2.tipo == STRING);
//  Error se esperaba valor numérico

            var3 = new Variable(-1, null);

            if (var1.tipo == DOUBLE || var2.tipo == DOUBLE) {
                var3.valor = (Object) Math.pow((Double) var1.valor, (Double) var2.valor);
                var3.tipo = DOUBLE;
            } else {
                var3.valor = (Object) (Integer) (int) Math.pow((Integer) var1.valor, (Integer) var2.valor);
                var3.tipo = INT;
            }
            return (T) var3;
        }
        return (T) var2;

    }

    @Override
    public T visitExp_una(Exp_unaContext ctx) {
        if (ctx.OP_UNA() != null) {
            char op = ctx.OP_UNA().getText().charAt(0);
            Variable var = (Variable) visitExp_una(ctx.exp_una());
            if (op == '!') {
                if (var.tipo != INT);
//  Error se esperaba valor entero
                var.valor = ((Integer) var.valor == 0) ? (Object) 1 : (Object) 0;
            } else {
                if (var.tipo == STRING);
//  Error se esperaba valor numérico se encontró STRING
                var.valor = (Object) ((Integer) var.valor);
            }
            return (T) var;
        } else {
            return visitTerm(ctx.term());
        }
    }

    String getTipo(int tipo) {
        switch (tipo) {
            case INT:
                return "INTEGER";
            case DOUBLE:
                return "DOUBLE";
            case STRING:
                return "STRING";
            default:
                return "Serás mamón";
        }
    }
}
