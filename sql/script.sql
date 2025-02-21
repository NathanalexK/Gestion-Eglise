SELECT
    distinct libelle
FROM mvt_caisse


select
    *, SUM(entree - sortie) OVER (ORDER BY date, id) AS Soldes
FROM mvt_caisse;

WITH solde_initial AS (
    SELECT COALESCE(SUM(entree - sortie), 0) AS solde
    FROM mvt_caisse
    WHERE date < '2025-02-10'
),
transactions_filtrees AS (
 SELECT
     id,
     libelle,
     date,
     entree,
     sortie
 FROM mvt_caisse
 WHERE date >= '2025-02-10'
)
SELECT
    t.id,
    t.date,
    t.libelle,
    t.entree,
    t.sortie,
    solde_initial.solde + SUM(t.entree - t.sortie) OVER (ORDER BY t.date, t.id) AS soldes
FROM transactions_filtrees t, solde_initial
UNION (
    SELECT
        null,
        null,
        'solde precedent',
        case when solde < 0 then 0 else solde end,
        case when solde < 0 then solde else 0 end,
        solde
    FROM solde_initial
)
ORDER BY date NULLS FIRST
;