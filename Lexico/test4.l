%option noyywrap
%{
	#include <stdio.h>
%}
%%

[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][\\d]|3[0-1])([A-Z0-9]{3}) {printf("RECONOCIDO RFC"); }
%%

int main() {
	yylex();
	return 0;
}