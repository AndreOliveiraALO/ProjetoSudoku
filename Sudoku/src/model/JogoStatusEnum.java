package model;

public enum JogoStatusEnum {
    SEM_STATUS("Não iniciado"),
    INCOMPLETO("Incompleto"),
    COMPLETO("Completo");

    private String label;

    JogoStatusEnum(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    } 

}
