%option noyywrap
%{
	#include <stdio.h>
%}

%%

[0-9]+  {printf("Reconocido ENTERO"); }
%%

int main() {
	yylex();
	return 0;
}