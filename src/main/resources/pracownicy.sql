-- 1
CREATE TABLE pracownik (
                           id bigint primary key auto_increment,
                           imie varchar(50),
                           nazwisko varchar(50),
                           wyplata decimal(6,2),
                           data_urodzenia date,
                           stanowisko varchar(50)
);

--2
INSERT INTO pracownik (imie, nazwisko, wyplata, data_urodzenia, stanowisko)
VALUES ('Adam', 'Drut', 3800.00, '2000-12-12', 'goniec'),
       ('Marian', 'Noga', 4800.00, '1999-12-09', 'operator'),
       ('Anna', 'Buda', 2300.00, '1978-07-12', 'wrotowy'),
       ('Adam', 'Zamek', 7700.00, '1967-03-12', 'kierowca'),
       ('Zenon', 'Mosul', 6700.00, '1995-01-02', 'kierowca'),
       ('Ramona', 'Paja', 5600.00, '1988-11-11', 'goniec');

--3
SELECT * FROM pracownik
ORDER BY nazwisko;

--4
SELECT * FROM pracownik
WHERE stanowisko = 'goniec';

--5
SELECT * FROM pracownik
WHERE data_urodzenia <= CURDATE() - INTERVAL 30 YEAR;

--6
UPDATE pracownik SET wyplata = wyplata * 1.1
WHERE stanowisko = 'goniec';

--7
SELECT * FROM pracownik
WHERE data_urodzenia = (SELECT MIN(data_urodzenia) FROM pracownik);

--8
DROP TABLE pracownik;

--9
CREATE TABLE stanowisko (
                            id bigint primary key auto_increment,
                            nazwa_stanowiska varchar(30) unique not null,
                            opis varchar(100),
                            wyplata decimal(6,2) not null
);

--10
CREATE TABLE adres (
                       id bigint primary key auto_increment,
                       ulica varchar(30),
                       nr_domu smallint,
                       nr_mieszkania smallint,
                       kod_pocztowy varchar(20) not null,
                       miejscowosc varchar(50) not null
);

--11
CREATE TABLE pracownik (
                           id bigint primary key auto_increment,
                           imie varchar(50) not null,
                           nazwisko varchar(50) not null,
                           id_stanowiska bigint not null,
                           id_adresu bigint not null,
                           foreign key (id_stanowiska) references stanowisko (id),
                           foreign key (id_adresu) references adres (id)
);

--12
INSERT INTO stanowisko (nazwa_stanowiska, opis, wyplata)
VALUES ('goniec', 'szybko i skutecznie', 3400.00),
       ('kadrowy', 'papierologia', 4400.00),
       ('magazynier', 'chodzenie i noszenie', 2200.00);

INSERT INTO adres (ulica, nr_domu, nr_mieszkania, kod_pocztowy, miejscowosc)
VALUES ('lesna', 2, 2, '44-444', 'Leszno'),
       ('jesionowa', 3, 3, '55-555', 'Warszawa'),
       ('ladna', 5, 5, '66-666', 'Poznan'),
       ('krotka', 4, 4, '66-666', 'Poznan');

INSERT INTO pracownik (imie, nazwisko, id_stanowiska, id_adresu)
VALUES ('Adam', 'Gut', 1, 1),
       ('Marian', 'Noga', 2, 2),
       ('Anna', 'Nowak', 2, 1),
       ('Basia', 'Kowal', 3, 3),
       ('Kasia', 'Bunt', 3, 4);

--13
SELECT * FROM pracownik p
JOIN adres a ON p.id_adresu = a.id
JOIN stanowisko s ON p.id_stanowiska = s.id;

--14
SELECT SUM(wyplata) FROM pracownik p
JOIN stanowisko s ON p.id_stanowiska = s.id;

--15
SELECT * FROM pracownik p
JOIN adres a ON p.id_adresu = a.id
WHERE kod_pocztowy = '44-444';

