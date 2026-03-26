package rcpa.labs.service;

import rcpa.labs.model.RecIntegral;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonSerializer {

    /**
     * Сериализация списка объектов в JSON файл
     * @param arrayOfObjects - список объектов
     * @param file - файл для записи
     */
    public static void toJson(ArrayList<?> arrayOfObjects, File file) throws IOException {
        final PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.print("[\n");

        arrayOfObjects.forEach(o -> {
            Class<?> cs = o.getClass();
            Field[] fields = cs.getDeclaredFields();

            writer.print("\t{\n");
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    if (i == fields.length - 1)
                        writer.printf("\t\t\"%s\":\"%s\"\n", fields[i].getName(), fields[i].get(o));
                    else
                        writer.printf("\t\t\"%s\":\"%s\",\n", fields[i].getName(), fields[i].get(o));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (o == arrayOfObjects.getLast()) writer.print("\t}\n");
            else writer.print("\t},\n");
        });

        writer.print("]");
        writer.close();
    }

    /**
     * Десериализация JSON файла в список объектов RecIntegral
     * @param file - JSON файл
     * @return ArrayList<RecIntegral> - список записей
     */
    public static ArrayList<RecIntegral> fromJson(File file) throws IOException {
        ArrayList<RecIntegral> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder jsonContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line.trim());
        }

        String content = jsonContent.toString();

        if (content.startsWith("[") && content.endsWith("]")) {
            content = content.substring(1, content.length() - 1);
        }

        String[] objects = splitJsonObjects(content);

        for (String objJson : objects) {
            RecIntegral rec = parseRecIntegral(objJson);
            if (rec != null) {
                result.add(rec);
            }
        }

        return result;
    }

    /**
     * Разбивает JSON строку на отдельные объекты
     * @param json - JSON строка
     * @return массив строк с объектами
     */
    private static String[] splitJsonObjects(String json) {
        ArrayList<String> objects = new ArrayList<>();
        int braceCount = 0;
        int start = -1;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '{') {
                if (braceCount == 0) {
                    start = i;
                }
                braceCount++;
            } else if (c == '}') {
                braceCount--;
                if (braceCount == 0 && start != -1) {
                    objects.add(json.substring(start, i + 1));
                }
            }
        }

        return objects.toArray(new String[0]);
    }

    /**
     * Парсит один JSON объект в RecIntegral
     * @param json - JSON строка объекта
     * @return RecIntegral или null в случае ошибки
     */
    private static RecIntegral parseRecIntegral(String json) {
        String lowBorder="", highBorder="", stepIntegration="", result="";

        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        // Разбиваем на поля
        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("\"", "");
                String value = keyValue[1].trim().replaceAll("\"", "");

                switch (key) {
                    case "lowBorder":
                        lowBorder = value;
                        break;
                    case "highBorder":
                        highBorder = value;
                        break;
                    case "stepIntegration":
                        stepIntegration = value;
                        break;
                    case "result":
                        result = value;
                        break;
                }
            }
        }

        return new RecIntegral(lowBorder, highBorder, stepIntegration, result);
    }


}
