package rcpa.labs.model;

import rcpa.labs.view.AddButton;
import rcpa.labs.view.CalculateButton;
import rcpa.labs.view.CalculateTrapButton;
import rcpa.labs.view.DeleteButton;

import javax.swing.*;
import java.util.function.Supplier;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс типов кнопок
 */
public enum ButtonType{
    ADD_BUTTON (AddButton::new),
    DELETE_BUTTON(DeleteButton::new),
    CALCULATE_BUTTON(CalculateButton::new),
    CALCULATE_TRAP_BUTTON(CalculateTrapButton::new);

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
