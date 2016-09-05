package com.pos.projetobdnc.entity;

import com.pos.projetobdnc.entity.Login;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-04T22:42:04")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, Long> id;
    public static volatile SingularAttribute<Cliente, Login> login;
    public static volatile SingularAttribute<Cliente, String> email;

}