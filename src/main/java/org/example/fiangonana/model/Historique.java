package org.example.fiangonana.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.util.Constante;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "historiques")
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historiques_id_gen")
    @SequenceGenerator(name = "historiques_id_gen", sequenceName = "historiques_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "pk")
    private Long pk;

    @Column(name = "data")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> data;

    @ColumnDefault("now()")
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Utilisateur user;

    @Column(name = "type_transaction")
    private Integer typeTransaction;

    public static Historique makeInsertion(Utilisateur u, Class<?> entiteClasse, Long pk, Map<String, Object> data) {
        Historique historique = getHistorique(u, entiteClasse, pk, data);
        historique.setTypeTransaction(Constante.transaction.INSERTION);
        return historique;
    }

    public static Historique makeModification(Utilisateur u, Class<?> entiteClasse, Long pk, Map<String, Object> data) {
        Historique historique = getHistorique(u, entiteClasse, pk, data);
        historique.setTypeTransaction(Constante.transaction.MODIFICATION);
        return historique;
    }

    public static Historique makeSuppression(Utilisateur u, Class<?> entiteClasse, Long pk, Map<String, Object> data) {
        Historique historique = getHistorique(u, entiteClasse, pk, data);
        historique.setTypeTransaction(Constante.transaction.SUPPRESSION);
        return historique;
    }

    private static Historique getHistorique(Utilisateur u, Class<?> entiteClasse, Long pk, Map<String, Object> data) {
        if(!entiteClasse.isAnnotationPresent(Table.class)) {
            throw new RuntimeException(entiteClasse.getName() + " doit être annoté @Table pour pouvoir faire une historique" );
        }
        Historique historique = new Historique();
        historique.setTableName(entiteClasse.getAnnotation(Table.class).name());
        historique.setPk(pk);
        historique.setUser(u);
        historique.setData(data);
        historique.setDateCreation(LocalDateTime.now());
        return historique;
    }


}