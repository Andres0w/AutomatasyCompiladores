%option noyywrap
%{
	#include <stdio.h>
%}
%%

[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])(H|M)[A-Z]{5}[A-Z0-9]{2} {printf("RECONOCIDO CURP"); }
%%

int main() {
	yylex();
	return 0;
}