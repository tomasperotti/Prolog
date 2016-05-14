% en(+p, +Conf, -Contenido)

en([X, Y], [ [p(Z,W,R)|Rs] | Xs], Res) :- en([X,Y], [Rs|Xs], Res).
en([X, Y], [ [p(Z,W,R)|[]] | Xs], Res) :- en([X,Y], Xs, Res).
en([X,Y], [ [p(X,Y,R)| Rs] | Xs], R).

% Ej. conf = [[p(4,1,a), p(4,2,a), p(4,3,a), p(4,4,a)],[p(3,1,a), p(3,2,a), p(3,3,a), p(3,4,a)],[p(2,1,a), p(2,2,a), p(2,3,a), p(2,4,a)],[p(1,1,a), p(1,2,a), p(1,3,a), p(1,4,a)]]

% cuatro en linea(+Color, +Conf, -CuatroEnLinea) 

cuatro(Color, Conf, Resultado):- cuatroAux(Color, Conf, 0, Aux), longitud(Aux,0,Long), mostrar4(Aux, Resultado,Long);
								 trans(Conf, Traspuesta), cuatroH(Color, Traspuesta, 0, Resultado), mostrar4(Aux, Resultado,Long);
								 ced(Color,Conf,Resultado).
cuatroAux(Color, Conf, Contador, Resultado):- cuatroH(Color, Conf, Contador, Resultado).

%Cuatro Horizontal (utilizado para vertical)

cuatroH(Color, [[]|Ys], Contador, Resultado) :- cuatroH(Color, Ys, 0, Resultado).
cuatroH(Color, Conf, 4, []).
cuatroH(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, [p(X,Y,Color)|Resultado]):- Contador1 is Contador+1, cuatroH(Color, [Xs|Ys], Contador1, Resultado).
cuatroH(Color, [[p(X,Y,R)|Xs]|Ys], Contador, Resultado):- cuatroH(Color, [Xs|Ys], 0, Resultado).

%Cuatro diagonal

ced(Color,[Lista | [ Lista2 | Lista3 ] ],Resultado):- cedRecorrerFila(Color,[Lista | [ Lista2 | Lista3 ] ],1,Resultado);cedRecorrerFila(Color,[ Lista2 | Lista3 ],2,Resultado);cedRecorrerFila(Color,Lista3,3,Resultado).

cedRecorrerFila(Color,[PrimeraFila | RestoFilas],X,Res):- cedFila(0,Color,PrimeraFila,X,Y,RestoFilas),generar(0,Color,X,Y,Res).

cedFila(4,Color,Fila,PosActual,ColActual,FilasSiguientes).
cedFila(Cont,Color, Fila, PosActual, ColActual, [ProxFila | FilasSiguientes]):- buscar(PosActual,ColActual,Fila,Color), Cont1 is Cont+1, Pos is PosActual+1, Col is ColActual+1,cedFila(Cont1,Color,ProxFila,Pos,Col,FilasSiguientes).



% metodos auxiliares

% Muestra los ultimos 4 elementos de una lista

mostrar4([_|Xs], Zs, Cont):- Cont1 is Cont-1, mostrar4(Xs, Zs, Cont1).
mostrar4(Zs, Zs, 4).

%Busca en una lista la posicion cuyas coordenadas son X,Y y permite obtener su color actual.
buscar(X,Y,[p(X,Y,Color) | RestoFila],Color).
buscar(X,Y,[p(W,Z,C) | RestoFila],Color):- buscar(X,Y,RestoFila,Color).

%Genera una lista con la diagonal ganadora

generar(4,Color,X,Y,Res).
generar(Cont,Color,X,Y,[p(X,Y,Color)| Res]):- Cont1 is Cont+1, X1 is X+1, Y1 is Y+1, generar(Cont1,Color,X1,Y1,Res).

% Obtiene la longitud de una lista.

longitud([],L, L).
longitud([X|Xs], R, L) :- R1 is R+1, longitud(Xs, R1, L).

% obtiene la matriz transversa [[1,2,3][4,5,6],[7,8,9]] =  [[1,4,7],[2,5,8],[3,6,9]].

trans([],[]).
trans([[]|_], []):-!.
trans([S|R], [L|L1]) :- trans(S, R, L, M), trans(M, L1).

trans([], _,[],[]).
trans([S1|S2], [], [S1|L1], [S2|M]):- trans([], [], L1, M).
trans([S1|S2], [R1|R2], [S1|L1], [S2|M]):- trans(R1, R2, L1, M).

% disponible(Ranura)

disponible(Ranura, Conf) :- en(Ranura, Conf, Contenido), Contenido = vacio.

% colocar ficha(+Color, +Ranura, +Conf, -ConfRes), G2130

colocar_ficha(Color, [X, Y] ,[], []).
colocar_ficha(Color, [X, Y] , [ [p(X, Y, vacio)|[]] | Xs ], [ p(X,Y,Color) | Zs ]) :- colocar_ficha(Color, [X,Y], Xs, Zs).
colocar_ficha(Color, [X, Y] , [ [p(Z, W, C)|[]] | Xs ], [ p(Z,W,C) | Zs ]) :- colocar_ficha(Color, [X,Y], Xs, Zs).
colocar_ficha(Color, [X, Y] , [ [p(X, Y, vacio)|Ys] | Xs ], [ p(X,Y,Color) | Zs ]) :- colocar_ficha(Color, [X,Y], [Ys|Xs], Zs).
colocar_ficha(Color, [X, Y] , [ [p(Z, W, C)|Ys] | Xs ], [ p(Z,W,C) | Zs ]) :- colocar_ficha(Color, [X,Y], [Ys|Xs], Zs).

% jugada maquina(+Color, +Conf, -Ranura)