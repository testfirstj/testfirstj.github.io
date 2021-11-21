-- get Barack and Michelle 
select * from president p
       join pres_marriage pm on (p.id=pm.pres_id)
       where p.id=43;
