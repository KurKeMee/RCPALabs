package rcpa.labs.config;

public interface Configuration {

    String LAB_NAME = "Lab1 Monin Kokarev";
    int LAB_WIDTH = 800;
    int LAB_HEIGHT = 600;

    // Таблица
    int ROW_HEIGHT = 30;
    int INTERCELL_SPACING = 10;

    // Текст
    int TEXT_WIDTH = 110;
    int TEXT_HEIGHT = 30;

    int START_FIELD_POSITION_X = 110;
    int START_FIELD_POSITION_Y = 20;
    int FIELD_SPACING = 80;

    // Кнопки
    int BUTTON_WIDTH = 200;
    int BUTTON_HEIGHT = 35;

    int START_BUTTON_POSITION_X = 20;
    int START_BUTTON_POSITION_Y = 50;
    int BUTTON_SPACING = 80;

    // Лэйблы
    int LABEL_WIDTH = 120;
    int LABEL_HEIGHT = 40;

    int START_LABEL_POSITION_X = 25;
    int START_LABEL_POSITION_Y = 15;
    int LABEL_SPACING = 80;

    // Прямоугольник с ошибками
    int RECT_X = 20;
    int RECT_Y = 400;
    int RECT_WIDTH = 200;
    int RECT_HEIGHT = 100;

    int MILLISECONDS_PER_FRAME = 40;

    byte LAB1 = 1;
    byte LAB2 = 2;
    byte LAB3 = 3;
}
