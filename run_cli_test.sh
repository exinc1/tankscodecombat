#!/bin/bash

cd "$(dirname "$0")"

TEMP_DIR=$(mktemp -d)

RHINO_JAR=$(find ~/.gradle/caches -name "rhino*.jar" 2>/dev/null | head -1)

if [ -z "$RHINO_JAR" ]; then
    echo "Downloading rhino dependency..."
    ./gradlew dependencies --console=plain 2>/dev/null
    RHINO_JAR=$(find ~/.gradle/caches -name "rhino*.jar" 2>/dev/null | head -1)
fi

if [ -z "$RHINO_JAR" ]; then
    echo "Error: Rhino not found."
    exit 1
fi

CLASSPATH="$RHINO_JAR"

echo "Compiling CLI game..."
javac -cp "$CLASSPATH" -d "$TEMP_DIR" cli/com/example/tankscodecombat/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    rm -rf "$TEMP_DIR"
    exit 1
fi

echo "Running..."
java -cp "$TEMP_DIR:$CLASSPATH" com.example.tankscodecombat.GameRunner

rm -rf "$TEMP_DIR"
