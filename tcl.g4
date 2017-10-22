grammar tcl;

inicio	:	declaracion_funcion modulo_ppal ;

declaracion_funcion	:	'proc' IDENTIFICADOR '{' args_funcion '}' '{' cuerpo_funcion '}' declaracion_funcion |  ;
args_funcion	:	'{' IDENTIFICADOR '}' args_funcion |  ;

cuerpo_funcion	:	declaracion cuerpo_funcion | puts cuerpo_funcion | gets ';' cuerpo_funcion | if_funcion cuerpo_funcion 
				| for_funcion cuerpo_funcion | while_funcion cuerpo_funcion | switch_funcion cuerpo_funcion 
				| r_return cuerpo_funcion | agrup ';' cuerpo_funcion |  ;

if_funcion	:	inicio_if cuerpo_funcion '}' elseif_funcion  ;
elseif_funcion	:	inicio_elseif cuerpo_funcion '}' elseif_funcion | else_funcion  ;
else_funcion	:	inicio_else cuerpo_funcion '}' |  ;

switch_funcion	:	inicio_switch case_funcion '}'  ;
case_funcion	:	inicio_case cuerpo_funcion '}' case2_funcion  ;
case2_funcion	:	inicio_case cuerpo_funcion '}' case2_funcion | default_funcion  ;
default_funcion	:	inicio_default cuerpo_funcion '}' |  ;

for_funcion	:	inicio_for cuerpo_loop_func '}'  ;
while_funcion	:	inicio_while cuerpo_loop_func '}'  ;

cuerpo_loop_func	:	r_break cuerpo_loop_func | r_continue cuerpo_loop_func | declaracion cuerpo_loop_func 
					| gets ';' cuerpo_loop_func | puts cuerpo_loop_func | return_loop cuerpo_loop_func 
					| if_loop_func cuerpo_loop_func | switch_loop_func cuerpo_loop_func | for_funcion cuerpo_loop_func 
					| while_funcion cuerpo_loop_func | agrup ';' cuerpo_loop_func |  ;

if_loop_func	:	inicio_if cuerpo_loop_func '}' elseif_loop_func  ;
elseif_loop_func	:	inicio_elseif cuerpo_loop_func '}' elseif_loop_func | else_loop_func  ;
else_loop_func	:	inicio_else cuerpo_loop_func '}' |  ;

switch_loop_func	:	inicio_switch case_loop_func '}'  ;
case_loop_func	:	inicio_case cuerpo_loop_func '}' case2_loop_func  ;
case2_loop_func	:	inicio_case cuerpo_loop_func '}' case2_loop_func | default_loop_func  ;
default_loop_func	:	inicio_default cuerpo_loop_func '}' |  ;

modulo_ppal	:	declaracion modulo_ppal | r_for modulo_ppal | gets ';' modulo_ppal | r_if modulo_ppal 
			| puts modulo_ppal | r_while modulo_ppal | r_switch modulo_ppal | agrup ';' modulo_ppal |  ;

r_if	:	inicio_if cuerpo_inst '}' elseif  ;
elseif	:	inicio_elseif cuerpo_inst '}' elseif | r_else  ;
r_else	:	inicio_else cuerpo_inst '}' |  ;

r_switch	:	inicio_switch r_case '}'  ;
r_case	:	inicio_case cuerpo_inst '}' case2  ;
case2	:	inicio_case cuerpo_inst '}' case2 | r_default  ;
r_default	:	inicio_default cuerpo_inst '}' |  ;

cuerpo_inst	:	declaracion cuerpo_inst | r_for cuerpo_inst | gets ';' cuerpo_inst | r_if cuerpo_inst 
			| puts cuerpo_inst | r_while cuerpo_inst | r_switch cuerpo_inst | agrup ';' cuerpo_inst |  ;
			
r_for	:	inicio_for cuerpo_loop '}'  ;
r_while	:	inicio_while cuerpo_loop '}'  ;
cuerpo_loop	:	declaracion cuerpo_loop | r_for cuerpo_loop | gets ';' cuerpo_loop | if_loop cuerpo_loop 
			| puts cuerpo_loop | r_while cuerpo_loop | switch_loop cuerpo_loop | r_break cuerpo_loop 
			| r_continue cuerpo_loop | agrup ';' cuerpo_loop |  ;
			
if_loop	:	inicio_if cuerpo_loop '}' elseif_loop  ;
elseif_loop	:	inicio_elseif cuerpo_loop '}' elseif_loop | else_loop  ;
else_loop	:	inicio_else cuerpo_loop '}' |  ;

switch_loop	:	inicio_switch case_loop '}'  ;
case_loop	:	inicio_case cuerpo_loop '}' case2_loop  ;
case2_loop	:	inicio_case cuerpo_loop '}' case2_loop | default_loop  ;
default_loop	:	inicio_default cuerpo_loop '}' |  ;

declaracion	:	'set' IDENTIFICADOR indice asignacion ';'  ;
gets	:	'gets' 'stdin'  ;
puts	:	'puts' asignacion ';'  ;

agrup	:	'[' aux_agrup  ;
aux_agrup	:	expr ']' | IDENTIFICADOR param_func ']' | gets ']' | 'array' aux_array  ;
aux_array	:	'size' IDENTIFICADOR ']' | 'exists' IDENTIFICADOR ']'  ;

param_func	:	'{' aux_param |  ;
aux_param	:	asignacion '}' param_func | expr '}' param_func  ;

asignacion	:	valor | '$' IDENTIFICADOR indice | agrup  ;

indice	:	'(' val_indice ')' |  ;
val_indice	:	valor | agrup  ;

valor	:	VALOR_STRING | VALOR_ENTERO | VALOR_DOUBLE  ;

incremento	:	VALOR_ENTERO |  ;

r_break	:	'break' ';'  ;
r_continue	:	'continue' ';'  ;
r_return	:	'return' r2 ';'  ;
return_loop	:	'return' r2 ';'  ;
r2	:	asignacion |  ;

expr	:	'expr' '{' expresion '}'  ;
asig_for	:	VALOR_ENTERO | '$' IDENTIFICADOR indice | expr  ;

inicio_if	:	'if' '{' expresion '}' 'then' '{'  ;
inicio_elseif	:	'elseif' '{' expresion '}' 'then' '{'  ;
inicio_else	:	'else' '{'  ;
inicio_for	:	'for' '{' 'set' IDENTIFICADOR asig_for '}' '{' expresion '}' '{' 'incr' IDENTIFICADOR incremento '}' '{'  ;
inicio_while	:	'while' '{' expresion '}' '{'  ;
inicio_switch	:	'switch' '$' IDENTIFICADOR indice '{'  ;
inicio_case	:	'case' VALOR_ENTERO '{'  ;
inicio_default	:	'default' '{'  ;

expresion	:	exp_or  ;
exp_or	:	exp_or '||' exp_and | exp_and  ;
exp_and	:	exp_and '&&' exp_ig | exp_ig  ;
exp_ig	:	exp_ig OP_IG exp_rel | exp_rel  ;
exp_rel	:	exp_rel OP_REL exp_add | exp_add  ;
exp_add	:	exp_add OP_ADD exp_mul | exp_mul  ;
exp_mul	:	exp_mul OP_MUL exp_pot | exp_pot  ;
exp_pot	:	exp_pot '**' exp_una | exp_una  ;
exp_una	:	OP_UNA exp_una | term  ;
term	:	'$' IDENTIFICADOR indice | agrup | valor | '(' exp_or ')'  ;


OP_IG	:	'eq' | '==' | 'ne' | '!='  ;
OP_REL	:	'>' | '<' | '>=' | '<='  ;
OP_ADD	:	'+' | '-'  ;
OP_MUL	:	'*' | '/' | '%'  ;
OP_UNA	:	'!' | '-'  ;


IDENTIFICADOR	:	[a-zA-Z_][a-zA-Z0-9_]*  ;
VALOR_ENTERO	:	[0-9]+  ;
VALOR_DOUBLE	:	[0-9]+ '.' [0-9]+  ;
VALOR_STRING	:	'"' ~[\r\n]* '"'  ;
WS	:	[ \t\r\n]+	->	skip  ;
COMMENT	:	'#' ~[\r\n]*	->	skip  ;
