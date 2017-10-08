function Q4()
    close all;
    %authorship: Siyi Guo 737008
    
    %basic setup for least squares problem
    x = [1.02 .95 .87 .77 .67 .56 .44 .30 .16 .01]';
    y = [0.39 .32 .27 .22 .18 .15 .13 .12 .13 .15]';
    A = [y.^2 x.*y x y ones(numel(y),1)];
    datapoints = [x.^2];
    
    
    figure;
%%%%%question4a
    %since it is rank efficient as rank(A) = num(column)
    %use Qr factorization for A
    constant = A \ datapoints;
    
    a = -1;
    b = constant(1);
    c = constant(2);
    d = constant(3);
    e = constant(4);
    f = constant(5);
    
    [X, Y] = meshgrid(-1:0.05:1.5, -0.5:0.05:2);
    Z = a*X.^2 + c*X.*Y + b*Y.^2 + d*X + e*Y + f;
    %plot all the 
    %[v v] gives a specific level of contour
    %[v1 v2 v3] gives levels that specific by contour
    figure(1)
    [C,h] = contour(X,Y,Z, [0 0], 'r','DisplayName', 'Qa orbit');
    hold on
    plot(x,y,  'xb', 'DisplayName', 'QaDatapoints', 'markers', 12);
    hold off
    legend('show')
    
    figure(2)
    [C,h] = contour(X,Y,Z, [0 0], 'r','DisplayName', 'Qa orbit');
    hold on;
    plot(x,y,  'xr', 'DisplayName', 'QaDatapoints');
    
    [Ua,Sa,Va] = svd(A);
    display(Sa);
    display(constant');
%%%%question4b
    x2 = x + (rand(numel(x),1) - 0.5)/100;
    y2 = y + (rand(numel(x),1) - 0.5)/100;
    A = [y2.^2 x2.*y2 x2 y2 ones(numel(y2),1)];
    datapoints = [x2.^2];
    
    [Q, R] = qr(A);
    constant = R \ (Q'*datapoints);
    
    a = -1;
    b = constant(1);
    c = constant(2);
    d = constant(3);
    e = constant(4);
    f = constant(5);

    [X, Y] = meshgrid(-1:0.05:1.5, -0.5:0.05:2);
    Z = a*X.^2 + c*X.*Y + b*Y.^2 + d*X + e*Y + f;
    [C,h] = contour(X,Y,Z, [0 0],'b','DisplayName', 'Qb orbit');
    plot(x2,y2,  'ob', 'DisplayName', 'QbDatapoints', 'markers', 12);
    hold off
    
    [Ub,Sb,Vb] = svd(A);
    display(Sb);
    display(constant');
%%%%legend the graph
    legend( 'show', 'Location', 'southeast');
end