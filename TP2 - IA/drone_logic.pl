% The code below is neccessary for the framework to modify these sentences.

%:- dynamic actualSituation/1,perception/5,executedAction/2,position/3,
%food/3,enemy/3,empty/3,energy/2.

:- dynamic radar/3, percepcion/7, victimario/3.  %fFALTA VER!!!!!!!!!!!!!!

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                            Reglas diagn�sticas                                  %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

radar(C,I,J) :- percepcion([[C,I,J],_,_,_,_,_,_,_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,[C,I,J],_,_,_,_,_,_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,[C,I,J],_,_,_,_,_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,_,[C,I,J],_,_,_,_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,_,_,[C,I,J],_,_,_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,_,_,_,[C,I,J],_,_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,_,_,_,_,[C,I,J],_,_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,_,_,_,_,_,[C,I,J],_],_,_,_,_,_,S), actualSituation(S).
radar(C,I,J) :- percepcion([_,_,_,_,_,_,_,_,[C,I,J]],_,_,_,_,_,S), actualSituation(S).


victimario(I,J,S) :- percepcion(_,[[I,J],_,_,_],_,X,Y,_,S), actualSituation(S), alNorte([I,J], [X,Y],S).
victimario(I,J,S) :- percepcion(_,[_,[I,J],_,_],_,X,Y,_,S), actualSituation(S), alEste([I,J], [X,Y],S).
victimario(I,J,S) :- percepcion(_,[_,_,[I,J],_],_,X,Y,_,S), actualSituation(S), alSur([I,J], [X,Y],S).
victimario(I,J,S) :- percepcion(_,[_,_,_,[I,J]],_,X,Y,_,S), actualSituation(S), alOeste([I,J], [X,Y],S).


energia(E,S) :- percepcion(_,_,_,_,_,E,S), actualSituation(S).

posicionAgente(X,Y,S) :- percepcion(_,_,_,X,Y,_,S), actualSituation(S).

victimarioEncontrado(ID,S) :- percepcion(_,_,ID,_,_,_,S), ID > 0, actualSituation(S).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Reglas causales                                     %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

visitada(X,Y,S) :- posicionAgente(X,Y,S).

movementAction(S) :- executedAction(alNorte,S).
movementAction(S) :- executedAction(alEste,S).
movementAction(S) :- executedAction(alSur,S).
movementAction(S) :- executedAction(alOeste,S).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Axiomas de estado sucesor                           %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Cuando la acci�n ejecutada no cambia la posici�n actual
% Marcar esquina visitada
est(S1) :- S1 > 0,S is S1-1, executedAction(irNorte,S), agenteEnPosicion(X,Y,S), adyacenteAlNorte(Xn,Yn,S),not(visitada(Xn,Yn,S1)),asserta(visitada(Xn,Yn,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irEste,S), agenteEnPosicion(X,Y,S), adyacenteAlEste(Xe,Ye,S), not(visitada(Xe,Ye,S1)),asserta(visitada(Xe,Ye,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irSur,S), agenteEnPosicion(X,Y,S), adyacenteAlSur(Xs,Ys,S), not(visitada(Xs,Ys,S1)),asserta(visitada(Xs,Ys,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irOeste,S), agenteEnPosicion(X,Y,S), adyacenteAlOeste(Xo,Yo,S), not(visitada(Xo,Yo,S1)),asserta(visitada(Xo,Yo,S1)).
est(S1) :- S1 > 0,S is S1-1,visitada(X,Y,S),not(visitada(X,Y,S1)),asserta(visitada(X,Y,S1)).
% Decrementar energ�a
est(S1) :- S1 > 0,S is S1-1, movementAction(S), agenteEnPosicion(X,Y,S), not(radar(_,X,Y)), energiaAgente(E,S), E>0, asserta(energiaAgente(E-2,S1),).
est(S1) :- S1 > 0,S is S1-1, movementAction(S), agenteEnPosicion(X,Y,S), radar(_,X,Y), energiaAgente(E,S), E>0, asserta(energiaAgente(E-1,S1),).

% Cuando la acci�n ejecutada cambia la posici�n actual
est(S1) :- S1 > 0,S is S1-1, executedAction(irNorte,S), agenteEnPosicion(X,Y,S), adyacenteAlNorte(Xn,Yn,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xn,Yn,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irEste,S), agenteEnPosicion(X,Y,S), adyacenteAlEste(Xe,Ye,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xe,Ye,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irSur,S), agenteEnPosicion(X,Y,S), adyacenteAlSur(Xs,Ys,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xs,Ys,S1)).
est(S1) :- S1 > 0,S is S1-1, executedAction(irOeste,S), agenteEnPosicion(X,Y,S), adyacenteAlOeste(Xo,Yo,S), energiaAgente(E,S), E > 0,asserta(agenteEnPosicion(Xo,Yo,S1)).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Ranking de acciones                                 %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Es excelente ir al norte, este, sur u oeste si la posici�n del drone es adyacente a la del victimario
excelente(irNorte,S) :- adyacenteAlNorte(Xn,Yn,S), victimario(Xn,Yn,S).
excelente(irEste,S) :- adyacenteAlEste(Xn,Yn,S), victimario(Xn,Yn,S).
excelente(irSur,S) :- adyacenteAlSur(Xn,Yn,S), victimario(Xn,Yn,S).
excelente(irOeste,S) :- adyacenteAlOeste(Xn,Yn,S), victimario(Xn,Yn,S).

% Es muy bueno ir hacia el norte, este, sur u oeste si hay personas en esas direcciones
muyBueno(irNorte,S) :- agenteEnPosicion(X,Y,S), alNorte([I,J],[X,Y],S), radar(_,I,J), adyacenteAlNorte(Xn,Yn,S).
muyBueno(irEste,S) :- agenteEnPosicion(X,Y,S), alEste([I,J],[X,Y],S), radar(_,I,J), adyacenteAlEste(Xe,Ye,S).
muyBueno(irSur,S) :- agenteEnPosicion(X,Y,S), alSur([I,J],[X,Y],S), radar(_,I,J), adyacenteAlSur(Xs,Ys,S).
muyBueno(irOeste,S) :- agenteEnPosicion(X,Y,S), alOeste([I,J],[X,Y],S), radar(_,I,J), adyacenteAlOeste(Xo,Yo,S).

% Es bueno ir hacia el norte, este, sur u oeste si no hay personas en esas direcciones
bueno(irNorte,S) :- agenteEnPosicion(X,Y,S), alNorte([I,J],[X,Y],S), �radar(_,I,J).
bueno(irEste,S) :- agenteEnPosicion(X,Y,S), alEste([I,J],[X,Y],S), �radar(_,I,J).
bueno(irSur,S) :- agenteEnPosicion(X,Y,S), alSur([I,J],[X,Y],S), �radar(_,I,J).
bueno(irOeste,S) :- agenteEnPosicion(X,Y,S), alOeste([I,J],[X,Y],S), �radar(_,I,J).

bestAction(noAction,S) :- goalReached(S),!.
bestAction(X,S) :- excelente(X,S),!.
bestAction(X,S) :- muyBueno(X,S),!.
bestAction(X,S) :- bueno(X,S),!.
bestAction(X,S) :- regular(X,S),!.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Sentencias extras                                   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Posici�n adyacente hacia una direcci�n (norte, este, sur, oeste) en la situaci�n S
adyacenteAlNorte(Xn, Yn, S) :- agenteEnPosicion(X,Y,S), X=:=Xn, Y>Yn, Yn<(Y-75), adyacente(X,Y,Xn,Yn).
adyacenteAlNorte(Xe, Ye, S) :- agenteEnPosicion(X,Y,S), Y=:=Ye, X<Xe, Xe<(X+75), adyacente(X,Y,Xe,Ye).
adyacenteAlNorte(Xs, Ys, S) :- agenteEnPosicion(X,Y,S), X=:=Xs, Y<Ys, Ys<(Y+75), adyacente(X,Y,Xs,Ys).
adyacenteAlNorte(Xo, Yo, S) :- agenteEnPosicion(X,Y,S), Y=:=Yo, X>Xo, Xo<(X-75), adyacente(X,Y,Xn,Yn).

% Posici�n ubicada en una direcci�n desde la esquina actual en la situaci�n S
alNorte([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), X=:=I, Y>J, adyacente(X,Y,I,J), radar(_,I,J).
alEste([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), Y=:=J, X<I, adyacente(X,Y,I,J), radar(_,I,J).
alSur([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), X=:=I, Y<J, adyacente(X,Y,I,J), radar(_,I,J).
alOeste([I,J],[X,Y],S) :- agenteEnPosicion(X,Y,S), Y=:=J, X>I, adyacente(X,Y,I,J), radar(_,I,J).


%% Para saber cu�ndo el agente alcanza el objetivo
mapaVisitado(S) :- visitado(0,0,S),visitado(70,0,S),visitado(130,0,S),
                   visitado(0,65,S),visitado(70,65,S),visitado(130,65,S),
                   visitado(0,135,S),visitado(70,135,S),visitado(130,135,S).

%%% AGREGAR LOS ADYACENTES (adyacente(X,Y,I,J)), RADAR (radar(C,I,J)) Y EL VICTIMARIO (victimario(Xn,Yn,S))

%%goalReached(S) :- worldEmpty(S).
goalReached(S) :- victimarioEncontrado(ID,S).
goalReached(S) :- mapaVisitado(S).



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Improvement proposal                                %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% The agent could save the average enery lost when fighting and moving. This way,
% when evaluating actions, it can decide to fight if and only if the average energy
% lost when fighting is less than the actual energy. The same can be done when
% it decides to move.

%%% Count elements in a list
% count([],0):-!.
% count([_|Ls],C):-count(Ls,T),C is T+1.

%%% Calculate the average in a list
% average(L,P):-count(L,C),C=:=0,P is 0,!.
% average(L,P):-sumlist(L,S), length(L,C), P is (S/C).

% fightingEnergyData([],1):-!.
% fightingEnergyData([D|Ds],S1):-S0 is S1-1,executedAction(pelear,S0),energy(E0,S0),energy(E1,S1),D is E1-E0,fightingEnergyData(Ds,S0),!.
% fightingEnergyData(D,S1):-S0 is S1-1,fightingEnergyData(D,S0),!.

% movingEnergyData([],1):-!.
% movingEnergyData([D|Ds],S1):-S0 is S1-1,movementAction(S0),energy(E0,S0),energy(E1,S1),D is E1-E0,movingEnergyData(Ds,S0),!.
% movingEnergyData(D,S1):-S0 is S1-1,movingEnergyData(D,S0),!.

% averageWhenFighting(0,1):-!.
% averageWhenFighting(P,S):-fightingEnergyData(Es,S),average(Es,P).

% averageWhenMoving(0,1):-!.
% averageWhenMoving(P,S):-movingEnergyData(Es,S),average(Es,P).



