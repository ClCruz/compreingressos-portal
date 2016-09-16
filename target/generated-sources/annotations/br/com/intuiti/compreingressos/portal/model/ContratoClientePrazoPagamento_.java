package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.FormaPagamento;
import br.com.intuiti.compreingressos.portal.model.PrazoPagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-16T11:05:10")
@StaticMetamodel(ContratoClientePrazoPagamento.class)
public class ContratoClientePrazoPagamento_ { 

    public static volatile SingularAttribute<ContratoClientePrazoPagamento, Integer> idContratoClientePrazoPagamento;
    public static volatile SingularAttribute<ContratoClientePrazoPagamento, ContratoCliente> idContratoCliente;
    public static volatile SingularAttribute<ContratoClientePrazoPagamento, FormaPagamento> idFormaPagamento;
    public static volatile SingularAttribute<ContratoClientePrazoPagamento, PrazoPagamento> idPrazoPagamento;

}