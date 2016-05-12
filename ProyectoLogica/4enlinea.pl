% en(+p, +Conf, -Contenido)

en([X, Y], [ [p(Z,W,R)|Rs] | Xs], Res) :- en([X,Y], [Rs|Xs], Res).
en([X, Y], [ [p(Z,W,R)|[]] | Xs], Res) :- en([X,Y], Xs, Res).
en([X,Y], [ [p(X,Y,R)| Rs] | Xs], R).

% conf = [[p(4,1,a), p(4,2,a), p(4,3,a), p(4,4,a)],[p(3,1,a), p(3,2,a), p(3,3,a), p(3,4,a)],[p(2,1,a), p(2,2,a), p(2,3,a), p(2,4,a)],[p(1,1,a), p(1,2,a), p(1,3,a), p(1,4,a)]]
% cuatro en linea(+Color, +Conf, -CuatroEnLinea) 

cuatro(Color, Conf, Resultado):- cuatroAux(Color, Conf, 0, Aux), longitud(Aux,0,Rictor), mostrar4(Aux, Resultado,Rictor).
cuatroAux(Color, Conf, Contador, Resultado):- cuatroH(Color, Conf, Contador, Resultado, Conf).

cuatroH(Color, [[]|Ys], Contador, Resultado, Conf2) :- cuatroH(Color, Ys, 0, Resultado, Conf2).
cuatroH(Color, Conf, 4, [], Conf2).
cuatroH(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, [p(X,Y,Color)|Resultado], Conf2):- Contador1 is Contador+1, cuatroH(Color, [Xs|Ys], Contador1, Resultado, Conf2).
cuatroH(Color, [[p(X,Y,R)|Xs]|Ys], Contador, Resultado,Conf2):- cuatroH(Color, [Xs|Ys], 0, Resultado, Conf2).

cuatroH(Color, [], Contador, Vertical, Conf2) :- trans(Conf2, Traspuesta), cuatroV(Color, Traspuesta, 0, Vertical, Conf2).

cuatroV(Color, [[]|Ys], Contador, Resultado, Conf2) :- cuatroV(Color, Ys, 0, Resultado, Conf2).
cuatroV(Color, Conf, 4, [], Conf2).
cuatroV(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, [p(X,Y,Color)|Resultado], Conf2):- Contador1 is Contador+1, cuatroV(Color, [Xs|Ys], Contador1, Resultado, Conf2).
cuatroV(Color, [[p(X,Y,R)|Xs]|Ys], Contador, Resultado,Conf2):- cuatroV(Color, [Xs|Ys], 0, Resultado, Conf2).

cuatroV(Color, [], Contador, Diagonal, Conf2) :- CuatroD(Color, Conf2, 0, Diagonal, Conf2).

cuatroD(Color, [[p(X,Y,Color)|Xs]|Ys], Contador, Diagonal, Conf2).

% BUSCAR DIAGONAL CON EN

diag(Color, [[p(X,Y, Color)|Xs], |Zs], Contador, Zs) :- X1 is X-1, Y1 is Y+1, en([X1,Y1],Zs,Color2), Color = Color2, diag(Color,)

% ejemplo busqueda diagonal matriz [[a,2,3],[1,a,3],[3,2,a]]

diagonal(Color, Conf, 3, []).
diagonal(Color,[[Color|Xs]|Zs], Contador, [Color| Rs]):- Contador1 is Contador+1, checkList(Zs,Contador1, Ps), diagonal(Color, Ps, Contador1, Rs).
%diagonal(Color,[[R|Xs]|Zs], Contador, Rs) :- diagonal(Color, Zs, 0, Rs).

checkList([[_|Xs]|Zs],Contador, [Xs|Zs]) :- Contador = 1.
checkList([[_,_|Xs]|Zs],Contador, [Xs|Zs]) :- Contador = 2.
checkList([[_,_,_|Xs]|Zs], Contador, [Xs|Zs]) :- Contador = 3.
checkList([[_,_,_|Xs]|Zs], Contador, [Xs|Zs]) :- Contador = 4.


ver([],[]).
ver([[p(X,Y,Color)|Xs]|Ys], [Color|Zs]) :- ver([Xs|Ys], Zs).

%cuatro(Color, Conf, Resultado) :- cuatroAux(Color, Conf, 0, Aux), longitud(Aux,0,Rictor), mostrar4(Aux, Resultado,Rictor).
%cuatroAux(Color, Conf, Contador, Resultado) :- cuatroH(Color, Conf, Contador, Resultado). 
%cuatroH(Color, Conf, 4, []).
%cuatroH(Color, [], 4, []).
%cuatroH(Color, [p(Z,W,Color) | Zs ], Contador, [ p(Z,W,Color) | Resultado]) :- Contador1 is Contador+1, cuatroH(Color, Zs, Contador1, Resultado).
%cuatroH(Color, [p(Z,W,R) | Zs ], Contador, Resultado) :- cuatroH(Color, Zs, 0, Resultado).


% metodos auxiliares

% Muestra los ultimos 4 elementos de una lista

mostrar4([_|Xs], Zs, Cont):- Cont1 is Cont-1, mostrar4(Xs, Zs, Cont1).
mostrar4(Zs, Zs, 4).

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


obt([X|Xs], Zs) :- obtAux(X, Xs, Zs).
obtAux( [W|Ws], [X| Xs], W).





%cuatro_en_linea(C, [ p(Z,W,C) | Xs], R) :- 

disponible(Ranura, Conf) :- en(Ranura, Conf, Contenido), Contenido = v.

% colocar ficha(+Color, +Ranura, +Conf, -ConfRes), G2130

colocar_ficha(Color, [X, Y] ,[], []).
colocar_ficha(Color, [X, Y] , [ p(X, Y, C) | Xs ], [ p(X,Y,Color) | Zs ]) :- colocar_ficha(Color, [X,Y], Xs, Zs).
colocar_ficha(Color, [X, Y] , [ p(Z, W, C) | Xs ], [ p(Z,W,C) | Zs ]) :- colocar_ficha(Color, [X,Y], Xs, Zs).

% jugada maquina(+Color, +Conf, -Ranura)

% EJEMPLO

conflow(Xs, Zs) :- lista(Xs, Zs), 
lista([], []).
lista([X|Xs], Zs) :- X=2, lista(Xs, Zs).
lista([X|Xs], [X|Zs]) :- not(X=2), lista(Xs, Zs).

lista2([X|Xs], Zs):- lista2(Xs, [X|Zs])











