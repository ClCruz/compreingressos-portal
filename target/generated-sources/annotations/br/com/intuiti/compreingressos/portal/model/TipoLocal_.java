package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-31T11:44:52")
@StaticMetamodel(TipoLocal.class)
public class TipoLocal_ { 

    public static volatile SingularAttribute<TipoLocal, String> dsTipoLocal;
    public static volatile CollectionAttribute<TipoLocal, LocalEvento> localEventoCollection;
    public static volatile SingularAttribute<TipoLocal, Boolean> inAtivo;
    public static volatile SingularAttribute<TipoLocal, Integer> idTipoLocal;

}