CC=javac
SRC=caffeine/*/*.java
TARGET=caffeine/game/Game

run : $(TARGET)
	java $(TARGET)

$(TARGET) :
	$(CC) $(SRC)

clean :
	rm -f *~ && \
rm -f */*/*.class