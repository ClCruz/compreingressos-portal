package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.FormaPagamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-25T11:16:18")
@StaticMetamodel(TipoMeioPagamento.class)
public class TipoMeioPagamento_ { 

    public static volatile SingularAttribute<TipoMeioPagamento, String> inTipoMeioPagamento;
    public static volatile SingularAttribute<TipoMeioPagamento, String> dsTipoMeioPagamento;
    public static volatile CollectionAttribute<TipoMeioPagamento, FormaPagamento> formaPagamentoCollection;

}