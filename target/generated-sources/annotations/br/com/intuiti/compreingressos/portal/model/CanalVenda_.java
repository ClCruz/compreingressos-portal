package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.TipoLancamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-08T14:43:44")
@StaticMetamodel(CanalVenda.class)
public class CanalVenda_ { 

    public static volatile SingularAttribute<CanalVenda, Integer> idCanalVenda;
    public static volatile CollectionAttribute<CanalVenda, TipoLancamento> tipoLancamentoCollection;
    public static volatile SingularAttribute<CanalVenda, String> dsCanalVenda;

}