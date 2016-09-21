package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Base;
import br.com.intuiti.compreingressos.portal.model.DocEstrangeiro;
import br.com.intuiti.compreingressos.portal.model.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-21T09:29:23")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> dsComplEndereco;
    public static volatile SingularAttribute<Cliente, String> cdEmailLogin;
    public static volatile SingularAttribute<Cliente, String> cdCep;
    public static volatile SingularAttribute<Cliente, String> dsEndereco;
    public static volatile CollectionAttribute<Cliente, Base> baseCollection;
    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile SingularAttribute<Cliente, Character> inRecebeInfo;
    public static volatile SingularAttribute<Cliente, String> dsNome;
    public static volatile SingularAttribute<Cliente, String> dsSobrenome;
    public static volatile SingularAttribute<Cliente, String> cdRg;
    public static volatile SingularAttribute<Cliente, String> cdCpf;
    public static volatile SingularAttribute<Cliente, String> cdPassword;
    public static volatile SingularAttribute<Cliente, Date> dtNascimento;
    public static volatile SingularAttribute<Cliente, String> dsBairro;
    public static volatile SingularAttribute<Cliente, DocEstrangeiro> idDocEstrangeiro;
    public static volatile SingularAttribute<Cliente, String> nrEndereco;
    public static volatile SingularAttribute<Cliente, Date> dtInclusao;
    public static volatile SingularAttribute<Cliente, String> dsCelular;
    public static volatile SingularAttribute<Cliente, Character> inAssinante;
    public static volatile SingularAttribute<Cliente, Character> inRecebeSms;
    public static volatile SingularAttribute<Cliente, String> dsTelefone;
    public static volatile SingularAttribute<Cliente, String> dsDddCelular;
    public static volatile SingularAttribute<Cliente, Character> inConcordaTermos;
    public static volatile SingularAttribute<Cliente, Character> inSexo;
    public static volatile SingularAttribute<Cliente, Estado> idEstado;
    public static volatile SingularAttribute<Cliente, String> dsDddTelefone;
    public static volatile SingularAttribute<Cliente, String> dsCidade;

}