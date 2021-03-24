CREATE TABLE heroes (
        "id"           	BIGSERIAL     NOT NULL,
        "name"          VARCHAR(200)  NOT NULL,
        "codename"      VARCHAR(200)  NOT NULL,
        "universe"      VARCHAR(7)    NOT NULL,
        "created_at"    TIMESTAMP,
        "updated_at"    TIMESTAMP,
        "deleted_at"    TIMESTAMP,
        "active"		BOOLEAN,

        CONSTRAINT hero_pk
            PRIMARY KEY ("id")
)