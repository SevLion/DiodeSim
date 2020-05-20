package chartpanel;

import javafx.scene.control.TextField;

//Поле для ввода значений int

public class IntNumberField extends TextField {
    String numberRegEx = "[0-9]*";

    @Override
    public void replaceText(int start, int end, String text) {
        String oldValue = getText();
        //Если вводится что-то не то, заменяем на то, что было до этого
        if (validate(text)) {
            super.replaceText(start, end, text);
            String newText = super.getText();
            if (!validate(newText)) {
                super.setText(oldValue);
            }
        }
    }

    public IntNumberField(int value) {
        super(String.valueOf(value));
    }

    public IntNumberField() {
        super();
    }

    public int getInt() {
        return Integer.parseInt(super.getText());
    }

    private boolean validate(String text) {
        return ("".equals(text) || text.matches(numberRegEx));
    }
}
