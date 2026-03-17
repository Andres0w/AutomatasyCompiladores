%option noyywrap
%{
	#include <stdio.h>
%}

%%

[0-9]+[.][0-9]+  {printf("Reconocido DECIMAL"); }
%%

int main() {
	yylex();
	return 0;
}