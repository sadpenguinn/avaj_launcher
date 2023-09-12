build:
	find * -name "*.java" > sources.txt
	javac @sources.txt

run:
	java -cp src github.com.sadpenguinn.avaj_launcher.Simulator "scenarios/main.txt"

results:
	cat simulation.txt

clean:
	find * -name "*.class" -delete
	rm sources.txt 2> /dev/null || true
	rm simulation.txt 2> /dev/null || true
