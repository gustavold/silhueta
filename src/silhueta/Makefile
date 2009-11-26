JAVAC=fsc
JAVA=scala


default: all

.PHONY : all run clean

all: 
	$(JAVAC) *.scala

ryn: run


run: all
	$(JAVA) silhueta.Main

clean:
	rm -f silhueta/*.class

 

