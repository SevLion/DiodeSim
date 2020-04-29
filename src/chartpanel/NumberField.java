package chartpanel;

import javafx.scene.control.TextField;

public class NumberField extends TextField {
    String numberRegEx = "[0-9, .]*";

    @Override
    public void replaceText(int start, int end, String text) {
        String oldValue = getText();
        if(validate(text)) {
            super.replaceText(start, end, text);
            String newText = super.getText();
            if(!validate(newText)){
                super.setText(oldValue);
            }
        }
    }

    private boolean validate(String text) {
        return ("".equals(text) || text.matches(numberRegEx));
    }
}
