package rcpa.labs.view;

import rcpa.labs.model.ButtonData;
import rcpa.labs.model.Button;
import rcpa.labs.model.RecIntegral;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс кнопки для работы с файлами
 * Наследуется от Button {@link Button}
 */
public class FileButton extends Button {

    public enum FileOperation {
        SAVE_TEXT,
        LOAD_TEXT,
        SAVE_BINARY,
        LOAD_BINARY
    }

    private FileOperation operation;
    private IntegrationTable linkedTable;

    public FileButton(FileOperation operation) {
        super();
        this.operation = operation;
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(50, 200, 200));
        addEventListener();
    }

    /**
     * Метод назначения действия кнопки
     */
    private void addEventListener() {
        this.addActionListener(e -> {
            linkedTable = getButtonData().getLinkedTable();
            if (linkedTable == null) return;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            switch (operation) {
                case SAVE_TEXT:
                    saveTextFile(fileChooser);
                    break;
                case LOAD_TEXT:
                    loadTextFile(fileChooser);
                    break;
                case SAVE_BINARY:
                    saveBinaryFile(fileChooser);
                    break;
                case LOAD_BINARY:
                    loadBinaryFile(fileChooser);
                    break;
            }
        });
    }

    /**
     * Сохранение в текстовый файл
     */
    private void saveTextFile(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Сохранить в текстовый файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы (*.txt)", "txt"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                linkedTable.getTableRows().forEach(r ->{
                    writer.print(r.getString()+"\n");
                });

                JOptionPane.showMessageDialog(this,
                        "Данные успешно сохранены в файл:\n" + file.getName(),
                        "Сохранение завершено",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка при сохранении файла:\n" + e.getMessage(),
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Загрузка из текстового файла
     */
    private void loadTextFile(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Загрузить из текстового файла");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы (*.txt)", "txt"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                linkedTable.clearTable();
                linkedTable.setTableRows(new ArrayList<>());

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    String[] values = line.split(":");
                    linkedTable.addRow(values,this.getButtonData().getParentPanel());

                }

                JOptionPane.showMessageDialog(this,
                        "Данные успешно загружены из файла:\n" + file.getName(),
                        "Загрузка завершена",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка при загрузке файла:\n" + e.getMessage(),
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Сохранение в бинарный файл (с использованием сериализации)
     */
    private void saveBinaryFile(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Сохранить в бинарный файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Бинарные файлы (*.bin)", "bin"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".bin")) {
                file = new File(file.getAbsolutePath() + ".bin");
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(linkedTable.getTableRows());


                JOptionPane.showMessageDialog(this,
                        "Данные успешно сохранены в бинарный файл:\n" + file.getName(),
                        "Сохранение завершено",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка при сохранении бинарного файла:\n" + e.getMessage(),
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Загрузка из бинарного файла (с использованием десериализации)
     */
    private void loadBinaryFile(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Загрузить из бинарного файла");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Бинарные файлы (*.bin)", "bin"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                linkedTable.clearTable();
                linkedTable.setTableRows(new ArrayList<>());

                ((ArrayList<RecIntegral>) ois.readObject()).forEach(r->{
                    linkedTable.addRow(r.getStringArray(),this.getButtonData().getParentPanel());
                });

                JOptionPane.showMessageDialog(this,
                        "Данные успешно загружены из бинарного файла:\n" + file.getName(),
                        "Загрузка завершена",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка при загрузке бинарного файла:\n" + e.getMessage(),
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
    }
}