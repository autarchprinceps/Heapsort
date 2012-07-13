def swap(a, b, l):
    tmp = l[a]
    l[a] = l[b]
    l[b] = tmp

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
            swap(parent, tmp, l)
            parent = tmp
        child = parent * 2 + 1;

def heapsort(x):
    start = (len(x) - 2) / 2
    end = len(x) - 1
    while start >= 0:
        seep(start, end, x)
        start -= 1
    while end > 0:
        swap(end, 0, x)
        end -= 1
        seep(0, end, x)

l = [2, 4, 1, 5, 6, 7, 9, 20, -2, 54, 345, 43, 2, 5, 6, 3, 7, 345, 56, 7, 34, 654, 324, 6, 42, 4, 5]

heapsort(l)

print(l)
