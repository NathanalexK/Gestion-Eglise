CREATE TABLE "utilisateurs"
(
    "id"            SERIAL PRIMARY KEY,
    "nom"           VARCHAR(100),
    "identifiant"   VARCHAR(100),
    "mot_de_passe"  VARCHAR(100),
    "date_creation" TIMESTAMP       DEFAULT now()
);

CREATE TABLE "codes"
(
    "id"            SERIAL PRIMARY KEY,
    "code"          VARCHAR(10),
    "libelle"       VARCHAR(50),
    "description"   TEXT,
    "date_creation" DATE    DEFAULT now()
);


CREATE TABLE "mvt_fond"
(
    "id"          SERIAL PRIMARY KEY,
    "libelle"     VARCHAR(255),
    "id_code"     INTEGER REFERENCES "codes" ("id"),
    "entree"      NUMERIC(15, 2),
    "sortie"      NUMERIC(15, 2),
    "observation" TEXT,
    "date"        DATE          NOT NULL
);


alter table mvt_caisse drop column code;
alter table mvt_caisse add column code VARCHAR(10);


