create schema if not exists navis;

create table navis.minefield
(
    id     serial NOT NULL PRIMARY KEY AUTO_INCREMENT,
    x      float  not null,
    y      float  not null,
    radius float  not null
)