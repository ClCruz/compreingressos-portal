package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Cliente;
import br.com.intuiti.compreingressos.portal.model.Evento;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-29T12:56:06")
@StaticMetamodel(Base.class)
public class Base_ { 

    public static volatile SingularAttribute<Base, BigDecimal> vlTaxaCartaoDeb;
    public static volatile SingularAttribute<Base, Integer> idBase;
    public static volatile SingularAttribute<Base, BigDecimal> vlIngresso;
    public static volatile SingularAttribute<Base, Integer> qtPrazoRepasseEmDias;
    public static volatile SingularAttribute<Base, String> dsEmail;
    public static volatile SingularAttribute<Base, Character> inAtivo;
    public static volatile SingularAttribute<Base, String> dsNrBanco;
    public static volatile SingularAttribute<Base, String> dsNrConta;
    public static volatile SingularAttribute<Base, String> dsDddTelFixo;
    public static volatile SingularAttribute<Base, Character> inPoupancaCc;
    public static volatile SingularAttribute<Base, String> dsCel;
    public static volatile SingularAttribute<Base, String> dsNomeContato;
    public static volatile SingularAttribute<Base, String> dsDddCel;
    public static volatile SingularAttribute<Base, BigDecimal> vlTaxaCartaoCred;
    public static volatile SingularAttribute<Base, String> dsNomeBanco;
    public static volatile SingularAttribute<Base, Cliente> idCliente;
    public static volatile SingularAttribute<Base, String> dsNomeBaseSql;
    public static volatile SingularAttribute<Base, String> dsRzSocial;
    public static volatile SingularAttribute<Base, String> cdCpfCnpj;
    public static volatile SingularAttribute<Base, String> dsNrAgencia;
    public static volatile SingularAttribute<Base, String> dsTelFixo;
    public static volatile SingularAttribute<Base, BigDecimal> vlTaxaRepasse;
    public static volatile SingularAttribute<Base, String> dsNomeTeatro;
    public static volatile CollectionAttribute<Base, Evento> eventoCollection;

}