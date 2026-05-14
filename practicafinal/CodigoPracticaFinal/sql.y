%{
#include <stdio.h>
#include <stdlib.h>

int yylex();
int yyparse();

extern FILE *yyin;
extern FILE *yyout;
extern char *yytext;
extern int linea;

int Errores = 0;

void yyerror(const char *s)
{
    Errores = 1;

    fprintf(
        yyout,
        "[Linea %d] Error cerca de '%s': %s\n",
        linea,
        yytext,
        s
    );
}

%}

%token SELECT INSERT UPDATE DELETE
%token INTO VALUES SET FROM WHERE AND OR
%token IDENTIFICADOR
%token ENTERO CADENA
%token COMA PUNTOYCOMA
%token IGUAL MENOR MAYOR MENORIGUAL MAYORIGUAL DISTINTO

%left OR
%left AND
%left IGUAL DISTINTO MENOR MENORIGUAL MAYOR MAYORIGUAL

%start input

%%

input:
      /* vacío */
    | input sentencia
    ;

sentencia:
      select_stmt PUNTOYCOMA
      {
          fprintf(yyout,
                  "[Linea %d] Sentencia SELECT valida\n",
                  linea);
      }

    | insert_stmt PUNTOYCOMA
      {
          fprintf(yyout,
                  "[Linea %d] Sentencia INSERT valida\n",
                  linea);
      }

    | update_stmt PUNTOYCOMA
      {
          fprintf(yyout,
                  "[Linea %d] Sentencia UPDATE valida\n",
                  linea);
      }

    | delete_stmt PUNTOYCOMA
      {
          fprintf(yyout,
                  "[Linea %d] Sentencia DELETE valida\n",
                  linea);
      }
      
    | error PUNTOYCOMA
      {
          fprintf(yyout,
                  "[Linea %d] Sentencia invalida\n",
                  linea);

          yyerrok;
      }
;

select_stmt:
      SELECT select_lista FROM IDENTIFICADOR opt_where
    ;

select_lista:
      '*'
    | lista_columnas
    ;

lista_columnas:
      IDENTIFICADOR
    | lista_columnas COMA IDENTIFICADOR
    ;

insert_stmt:
      INSERT INTO IDENTIFICADOR opt_columnas VALUES '(' lista_valores ')'
    ;

opt_columnas:
      /* vacío */
    | '(' lista_columnas ')'
    ;

lista_valores:
      valor
    | lista_valores COMA valor
    ;

valor:
      ENTERO
    | CADENA
    ;

update_stmt:
      UPDATE IDENTIFICADOR SET lista_asignaciones opt_where
    ;

lista_asignaciones:
      asignacion
    | lista_asignaciones COMA asignacion
    ;

asignacion:
      IDENTIFICADOR IGUAL valor
    ;

delete_stmt:
      DELETE FROM IDENTIFICADOR opt_where
    ;

opt_where:
      /* vacío */
    | WHERE condicion
    ;

condicion:
      condicion AND condicion
    | condicion OR  condicion
    | '(' condicion ')'
    | comparacion
    ;

comparacion:
      IDENTIFICADOR operador valor
    ;

operador:
      IGUAL
    | DISTINTO
    | MENOR
    | MENORIGUAL
    | MAYOR
    | MAYORIGUAL
    ;

%%

int main()
{
    FILE *archivo;

    linea = 1;

    yyout = fopen("resultado.txt", "w");

    if (yyout == NULL)
    {
        printf("Error al crear resultado.txt\n");
        return 1;
    }

    archivo = fopen("entrada.txt", "r");

    if (archivo == NULL)
    {
        printf("Error al abrir entrada.txt\n");
        fclose(yyout);
        return 1;
    }


    yyin = archivo;

    yyparse();

    printf("Salida escrita en resultado.txt\n");
    fclose(archivo);
    fclose(yyout);

    return 0;
}