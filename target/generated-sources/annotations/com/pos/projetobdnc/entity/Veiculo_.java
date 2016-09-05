package com.pos.projetobdnc.entity;

import com.pos.projetobdnc.entity.Aluguel;
import com.pos.projetobdnc.enums.TipoVeiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-04T22:42:04")
@StaticMetamodel(Veiculo.class)
public class Veiculo_ { 

    public static volatile SingularAttribute<Veiculo, TipoVeiculo> tipo;
    public static volatile SingularAttribute<Veiculo, String> foto;
    public static volatile SingularAttribute<Veiculo, Aluguel> aluguel;
    public static volatile SingularAttribute<Veiculo, String> nome;
    public static volatile SingularAttribute<Veiculo, Long> id;
    public static volatile SingularAttribute<Veiculo, String> fabricante;
    public static volatile SingularAttribute<Veiculo, String> placa;
    public static volatile SingularAttribute<Veiculo, String> descricao;

}