 # 📌 Task Tracker CLI (Java)

O Task Tracker CLI é uma aplicação simples de linha de comando desenvolvida em Java para gerenciar tarefas.

O objetivo do projeto é permitir que o usuário acompanhe o que precisa fazer, o que já foi feito e o que está em andamento, utilizando comandos executados diretamente no terminal.

Este projeto foi desenvolvido para praticar conceitos fundamentais de programação, como manipulação de argumentos, estrutura condicional e operações CRUD.
<br><br>

## 🚀 Requisitos do Projeto

**O aplicativo deve**:

- Ser executado via linha de comando

- Aceitar ações e entradas como argumentos

- Armazenar tarefas enquanto estiver em execução

- Permitir ao usuário:

- Adicionar tarefas

- Atualizar tarefas

- Excluir tarefas

- Marcar tarefas como em andamento

- Marcar tarefas como concluídas

- Listar todas as tarefas

- Listar tarefas concluídas

- Listar tarefas pendentes

- Listar tarefas em andamento

## 🛠️ Como Compilar

Antes de executar o projeto, é necessário compilar os arquivos .java.

Certifique-se de estar na pasta raiz do projeto e execute:

> `` javac -d bin src/*.java ``


Esse comando compila todos os arquivos da pasta src e envia os arquivos .class para a pasta bin.

## ▶️ Como Executar

Após compilar, utilize os comandos abaixo.

O parâmetro -cp bin é necessário para que o Java localize as classes compiladas.


### 📖 Guia de Comandos  
---

1️⃣ **Adicionar uma nova tarefa**

Cria uma nova tarefa na lista.  
⚠️ Lembre-se de usar aspas na descrição.

> ``java -cp bin ToDoApp add "Descrição da tarefa aqui"``

2️⃣ **Listar tarefas**

Listar todas as tarefas

> ``java -cp bin ToDoApp list``


Listar apenas tarefas pendentes

> ``java -cp bin ToDoApp list todo``


Listar apenas tarefas concluídas

> ``java -cp bin ToDoApp list done``


Listar apenas tarefas em andamento

> ``java -cp bin ToDoApp list in-progress``

3️⃣ **Atualizar uma tarefa**

Permite alterar a descrição de uma tarefa existente.

> ``java -cp bin ToDoApp update id "Nova descrição da tarefa"``


Exemplo:

> ``java -cp bin ToDoApp update 1 "Comprar leite e pão"``

4️⃣ **Excluir uma tarefa**

Remove permanentemente uma tarefa da lista.

>``java -cp bin ToDoApp delete id``


Exemplo:

>``java -cp bin ToDoApp delete 2``

5️⃣ **Alterar status da tarefa**

Marcar como em andamento

> ``java -cp bin ToDoApp mark-in-progress id``


Marcar como concluída

> ``java -cp bin ToDoApp mark-done id``


Exemplo:

> ``java -cp bin ToDoApp mark-done 3``


#### 📚 Conceitos Aplicados
---

Durante o desenvolvimento deste projeto, foram utilizados:

- Manipulação de argumentos via linha de comando

- Estruturas condicionais

- Organização da lógica de comandos

- CRUD (Create, Read, Update, Delete)

- Controle de estado de tarefas (todo, in-progress, done)

### 🎯 Conclusão

Este projeto foi essencial para reforçar fundamentos da programação em Java e compreender como aplicações CLI funcionam internamente. Além disso, simula o comportamento de ferramentas reais utilizadas no dia a dia de desenvolvedores.