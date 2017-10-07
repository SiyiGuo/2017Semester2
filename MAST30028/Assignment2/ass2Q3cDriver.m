function ass2Q3cDriver()
    %Authorship:Siyi GUo 737008
    %This is the driver function for question B of Question 3 in
    %Assignment2
    %when using this funtion, put ass2Q3.m and func under the path
    
    %1st fuess
    [roots, count, resids, history] = ass2Q3(@funcQ3c, [1.2;2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f]\n', count, roots);
    figure(1)
    plot(history(1,:), history(2,:),'ro')
    figure(5)
    plot([1:1:count], resids, '-s')
    %2nd fuess
    [roots, count, resids, history] = ass2Q3(@funcQ3c, [-2;2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f]\n', count, roots);
    figure(2)
    plot(history(1,:), history(2,:),'k<')
    figure(6)
    plot([1:1:count], resids, '-s')
    hold on;
    %3rd fuess
    [roots, count, resids, history] = ass2Q3(@funcQ3c, [-1.2;-2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f]\n', count, roots);
    figure(3)
    plot(history(1,:), history(2,:),'gs')
    figure(7)
    plot([1:1:count], resids, '-s')
    hold on;
    %4th fuess
    [roots, count, resids, history] = ass2Q3(@funcQ3c, [2;-2.5]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f]\n', count, roots);
    figure(4)
    plot(history(1,:), history(2,:),'x')
    figure(8)
    loglog([1:1:count], resids, '-s')
    hold on;
end