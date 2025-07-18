# Sudoku Java 🧩

Projeto de Sudoku com Java — versão terminal e com interface gráfica (Swing).

## 🎮 Funcionalidades

- Modo terminal com entrada por argumentos
- Interface gráfica Swing (JTextFields 9x9)
- Geração aleatória de tabuleiros com 3 níveis de dificuldade: Fácil, Médio, Difícil
- Resolução automática usando algoritmo de backtracking
- Validação de regras: linha, coluna e bloco 3x3

## 📂 Estrutura

```
sudoku-java/
├── src/
│   ├── Cell.java
│   ├── SudokuBoard.java
│   ├── SudokuTerminal.java
│   └── SudokuGUI.java
```

## 🚀 Execução

### Terminal (CLI)

```bash
javac src/*.java
java -cp src SudokuTerminal 0,0;4,false 1,0;7,false ...
```

### Interface Gráfica (GUI)

```bash
javac src/*.java
java -cp src SudokuGUI
```

## 📌 Requisitos

- Java 11 ou superior
- IDE como IntelliJ IDEA ou VS Code (opcional)

## 👨‍💻 Autor

Mário Evangelista — Projeto baseado nos estudos da DIO ([repositório original](https://github.com/digitalinnovationone/sudoku))

---