% en(+p, +Conf, -Contenido)
% Retorna el contenido de la ranura pasada por parámetro
 
en([X, Y], [ [p(Z,W,R)|Rs] | Xs], Res) :- en([X,Y], [Rs|Xs], Res).
en([X, Y], [ [p(Z,W,R)|[]] | Xs], Res) :- en([X,Y], Xs, Res).
en([X,Y], [ [p(X,Y,R)| Rs] | Xs], R).

% Ej. conf = [[p(4,1,a), p(4,2,a), p(4,3,a), p(4,4,a)],[p(3,1,a), p(3,2,a), p(3,3,a), p(3,4,a)],[p(2,1,a), p(2,2,a), p(2,3,a), p(2,4,a)],[p(1,1,a), p(1,2,a), p(1,3,a), p(1,4,a)]]

% cuatro(+Color, +Conf, -CuatroEnLinea) 
% Si es true retorna la lista que conforma cuatro en linea, si es false retorna false.

cuatro(Color, Conf, Resultado):- 

 cuatroH(Color, Conf, 0, 4, Aux), longitud(Aux,0,Long), mostrar(4,Aux, Resultado,Long);
 trans(Conf, Traspuesta),cuatroH(Color, Traspuesta, 0,4,Aux), longitud(Aux,0,Long), mostrar(4,Aux, Resultado,Long);
ced(Color,Conf,4,Aux),longitud(Aux,0,Long),mostrar(4,Aux,Resultado,Long).

 
%Cuatro Horizontal (utilizado para vertical)

cuatroH(Color, [[]|Ys], Contador, Limite,Resultado) :- cuatroH(Color, Ys, 0, Limite, Resultado).
cuatroH(Color, Conf, Limite, Limite, []).
cuatroH(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, Limite, [p(X,Y,Color)|Resultado]):- Contador1 is Contador+1, cuatroH(Color, [Xs|Ys], Contador1, Limite, Resultado).
cuatroH(Color, [[p(X,Y,R)|Xs]|Ys], Contador, Limite, Resultado):- R \= Color, cuatroH(Color, [Xs|Ys], 0, Limite, Resultado).

%Cuatro diagonal ID

ced(Color,[Lista | [ Lista2 | Lista3 ] ],Limite,Resultado):- 
	cedRecorrerFila(Color,[Lista | [Lista2 | Lista3]],6,Limite,Resultado);
	cedRecorrerFila(Color,[Lista2 | Lista3],5,Limite,Resultado);
	cedRecorrerFila(Color,Lista3,4,Limite,Resultado).

cedRecorrerFila(Color,[PrimeraFila | RestoFilas],X,Limite,Res):- 
	cedFila(0,Color,PrimeraFila,X,1,Limite,RestoFilas), generar(0,Color,X,1,Limite,Res);
	cedFila(0,Color,PrimeraFila,X,2,Limite,RestoFilas), generar(0,Color,X,2,Limite,Res);
	cedFila(0,Color,PrimeraFila,X,3,Limite,RestoFilas), generar(0,Color,X,3,Limite,Res);
	cedFila(0,Color,PrimeraFila,X,4,Limite,RestoFilas), generar(0,Color,X,4,Limite,Res);
	cedFilaD(0,Color,PrimeraFila,X,4,Limite,RestoFilas),generarD(0,Color,X,4,Limite,Res);
	cedFilaD(0,Color,PrimeraFila,X,5,Limite,RestoFilas),generarD(0,Color,X,5,Limite,Res);
	cedFilaD(0,Color,PrimeraFila,X,6,Limite,RestoFilas),generarD(0,Color,X,6,Limite,Res);
	cedFilaD(0,Color,PrimeraFila,X,7,Limite,RestoFilas),generarD(0,Color,X,7,Limite,Res).

% Diagonal ID

cedFila(Limite,Color,Fila,PosActual,ColActual,Limite,FilasSiguientes):- writeln(ced1).

cedFila(Cont,Color, Fila, PosActual, ColActual,Limite, [ProxFila | FilasSiguientes]):- 
	buscar(PosActual,ColActual,Fila,Color), Cont1 is Cont+1, Pos is PosActual-1, Col is ColActual+1,cedFila(Cont1,Color,ProxFila,Pos,Col,Limite,FilasSiguientes).
cedFila(Cont,Color, Fila, PosActual, ColActual, []):- 
	buscar(PosActual,ColActual,Fila,Color), Cont1 is Cont+1, Pos is PosActual-1, Col is ColActual+1,cedFila(Cont1,Color,ProxFila,Pos,Col,Limite,FilasSiguientes).
	 
% Diagonal DI
	
cedFilaD(Limite,Color,Fila,PosActual,ColActual,Limite,FilasSiguientes):- writeln(ced2).

cedFilaD(Cont,Color, Fila, PosActual, ColActual, Limite, [ProxFila | FilasSiguientes]):- 
	buscar(PosActual,ColActual,Fila,Color), Cont1 is Cont+1, Pos is PosActual-1, Col is ColActual-1,cedFilaD(Cont1,Color,ProxFila,Pos,Col,Limite,FilasSiguientes).
cedFilaD(Cont,Color, Fila, PosActual, ColActual, Limite, []):- 
	buscar(PosActual,ColActual,Fila,Color), Cont1 is Cont+1, Pos is PosActual-1, Col is ColActual-1,cedFilaD(Cont1,Color,ProxFila,Pos,Col,Limite,FilasSiguientes).


%Busca en una lista la posicion cuyas coordenadas son X,Y y permite obtener su color actual.
buscar(X,Y,[p(X,Y,Color) | RestoFila],Color).
buscar(X,Y,[p(W,Z,C) | RestoFila],Color):- buscar(X,Y,RestoFila,Color).
	
%Genera una lista con la diagonal ganadora

generar(Limite,Color,X,Y,Limite,Res).
generar(Cont,Color,X,Y,Limite,[p(X,Y,Color)| Res]):- Cont1 is Cont+1, X1 is X-1, Y1 is Y+1,generar(Cont1,Color,X1,Y1,Limite,Res).
	
	
% generar() COMPLETAR
generarD(Limite,Color,X,Y,Limite,Res).
generarD(Cont,Color,X,Y,Limite,[p(X,Y,Color)| Res]):- Cont1 is Cont+1, X1 is X-1, Y1 is Y-1,generarD(Cont1,Color,X1,Y1,Limite,Res).

% mostrar(+N). Muestra los ultimos n elementos de una lista

mostrar(N,[_|Xs], Zs, Cont):- Cont1 is Cont-1, mostrar(N,Xs, Zs, Cont1).
mostrar(N, Zs, Zs, N).
	
% % % % % % % % % % % %
% Metodos auxiliares  % 
% %  % % % % % %  % % %

% tres(+Color, +Conf, Resultado)
% Si es true retorna la lista que conforma tres en linea, si es false retorna false.

tres(Color, Conf, Resultado):- cuatroH(Color, Conf, 0,3, Aux), longitud(Aux,0,Long), mostrar(3,Aux, Resultado,Long);
 trans(Conf, Traspuesta), cuatroH(Color, Traspuesta, 0,3, Aux), longitud(Aux,0,Long), mostrar(3,Aux, Resultado,Long);
 ced(Color,Conf,3,Aux),longitud(Aux,0,Long),mostrar(3,Aux,Resultado,Long).
 
% dos(+Color, +Conf, Resultado)
% Si es true retorna la lista que conforma dos en linea, si es false retorna false.

 dos(Color, Conf, Resultado):- cuatroH(Color, Conf, 0,2, Aux), longitud(Aux,0,Long), mostrar(2,Aux,Resultado,Long);
 trans(Conf, Traspuesta), cuatroH(Color, Traspuesta, 0,2, Aux), longitud(Aux,0,Long), mostrar(2,Aux,Resultado,Long);
 ced(Color,Conf,2,Aux),longitud(Aux,0,Long),mostrar(2,Aux,Resultado,Long).

	
	
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

%disponible([X,Y], Conf) :- ultimaFila(Conf, Fila), Fila=X, en([X,Y], Conf, Contenido), Contenido=v.


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

% jugada maquina(+Color, +Conf, -Ranura)

jugada_maquina(Color, Conf, Ranura):- writeln(jugadaganadora),jugada_ganadora(Color, Conf, Ranura,Cuatro);writeln(jugadabloqueo),jugada_bloqueo(Color, Conf, Ranura);writeln(jugadamate),jugada_mate(Color, Conf, Ranura);writeln(segurola),jugada_segura(Color, Conf, Ranura).
 

 
jugada_ganadora(Color, Conf, [X,Y],Cuatro):- colocar_ficha(Color, [X,Y], Conf, ConfRes), cuatro(Color, ConfRes, Cuatro).
jugada_bloqueo(Color,Conf, Resultado):- Color=a, jugada_ganadora(r, Conf, Resultado,Cuatro).
jugada_bloqueo(Color,Conf, Resultado):- Color=r, jugada_ganadora(a, Conf, Resultado,Cuatro).
  
jugada_segura(Color, Conf, [X,Y]) :- jugada_inicial(Color, Conf, [X,Y]), writeln(inicial).
jugada_segura(Color, Conf, [X,Y]) :- writeln(seguraTres), colocar_ficha(Color, [X,Y], Conf, ConfRes), tres(Color, ConfRes, Tres), colorContrario(Color, OtroColor),writeln(seguraTresPiola), not(jugada_ganadora(OtroColor, ConfRes, R,Cuatro)).
jugada_segura(Color, Conf, [X,Y]) :- writeln(seguradoDos), colocar_ficha(Color, [X,Y], Conf, ConfRes), dos(Color, ConfRes, Cuatro), colorContrario(Color, OtroColor),writeln(seguraDosPiola),not(jugada_ganadora(OtroColor, ConfRes, R,Cuatro)).
jugada_inicial(Color, Conf, [6,4]) :- disponible([6,4], Conf).

%ugada_mate(Color, Conf, [X,Y]):- colocar_ficha(Color,[X,Y],Conf,ConfRes),jugada_bloqueo(Color,ConfRes,[Q,W]),jugada_bloqueo(Color,ConfRes,[A,B]), A \= Q, B \= W.

jugada_mate(Color, Conf, [X,Y]):- colocar_ficha(Color,[X,Y],Conf,ConfRes),jugada_ganadora(Color,ConfRes,R1,Cuatro1), jugada_ganadora(Color,ConfRes,R2,Cuatro2), Cuatro1 \= Cuatro2.

%
%
% metodos auxiliares
%
% 

%columnaVacia(+numColumna, +Conf) devuelve true si la columna numColumna esta vacia.

columnaVacia(numColumna,Conf) :- columnaVaciaAux(numColumna, Conf, numColumna).
columnaVaciaAux(Cont,[], numColumna).
columnaVaciaAux(Cont,[[p(X,Y,C) |Xs]|Zs],numColumna) :- Cont1 is Cont-1, columnaVaciaAux(Cont1, [Xs|Zs], numColumna).
columnaVaciaAux(Cont,[[p(X,Y,v) |Xs]|Zs],numColumna) :- Cont=1 , columnaVaciaAux(numColumna,Zs, numColumna). 

%superficie 




