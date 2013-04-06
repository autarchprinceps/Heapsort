#!/usr/bin/python
def seep(start, end, l):
    parent = start
    tmp = -1
    child = parent * 2 + 1
    while child <= end:
        if l[parent] < l[child]:
            tmp = child
        else:
            tmp = parent
        if child + 1 <= end and l[tmp] < l[child + 1]:
            tmp = child + 1
        if tmp == parent:
            break
        else:
            l[parent], l[tmp] = l[tmp], l[parent]
            parent = tmp
        child = parent * 2 + 1

def heapsort(l):
    start = (len(l) - 2) / 2
    end = len(l) - 1
    while start >= 0:
        seep(start, end, l)
        start -= 1
    while end > 0:
        l[end], l[0] = l[0], l[end]
        end -= 1
        seep(0, end, l)

l = [2, 4, 1, 5, 6, 7, 9, 20, -2, 54, 345, 43, 2, 5, 6, 3, 7, 345, 56, 7, 34, 654, 324, 6, 42, 4, 5]
print(l)

heapsort(l)

print(l)
