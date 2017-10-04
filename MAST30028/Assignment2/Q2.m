function Q2()
    %set up for A
    ns = [25, 50];
    condition_nums = [1, 1e+4, 1e+8, 1e+12];

    %generating different matrix A
    %Use Gallery for A
    fprintf('Method,           n,   conditional number, normwiseRelativeForwardError, relativeResidual\n');
    for j = 1:numel(ns)
        n = ns(j);
        %set up for the known solution
        %set up the solution
        x = 100*ones(n,1);
        for k = 1:numel(condition_nums)
            %get Matrix A
            condno = condition_nums(k);
            A = gallery('randsvd', n,condno);

            %use x and A to get b
            b = A*x;

            %use 3 different methods to reverse back x
            x1 = method1(A,b);
            fprintf('LU Facto, %10d, %20d, %20.20f, %28.20f\n', n, condno, normRelativeForwardError(x1, x), relativeResidual(x1, b, A));
            x2 = method2(A,b);
            fprintf('Inv    A, %10d, %20d, %20.20f, %28.20f\n', n, condno, normRelativeForwardError(x2, x), relativeResidual(x2, b, A));
            x3 = method3(A,b);
            fprintf('QR Facto, %10d, %20d, %20.20f, %28.20f\n\n', n, condno, normRelativeForwardError(x3, x), relativeResidual(x3, b, A));
        end
    end

    %Use Gfpp for A
    fprintf('Method,           n, normwiseRelativeForwardError, relativeResidual\n');
    for j = 1:numel(ns)
        n = ns(j);
        %set up the solution
        x = ones(n,1);

        %get Matrix A
        A = gfpp(n);

        %use x and A to get b
        b = A*x;

        %use 3 different methods to reverse back x
        x1 = method1(A,b);
        fprintf('LU Facto, %10d, %20.20f, %28.20f\n', n, normRelativeForwardError(x1, x), relativeResidual(x1, b, A));
        x2 = method2(A,b);
        fprintf('Inv    A, %10d, %20.20f, %28.20f\n', n, normRelativeForwardError(x2, x), relativeResidual(x2, b, A));
        x3 = method3(A,b);
        fprintf('QR Facto, %10d, %20.20f, %28.20f\n\n', n, normRelativeForwardError(x3, x), relativeResidual(x3, b, A));
    end
end

function x = method1(A, b)
    x = A\b;
end

function x = method2(A,b)
    x = inv(A) * b;
end

function x = method3(A,b)
    [Q,R] = qr(A);
    x = R\(Q'*b);
end

function re = normRelativeForwardError(errorx, truex)
    re = norm(errorx - truex) / norm(truex);
end

function rres = relativeResidual(errorx, b, A)
    rres = norm(b - A*errorx) / (norm(A)* norm(errorx));
end


