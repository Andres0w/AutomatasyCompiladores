#include <iostream>
using namespace std;

int main()
{
    char variable[20];
    // Booleanos para verificar la presencia de los tipos de caracteres
    bool tieneNumero = false;
    bool tieneLetra = false;
    cout << "Ingresa una cadena de caracteres (maximo 20): ";
    cin >> variable;

    // Verificado por cada letra
    for (int i = 0; i < sizeof(variable); i++)
    {
        char valorActual = variable[i];
        if (valorActual == '\0') {
            break;
        }
        if (valorActual >= '0' && valorActual <= '9')
        {
            tieneNumero = true;
        } else if ((valorActual >= 'a' && valorActual <= 'z') || (valorActual >= 'A' && valorActual <= 'Z')){
            tieneLetra = true;
        }
    }

    if (tieneNumero && tieneLetra) {
        cout << "La cadena es compuesta";
    } else if (tieneNumero && !tieneLetra) {
        cout << "La cadena es un numero entero";
    } else if (!tieneNumero && tieneLetra) {
        cout << "La cadena tiene solo letras";
    }
    return 0;
}
