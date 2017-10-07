function Q4()
    %authorship: Siyi Guo 737008
    
    %basic setup for least squares problem
    x = [1.02 .95 .87 .77 .67 .56 .44 .30 .16 .01]';
    y = [0.39 .32 .27 .22 .18 .15 .13 .12 .13 .15]';
    A = [y.^2 x.*y x y ones(numel(y),1)];
    b = [x.^2];
    
    %question4a
    %since it is rank efficient as rank(A) = num(column)
    %use Qr factorization for A
    [Q, R] = qr(A);
    constant = R \ (Q'*b);
    
    a = -1;
    b = constant(1);
    c = constant(2);
    d = constant(3);
    e = constant(4);
    f = constant(5);
    
    [X, Y] = meshgrid(sort(x),sort(y));
    Z = a*X.^2 + b*X.*Y + c*Y.^2 + d*X + e*Y + f;
    contour(X,Y,Z, [0 0]);
    hold on;
    plot(x,y,  's');
    hold on;
    
    %question4b
    x = x + (rand(numel(x),1) - 0.5)/100;
    y = y + (rand(numel(x),1) - 0.5)/100;
    A = [y.^2 x.*y x y ones(numel(y),1)];
    b = [x.^2];
    
    [Q, R] = qr(A);
    constant = R \ (Q'*b);
    
    a = -1;
    b = constant(1);
    c = constant(2);
    d = constant(3);
    e = constant(4);
    f = constant(5);
    
    [X, Y] = meshgrid(sort(x),sort(y));
    Z = a*X.^2 + b*X.*Y + c*Y.^2 + d*X + e*Y + f;
    contour(X,Y,Z, [0 0]);
    hold on;
    plot(x,y,  'o');
    
end