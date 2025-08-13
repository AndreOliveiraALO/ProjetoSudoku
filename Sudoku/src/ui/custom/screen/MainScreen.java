package ui.custom.screen;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Area;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static service.EventEnum.LIMPAR_AREA;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import service.NotifierService;
import service.QuadroService;
import ui.custom.button.CheckGameStatusButton;
import ui.custom.button.FinishGameButton;
import ui.custom.button.ResetButton;
import ui.custom.frame.MainFrame;
import ui.custom.input.NumberText;
import ui.custom.panel.MainPanel;
import ui.custom.panel.SudokuSector;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);

    private final QuadroService quadroService;
    private final NotifierService notifierService;

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;

    public MainScreen(final Map<String, String> gameConfig) {
        this.quadroService = new QuadroService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        for (int r = 0; r < 9; r+=3) {
            var endRow = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endCol = c + 2;
                var areas = getSpacesFromSector(quadroService.getAreas(), c, endCol, r, endRow);
                JPanel setor = generateSection(areas);
                mainPanel.add(setor);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Area> getSpacesFromSector(final List<List<Area>> areas,
                                            final int initCol, final int endCol,
                                            final int initRow, final int endRow){
        List<Area> areaSetor = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                areaSetor.add(areas.get(c).get(r));
            }
        }
        return areaSetor;
    }

    private JPanel generateSection(final List<Area> areas){
        List<NumberText> fields = new ArrayList<>(areas.stream().map(NumberText::new).toList());
        fields.forEach(t -> notifierService.subscribe(LIMPAR_AREA, t));
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(final JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e -> {
            if (quadroService.gameIsFinished()){
                showMessageDialog(null, "Parabéns você concluiu o jogo");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            } else {
                var message = "Seu jogo tem alguma inconsistência, ajuste e tente novamente";
                showMessageDialog(null, message);
            }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(final JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e -> {
            var temErros = quadroService.temErros();
            var jogoStatus = quadroService.getStatus();
            var message = switch (jogoStatus){
                case SEM_STATUS -> "O jogo não foi iniciado";
                case INCOMPLETO -> "O jogo está imcompleto";
                case COMPLETO -> "O jogo está completo";
            };
            message += temErros ? " e contém erros" : " e não contém erros";
            showMessageDialog(null, message);
        });
        mainPanel.add(MainScreen.this.checkGameStatusButton);
    }

    private void addResetButton(final JPanel mainPanel) {
        resetButton = new ResetButton(e ->{
            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            if (dialogResult == 0){
                quadroService.reset();
                notifierService.notify(LIMPAR_AREA);
            }
        });
        mainPanel.add(resetButton);
    }

}
