package model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Quadro {

    private List<List<Area>> areas;

    public Quadro(List<List<Area>> areas) {
        this.areas = areas;
    }

    public List<List<Area>> getAreas() {
        return this.areas;
    }

    public JogoStatusEnum getStatus() {
        if(areas.stream()
                .flatMap(Collection::stream)
                .noneMatch(a -> !a.isFixo() && nonNull(a.getAtual()))){
            return JogoStatusEnum.SEM_STATUS;    
        }
        return areas.stream()
            .flatMap(Collection::stream)
            .anyMatch(a-> isNull(a.getAtual()))? JogoStatusEnum.INCOMPLETO: JogoStatusEnum.COMPLETO;
    }

    public boolean temErros(){
        if (getStatus() == JogoStatusEnum.SEM_STATUS)
            return false;
        return areas.stream()
            .flatMap(Collection::stream) 
            .anyMatch(a -> nonNull(a.getAtual()) && !a.getAtual().equals(a.getEsperado()));   

    }

    public boolean mudarValor(final int coluna, final int linha, final int valor){
        var area = areas.get(coluna).get(linha);
        if (area.isFixo())
            return false; 
        area.setAtual(valor);
        return true;
    }

    public boolean limparValor(final int coluna, final int linha){
        var area = areas.get(coluna).get(linha);
        if (area.isFixo())
            return false; 
        area.limparArea();
        return true;
    }

    public void reset(){
        areas.forEach(a -> a.forEach(Area::limparArea));
    }

    public boolean jogoFinalizado(){
        return !temErros() && getStatus() == JogoStatusEnum.COMPLETO;
    }


}
