#include <stdio.h>

void printList(long* list, long len) {
    printf("list(");
    for(long i = 0; i < len; i++) {
        printf(" %ld ", list[i]);
    }
    printf(")\n");
}

#define len 27

int main() {
	long l[len] = { 2, 4, 1, 5, 6, 7, 9, 20, -2, 54, 345, 43, 2, 5, 6, 3, 7, 345, 56, 7, 34, 654, 324, 6, 42, 4, 5 };
    printList(l, len);	
	__asm__ (
"													\n\
movq %%rdx, %%r8;										\n\
subq $2, %%r8;											\n\
sarq $1, %%r8; 						\n\
movq %%rdx, %%r9;											\n\
decq %%r9;													\n\
heapsort_while1:											\n\
call seep;													\n\
decq %%r8;												\n\
cmpq $0, %%r8;											\n\
jne heapsort_while1;										\n\
heapsort_while2:											\n\
cmpq $0, %%r9;												\n\
je end_heapsort;			\n\
movq %%r9, %%r10;											\n\
movq $0, %%r11;											\n\
call swap;													\n\
decq %%r9;													\n\
movq $0, %%r8;											\n\
call seep;													\n\
jmp heapsort_while2;										\n\
seep:														\n\
movq %%r8, %%r12;										\n\
movq %%r12, %%r13;										\n\
salq $1, %%r13;											\n\
incq %%r13;												\n\
seep_while:													\n\
cmp %%r13, %%r9;											\n\
ret;				\n\
movq (%%rcx, %%r12), %%r14;			\n\
cmpq %%r14, (%%rcx, %%r13);			\n\
jge if1else;												\n\
movq %%r13, %%r15;										\n\
jmp if1end;													\n\
if1else:													\n\
movq %%r12, %%r15;										\n\
if1end:														\n\
incq %%r13;												\n\
cmpq %%r13, %%r9;											\n\
jg if2end;													\n\
movq (%%rcx, %%r15), %%r14;			\n\
cmpq %%r14, (%%rcx, %%r13);			\n\
jge if2end;													\n\
movq %%r13,												\n\
if2end:														\n\
decq %%r13;												\n\
cmpq %%r15, %%r12;										\n\
jne if3false;												\n\
ret;				\n\
if3false:													\n\
movq %%r12, %%r10;										\n\
movq %%r15, %%r11;											\n\
call swap;													\n\
movq %%r15, %%r12;										\n\
movq %%r12, %%r13;										\n\
salq %%r13;												\n\
incq %%r13;												\n\
jmp seep_while;												\n\
swap:														\n\
movq (%%rcx, %%r10), %%r14;			\n\
xchg %%r14, (%%rcx, %%r11);			\n\
movq %%r14, (%%rcx, %%r10);			\n\
ret;														\n\
end_heapsort:"
:
:"c"(l), "d"(len)
:"memory"
	);
	printList(l, len);
}
