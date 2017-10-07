function [f, J] = funcQ3c(xx)
    %this is the function handler for question 3C
    %it takes a 2*1 column vector as input
    %and return a 2*1 column vector
    %as well as the jarcobian at xx of f
    %where jarcobian and f need to be hardcoded by user
    x1 = xx(1);
    x2 = xx(2);
    f = [x1^2 + x1*x2^3 - 9; 3*x1^2*x2 - x2^3-4];
    J = [2*x1 + x2^3 , 3*x1*x2^2; 6*x1*x2 , 3*x1^2 - 3*x2^2];
end