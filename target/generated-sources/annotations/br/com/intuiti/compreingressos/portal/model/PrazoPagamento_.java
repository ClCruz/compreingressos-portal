package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-23T13:37:14")
@StaticMetamodel(PrazoPagamento.class)
public class PrazoPagamento_ { 

    public static volatile SingularAttribute<PrazoPagamento, String> dsDiasFixos;
    public static volatile SingularAttribute<PrazoPagamento, Integer> qtDiasPrazo;
    public static volatile SingularAttribute<PrazoPagamento, String> dsPrazoPagamento;
    public static volatile SingularAttribute<PrazoPagamento, Integer> inDiaSemana;
    public static volatile CollectionAttribute<PrazoPagamento, ContratoClientePrazoPagamento> contratoClientePrazoPagamentoCollection;
    public static volatile SingularAttribute<PrazoPagamento, Integer> idPrazoPagamento;

}