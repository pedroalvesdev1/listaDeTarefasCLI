import java.util.ArrayList;
import java.nio.file.*;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TodoService {

    private ArrayList<Task> tasks;
    private int nextId = 1;
    private final String FILE_PATH = "tasks.json";

    public TodoService() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    private void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {

            out.print("[");

            for (int couter = 0; couter < tasks.size(); couter++) {
                Task task = tasks.get(couter);
                String jsonTask = String.format(
                        "{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}",
                        task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(),
                        task.getUpdatedAt());

                out.print(jsonTask);

                if (couter < tasks.size() - 1) {
                    out.print(",");
                }
            }

            out.print("]");

        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public void addTask(String description) {
        Task newTask = new Task(nextId++, description);
        tasks.add(newTask);
        saveToFile();
        System.out.println("Tarefa adicionada com sucesso (ID: " + newTask.getId() + ")");
    }

    public void updateTask(int id, String newDescription) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setDescription(newDescription);
            saveToFile();
            System.out.println("Tarefa " + id + " atualizada com sucesso.");
        }
    }

    public void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveToFile();
            System.out.println("Tarefa " + id + " apagada.");
        }
    }

    public void changeStatus(int id, String newStatus) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus(newStatus);
            saveToFile();
            System.out.println("Tarefa " + id + " marcada como " + newStatus);
        }
    }

    public void listTasks(String filter) {
        if (tasks.isEmpty()) {
            System.out.println("A lista de tarefas está vazia");
            return;
        }

        boolean foundAny = false;

        for (Task task : tasks) {
            if (filter.equals("all") || task.getStatus().equalsIgnoreCase(filter)) {
                System.out.println(task);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("Nenhum tarefa encontrada com o status: " + filter);
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private void loadTasksFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path))
            return;

        try {
            List<String> lines = Files.readAllLines(path);
            StringBuilder fullJson = new StringBuilder();

            for (String line : lines)
                fullJson.append(line.trim());

            String content = fullJson.toString();

            if (content.equals("[]") || content.isEmpty())
                return;

            content = content.substring(1, content.length() - 1);

            String[] tasksObjects = content.split("(?<=\\}),?");

            for (String obj : tasksObjects) {
                if (obj.trim().isEmpty())
                    continue;

                tasks.add(parseTaskFromJson(obj));

            }

            if (!tasks.isEmpty()) {
                nextId = tasks.stream().mapToInt(Task::getId).max().getAsInt() + 1;
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
    }

    private Task parseTaskFromJson(String json) {
        int id = Integer.parseInt(extractValue(json, "id"));
        String description = extractValue(json, "description");
        String status = extractValue(json, "status");
        String createdAt = extractValue(json, "createdAt");
        String updatedAt = extractValue(json, "updateAt");

        Task task = new Task(id, description);
        task.setStatus(status);
        return task;
    }

    private String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern) + pattern.length();
        int end = json.indexOf(",", start);
        if (end == -1)
            end = json.indexOf("}", start);

        String value = json.substring(start, end).trim();
        return value.replace("\"", "");
    }

}
