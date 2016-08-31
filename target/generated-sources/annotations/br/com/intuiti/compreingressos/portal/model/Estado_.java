package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Municipio;
import br.com.intuiti.compreingressos.portal.model.RegiaoGeografica;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-31T11:44:52")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile CollectionAttribute<Estado, Municipio> municipioCollection;
    public static volatile SingularAttribute<Estado, Short> idEstado;
    public static volatile SingularAttribute<Estado, String> sgEstado;
    public static volatile SingularAttribute<Estado, RegiaoGeografica> idRegiaoGeografica;
    public static volatile SingularAttribute<Estado, String> dsEstado;

}