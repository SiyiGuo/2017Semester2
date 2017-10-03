function Q2()

%set up for A


ns = [25, 50];
condition_nums = [1, 1e+4, 1e+8, 1e+12];

%generating different matrix A

%Use Gallery for A
for j = 1:numel(ns)
    n = ns(j);
    for k = 1:numel(condition_nums)
        condno = condition_nums(k);
        A = gallery('randsvd', n, condno);
        disp(size(A))
    end
end
    
%Use Gfpp for A
for j = 1:numel(ns)
    n = ns(j);
    A = gfpp(n);
    disp(size(A))
end


