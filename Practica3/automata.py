# Importacion de libreria
from automata.fa.dfa import DFA

# Creacion del automata
automata = DFA(
    # Alfabeto de entrada
    input_symbols={'0', '1'},
    # Conjunto de estados
    states={'q1', 'q2', 'q3'},
    # Estados de transicion
    transitions={
        'q1': {'0': 'q1', '1': 'q2'},
        'q2': {'0': 'q3', '1': 'q2'},
        'q3': {'0': 'q2', '1': 'q2'}
    },
    # Estado inicial
    initial_state='q1',
    # Estado final
    final_states={'q2'}
)
# Funcion para entrada de caracteres en el automata finito determinista
def leerentrada(AFD):
    try:
        while True:
            # Entrada de caracteres, Aceptado si es valido, Rechazado si falla
            if AFD.accepts_input(input("Entrada: ")):
                print("Aceptado")
            else:
                print("Rechazado")
    except KeyboardInterrupt:
        print("")

leerentrada(automata)
