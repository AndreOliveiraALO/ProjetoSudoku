package ui.custom.input;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import javax.swing.JTextField;
import model.Area;
import service.EventEnum;
import service.EventListener;

import static service.EventEnum.LIMPAR_AREA;
import static java.awt.Font.PLAIN;

public class NumberText extends JTextField implements EventListener {
    private final Area area;

    public NumberText(final Area area) {
        this.area = area;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!area.isFixo());
        if (area.isFixo()){
            this.setText(area.getAtual().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
                mudarArea();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                mudarArea();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                mudarArea();
            }

            private void mudarArea(){
                if (getText().isEmpty()){
                    area.limparArea();
                    return;
                }
                area.setAtual(Integer.parseInt(getText()));
            }
        });
    }

    @Override
    public void update(final EventEnum eventType) {
        if (eventType.equals(LIMPAR_AREA) && (this.isEnabled())){
            this.setText("");
        }
    }

}
