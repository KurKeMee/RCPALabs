package rcpa.labs.model;

import rcpa.labs.view.AddButton;

import javax.swing.*;
import java.util.function.Supplier;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс типов кнопок
 */
public enum ButtonType{
    ADD_BUTTON (AddButton::new);

    /**
     * Переменная для хранения Supplier {@link Supplier}
     */
    private final Supplier<Button> supplier;

    /**
     * Конструктор ButtonType
     *
     * @param supplier - Supplier
     */
    ButtonType(Supplier<Button> supplier) {
        this.supplier = supplier;
    }

    /**
     * Метод для создания экземпляра класса определенного типа
     * @return Button - возвращает объект класса заданного типа
     */
    public Button create(){
        return supplier.get();
    }
}
