function [roots, count, resids, history] = ass2Q3(func, x0, tol)
    %value that should be return
    count = 0;
    history = [x0];
    resids = [];
    roots = x0;
    
    %asserting the tolerance value
    if nargin == 2
        tol = 1e-10;
    end
    
    %set up for iteration
    approx_x = x0;
    while count <= 50
        %calculate function value and Jarcobian
        [exact_f, Jar] = func(approx_x);
        
        %get residual of function using the infinity norm
        residual = norm(exact_f, Inf);
        resids = [resids residual];
        if  residual <= tol
            break;
        end
            
        sn = Jar \ exact_f;
        approx_x = approx_x - sn;
        roots = approx_x;
        
        %record everything
        history = [history roots];
        count = count + 1;
    end
end





