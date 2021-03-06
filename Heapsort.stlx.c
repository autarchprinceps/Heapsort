swap := procedure(a, b, rw l) {
    tmp := l[a];
    l[a] := l[b];
    l[b] := tmp;
};

seep := procedure(start, end, rw l) {
    parent := start;
    tmp := -1;
    child := parent * 2 + 1;
    while(child <= end) {
        if(l[parent + 1] < l[child + 1]) {
            tmp := child;
        } else {
            tmp := parent;
		}
        if(child + 1 <= end && l[tmp + 1] < l[child + 2]) {
            tmp := child + 1;
		}
        if(tmp == parent) {
            break;
        } else {
            swap(parent + 1, tmp + 1, l);
            parent := tmp;
		}
        child := parent * 2 + 1;
	}
};

heapsort := procedure(rw x) {
    start := (#x - 2) / 2;
    end := #x - 1;
    while(start >= 0) {
        seep(start, end, x);
        start -= 1;
	}
    while(end > 0) {
        swap(end + 1, 1, x);
        end -= 1;
        seep(0, end, x);
	}
};

l := [2, 4, 1, 5, 6, 7];

heapsort(l);

print(l);
