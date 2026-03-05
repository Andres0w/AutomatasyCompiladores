%option noyywrap
%{
	#include <stdio.h>
	#define TOKEN_INICIO 1
	#define TOKEN_FIN 2
	#define TOKEN_VECTOR 3
	#define TOKEN_ENTERO 4
	#define TOKEN_LOGICO 5
%}
%%
INICIO { return TOKEN_INICIO;}
FIN {return TOKEN_FIN;}
VECTOR {return TOKEN_VECTOR;}
ENTERO {return TOKEN_ENTERO;}
LOGICO {return TOKEN_LOGICO;}
%%

int main()
{

	int token;
	while(1) {
		token = yylex();
		if (token == TOKEN_INICIO) printf("TOKEN INICIO RECONOCIDO\n");
		if (token == TOKEN_FIN) printf("TOKEN FIN RECONOCIDO\n");
		if (token == TOKEN_VECTOR) printf("TOKEN VECTOR RECONOCIDO\n");
		if (token == TOKEN_ENTERO) printf("TOKEN ENTERO RECONOCIDO\n");
		if (token == TOKEN_LOGICO) printf("TOKEN LOGICO RECONOCIDO\n");
		if (token == 0) break;
	}
	return 0;
}