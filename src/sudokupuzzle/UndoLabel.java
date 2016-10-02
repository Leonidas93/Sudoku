package sudokupuzzle;
import javax.swing.*;



public class UndoLabel {
    JLabel label = new JLabel();
    String old_value;
    String new_value;
    
    public UndoLabel(JLabel l, String o, String n){
        label=l;
        old_value=o;
        new_value=n;
    }

    public JLabel getLabel() {
        return label;
    }

    public String getOld_value() {
        return old_value;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setOld_value(String old_value) {
        this.old_value = old_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }
    
}
