%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% EN %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% en(+p, +Conf, -Contenido)
% Retorna el contenido de la ranura pasada por parámetro
 
en([X, Y], [ [p(Z,W,R)|Rs] | Xs], Res) :- en([X,Y], [Rs|Xs], Res).
en([X, Y], [ [p(Z,W,R)|[]] | Xs], Res) :- en([X,Y], Xs, Res).
en([X,Y], [ [p(X,Y,R)| Rs] | Xs], R).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% CUATRO EN LINEA %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% cuatro(+Color, +Conf, -CuatroEnLinea) 
% Si es true retorna la lista que conforma cuatro en linea, false en caso contrario.

cuatro(Color, Conf, Resultado):- 
 horizontal(Color, Conf, 0, 4, Aux), longitud(Aux,0,Long), mostrar(4,Aux, Resultado,Long);
 trans(Conf, Transpuesta), horizontal(Color, Transpuesta, 0,4,Aux), longitud(Aux,0,Long), mostrar(4,Aux, Resultado,Long);
 ced(Color,Conf,4,Resultado).

% horizontal(+Color,+Config,+Contador,+Limite,-Resultado)
% Busca N elementos en linea de forma horizontal (N = Limite)

horizontal(Color, [[]|Ys], Contador, Limite,Resultado) :- horizontal(Color, Ys, 0, Limite, Resultado).
horizontal(Color, Conf, Limite, Limite, []).
horizontal(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, Limite, [p(X,Y,Color)|Resultado]):- Contador1 is Contador+1, horizontal(Color, [Xs|Ys], Contador1, Limite, Resultado).
horizontal(Color, [[p(X,Y,R)|Xs]|Ys], Contador, Limite, Resultado):- R \= Color, horizontal(Color, [Xs|Ys], 0, Limite, Resultado).

% ced(Color,Conf,Limite,Res) COMPLETAR

ced(Color,[PrimeraFila | [SegundaFila | TercerFila]],Limite,Res):-
	cedRF(Limite,6,[PrimeraFila | [SegundaFila | TercerFila]],R1), horizontal(Color,R1,0,Limite,R2), longitud(R2,0,Long), mostrar(Limite,R2,Res,Long);
	cedRF(Limite,5,[SegundaFila | TercerFila],R3), horizontal(Color,R3,0,Limite,R4), longitud(R4,0,Long), mostrar(Limite,R4, Res,Long);
	cedRF(Limite,4,TercerFila,R5), horizontal(Color,R5,0,Limite,R6), longitud(R6,0,Long), mostrar(Limite,R6, Res,Long).
	
% COMPLETAR

cedRF(Limite, X, [PrimeraFila | RestoFilas],Res):- 
	cedID(Limite,X,1,PrimeraFila, RestoFilas,L1),
	cedID(Limite,X,2,PrimeraFila, RestoFilas,L2),
	cedID(Limite,X,3,PrimeraFila, RestoFilas,L3),
	cedID(Limite,X,4,PrimeraFila, RestoFilas,L4),
	cedDI(Limite,X,4,PrimeraFila, RestoFilas,L5),
	cedDI(Limite,X,5,PrimeraFila, RestoFilas,L6),
	cedDI(Limite,X,6,PrimeraFila, RestoFilas,L7),
	cedDI(Limite,X,7,PrimeraFila, RestoFilas,L8),
	Res = [L1,L2,L3,L4,L5,L6,L7,L8].

%cedID (+Contador,+X,+Y,+Fila,+RestoFilas,-Lista)
%Almacena las diagonales que se producen de izquierda a derecha en una lista 

cedID(0, X, Y, Fila, RestoFilas, []).
cedID(C, X, Y, Fila, [], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y + 1, C1 is C - 1, cedID(C1, X1, Y1, [], [], Lista).
cedID(C, X, Y, Fila, [FilaSig | Resto], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y + 1, C1 is C - 1, cedID(C1, X1, Y1, FilaSig, Resto, Lista).

%cedDI (+Contador,+X,+Y,+Fila,+RestoFilas,-Lista)
%Almacena las diagonales que se producen de derecha a izquierda en una lista 

cedDI(0, X, Y, Fila, RestoFilas, []).
cedDI(C, X, Y, Fila, [], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y - 1, C1 is C - 1, cedDI(C1, X1, Y1, [], [], Lista).
cedDI(C, X, Y, Fila, [FilaSig | Resto], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y - 1, C1 is C - 1, cedDI(C1, X1, Y1, FilaSig, Resto, Lista).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% DISPONIBLE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% disponible(+Ranura,+-Conf)
% Retorna true si la posición ingresada por parámetro esta disponible, false en caso contrario.

disponible([X,Y], Conf) :- ultimaFila(Conf, Filas), disAux([X,Y], Conf, Filas).
disAux([X,Y], Conf, Filas) :-[X,Y]=[Filas,Y],  en([Filas,Y], Conf, Contenido), Contenido=v.
disAux([X,Y], Conf, Filas) :- en([Filas,Y], Conf, Contenido), not(Contenido=v), Filas1 is Filas-1, disAux([X,Y], Conf, Filas1).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% COLOCAR FICHA %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% colocar ficha(+Color, +Ranura, +Conf, -ConfRes)
% coloca la ficha en la posición ingresa por parámetro si esta disponible, y retorna la configuración resultante.

colocar_ficha(Color, [X,Y], Conf, ConfRes):- disponible([X,Y],Conf),colocar_ficha1(Color, [X,Y], Conf, ConfRes).
colocar_ficha1(Color, [X,Y], [], []).
colocar_ficha1(Color, [X,Y], [Xs|RestoFilas], [Zs|Resultado]) :- colocarAux(Color, [X,Y], Xs, Zs), colocar_ficha1(Color,[X,Y], RestoFilas, Resultado).
colocarAux(Color, [X,Y], [], []).
colocarAux(Color, [X,Y], [p(X,Y, v)| XXs], [p(X,Y,Color)|Zs]):- colocarAux(Color, [X,Y], XXs, Zs).
colocarAux(Color, [X,Y], [p(Z,W, C)| XXs], [p(Z,W,C)|Zs]):- colocarAux(Color, [X,Y], XXs, Zs).
 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% JUGADA MÁQUINA %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% jugada maquina(+Color, +Conf, -Ranura)
% Recibe como parámetro un color y una configuración y retorna la ranura donde debe colocarse la ficha tomando en cuenta un orden de prioridad de jugada.

jugada_maquina(Color, Conf, Ranura):- 
	jugada_ganadora(Color, Conf, Ranura,Cuatro); 
	jugada_bloqueo(Color, Conf, Ranura); 
	jugada_mate(Color, Conf, Ranura);
	jugada_segura(Color, Conf, Ranura).
 
% jugada_ganadora(+Color, +Conf, +Ranura, +Cuatro)
%Recibe como parámetro un color y una configuración y retorna la ranura donde debe colocarse la ficha para ejecutar una jugada ganadora.

jugada_ganadora(Color, Conf, [X,Y],Cuatro):- 
	colocar_ficha(Color, [X,Y], Conf, ConfRes), 
	cuatro(Color, ConfRes, Cuatro).
	
% jugada_bloqueo(+Color, +Conf, +Ranura, +Cuatro)
% Recibe como parámetro un color y una configuración y retorna la ranura donde debe colocarse la ficha para ejecutar una jugada bloqueo.

jugada_bloqueo(Color,Conf, Resultado):- Color=a, jugada_ganadora(r, Conf, Resultado,Cuatro).
jugada_bloqueo(Color,Conf, Resultado):- Color=r, jugada_ganadora(a, Conf, Resultado,Cuatro).

% jugada_segura(+Color, +Conf, +Ranura, +Cuatro)
% Recibe como parámetro un color y una configuración y retorna la ranura donde debe colocarse la ficha para ejecutar una jugada segura.

jugada_segura(Color, Conf, [X,Y]) :- 
	colocar_ficha(Color, [X,Y], Conf, ConfRes), 
	combinacionN(3,Color, ConfRes, Tres), 
	colorContrario(Color, OtroColor), 
	not(jugada_ganadora(OtroColor, ConfRes, R,Cuatro)), 
	buscarAux(X,Y,Tres,Color).
	
jugada_segura(Color, Conf, [X,Y]) :- 
	colocar_ficha(Color, [X,Y], Conf, ConfRes),
	combinacionN(2,Color, ConfRes, Dos),
	colorContrario(Color, OtroColor),
	not(jugada_ganadora(OtroColor, ConfRes, R,Cuatro)),
	buscarAux(X,Y,Dos,Color).
	
jugada_segura(Color, Conf, [X,Y]) :- 
	jugada_inicial(Color, Conf, [X,Y]).

% jugada_inicial(+Color, +Conf, +Ranura, +Cuatro)
% Recibe como parámetro un color y una configuración y retorna la ranura donde debe colocarse la ficha para ejecutar la jugada inicial.

jugada_inicial(Color,[FilaActual | Resto],[X,Y]):- 
	obtenerDisponibles(7,[FilaActual | Resto],FilaActual,L,X), L \= [], random_member(Y, L), colocar_ficha(Color, [X,Y], [FilaActual | Resto], ConfRes), colorContrario(Color, OtroColor), not(jugada_ganadora(OtroColor, ConfRes, R,Cuatro)); 
	jugada_inicial(Color,Resto,[X,Y]).

jugada_inicial(Color,[FilaActual | Resto],[X,Y]):- 
	obtenerDisponibles(7,[FilaActual | Resto],FilaActual,L,X), L \= [], random_member(Y, L); 
	jugada_inicial(Color,Resto,[X,Y]).

% jugada_mate(+Color, +Conf, +Ranura, +Cuatro)
% Recibe como parámetro un color y una configuración y retorna la ranura donde debe colocarse la ficha para ejecutar una jugada mate.

jugada_mate(Color, Conf, [X,Y]):- 
	colocar_ficha(Color,[X,Y],Conf,ConfRes),
	jugada_ganadora(Color,ConfRes,R1,Cuatro1), 
	jugada_ganadora(Color,ConfRes,R2,Cuatro2), 
	R1 \= R2, 
	Cuatro1 \= Cuatro2.
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MÉTODOS AUXILIARES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% combinacionN(+N,+Color, +Conf, -Resultado)
% Si es true retorna la lista que conforma N fichas en linea.

combinacionN(N,Color, Conf, Resultado):- ced(Color,Conf,N,Resultado);
	horizontal(Color, Conf, 0,N, Aux), longitud(Aux,0,Long), mostrar(N,Aux, Resultado,Long);
	trans(Conf, Transpuesta), horizontal(Color, Transpuesta, 0,N, Aux), longitud(Aux,0,Long), mostrar(N,Aux, Resultado,Long).

% buscarAux (X, Y, Conf, Color)
% busca en una fila la posicion X,Y con un determinado Color. 

buscarAux(X,Y,[p(X,Y,Color) | RestoFila],Color).
buscarAux(X,Y,[p(W,Z,C) | RestoFila],Color):- buscarAux(X,Y,RestoFila,Color).

% obtenerDisponibles (Cont, Conf, FilaActual, ListaResultado, X)
% obtiene todas las posiciones disponibles de una fila (es decir todas las p(X,Y,v) de una fila)
% y las retorna en ListaResultado. En X retorna la coordenada de la fila. 

obtenerDisponibles(0,Conf, FilaActual,[], X).
obtenerDisponibles(Cont, Conf, [p(X,Y,v) | R], [Y | L], X):- 
	disponible([X,Y],Conf), 
	Cont1 is Cont - 1, 
	obtenerDisponibles(Cont1, Conf, R, L, X).
obtenerDisponibles(Cont, Conf, [p(X,Y,C) | R], L, X):- 
	Cont1 is Cont - 1, 
	obtenerDisponibles(Cont1, Conf, R, L, X).

% colorContrario(+Color,-ColorContrario)
% Retorna el color contrario al ingresado por parámetro.

colorContrario(r, a).
colorContrario(a, r).

% ultimaFila(+Conf,-Ultima)
% Retorna cual es la ultima fila de la configuracion

ultimaFila([[p(X,Y,C)|Xs]|Zs], X).

% estallena(+Conf)
% Retorna true si la configuración ingresada por parámetro esta llena.

estaLlena([]).
estaLlena([[]|Zs]) :- estaLlena(Zs).
estaLlena([[p(X,Y,a)|Xs]|Zs]) :- estaLlena([Xs|Zs]).
estaLlena([[p(X,Y,r)|Xs]|Zs]) :- estaLlena([Xs|Zs]).	

% longitud(+Lista, +Contador, -Longitud)
% Obtiene la longitud de una lista ingresada por parámetro.

longitud([],L, L).
longitud([X|Xs], R, L) :- R1 is R+1, longitud(Xs, R1, L).

% trans(+Conf, -Transversa)
% obtiene la matriz transversa de una matriz ingresada por parámetro.

trans([],[]).
trans([[]|_], []):-!.
trans([S|R], [L|L1]):- trans(S, R, L, M), trans(M, L1).

trans([], _,[],[]).
trans([S1|S2], [], [S1|L1], [S2|M]):- trans([], [], L1, M).
trans([S1|S2], [R1|R2], [S1|L1], [S2|M]):- trans(R1, R2, L1, M).

%buscar(X,Y,fila,Pos)
%Busca en una lista la posicion cuyas coordenadas son X,Y y la retorna en Pos.

buscar(X,Y,[p(X,Y,C) | RestoFila],p(X,Y,C)).
buscar(X,Y,[p(W,Z,C) | RestoFila],Pos):- buscar(X,Y,RestoFila,Pos).
	
% mostrar(+N,+Lista, -Resultado, +Cont)
% Muestra los ultimos n elementos de una lista

mostrar(N, Zs, Zs, N).
mostrar(N,[_|Xs], Zs, Cont):- Cont1 is Cont-1, mostrar(N,Xs, Zs, Cont1).



