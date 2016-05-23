% Ej. conf = [[p(4,1,a), p(4,2,a), p(4,3,a), p(4,4,a)],[p(3,1,a), p(3,2,a), p(3,3,a), p(3,4,a)],[p(2,1,a), p(2,2,a), p(2,3,a), p(2,4,a)],[p(1,1,a), p(1,2,a), p(1,3,a), p(1,4,a)]]

% en(+p, +Conf, -Contenido)
% Retorna el contenido de la ranura pasada por parámetro
 
en([X, Y], [ [p(Z,W,R)|Rs] | Xs], Res) :- en([X,Y], [Rs|Xs], Res).
en([X, Y], [ [p(Z,W,R)|[]] | Xs], Res) :- en([X,Y], Xs, Res).
en([X,Y], [ [p(X,Y,R)| Rs] | Xs], R).

% cuatro(+Color, +Conf, -CuatroEnLinea) 
% Si es true retorna la lista que conforma cuatro en linea, caso contrario retorna false.

cuatro(Color, Conf, Resultado):- 
 horizontal(Color, Conf, 0, 4, Aux), longitud(Aux,0,Long), mostrar(4,Aux, Resultado,Long);
 trans(Conf, Transpuesta), horizontal(Color, Transpuesta, 0,4,Aux), longitud(Aux,0,Long), mostrar(4,Aux, Resultado,Long);
 ced(Color,Conf,4,Resultado).
 
% Busca N elementos en linea de forma horizontal (N = Limite)
%horizontal(Color,Config,Contador,Limite,Resultado)

horizontal(Color, [[]|Ys], Contador, Limite,Resultado) :- horizontal(Color, Ys, 0, Limite, Resultado).
horizontal(Color, Conf, Limite, Limite, []).
horizontal(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, Limite, [p(X,Y,Color)|Resultado]):- Contador1 is Contador+1, horizontal(Color, [Xs|Ys], Contador1, Limite, Resultado).
horizontal(Color, [[p(X,Y,R)|Xs]|Ys], Contador, Limite, Resultado):- R \= Color, horizontal(Color, [Xs|Ys], 0, Limite, Resultado).

% ced(Color,Conf,Limite,Res)

ced(Color,[PrimeraFila | [SegundaFila | TercerFila]],Limite,Res):-
	cedRF(Limite,6,[PrimeraFila | [SegundaFila | TercerFila]],R1), horizontal(Color,R1,0,Limite,R2), longitud(R2,0,Long), mostrar(Limite,R2,Res,Long);
	cedRF(Limite,5,[SegundaFila | TercerFila],R3), horizontal(Color,R3,0,Limite,R4), longitud(R4,0,Long), mostrar(Limite,R4, Res,Long);
	cedRF(Limite,4,TercerFila,R5), horizontal(Color,R5,0,Limite,R6), longitud(R6,0,Long), mostrar(Limite,R6, Res,Long).

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

%Almacena las diagonales que se producen de izquierda a derecha en una lista 
%cedID (Contador,X,Y,Fila,RestoFilas,Lista)

cedID(0, X, Y, Fila, RestoFilas, []).
cedID(C, X, Y, Fila, [], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y + 1, C1 is C - 1, cedID(C1, X1, Y1, [], [], Lista).
cedID(C, X, Y, Fila, [FilaSig | Resto], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y + 1, C1 is C - 1, cedID(C1, X1, Y1, FilaSig, Resto, Lista).

%Almacena las diagonales que se producen de derecha a izquierda en una lista 
%cedDI (Contador,X,Y,Fila,RestoFilas,Lista)

cedDI(0, X, Y, Fila, RestoFilas, []).
cedDI(C, X, Y, Fila, [], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y - 1, C1 is C - 1, cedDI(C1, X1, Y1, [], [], Lista).
cedDI(C, X, Y, Fila, [FilaSig | Resto], [Pos | Lista]):- buscar(X,Y,Fila,Pos), X1 is X - 1, Y1 is Y - 1, C1 is C - 1, cedDI(C1, X1, Y1, FilaSig, Resto, Lista).

%Busca en una lista la posicion cuyas coordenadas son X,Y.

buscar(X,Y,[p(X,Y,C) | RestoFila],p(X,Y,C)).
buscar(X,Y,[p(W,Z,C) | RestoFila],Diagonales):- buscar(X,Y,RestoFila,Diagonales).
	
% mostrar(+N). Muestra los ultimos n elementos de una lista

mostrar(N, Zs, Zs, N).
mostrar(N,[_|Xs], Zs, Cont):- Cont1 is Cont-1, mostrar(N,Xs, Zs, Cont1).

% % % % % % % % % % % %
% Metodos auxiliares  % 
% %  % % % % % %  % % %

	
% estallena(+Conf) 

estaLlena([]).
estaLlena([[]|Zs]) :- estaLlena(Zs).
estaLlena([[p(X,Y,a)|Xs]|Zs]) :- estaLlena([Xs|Zs]).
estaLlena([[p(X,Y,r)|Xs]|Zs]) :- estaLlena([Xs|Zs]).	
	
% Obtiene la longitud de una lista.

longitud([],L, L).
longitud([X|Xs], R, L) :- R1 is R+1, longitud(Xs, R1, L).

% obtiene la matriz transversa [[1,2,3][4,5,6],[7,8,9]] =  [[1,4,7],[2,5,8],[3,6,9]].

trans([],[]).
trans([[]|_], []):-!.
trans([S|R], [L|L1]):- trans(S, R, L, M), trans(M, L1).

trans([], _,[],[]).
trans([S1|S2], [], [S1|L1], [S2|M]):- trans([], [], L1, M).
trans([S1|S2], [R1|R2], [S1|L1], [S2|M]):- trans(R1, R2, L1, M).

% disponible(Ranura) SE PUEDE CAMBIAR INVOCANDO A SUPERFICIE write(queonda2), en([X,Y], Conf, Contenido), Contenido = v, write(esvacio2), 

disponible([X,Y], Conf) :- ultimaFila(Conf, Filas), disAux([X,Y], Conf, Filas).
disAux([X,Y], Conf, Filas) :-[X,Y]=[Filas,Y],  en([Filas,Y], Conf, Contenido), Contenido=v.
disAux([X,Y], Conf, Filas) :- en([Filas,Y], Conf, Contenido), not(Contenido=v), Filas1 is Filas-1, disAux([X,Y], Conf, Filas1).

%ultimaFila Retorna cual es la ultima fila de la configuracion

ultimaFila([[p(X,Y,C)|Xs]|Zs], X).

% colocar ficha(+Color, +Ranura, +Conf, -ConfRes), G2130

colocar_ficha(Color, [X,Y], Conf, ConfRes):- disponible([X,Y],Conf),colocar_ficha1(Color, [X,Y], Conf, ConfRes).
colocar_ficha1(Color, [X,Y], [], []).
colocar_ficha1(Color, [X,Y], [Xs|RestoFilas], [Zs|Resultado]) :- colocarAux(Color, [X,Y], Xs, Zs), colocar_ficha1(Color,[X,Y], RestoFilas, Resultado).
colocarAux(Color, [X,Y], [], []).
colocarAux(Color, [X,Y], [p(X,Y, v)| XXs], [p(X,Y,Color)|Zs]):- colocarAux(Color, [X,Y], XXs, Zs).
colocarAux(Color, [X,Y], [p(Z,W, C)| XXs], [p(Z,W,C)|Zs]):- colocarAux(Color, [X,Y], XXs, Zs).

%colorContrario(+Color, ColorContrario)

colorContrario(r, a).
colorContrario(a, r).

% combinacionN(N,Color, Conf, Resultado)
% Si es true retorna la lista que conforma N fichas en linea.

combinacionN(N,Color, Conf, Resultado):- ced(Color,Conf,N,Resultado);
	horizontal(Color, Conf, 0,N, Aux), longitud(Aux,0,Long), mostrar(N,Aux, Resultado,Long);
	trans(Conf, Transpuesta), horizontal(Color, Transpuesta, 0,N, Aux), longitud(Aux,0,Long), mostrar(N,Aux, Resultado,Long).
 
% jugada maquina(+Color, +Conf, -Ranura)

jugada_maquina(Color, Conf, Ranura):- 
	jugada_ganadora(Color, Conf, Ranura,Cuatro); 
	jugada_bloqueo(Color, Conf, Ranura); 
	jugada_mate(Color, Conf, Ranura);
	jugada_segura(Color, Conf, Ranura).
 

jugada_ganadora(Color, Conf, [X,Y],Cuatro):- 
	colocar_ficha(Color, [X,Y], Conf, ConfRes), 
	cuatro(Color, ConfRes, Cuatro).

jugada_bloqueo(Color,Conf, Resultado):- Color=a, jugada_ganadora(r, Conf, Resultado,Cuatro).
jugada_bloqueo(Color,Conf, Resultado):- Color=r, jugada_ganadora(a, Conf, Resultado,Cuatro).
  
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
	jugada_inicial(Color, Conf, [X,Y]),
	colocar_ficha(Color, [X,Y], Conf, ConfRes),
	colorContrario(Color, OtroColor),
	not(jugada_ganadora(OtroColor, ConfRes, R,Cuatro)).

buscarAux(X,Y,[p(X,Y,Color) | RestoFila],Color).
buscarAux(X,Y,[p(W,Z,C) | RestoFila],Color):- buscarAux(X,Y,RestoFila,Color).

jugada_inicial(Color,[FilaActual | Resto],[X,Y]):- 
	obtenerDisponibles(7,[FilaActual | Resto],FilaActual,L,X), L \= [], random_member(Y, L); 
	jugada_inicial(Color,Resto,[X,Y]).

obtenerDisponibles(0,Conf, FilaActual,[], X).
obtenerDisponibles(Cont, Conf, [p(X,Y,v) | R], [Y | L], X):- 
	disponible([X,Y],Conf), 
	Cont1 is Cont - 1, 
	obtenerDisponibles(Cont1, Conf, R, L, X).
obtenerDisponibles(Cont, Conf, [p(X,Y,C) | R], L, X):- 
	Cont1 is Cont - 1, 
	obtenerDisponibles(Cont1, Conf, R, L, X).

jugada_mate(Color, Conf, [X,Y]):- 
	colocar_ficha(Color,[X,Y],Conf,ConfRes),
	jugada_ganadora(Color,ConfRes,R1,Cuatro1), 
	jugada_ganadora(Color,ConfRes,R2,Cuatro2), 
	R1 \= R2, 
	Cuatro1 \= Cuatro2.




