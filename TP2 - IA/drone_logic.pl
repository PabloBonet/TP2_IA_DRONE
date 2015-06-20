% The code below is neccessary for the framework to modify these sentences.


%:- dynamic radar/3, percepcion/6, victimario/3, executedAction/2, actualSituation/1, agenteEnPosicion/3.

:- dynamic percepcion/6, executedAction/2, actualSituation/1, agenteEnPosicion/3, visitada/3, victimario/3.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                            Estado mapa                                         %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 adyacente(0,0,70,0).
 adyacente(70,0,0,0).
 adyacente(0,0,0,65).
 adyacente(0,65,0,0).
 adyacente(70,0,130,0).
 adyacente(130,0,70,0).
 adyacente(130,0,130,65).
 adyacente(130,65,130,0).
 adyacente(0,65,70,65).
 adyacente(70,65,0,65).
 adyacente(0,65,0,135).
 adyacente(0,135,0,65).
 adyacente(70,65,70,135).
 adyacente(70,135,70,65).
 adyacente(70,135,130,135).
 adyacente(130,135,70,135).
 adyacente(0,135,70,135).
 adyacente(70,135,0,135).
 adyacente(70,65,130,65).
 adyacente(130,65,70,65).
 adyacente(130,65,130,135).
 adyacente(130,135,130,65).
 adyacente(70,0,70,65).
 adyacente(70,65,70,0).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                            Reglas diagnósticas                                  %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

radar(C,I,J) :- percepcion([[C,I,J],_,_,_,_,_,_,_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,[C,I,J],_,_,_,_,_,_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,[C,I,J],_,_,_,_,_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,_,[C,I,J],_,_,_,_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,_,_,[C,I,J],_,_,_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,_,_,_,[C,I,J],_,_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,_,_,_,_,[C,I,J],_,_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,_,_,_,_,_,[C,I,J],_],_,_,_,_,_), C > 0.
radar(C,I,J) :- percepcion([_,_,_,_,_,_,_,_,[C,I,J]],_,_,_,_,_), C > 0.


victimario(I,J,S) :- percepcion(_,[[I,J],_,_,_],_,_,_,_), actualSituation(S), I >= 0, J >= 0.
victimario(I,J,S) :- percepcion(_,[_,[I,J],_,_],_,_,_,_), actualSituation(S), I >= 0, J >= 0.
victimario(I,J,S) :- percepcion(_,[_,_,[I,J],_],_,_,_,_), actualSituation(S), I >= 0, J >= 0.
victimario(I,J,S) :- percepcion(_,[_,_,_,[I,J]],_,_,_,_), actualSituation(S), I >= 0, J >= 0.


energia(E,S) :- percepcion(_,_,_,_,_,E), actualSituation(S).

agenteEnPosicion(X,Y,S) :- percepcion(_,_,_,X,Y,_), actualSituation(S).

victimarioEncontrado(ID,S) :- percepcion(_,_,ID,_,_,_), ID > 0, actualSituation(S).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Reglas causales                                     %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

visitada(X,Y,S) :- agenteEnPosicion(X,Y,S).

movementAction(S) :- executedAction(alNorte,S).
movementAction(S) :- executedAction(alEste,S).
movementAction(S) :- executedAction(alSur,S).
movementAction(S) :- executedAction(alOeste,S).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Axiomas de estado sucesor                           %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Cuando la acción ejecutada no cambia la posición actual
% Marcar esquina visitada
est(S1) :- S1 > 0,S is S1-1, executedAction(irNorte,S), adyacenteAlNorte(Xn,Yn,S),not(visitada(Xn,Yn,S1)),asserta(visitada(Xn,Yn,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irEste,S), adyacenteAlEste(Xe,Ye,S), not(visitada(Xe,Ye,S1)),asserta(visitada(Xe,Ye,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irSur,S), adyacenteAlSur(Xs,Ys,S), not(visitada(Xs,Ys,S1)),asserta(visitada(Xs,Ys,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irOeste,S), adyacenteAlOeste(Xo,Yo,S), not(visitada(Xo,Yo,S1)),asserta(visitada(Xo,Yo,S1)).
est(S1) :- S1 > 0,S is S1-1,visitada(X,Y,S),not(visitada(X,Y,S1)),asserta(visitada(X,Y,S1)).
% Decrementar energía

est(S1) :- S1 > 0,S is S1-1, movementAction(S), agenteEnPosicion(X,Y,S), radar(C,X,Y), energiaAgente(E,S), E>0, asserta(energiaAgente(E-1,S1)).
est(S1) :- S1 > 0,S is S1-1, movementAction(S), agenteEnPosicion(X,Y,S), not(radar(C,X,Y)), energiaAgente(E,S), E>0, asserta(energiaAgente(E-2,S1)).

%Marca posicion del victimario
est(S1) :- S1 > 0,S is S1-1,victimario(X,Y,S),not(victimario(X,Y,S1)),asserta(victimario(X,Y,S1)).


% Cuando la acción ejecutada cambia la posición actual
est(S1) :- S1 > 0,S is S1-1, executedAction(irNorte,S), adyacenteAlNorte(Xn,Yn,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xn,Yn,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irEste,S), adyacenteAlEste(Xe,Ye,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xe,Ye,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irSur,S), adyacenteAlSur(Xs,Ys,S), energiaAgente(E,S), E > 0, asserta(agenteEnPosicion(Xs,Ys,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irOeste,S), adyacenteAlOeste(Xo,Yo,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xo,Yo,S1)).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Ranking de acciones                                 %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Es excelente ir al norte, este, sur u oeste si la posición del drone es adyacente a la del victimario
excelente(irNorte,S) :- adyacenteAlNorte(Xn,Yn,S), victimario(Xn,Yn,S), actualSituation(S).
excelente(irEste,S) :- adyacenteAlEste(Xn,Yn,S), victimario(Xn,Yn,S), actualSituation(S).
excelente(irSur,S) :- adyacenteAlSur(Xn,Yn,S), victimario(Xn,Yn,S), actualSituation(S).
excelente(irOeste,S) :- adyacenteAlOeste(Xn,Yn,S), victimario(Xn,Yn,S), actualSituation(S).

% Es muy bueno ir hacia el norte, este, sur u oeste si hay victimarios en esas direcciones y todavia no se visito la esquina
muyBueno(irNorte,S) :- agenteEnPosicion(X,Y,S), alNorte([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)), victimario(I,J,S).
muyBueno(irEste,S) :- agenteEnPosicion(X,Y,S), alEste([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)), victimario(I,J,S).
muyBueno(irSur,S) :- agenteEnPosicion(X,Y,S), alSur([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)), victimario(I,J,S).
muyBueno(irOeste,S) :- agenteEnPosicion(X,Y,S), alOeste([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)), victimario(I,J,S).

%Es bueno ir hacia el norte, este, sur u oeste si hay personas en esas direcciones y todavia no se visito la esquina
bueno(irNorte,S) :- agenteEnPosicion(X,Y,S), alNorte([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)).
bueno(irEste,S) :- agenteEnPosicion(X,Y,S), alEste([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)).
bueno(irSur,S) :- agenteEnPosicion(X,Y,S), alSur([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)).
bueno(irOeste,S) :- agenteEnPosicion(X,Y,S), alOeste([I,J],[X,Y],S), radar(C,I,J), C>0, actualSituation(S), not(visitada(I,J,S)).


% Es regular ir hacia el norte, este, sur u oeste si no hay personas en esas direcciones
regular(irNorte,S) :- agenteEnPosicion(X,Y,S), alNorte([I,J],[X,Y],S), actualSituation(S), not(visitada(I,J,S)).
regular(irEste,S) :- agenteEnPosicion(X,Y,S), alEste([I,J],[X,Y],S), actualSituation(S), not(visitada(I,J,S)).
regular(irSur,S) :- agenteEnPosicion(X,Y,S), alSur([I,J],[X,Y],S), actualSituation(S), not(visitada(I,J,S)).
regular(irOeste,S) :- agenteEnPosicion(X,Y,S), alOeste([I,J],[X,Y],S), actualSituation(S), not(visitada(I,J,S)).



bestAction(noAction,S) :- goalReached(S),!.
bestAction(X,S) :- excelente(X,S),!.
bestAction(X,S) :- muyBueno(X,S),!.
bestAction(X,S) :- bueno(X,S),!.
bestAction(X,S) :- regular(X,S),!.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Sentencias extras                                   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Posición adyacente hacia una dirección (norte, este, sur, oeste) en la situación S
adyacenteAlNorte(Xn, Yn, S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,Xn,Yn), X == Xn, Y>Yn.
adyacenteAlEste(Xe, Ye, S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,Xe,Ye), Y == Ye, X<Xe.
adyacenteAlSur(Xs, Ys, S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,Xs,Ys), X == Xs, Y<Ys.
adyacenteAlOeste(Xo, Yo, S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,Xo,Yo), Y == Yo, X>Xo.


alNorte([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacenteAlNorte(I,J,S).
alEste([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacenteAlEste(I,J,S).
alSur([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacenteAlSur(I,J,S).
alOeste([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacenteAlOeste(I,J,S).

alNorte([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,W,Z), adyacente(W,Z,I,J), X == I, Y>J.
alEste([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,W,Z), adyacente(W,Z,I,J), Y == J, X<I.
alSur([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,W,Z), adyacente(W,Z,I,J), X == I, Y<J.
alOeste([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), adyacente(X,Y,W,Z), adyacente(W,Z,I,J), Y == J, X>I.



%% Para saber cuándo el agente alcanza el objetivo
mapaVisitado(S) :- visitada(0,0,S),visitada(70,0,S),visitada(130,0,S),
                   visitada(0,65,S),visitada(70,65,S),visitada(130,65,S),
                   visitada(0,135,S),visitada(70,135,S),visitada(130,135,S).

%%% AGREGAR LOS ADYACENTES (adyacente(X,Y,I,J)), RADAR (radar(C,I,J)) Y EL VICTIMARIO (victimario(Xn,Yn,S))

goalReached(S) :- victimarioEncontrado(ID,S).
goalReached(S) :- mapaVisitado(S).

