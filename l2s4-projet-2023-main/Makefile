cls:
	javac -classpath jars/*:src -d classes src/*/*.java 

clean:
	rm -r classes docs

jeu.jar:
	jar cfm jars/jeu.jar manifest.mf -C classes pandemic -C classes listchooser

doc:
	javadoc -classpath jars/*:src -d docs src/*/*.java

