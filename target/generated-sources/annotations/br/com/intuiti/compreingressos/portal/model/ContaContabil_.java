package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.TipoLancamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-29T16:20:12")
@StaticMetamodel(ContaContabil.class)
public class ContaContabil_ { 

    public static volatile CollectionAttribute<ContaContabil, TipoLancamento> tipoLancamentoCollection1;
    public static volatile CollectionAttribute<ContaContabil, TipoLancamento> tipoLancamentoCollection;
    public static volatile SingularAttribute<ContaContabil, Boolean> inAtivo;
    public static volatile SingularAttribute<ContaContabil, String> dsContaContabil;
    public static volatile SingularAttribute<ContaContabil, Integer> idContaContabil;
    public static volatile SingularAttribute<ContaContabil, String> nrContaContabil;

}