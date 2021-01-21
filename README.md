# Wizards vs warriors
## Foundational Java and OOP Homework (Unit 1)
Grupo A: "E.M.O.F." (En Mi Ordenador Funciona)

Este repositorio contiene el código para ejecutar el juego "Warriors vs Wizards".

Para iniciar el juego se debe abrir el archivo: Main.java desde cualquier IDE que soporte JVM.

Al abrir el archivo, se debe ejecutar el mismo desde el IDE.

Una vez ejecutado, el podrá ver por consola la siguiente pantalla:

![](https://github.com/Adrimava/group-A-weekly-assessment-1/blob/develop/images/01_Intro.png)

Este es el menú de inicio, hay 5 opciones posibles. El menú ha sido diseñado para que solo acepte esos 5 números posibles. En caso de ingresar otro caracter, se recibe el mensaje "You must type a number" y se pide ingresar nuevamente el número.

## Opción 1: Crear las propias partidas y elegir el personaje en cada round <a name="opt1"></a>
Al elegir esta opción, se pregunta al jugador cuántos personajes pelearán en cada partida. El número máximo de personajes es 10.

### "Character creation" menú <a name="char_menu"></a>
Luego de elegir el número de personajes, aparecerá iun menú para crear personajes. Este menú da dos opciones: Crear un personaje propio o que el juego asigne uno de forma aleatoria.

![](https://github.com/Adrimava/group-A-weekly-assessment-1/blob/develop/images/2_Character_creation.png)

Entre paréntesis, aparecerá el número de personaje que se está creando. Por ejemplo, si se eligió que 5 personajes iban a jugar la partida, al crear el personaje aparecerá: (1/5) en el primer personaje, (2/5) en el segundo y así suscesivamente.

Si se elige crear el propio personaje, lo primero será elegir si es "warrior" o "wizard". Luego, se pedirá ponerle un nombre al personaje y se pedirá ingresar los siguientes XXX 

En caso de warriors: 
* Health points
* Stamina (de 10 a 50)
* Strength (de 1 a 10)

En caso de wizards:
* Health points
* Mana (de 10 a 50)
* Intelligence (de 1 a 50)

Estos pasos se realizan para cada una de las partidas.

### Selección de oponentes para la batalla <a name="opponents_selection"></a>
Una vez creados todo los personajes para ambas partidas, el sistema imprimirá por pantalla a los personajes miembros de la misma para que sean elegidos para la batalla.

Se imprimirá por pantalla el personaje elegido:
![](https://github.com/Adrimava/group-A-weekly-assessment-1/blob/develop/images/3_Selected_character.png)

Una vez elegidos los personajes, ¡empieza la batalla!

### Las batallas <a name="the_battles"></a>

Cada ataque será preparado por el ordenador y se imprimirá un log de los ataques realizados y los impactos en cada jugador.

Finalmente se imprimirá por pantalla el jugador ganador y se pedirá elegir los siguientes oponentes:
![](https://github.com/Adrimava/group-A-weekly-assessment-1/blob/develop/images/4_winner_select_next.png)

El juego continuará enfrentando oponentes hasta que una de las partidas termine sin jugadores.

### Ir al cementerio (graveyard) <a name="visit_graveyard"></a>
Cuando una de las partidas ha eliminado a todos los jugadores de la partida contrincante, el juego la declara ganadora y el combate ha terminado.

Se preguntará al usuario si se desea visitar el cementerio (graveyard):
![](https://github.com/Adrimava/group-A-weekly-assessment-1/blob/develop/images/5_battle_ended_visit_grvyrd.png)

Si se ha elegido verla, el menú pregunta si se desea volver a jugar o si se desea salir del juego.

## Opción 2: Subir las partidas desde un csv
Esta opción permite cargar las partidas desde archivos csv. Para hacerlo se debe ingresar el nombre del archivo en dicho formato.

Se debe subir cada partida como un archivo csv independiente.

Una vez cargadas las partidas, el juego continúa como explicado en [selección de oponentes para la batalla](#opponents_selection), [las batallas](#the_battles) e [ir al cementerio](#visit_graveyard).

## Opción 3: Generar partidas aleatoriamente y elegir el personaje en cada round
En esta opción se pide al jugador elegir el número de personajes que conformarán la partida.

Automática y aleatoriamente, se asignan personajes a las partidas según el número elegido.

Lo siguiente, y como visto en [selección de oponentes para la batalla](#opponents_selection), es elegir a los oponentes, luego de lo cual, ¡la batalla inicia!

Al tener una partida ganadora, nuevamente se puede [visitar el cementerio](#graveyard) y elegir volver a jugar o salir del juego.

## Opción 4: Generar partidas y elegir el personaje aleatoriamente
En esta opción, se crean automáticamente dos partidas y el juego inicia inmediatamente. El jugador puede revisar por consola como avanzan las batallas y los ganadores en cada caso.
![](https://github.com/Adrimava/group-A-weekly-assessment-1/blob/develop/images/6_random_choice.gif)

