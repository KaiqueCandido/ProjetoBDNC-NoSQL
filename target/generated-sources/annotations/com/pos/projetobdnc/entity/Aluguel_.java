package com.pos.projetobdnc.entity;

import com.pos.projetobdnc.entity.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-30T11:30:02")
@StaticMetamodel(Aluguel.class)
public class Aluguel_ { 

    public static volatile SingularAttribute<Aluguel, Cliente> cliente;
    public static volatile SingularAttribute<Aluguel, Integer> chegada;
    public static volatile SingularAttribute<Aluguel, Double> valor;
    public static volatile SingularAttribute<Aluguel, Integer> saida;
    public static volatile SingularAttribute<Aluguel, Long> id;

}