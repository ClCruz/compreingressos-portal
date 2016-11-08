package br.com.intuiti.compreingressos.portal.enumerator;

public enum Status {
    
    APROVADO("A"), REPROVADO("R");
    private String valor;
    
    private Status(String valor) {
	this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
