all: program


program :source1.o source2.o source3.o
	gcc source1.o source2.o source3.o -o program 

source1.o: source1.c header.h
	gcc -c source1.c

source2.o: source2.c header.h
	gcc -c source2.c

source3.o: source3.c header.h
	gcc -c source3.c

run:
	./program

debug:
	./program -d

clean:
	rm source1.o source2.o source3.o

