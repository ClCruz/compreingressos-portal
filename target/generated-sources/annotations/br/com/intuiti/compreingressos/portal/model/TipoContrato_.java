package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-19T14:20:40")
@StaticMetamodel(TipoContrato.class)
public class TipoContrato_ { 

    public static volatile SingularAttribute<TipoContrato, Integer> idTipoContrato;
    public static volatile SingularAttribute<TipoContrato, String> dsTipoContrato;
    public static volatile CollectionAttribute<TipoContrato, ContratoCliente> contratoClienteCollection;

}