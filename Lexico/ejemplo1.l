%option noyywrap
%{
	#include<stdio.h>
%}

%%
INICIO {printf("Reconocido INICIO\n");}
FIN {printf("Reconocido FIN\n");}
VECTOR {printf("Reconocido VECTOR\n");}
ENTERO {printf("Reconocido ENTERO\n");}
LOGICO {printf("Reconocido LOGICO\n");}
%%
int main()
{

	return yylex();
}