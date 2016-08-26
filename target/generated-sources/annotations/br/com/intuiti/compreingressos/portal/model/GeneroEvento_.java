package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.SegmentoEvento;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-26T11:26:02")
@StaticMetamodel(GeneroEvento.class)
public class GeneroEvento_ { 

    public static volatile SingularAttribute<GeneroEvento, String> dsGeneroEvento;
    public static volatile SingularAttribute<GeneroEvento, Boolean> inAtivo;
    public static volatile SingularAttribute<GeneroEvento, Usuario> idUsuario;
    public static volatile SingularAttribute<GeneroEvento, Integer> idGeneroEvento;
    public static volatile SingularAttribute<GeneroEvento, SegmentoEvento> idSegmentoEvento;

}