#include <stdlib.h>
#include <stdio.h>

void swap(int a, int b, int* l) {
    int tmp = l[a];
    l[a] = l[b];
    l[b] = tmp;
}

void seep(int start, int end, int* l) {
    int parent = start;
    int tmp = -1;
    int child = parent * 2 + 1;
    while(child <= end) {
        if(l[parent] < l[child]) {
            tmp = child;
        } else {
            tmp = parent;
        }
        if(child + 1 <= end && l[tmp] < l[child + 1]) {
            tmp = child + 1;
        }
        if(tmp == parent) {
            break;
        } else {
            swap(parent, tmp, l);
            parent = tmp;
        }
        child = parent * 2 + 1;
    }
}

void heapsort(int* x, int len) {
    int start = (len - 2) / 2;
    int end = len - 1;
    while(start >= 0) {
        seep(start, end, x);
        start -= 1;
    }
    while(end > 0) {
        swap(end, 0, x);
        end -= 1;
        seep(0, end, x);
    }
}

void printList(int* list, int len) {
    printf("list(");
    for(int i = 0; i < len; i++) {
        printf(" %d ", list[i]);
    }
    printf(")\n");
}

int main(int argc, char** argv) {
    int l[27] = { 2, 4, 1, 5, 6, 7, 9, 20, -2, 54, 345, 43, 2, 5, 6, 3, 7, 345, 56, 7, 34, 654, 324, 6, 42, 4, 5 };
    printList(l, 27);
    heapsort(l, 27);
    printList(l, 27);
}
