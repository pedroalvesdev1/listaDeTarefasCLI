public class ToDoApp {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Exemplo de uso pelo CLI: java ToDoApp [comado] [opções]: \n" +
                    "Comandos disponiveis: \n" +
                    "add \"Descrição\"                -Adicionar tarefa \n" +
                    "list                           -Listar todas as tarefas \n" +
                    "list done                      -Listar tarefas concluidas \n" +
                    "list todo                      -Listar tarefas pendentes\n" +
                    "list in-progress               -Listar tarefas\n" +
                    "update [id] \"Novo texto\"       -Atualizar tarefa\n" +
                    "delete [id                     -Apaga uma tarefa]\n" +
                    "mark-in-progress [id]          -Marca uma tarefa como em andamento\n" +
                    "mark-done [id]                 -Marca uma tarefa como concluida");
            return;
        }

        TodoService service = new TodoService();

        String command = args[0];

        try {
            switch (command) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Erro: digite a descrição da tarefa entre aspas.");
                    } else {
                        service.addTask(args[1]);
                    }
                    break;

                case "list":
                    String filter = (args.length > 1) ? args[1] : "all";
                    service.listTasks(filter);
                    break;

                case "update":
                    if (args.length < 3) {
                        System.out.println("Erro: Digite o ID e a nova descrição.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        String newDesc = args[2];
                        service.updateTask(
                                id, newDesc);
                    }
                    break;

                case "delete":
                    if (args.length < 2) {
                        System.out.println("Erro: Digite o ID da tarefa.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        service.deleteTask(id);
                    }
                    break;

                case "mark-in-progress":
                    if(args.length < 2){
                        System.out.println("Erro: Digite o ID da tarefa.");
                    }else{
                        int id = Integer.parseInt(args[1]);
                        service.changeStatus(id, "Em progresso");
                    }
                break;

                case "mark-done":

                    if (args.length < 2) {
                        System.out.println("Erro: Digite o ID da tarefa.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        service.changeStatus(id, "Feito");
                    }
                    break;

                default:
                    System.out.println("Comando desconhecido: " + command);

                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro:O ID deve ser um número válido.");

        }catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());

        }

    }

}