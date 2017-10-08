function ass2Q3cDriver()
    close all;
    %Authorship:Siyi GUo 737008
    %This is the driver function for question B of Question 3 in
    %Assignment2
    %when using this funtion, put ass2Q3.m and func under the path
    
    %1st fuess
    [roots, count, resids, history1] = ass2Q3(@funcQ3c, [1.2;2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f], initial guess is [%1.1f %1.1f]\n', count, roots, [1.2;2.5]);
    figure(1)
    plot(history1(1,:), history1(2,:),'-ro','DisplayName', 'x1 iteration history')
    legend('[1.2;2.5] iteration history');
    figure(5)
    semilogy([1:1:count + 1], resids, '-s', 'DisplayName', 'convergence history')
    legend('[1.2;2.5] residual history');
    %2nd fuess
    [roots, count, resids, history2] = ass2Q3(@funcQ3c, [-2;2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f], initial guess is [%d %1.1f]\n', count, roots, [-2;2.5]);
    figure(2)
    plot(history2(1,:), history2(2,:),'-k<')
    legend('[-2;2.5] iteration history');
    figure(6)
    semilogy([1:1:count + 1], resids, '-s')
    legend('[-2;2.5] residual history');
    hold on;
    %3rd fuess
    [roots, count, resids, history3] = ass2Q3(@funcQ3c, [-1.2;-2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f], initial guess is [%1.1f %1.1f]\n', count, roots, [-1.2;-2.5]);
    figure(3)
    plot(history3(1,:), history3(2,:),'-gs')
    legend('[-1.2;-2.5] iteration history');
    figure(7)
    semilogy([1:1:count + 1], resids, '-s')
    legend('[-1.2;-2.5] residual history');
    hold on;
    %4th fuess
    [roots, count, resids, history4] = ass2Q3(@funcQ3c, [2;-2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f], initial guess is [%d %1.1f]\n', count, roots, [2;-2.5]);
    figure(4)
    plot(history4(1,:), history4(2,:),'-x')
    legend('[2;-2.5] iteration history');
    figure(8)
    semilogy([1:1:count + 1], resids, '-s')
    legend('[2;-2.5] residual history');
    hold on;
    
    figure(9)
    plot_system_equations();
    hold on;
    plot(history1(1,:), history1(2,:),'-ro','DisplayName', 'x1 iteration history');
    plot(history2(1,:), history2(2,:),'-k<', 'DisplayName', 'x2 iteration history')
    plot(history3(1,:), history3(2,:),'-gs', 'DisplayName', 'x3 iteration history')
    legend('show', 'location', 'southeast')
    
    figure(10)
    [X1, X2] = meshgrid(-25:0.1:4, -4:0.1:25);
    Z1 = X1.^2 + X1.*X2.^3  - 9;
    Z2 = 3*X1.^2.*X2 - X2.^3 - 4;
    [C,h] = contour(X1,X2,Z1,[-40:10:40 0:1000:5000 0:5000:25000], 'c');
    clabel(C,h);
    hold on
    [C,h] = contour(X1,X2,Z2,[-40:10:40 0:1000:5000 0:5000:25000], 'm');
    clabel(C,h);
    plot(history4(1,:), history4(2,:),'-x', 'DisplayName', 'X4 iteration history')
    legend('show')
end

function plot_system_equations()
    [X1, X2] = meshgrid(-4:0.1:4, -4:0.1:4);
    Z1 = X1.^2 + X1.*X2.^3  - 9;
    Z2 = 3*X1.^2.*X2 - X2.^3 - 4;
    [C,h] = contour(X1,X2,Z1,[0 0], 'c');
    clabel(C,h);
    %surf(X1,X2,Z1);
    hold on;
    [C,h] = contour(X1,X2,Z2,[0,0], 'm');
    clabel(C,h);
    %surf(X1,X2,Z2);
end