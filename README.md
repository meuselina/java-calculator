# Calculator

A simple console calculator in Java.

## Features

- Operators: `+` `-` `*` `/` `%` and power `^`
- Supports both decimal separators: `3.5` and `3,5` are both accepted
- Handles invalid input and division by zero without crashing
- Checks the operator right away, before asking for the second number
- Very large results are shown correctly (e.g. `2 ^ 100`)
- Type `q` to quit

## Usage

```bash
javac *.java
java Calculator       # calculator
java CalculatorTest   # 15 automated tests
```

```
=== Calculator ===
Operators: +  -  *  /  %  ^
Type 'q' to quit.

First number: 2
Operator: ^
Second number: 10
Result: 2 ^ 10 = 1024
```

## Built with

Java
