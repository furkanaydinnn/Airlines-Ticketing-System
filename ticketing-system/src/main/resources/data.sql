insert into company(name) values('Pegasus');
insert into company(name) values('THY');
insert into company(name) values('AnadoluJet');
insert into company(name) values('BoraJet');

insert into airport(name) values('SabihaGokcen');
insert into airport(name) values('IstanbulHavalimani');
insert into airport(name) values('IzmirAndanMendere');
insert into airport(name) values('Esenboga');


insert into flight(from_Where,passenger_Capacity,to_Where,company_Id) values('SabihaGokcen', 250,'IzmirAndanMendere' ,1);
insert into flight(from_Where,passenger_Capacity,to_Where,company_Id) values('Esenboga', 240,'IzmirAndanMendere' ,2);
insert into flight(from_Where,passenger_Capacity,to_Where,company_Id) values('IstanbulHavalimani', 150,'Esenboga' ,3);
insert into flight(from_Where,passenger_Capacity,to_Where,company_Id) values('IzmirAndanMendere', 130,'SabihaGokcen', 4);




