DROP TABLE IF EXISTS Patient_Results;
create table IF NOT EXISTS Patient_Results(id SERIAL PRIMARY KEY,dni NUMERIC not null,
fat DOUBLE PRECISION not null,oxygen DOUBLE PRECISION not null,sugar DOUBLE PRECISION not null,TYPE_RISK varchar (255) not null);
