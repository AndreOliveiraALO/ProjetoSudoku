package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Area;
import model.JogoStatusEnum;
import model.Quadro;

public class QuadroService {

    private final static int QUADRO_LIMITE = 9;

    private final Quadro quadro;

    public QuadroService(final Map<String, String> gameConfig) {
        this.quadro = new Quadro(iniQuadro(gameConfig));
    }

    public List<List<Area>> getAreas(){
        return quadro.getAreas();
    }

    public void reset(){
        quadro.reset();
    }

    public boolean temErros(){
        return quadro.temErros();
    }

    public JogoStatusEnum getStatus(){
        return quadro.getStatus();
    }

    public boolean gameIsFinished(){
        return quadro.jogoFinalizado();
    }

    private List<List<Area>> iniQuadro(final Map<String, String> gameConfig) {
        List<List<Area>> areas = new ArrayList<>();
        for (int i = 0; i < QUADRO_LIMITE; i++) {
            areas.add(new ArrayList<>());
            for (int j = 0; j < QUADRO_LIMITE; j++) {
                var posicao = gameConfig.get("%s,%s".formatted(i, j));
                var esperada = Integer.parseInt(posicao.split(",")[0]);
                var fixa = Boolean.parseBoolean(posicao.split(",")[1]);

                var areaAtual = new Area(esperada, fixa);
                areas.get(i).add(areaAtual);
            }      
        }
        return areas;
    }

}
