package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Banco;
import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.Municipio;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-23T13:37:14")
@StaticMetamodel(Contratante.class)
public class Contratante_ { 

    public static volatile SingularAttribute<Contratante, String> cdSapCardcode;
    public static volatile SingularAttribute<Contratante, Character> dvContaBancaria;
    public static volatile SingularAttribute<Contratante, Banco> idBanco;
    public static volatile SingularAttribute<Contratante, String> cdContaBancaria;
    public static volatile SingularAttribute<Contratante, String> nrTelefone;
    public static volatile SingularAttribute<Contratante, String> nmRepresLegal;
    public static volatile SingularAttribute<Contratante, String> dsComplemento;
    public static volatile SingularAttribute<Contratante, String> cdEmailRepresLegal;
    public static volatile SingularAttribute<Contratante, Boolean> inAtivo;
    public static volatile SingularAttribute<Contratante, String> dsBairroRepresLegal;
    public static volatile SingularAttribute<Contratante, Boolean> inPessoaJuridica;
    public static volatile SingularAttribute<Contratante, String> nmTitularContaRepasse;
    public static volatile SingularAttribute<Contratante, String> cdCep;
    public static volatile SingularAttribute<Contratante, String> dsEndereco;
    public static volatile CollectionAttribute<Contratante, ContratoCliente> contratoClienteCollection;
    public static volatile SingularAttribute<Contratante, String> cdRgRepresLegal;
    public static volatile SingularAttribute<Contratante, String> nmRazaoSocial;
    public static volatile SingularAttribute<Contratante, Integer> idContratante;
    public static volatile SingularAttribute<Contratante, String> dsNumero;
    public static volatile SingularAttribute<Contratante, String> cdEmail;
    public static volatile SingularAttribute<Contratante, String> cdUfRgRepresLegal;
    public static volatile SingularAttribute<Contratante, String> cdCpfRepresLegal;
    public static volatile SingularAttribute<Contratante, Date> dtInativacao;
    public static volatile SingularAttribute<Contratante, String> cdAgenciaBanco;
    public static volatile SingularAttribute<Contratante, String> dsComplementoEnderecoRepresLegal;
    public static volatile SingularAttribute<Contratante, String> dsBairro;
    public static volatile SingularAttribute<Contratante, String> cdCepRepresLegal;
    public static volatile SingularAttribute<Contratante, Character> dvAgenciaBanco;
    public static volatile SingularAttribute<Contratante, String> cdCpfCnpjTitularConta;
    public static volatile SingularAttribute<Contratante, Municipio> idMunicipio;
    public static volatile SingularAttribute<Contratante, String> nrCelularRepresLegal;
    public static volatile SingularAttribute<Contratante, String> nmFantasia;
    public static volatile SingularAttribute<Contratante, BigInteger> cdCnpjCpf;
    public static volatile SingularAttribute<Contratante, String> nmCargoRepresLegal;
    public static volatile SingularAttribute<Contratante, String> dsNumeroEnderecoRepresLegal;
    public static volatile SingularAttribute<Contratante, Municipio> idMunicipioRepresLegal;
    public static volatile SingularAttribute<Contratante, String> nrTelefoneRepresLegal;
    public static volatile SingularAttribute<Contratante, String> dsEnderecoRepresLegal;

}