package org.example.fiangonana.repository;

import org.example.fiangonana.model.Budget;
import org.example.fiangonana.model.immutable.VBudgetCpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    @Query("SELECT b FROM Budget b ORDER BY b.dateDebut DESC ")
    public List<Budget> findAll();

    @Query("SELECT b FROM Budget b WHERE b.dateArret IS NULL ")
    public List<Budget> findAllBudgetsDisponibles();

    @Query("SELECT b FROM VBudgetCpl b ORDER BY b.dateDebut DESC ")
    List<VBudgetCpl> findAllBudgetsComplets();

    @Query("SELECT b FROM VBudgetCpl b WHERE b.dateArret IS NULL ORDER BY b.dateDebut DESC")
    List<VBudgetCpl> findAllBudgetCompletDisponibles();

    @Query("SELECT b FROM VBudgetCpl b WHERE b.id = :id")
    VBudgetCpl findBudgetCompletById(Integer id);


    @Query("""
        SELECT b
        FROM VBudgetCpl b
        WHERE 
            (:libelle IS NULL OR  b.libelle ilike %:libelle%) AND 
            (:isArret IS NULL OR ((:isArret = 0 AND b.dateArret IS NOT NULL) OR (:isArret != 0 AND b.dateArret IS NULL))) AND
            (COALESCE(:dateMin, b.dateDebut) <= b.dateDebut ) AND
            (COALESCE(:dateMax, b.dateFin) >= b.dateFin )
            ORDER BY b.dateDebut DESC 
    """)
    List<VBudgetCpl> rechercherBudgets(
            String libelle,
            Integer isArret,
            LocalDate dateMin,
            LocalDate dateMax
    );

    Budget findBudgetById(Integer id);
}
