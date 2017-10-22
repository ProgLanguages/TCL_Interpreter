// Generated from tcl.g4 by ANTLR 4.7
package classes;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link tclParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface tclVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio(tclParser.InicioContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#declaracion_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_funcion(tclParser.Declaracion_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#args_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs_funcion(tclParser.Args_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#cuerpo_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpo_funcion(tclParser.Cuerpo_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#if_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_funcion(tclParser.If_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#elseif_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseif_funcion(tclParser.Elseif_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#else_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_funcion(tclParser.Else_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#switch_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitch_funcion(tclParser.Switch_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_funcion(tclParser.Case_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case2_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase2_funcion(tclParser.Case2_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#default_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault_funcion(tclParser.Default_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#for_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_funcion(tclParser.For_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#while_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_funcion(tclParser.While_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#cuerpo_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpo_loop_func(tclParser.Cuerpo_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#if_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_loop_func(tclParser.If_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#elseif_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseif_loop_func(tclParser.Elseif_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#else_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_loop_func(tclParser.Else_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#switch_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitch_loop_func(tclParser.Switch_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_loop_func(tclParser.Case_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case2_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase2_loop_func(tclParser.Case2_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#default_loop_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault_loop_func(tclParser.Default_loop_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#modulo_ppal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModulo_ppal(tclParser.Modulo_ppalContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_if(tclParser.R_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#elseif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseif(tclParser.ElseifContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_else(tclParser.R_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_switch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_switch(tclParser.R_switchContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_case}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_case(tclParser.R_caseContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase2(tclParser.Case2Context ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_default}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_default(tclParser.R_defaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#cuerpo_inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpo_inst(tclParser.Cuerpo_instContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_for(tclParser.R_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_while(tclParser.R_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#cuerpo_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpo_loop(tclParser.Cuerpo_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#if_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_loop(tclParser.If_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#elseif_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseif_loop(tclParser.Elseif_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#else_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_loop(tclParser.Else_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#switch_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitch_loop(tclParser.Switch_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_loop(tclParser.Case_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#case2_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase2_loop(tclParser.Case2_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#default_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault_loop(tclParser.Default_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(tclParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#gets}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGets(tclParser.GetsContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#puts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPuts(tclParser.PutsContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#agrup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgrup(tclParser.AgrupContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#aux_agrup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAux_agrup(tclParser.Aux_agrupContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#aux_array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAux_array(tclParser.Aux_arrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#param_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_func(tclParser.Param_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#aux_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAux_param(tclParser.Aux_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(tclParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#indice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndice(tclParser.IndiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#val_indice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVal_indice(tclParser.Val_indiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#valor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor(tclParser.ValorContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#incremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncremento(tclParser.IncrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_break}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_break(tclParser.R_breakContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_continue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_continue(tclParser.R_continueContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR_return(tclParser.R_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#return_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_loop(tclParser.Return_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#r2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR2(tclParser.R2Context ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(tclParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#asig_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsig_for(tclParser.Asig_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_if(tclParser.Inicio_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_elseif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_elseif(tclParser.Inicio_elseifContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_else(tclParser.Inicio_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_for(tclParser.Inicio_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_while(tclParser.Inicio_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_switch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_switch(tclParser.Inicio_switchContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_case}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_case(tclParser.Inicio_caseContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#inicio_default}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicio_default(tclParser.Inicio_defaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(tclParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_or(tclParser.Exp_orContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_and(tclParser.Exp_andContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_ig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_ig(tclParser.Exp_igContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_rel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_rel(tclParser.Exp_relContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_add(tclParser.Exp_addContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_mul(tclParser.Exp_mulContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_pot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_pot(tclParser.Exp_potContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#exp_una}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_una(tclParser.Exp_unaContext ctx);
	/**
	 * Visit a parse tree produced by {@link tclParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(tclParser.TermContext ctx);
}