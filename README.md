# Taschenrechner · Calculator

A simple console calculator in Java.

## Features

- Operators: `+` `-` `*` `/` `%` and power `^`
- Accepts both `3.5` and `3,5` as decimal input
- Handles invalid input and division by zero without crashing
- Checks the operator right away, before asking for the second number
- Very large results are shown correctly (e.g. `2 ^ 100`)
- Type `q` to quit

## Usage

```bash
javac *.java
java Taschenrechner       # calculator
java TaschenrechnerTest   # 15 automated tests
```

```
=== Taschenrechner ===
Operatoren: +  -  *  /  %  ^
Erste Zahl: 2
Operator: ^
Zweite Zahl: 10
Ergebnis: 2 ^ 10 = 1024
```

## Built with

Java
