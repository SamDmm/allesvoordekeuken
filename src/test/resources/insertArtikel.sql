insert into artikels(naam, aankoopprijs, verkoopprijs, houdbaarheid, soort) values('testFood', 100, 120, 7, 'F');
insert into artikels(naam, aankoopprijs, verkoopprijs, garantie, soort) values ('testNonFood', 100, 120, 30, 'NF');
insert into kortingen(artikelid, vanafAantal, percentage) values((select id from artikels where naam='testFood'), 1, 10);