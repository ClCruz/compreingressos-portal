package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-23T13:37:14")
@StaticMetamodel(Vendedor.class)
public class Vendedor_ { 

    public static volatile SingularAttribute<Vendedor, Boolean> inAtivo;
    public static volatile SingularAttribute<Vendedor, Integer> idVendedor;
    public static volatile SingularAttribute<Vendedor, String> dsVendedor;
    public static volatile CollectionAttribute<Vendedor, ContratoCliente> contratoClienteCollection;

}