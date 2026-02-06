package rcpa.labs.config;

import javax.print.DocFlavor;

public interface Configuration {

    String LAB_NAME = "Lab1 Monin Kokarev";
    int LAB_WIDTH = 800;
    int LAB_HEIGHT = 600;

    // Таблица
    int ROW_HEIGHT = 30;
    int INTERCELL_SPACING = 10;

    // Поля для ввода
    int TEXT_WIDTH = 110;
    int TEXT_HEIGHT = 30;

    int START_FIELD_POSITION_X = 50;
    int START_FIELD_POSITION_Y = 50;
    int FIELD_SPACING = 80;

    // Кнопки
    int BUTTON_WIDTH = 150;
    int BUTTON_HEIGHT = 30;

    int ADD_BUTTON_POSITION_X = 30;
    int ADD_BUTTON_POSITION_Y = 270;
    int CALCULATE_BUTTON_POSITION_X = 330;
    int CALCULATE_BUTTON_POSITION_Y = 450;
    int CALCULATE_TRAP_BUTTON_POSITION_X = 330;
    int CALCULATE_TRAP_BUTTON_POSITION_Y = 500;
    int DELETE_BUTTON_POSITION_X = 530;
    int DELETE_BUTTON_POSITION_Y = 450;

    // Лэйблы
    int LABEL_WIDTH = 200;
    int LABEL_HEIGHT = 40;

    int START_LABEL_POSITION_X = 30;
    int START_LABEL_POSITION_Y = 15;
    int LABEL_SPACING = 80;

    // Прямоугольник с ошибками
    int RECT_X = 190;
    int RECT_Y = 500;
    int RECT_WIDTH = 300;
    int RECT_HEIGHT = 30;

    //Тексты ошибок
    String FIELDS_EMPTY = "Поля не заполнены";
    String TOP_BORDER_EMPTY = "Поле верхняя граница не заполнено";
    String BOTTOM_BORDER_EMPTY = "Поле нижняя граница не заполнено";
    String STEP_FIELD_EMPTY = "Поле шаг интегрирования не заполнено";
    String SOMETHING_GO_WRONG = "Что-то пошло не так";
    String ROW_NO_SELECTED = "Не выбрано ни одной строки";
    String TOP_BIGGER_BOTTOM = "Верхняя граница меньше нижней границы";
    String ADD_NEW_ROW_SUCCESS = "Строка успешно добавлена";
    String DELETE_ROW_SUCCESS = "Строка успешно удалена";
    String CALCULATE_ROW_SUCCESS = "Значение успешно вычислено";

    //Анимация
    int MILLISECONDS_PER_FRAME = 40;


    //Номера лабораторных
    byte LAB1 = 1;
    byte LAB2 = 2;
    byte LAB3 = 3;
}
