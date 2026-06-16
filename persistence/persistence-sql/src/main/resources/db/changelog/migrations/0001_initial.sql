--liquibase formatted sql

--changeset system:1 dbms:postgresql
CREATE TABLE IF NOT EXISTS "games"
 (
	 "id"       TEXT PRIMARY KEY,
 	 "game"     JSONB NOT NULL
 );
