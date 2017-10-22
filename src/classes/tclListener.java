// Generated from tcl.g4 by ANTLR 4.7
package classes;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link tclParser}.
 */
public interface tclListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio}.
	 * @param ctx the parse tree
	 */
	void enterInicio(tclParser.InicioContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio}.
	 * @param ctx the parse tree
	 */
	void exitInicio(tclParser.InicioContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#declaracion_funcion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_funcion(tclParser.Declaracion_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#declaracion_funcion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_funcion(tclParser.Declaracion_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#args_funcion}.
	 * @param ctx the parse tree
	 */
	void enterArgs_funcion(tclParser.Args_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#args_funcion}.
	 * @param ctx the parse tree
	 */
	void exitArgs_funcion(tclParser.Args_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#cuerpo_funcion}.
	 * @param ctx the parse tree
	 */
	void enterCuerpo_funcion(tclParser.Cuerpo_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#cuerpo_funcion}.
	 * @param ctx the parse tree
	 */
	void exitCuerpo_funcion(tclParser.Cuerpo_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#if_funcion}.
	 * @param ctx the parse tree
	 */
	void enterIf_funcion(tclParser.If_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#if_funcion}.
	 * @param ctx the parse tree
	 */
	void exitIf_funcion(tclParser.If_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#elseif_funcion}.
	 * @param ctx the parse tree
	 */
	void enterElseif_funcion(tclParser.Elseif_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#elseif_funcion}.
	 * @param ctx the parse tree
	 */
	void exitElseif_funcion(tclParser.Elseif_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#else_funcion}.
	 * @param ctx the parse tree
	 */
	void enterElse_funcion(tclParser.Else_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#else_funcion}.
	 * @param ctx the parse tree
	 */
	void exitElse_funcion(tclParser.Else_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#switch_funcion}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_funcion(tclParser.Switch_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#switch_funcion}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_funcion(tclParser.Switch_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case_funcion}.
	 * @param ctx the parse tree
	 */
	void enterCase_funcion(tclParser.Case_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case_funcion}.
	 * @param ctx the parse tree
	 */
	void exitCase_funcion(tclParser.Case_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case2_funcion}.
	 * @param ctx the parse tree
	 */
	void enterCase2_funcion(tclParser.Case2_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case2_funcion}.
	 * @param ctx the parse tree
	 */
	void exitCase2_funcion(tclParser.Case2_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#default_funcion}.
	 * @param ctx the parse tree
	 */
	void enterDefault_funcion(tclParser.Default_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#default_funcion}.
	 * @param ctx the parse tree
	 */
	void exitDefault_funcion(tclParser.Default_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#for_funcion}.
	 * @param ctx the parse tree
	 */
	void enterFor_funcion(tclParser.For_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#for_funcion}.
	 * @param ctx the parse tree
	 */
	void exitFor_funcion(tclParser.For_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#while_funcion}.
	 * @param ctx the parse tree
	 */
	void enterWhile_funcion(tclParser.While_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#while_funcion}.
	 * @param ctx the parse tree
	 */
	void exitWhile_funcion(tclParser.While_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#cuerpo_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterCuerpo_loop_func(tclParser.Cuerpo_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#cuerpo_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitCuerpo_loop_func(tclParser.Cuerpo_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#if_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterIf_loop_func(tclParser.If_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#if_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitIf_loop_func(tclParser.If_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#elseif_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterElseif_loop_func(tclParser.Elseif_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#elseif_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitElseif_loop_func(tclParser.Elseif_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#else_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterElse_loop_func(tclParser.Else_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#else_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitElse_loop_func(tclParser.Else_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#switch_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_loop_func(tclParser.Switch_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#switch_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_loop_func(tclParser.Switch_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterCase_loop_func(tclParser.Case_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitCase_loop_func(tclParser.Case_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case2_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterCase2_loop_func(tclParser.Case2_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case2_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitCase2_loop_func(tclParser.Case2_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#default_loop_func}.
	 * @param ctx the parse tree
	 */
	void enterDefault_loop_func(tclParser.Default_loop_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#default_loop_func}.
	 * @param ctx the parse tree
	 */
	void exitDefault_loop_func(tclParser.Default_loop_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#modulo_ppal}.
	 * @param ctx the parse tree
	 */
	void enterModulo_ppal(tclParser.Modulo_ppalContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#modulo_ppal}.
	 * @param ctx the parse tree
	 */
	void exitModulo_ppal(tclParser.Modulo_ppalContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_if}.
	 * @param ctx the parse tree
	 */
	void enterR_if(tclParser.R_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_if}.
	 * @param ctx the parse tree
	 */
	void exitR_if(tclParser.R_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#elseif}.
	 * @param ctx the parse tree
	 */
	void enterElseif(tclParser.ElseifContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#elseif}.
	 * @param ctx the parse tree
	 */
	void exitElseif(tclParser.ElseifContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_else}.
	 * @param ctx the parse tree
	 */
	void enterR_else(tclParser.R_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_else}.
	 * @param ctx the parse tree
	 */
	void exitR_else(tclParser.R_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_switch}.
	 * @param ctx the parse tree
	 */
	void enterR_switch(tclParser.R_switchContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_switch}.
	 * @param ctx the parse tree
	 */
	void exitR_switch(tclParser.R_switchContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_case}.
	 * @param ctx the parse tree
	 */
	void enterR_case(tclParser.R_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_case}.
	 * @param ctx the parse tree
	 */
	void exitR_case(tclParser.R_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case2}.
	 * @param ctx the parse tree
	 */
	void enterCase2(tclParser.Case2Context ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case2}.
	 * @param ctx the parse tree
	 */
	void exitCase2(tclParser.Case2Context ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_default}.
	 * @param ctx the parse tree
	 */
	void enterR_default(tclParser.R_defaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_default}.
	 * @param ctx the parse tree
	 */
	void exitR_default(tclParser.R_defaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#cuerpo_inst}.
	 * @param ctx the parse tree
	 */
	void enterCuerpo_inst(tclParser.Cuerpo_instContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#cuerpo_inst}.
	 * @param ctx the parse tree
	 */
	void exitCuerpo_inst(tclParser.Cuerpo_instContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_for}.
	 * @param ctx the parse tree
	 */
	void enterR_for(tclParser.R_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_for}.
	 * @param ctx the parse tree
	 */
	void exitR_for(tclParser.R_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_while}.
	 * @param ctx the parse tree
	 */
	void enterR_while(tclParser.R_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_while}.
	 * @param ctx the parse tree
	 */
	void exitR_while(tclParser.R_whileContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#cuerpo_loop}.
	 * @param ctx the parse tree
	 */
	void enterCuerpo_loop(tclParser.Cuerpo_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#cuerpo_loop}.
	 * @param ctx the parse tree
	 */
	void exitCuerpo_loop(tclParser.Cuerpo_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#if_loop}.
	 * @param ctx the parse tree
	 */
	void enterIf_loop(tclParser.If_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#if_loop}.
	 * @param ctx the parse tree
	 */
	void exitIf_loop(tclParser.If_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#elseif_loop}.
	 * @param ctx the parse tree
	 */
	void enterElseif_loop(tclParser.Elseif_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#elseif_loop}.
	 * @param ctx the parse tree
	 */
	void exitElseif_loop(tclParser.Elseif_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#else_loop}.
	 * @param ctx the parse tree
	 */
	void enterElse_loop(tclParser.Else_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#else_loop}.
	 * @param ctx the parse tree
	 */
	void exitElse_loop(tclParser.Else_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#switch_loop}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_loop(tclParser.Switch_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#switch_loop}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_loop(tclParser.Switch_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case_loop}.
	 * @param ctx the parse tree
	 */
	void enterCase_loop(tclParser.Case_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case_loop}.
	 * @param ctx the parse tree
	 */
	void exitCase_loop(tclParser.Case_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#case2_loop}.
	 * @param ctx the parse tree
	 */
	void enterCase2_loop(tclParser.Case2_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#case2_loop}.
	 * @param ctx the parse tree
	 */
	void exitCase2_loop(tclParser.Case2_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#default_loop}.
	 * @param ctx the parse tree
	 */
	void enterDefault_loop(tclParser.Default_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#default_loop}.
	 * @param ctx the parse tree
	 */
	void exitDefault_loop(tclParser.Default_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#puts}.
	 * @param ctx the parse tree
	 */
	void enterPuts(tclParser.PutsContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#puts}.
	 * @param ctx the parse tree
	 */
	void exitPuts(tclParser.PutsContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#gets}.
	 * @param ctx the parse tree
	 */
	void enterGets(tclParser.GetsContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#gets}.
	 * @param ctx the parse tree
	 */
	void exitGets(tclParser.GetsContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(tclParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(tclParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#agrup}.
	 * @param ctx the parse tree
	 */
	void enterAgrup(tclParser.AgrupContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#agrup}.
	 * @param ctx the parse tree
	 */
	void exitAgrup(tclParser.AgrupContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#aux_agrup}.
	 * @param ctx the parse tree
	 */
	void enterAux_agrup(tclParser.Aux_agrupContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#aux_agrup}.
	 * @param ctx the parse tree
	 */
	void exitAux_agrup(tclParser.Aux_agrupContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#aux_array}.
	 * @param ctx the parse tree
	 */
	void enterAux_array(tclParser.Aux_arrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#aux_array}.
	 * @param ctx the parse tree
	 */
	void exitAux_array(tclParser.Aux_arrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#param_func}.
	 * @param ctx the parse tree
	 */
	void enterParam_func(tclParser.Param_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#param_func}.
	 * @param ctx the parse tree
	 */
	void exitParam_func(tclParser.Param_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#aux_param}.
	 * @param ctx the parse tree
	 */
	void enterAux_param(tclParser.Aux_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#aux_param}.
	 * @param ctx the parse tree
	 */
	void exitAux_param(tclParser.Aux_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(tclParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(tclParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#indice}.
	 * @param ctx the parse tree
	 */
	void enterIndice(tclParser.IndiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#indice}.
	 * @param ctx the parse tree
	 */
	void exitIndice(tclParser.IndiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#val_indice}.
	 * @param ctx the parse tree
	 */
	void enterVal_indice(tclParser.Val_indiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#val_indice}.
	 * @param ctx the parse tree
	 */
	void exitVal_indice(tclParser.Val_indiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#valor}.
	 * @param ctx the parse tree
	 */
	void enterValor(tclParser.ValorContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#valor}.
	 * @param ctx the parse tree
	 */
	void exitValor(tclParser.ValorContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#incremento}.
	 * @param ctx the parse tree
	 */
	void enterIncremento(tclParser.IncrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#incremento}.
	 * @param ctx the parse tree
	 */
	void exitIncremento(tclParser.IncrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_break}.
	 * @param ctx the parse tree
	 */
	void enterR_break(tclParser.R_breakContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_break}.
	 * @param ctx the parse tree
	 */
	void exitR_break(tclParser.R_breakContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_continue}.
	 * @param ctx the parse tree
	 */
	void enterR_continue(tclParser.R_continueContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_continue}.
	 * @param ctx the parse tree
	 */
	void exitR_continue(tclParser.R_continueContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r_return}.
	 * @param ctx the parse tree
	 */
	void enterR_return(tclParser.R_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r_return}.
	 * @param ctx the parse tree
	 */
	void exitR_return(tclParser.R_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#return_loop}.
	 * @param ctx the parse tree
	 */
	void enterReturn_loop(tclParser.Return_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#return_loop}.
	 * @param ctx the parse tree
	 */
	void exitReturn_loop(tclParser.Return_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#r2}.
	 * @param ctx the parse tree
	 */
	void enterR2(tclParser.R2Context ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#r2}.
	 * @param ctx the parse tree
	 */
	void exitR2(tclParser.R2Context ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(tclParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(tclParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#asig_for}.
	 * @param ctx the parse tree
	 */
	void enterAsig_for(tclParser.Asig_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#asig_for}.
	 * @param ctx the parse tree
	 */
	void exitAsig_for(tclParser.Asig_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_if}.
	 * @param ctx the parse tree
	 */
	void enterInicio_if(tclParser.Inicio_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_if}.
	 * @param ctx the parse tree
	 */
	void exitInicio_if(tclParser.Inicio_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_elseif}.
	 * @param ctx the parse tree
	 */
	void enterInicio_elseif(tclParser.Inicio_elseifContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_elseif}.
	 * @param ctx the parse tree
	 */
	void exitInicio_elseif(tclParser.Inicio_elseifContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_else}.
	 * @param ctx the parse tree
	 */
	void enterInicio_else(tclParser.Inicio_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_else}.
	 * @param ctx the parse tree
	 */
	void exitInicio_else(tclParser.Inicio_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_switch}.
	 * @param ctx the parse tree
	 */
	void enterInicio_switch(tclParser.Inicio_switchContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_switch}.
	 * @param ctx the parse tree
	 */
	void exitInicio_switch(tclParser.Inicio_switchContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_case}.
	 * @param ctx the parse tree
	 */
	void enterInicio_case(tclParser.Inicio_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_case}.
	 * @param ctx the parse tree
	 */
	void exitInicio_case(tclParser.Inicio_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_default}.
	 * @param ctx the parse tree
	 */
	void enterInicio_default(tclParser.Inicio_defaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_default}.
	 * @param ctx the parse tree
	 */
	void exitInicio_default(tclParser.Inicio_defaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_for}.
	 * @param ctx the parse tree
	 */
	void enterInicio_for(tclParser.Inicio_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_for}.
	 * @param ctx the parse tree
	 */
	void exitInicio_for(tclParser.Inicio_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#dec_for}.
	 * @param ctx the parse tree
	 */
	void enterDec_for(tclParser.Dec_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#dec_for}.
	 * @param ctx the parse tree
	 */
	void exitDec_for(tclParser.Dec_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#inicio_while}.
	 * @param ctx the parse tree
	 */
	void enterInicio_while(tclParser.Inicio_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#inicio_while}.
	 * @param ctx the parse tree
	 */
	void exitInicio_while(tclParser.Inicio_whileContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(tclParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(tclParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_or}.
	 * @param ctx the parse tree
	 */
	void enterExp_or(tclParser.Exp_orContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_or}.
	 * @param ctx the parse tree
	 */
	void exitExp_or(tclParser.Exp_orContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_and}.
	 * @param ctx the parse tree
	 */
	void enterExp_and(tclParser.Exp_andContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_and}.
	 * @param ctx the parse tree
	 */
	void exitExp_and(tclParser.Exp_andContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_ig}.
	 * @param ctx the parse tree
	 */
	void enterExp_ig(tclParser.Exp_igContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_ig}.
	 * @param ctx the parse tree
	 */
	void exitExp_ig(tclParser.Exp_igContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_rel}.
	 * @param ctx the parse tree
	 */
	void enterExp_rel(tclParser.Exp_relContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_rel}.
	 * @param ctx the parse tree
	 */
	void exitExp_rel(tclParser.Exp_relContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_add}.
	 * @param ctx the parse tree
	 */
	void enterExp_add(tclParser.Exp_addContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_add}.
	 * @param ctx the parse tree
	 */
	void exitExp_add(tclParser.Exp_addContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_mul}.
	 * @param ctx the parse tree
	 */
	void enterExp_mul(tclParser.Exp_mulContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_mul}.
	 * @param ctx the parse tree
	 */
	void exitExp_mul(tclParser.Exp_mulContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_pot}.
	 * @param ctx the parse tree
	 */
	void enterExp_pot(tclParser.Exp_potContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_pot}.
	 * @param ctx the parse tree
	 */
	void exitExp_pot(tclParser.Exp_potContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#exp_una}.
	 * @param ctx the parse tree
	 */
	void enterExp_una(tclParser.Exp_unaContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#exp_una}.
	 * @param ctx the parse tree
	 */
	void exitExp_una(tclParser.Exp_unaContext ctx);
	/**
	 * Enter a parse tree produced by {@link tclParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(tclParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link tclParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(tclParser.TermContext ctx);
}