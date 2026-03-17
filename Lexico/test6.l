%option noyywrap
%{
	#include <stdio.h>
%}
letras [a-z]{2}
clave [0-9]{6}
link uaeh.edu.mx
%%

{letras}{clave}@{link} {printf("RECONOCIDO CORREO INSTITUCIONAL"); }
%%

int main() {
	yylex();
	return 0;
}