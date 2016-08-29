package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.GeneroEvento;
import br.com.intuiti.compreingressos.portal.model.SegmentoEvento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-29T16:20:12")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> inPos;
    public static volatile SingularAttribute<Usuario, String> dsEmail;
    public static volatile SingularAttribute<Usuario, String> dsNome;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, Integer> inAtivo;
    public static volatile SingularAttribute<Usuario, Integer> inPdv;
    public static volatile SingularAttribute<Usuario, String> cdLogin;
    public static volatile CollectionAttribute<Usuario, SegmentoEvento> segmentoEventoCollection;
    public static volatile SingularAttribute<Usuario, Integer> inTelemarketing;
    public static volatile SingularAttribute<Usuario, Integer> inAdmin;
    public static volatile CollectionAttribute<Usuario, GeneroEvento> generoEventoCollection;
    public static volatile SingularAttribute<Usuario, String> cdPww;

}