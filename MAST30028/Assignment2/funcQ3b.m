
function [f, J] = funcQ3b(xx)
    %this function is wriittern by the user
    %with the expression for function and J
    %that take a  vector as input
    %return two value
    %f: the value of systems of functions at x
    %the jarcobian at that point
    x = xx(1);
    y = xx(2);
    f = [x^2 - y - 1; -x + y^2 - 1];
    %the Jarcobian is also input by the user
    J = [2*x -1;-1 2*y];
end