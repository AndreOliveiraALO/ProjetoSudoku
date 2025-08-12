package model;

public class Area {
    private Integer atual;
    private final int esperado;
    private final boolean fixo;

    public Area(final int esperado, final boolean fixo) {
        this.esperado = esperado;
        this.fixo = fixo;
        if (fixo)
            this.atual = esperado; 
    }

    public Integer getAtual() {
        return atual;
    }
    public void setAtual(final Integer atual) {
        if (!fixo)        
            this.atual = atual;
    }
 
    public void limparArea(){
        setAtual(null);
    }

    public int getEsperado() {
        return esperado;
    }

    public boolean isFixo() {
        return fixo;
    }
}
