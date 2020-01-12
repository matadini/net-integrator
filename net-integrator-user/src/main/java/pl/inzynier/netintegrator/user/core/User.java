package pl.inzynier.netintegrator.user.core;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "net_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;

    String login;

    String password;

}
