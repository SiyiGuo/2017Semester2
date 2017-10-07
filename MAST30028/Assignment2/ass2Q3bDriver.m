function ass2Q3bDriver()
    %Authorship:Siyi GUo 737008
    %This is the driver function for question B of Question 3 in
    %Assignment2
    %when using this funtion, put ass2Q3.m and func under the path
    [roots, count, resids, history] = ass2Q3(@funcQ3b, [1;2]);
    fprintf('After %d iteration, the roots are [%8.7f %8.7f]\n', count, roots);
end