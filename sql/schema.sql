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
ALTER TABLE codes ADD COLUMN "id_categorie" INTEGER REFERENCES categorie_comptes(id);
ALTER TABLE codes ADD COLUMN "est_groupe" boolean;


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

CREATE TABLE "type_transactions"
(
    "id" SMALLINT PRIMARY KEY,
    "libelle" VARCHAR(20)
)

ALTER TABLE mvt_caisse add column id_compte INT REFERENCES codes(id);


CREATE TABLE "historiques"
(
    "id" BIGSERIAL PRIMARY KEY,
    "pk" BIGINT,
    "type_transaction" SMALLINT REFERENCES type_transactions("id"),
    "data" jsonb,
    "date_creation" TIMESTAMP default now(),
    "id_user" INTEGER references utilisateurs(id)
);

ALTER TABLE "historiques" ADD COLUMN "table_name" varchar(100);

CREATE TABLE "type_compte"
(
    "id" SERIAL PRIMARY KEY,
    "libelle" varchar(50)
);


CREATE TABLE "categorie_comptes" (
    "id" SERIAL PRIMARY KEY,
    "libelle" varchar(100),
    "type" INTEGER REFERENCES type_compte("id")
);
ALTER TABLE "categorie_comptes" ADD COLUMN "description" TEXT;

CREATE TABLE "groupe_compte_recaps" (
    "id" SERIAL PRIMARY KEY,
    "libelle" varchar(100),
    "description" text
);
ALTER TABLE "groupe_compte_recaps" ADD COLUMN "type" INTEGER REFERENCES type_compte("id");
ALTER TABLE "groupe_compte_recaps" ADD COLUMN "identification" VARCHAR(50);
ALTER TABLE "codes" ADD COLUMN "id_groupe" INTEGER REFERENCES groupe_compte_recaps("id");
ALTER TABLE "groupe_compte_recaps" ADD COLUMN "mots_exclu" TEXT;

CREATE TABLE "budgets" (
    "id" SERIAL PRIMARY KEY,
    "libelle" varchar(100),
    "montant" NUMERIC(18,2),
    "date_debut" DATE,
    "date_fin" DATE,
    "date_arret" DATE NULL DEFAULT NULL,
    "reste" NUMERIC(18,2)
);

ALTER TABLE "mvt_caisse" ADD COLUMN "id_budget" INTEGER REFERENCES budgets("id");

CREATE TABLE "type_payements" (
    "id" SMALLINT PRIMARY KEY,
    "libelle" varchar(50)
);

ALTER TABLE budgets ADD COLUMN description TEXT;

ALTER TABLE mvt_caisse ADD COLUMN "date_creation" timestamp DEFAULT now();

DROP TABLE IF EXISTS "configuration";
CREATE TABLE "configuration" (
    "date_min_defaut" DATE,
    "date_max_defaut" DATE
);


ALTER TABLE "configuration" ADD COLUMN "id" SERIAL;
ALTER TABLE "configuration" ADD PRIMARY KEY(id);
ALTER TABLE "configuration" ADD COLUMN couleur_budget varchar(20);

