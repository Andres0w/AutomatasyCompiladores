%option noyywrap
%{
	#include <stdio.h>
%}
INICIOIDENT [A-Za-z_]
EXTRAIDENT [A-Za-z0-9_]
%%

{INICIOIDENT}{EXTRAIDENT}?+  {printf("Reconocido variable"); }
%%

int main() {
	yylex();
	return 0;
}