package pl.inzynier.netintegrator.server.module.user.standard;

import lombok.Data;

import javax.persistence.*;

/**
 * Encja dedykowana uzytkownikom tj. konta uzytkownikow klienta rozwiazania,
 * w zalozeniu ma byc parentem dla urlMappingow
 */
@Data
@Entity
class StandardUser {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long standardUserId;

}

