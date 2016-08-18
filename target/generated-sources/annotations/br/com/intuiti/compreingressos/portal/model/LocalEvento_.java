package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Municipio;
import br.com.intuiti.compreingressos.portal.model.TipoLocal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-17T18:52:59")
@StaticMetamodel(LocalEvento.class)
public class LocalEvento_ { 

    public static volatile SingularAttribute<LocalEvento, String> nrTelefone;
    public static volatile SingularAttribute<LocalEvento, String> dsComplemento;
    public static volatile SingularAttribute<LocalEvento, String> cdCepCliente;
    public static volatile SingularAttribute<LocalEvento, Boolean> inAtivo;
    public static volatile SingularAttribute<LocalEvento, TipoLocal> idTipoLocal;
    public static volatile SingularAttribute<LocalEvento, Municipio> idMunicipio;
    public static volatile SingularAttribute<LocalEvento, String> cdUrlSite;
    public static volatile SingularAttribute<LocalEvento, String> dsEndereco;
    public static volatile SingularAttribute<LocalEvento, Integer> idLocalEvento;
    public static volatile SingularAttribute<LocalEvento, String> dsNumero;
    public static volatile SingularAttribute<LocalEvento, String> cdEmail;
    public static volatile SingularAttribute<LocalEvento, Date> dtInativacao;
    public static volatile SingularAttribute<LocalEvento, String> dsLocalEvento;
    public static volatile SingularAttribute<LocalEvento, String> dsBairro;

}