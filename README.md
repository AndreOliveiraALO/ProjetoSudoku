# Projeto Sudoku – Bootcamp DIO Santander

## 📌 Descrição
Esse projeto, idealizado como parte do **Bootcamp DIO Santander – Back‑End com Java**, propõe um jogo **Sudoku** implementado em duas versões distintas:
- **Branch `main`** – Versão **console** (linha de comando).
- **Branch `ui`** – Versão com **interface gráfica** (GUI).

O objetivo é demonstrar domínio de **POO**, lógica de programação, utilização de coleções, controle de estado e boas práticas de versionamento com **Git/GitHub**.

## 🛠 Tecnologias utilizadas
- Java (JDK 17+)
- Programação Orientada a Objetos (POO)
- Collections (List, Map, etc.)
- **Swing** (apenas na versão com GUI)
- Git e GitHub

## 🗂 Estrutura do projeto

### Branch `main` (Console)
```
ProjetoSudoku/
├── src/
│   ├── model/           ← Entidades do jogo (e.g., Sudoku, Celula)
│   ├── util/            ← funcionalidades úteis para diversas tarefas.
│   └── App.java         ← Ponto de entrada (classe principal)
└── README.md
```
**Fluxo de execução**:
1. Recebe argumentos no formato
   `"0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false"`
2. Inicializa o tabuleiro baseado nesses valores.
3. Em `model`, as regras do Sudoku são aplicadas: verificação de jogadas, estado do tabuleiro, etc.

---

### Branch `ui` (Interface Gráfica)
```
ProjetoSudoku/
├── src/
│   ├── model/           ← Mesma estrutura: Sudoku, Quadro etc.
│   ├── service/         ← Lógica do jogo (validação, inicialização)
│   ├── ui/              ← Componentes gráficos com Swing (JFrame, JPanel, etc.)
│   └── UIApp.java       ← Classe principal que inicializa a GUI
└── README.md
```
**Fluxo de execução**:
1. Tabuleiro carregado visualmente dentro de uma janela Swing.
2. O usuário preenche as células diretamente.
3. Validações automáticas e feedback visual são apresentados conforme o usuário joga.

---

## 🔹 Diagrama de classes 

![Diagrama](https://github.com/AndreOliveiraALO/Projetos/blob/main/Imagens/projetoSudoku/DiagramaSudoku.png)
---

## 🚀 Como executar

### Para a versão **console**:
```bash
git clone https://github.com/AndreOliveiraALO/ProjetoSudoku.git
cd ProjetoSudoku
git checkout main
javac -d out src/**/*.java
java -cp out App "0,0;4,false 1,0;7,false ..."  # insira seus argumentos
```

### Para a versão **com GUI**:
```bash
git clone https://github.com/AndreOliveiraALO/ProjetoSudoku.git
cd ProjetoSudoku
git checkout ui
javac -d out src/**/*.java
java -cp out UIApp "0,0;4,false 1,0;7,false ..."  # argumentos para carregar o tabuleiro
```

---

## 📸 Imagens do projeto 
### Versão console
**Menu**

![Menu](https://github.com/AndreOliveiraALO/Projetos/blob/main/Imagens/projetoSudoku/SudokuConsole.png)

**Tabuleiro**

![Tabuleiro](https://github.com/AndreOliveiraALO/Projetos/blob/main/Imagens/projetoSudoku/SudokuConsoleTabuleiro.png)
  
### Versão GUI
**Interface Grafica**

![Interface Grafica](https://github.com/AndreOliveiraALO/Projetos/blob/main/Imagens/projetoSudoku/SudokuInterfaceGrafica.png)

---

## 📄 Licença
Este projeto está licenciado sob a [![NPM](https://img.shields.io/npm/l/react)](https://github.com/AndreOliveiraALO/dslist/blob/main/LICENSE)

## 👨‍💼 Autor

[![LinkedIn](https://img.shields.io/badge/-André%20Luiz%20de%20Oliveira-blue?logo=linkedin\&style=flat-square)](https://www.linkedin.com/in/andre-oliveira-a9a4281b0/)
