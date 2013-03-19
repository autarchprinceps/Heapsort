#TODO 64-bitifizieren?
#method main

#method heapsort %%list
heapsort:
movl %%len, %%start;
subl $2, %%start;
sarl $1, %%start; # Division durch 2
movl %%len, %%end;
decl %%end;

heapsort_while1:
call seep;
decl %%start;
cmp $0, %%start;
jne heapsort_while1;

heapsort_while2:
cmp $0, %%end;
je end_heapsort; #TODO replace with return to main
#region swap #TODn swap(end, 0, x);
movl %%end, %%posa;
movl $0, %%posb;
call swap;
#endregion
decl %%end;
#region seep(0, end, x)
movl $0, %%start;
call seep;
#endregion
jmp heapsort_while2;

#method seep %%start, %%end, %%list
seep:
movl %%start, %%parent;
movl %%parent, %%child;
sall $1, %%child;
incl %%child;

seep_while:
cmp %%child, %%end;
ret; #jl end_seep; #TODn replace with return
#region if1
movl (%%list, %%parent), %%memoryAccessRestriction;
cmp %%memoryAccessRestriction, (%%list, %%child);
jge if1else;
movl %%child, %%tmp;
jmp if1end;
if1else:
movl %%parent, %%tmp;
if1end:
#endregion
#region if2
incl %%child;
cmp %%child, %%end;
jg if2end;
movl (%%list, %%tmp), %%memoryAccessRestriction;
cmp %%memoryAccessRestriction, (%%list, %%child);
jge if2end;
movl %%child, %%tmp;
if2end:
decl %%child;
#endregion
#region if3
cmp %%tmp, %%parent;
jne if3false;
ret; #je end_seep; #TODn replace with return
if3false:
movl %%parent, %%posa;
movl %%tmp, %%posb;
call swap;
movl %%tmp, %%parent;
#endregion
movl %%parent, %%child;
sall %%child;
incl %%child;
jmp seep_while;

#method swap posa, posb
swap:
movl (%%list, %%posa), %%memoryAccessRestriction;
xchg %%memoryAccessRestriction, (%%list, %%posb);
movl %%memoryAccessRestriction, (%%list, %%posa);
ret;

