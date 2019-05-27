package pl.inzynier.netintegrator.server.module.user.admin;

import lombok.Data;

import javax.persistence.*;

/**
 * Encja dedykowana 'boskim' uzytkownikom tj. konta uzytkownikow dostawcy rozwiazania
 */
@Data
@Entity
class AdminUser {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long adminUserId;
}
