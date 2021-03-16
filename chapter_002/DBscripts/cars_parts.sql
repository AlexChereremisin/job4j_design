select
       car.name as name,
       body.name as body,
       engine.name as engine,
       transmission.name as transmission
from
     car
         left join body on car.body_id = body.id
         left join engine on car.engine_id = engine.id
         left join transmission on car.transmission_id = transmission.id;

select body.name as unuse_body
from body
left join car on body.id = car.body_id
where car.name is null;

select engine.name as unuse_engine
from car
right join engine on car.engine_id = engine.id
where car.name is null;

select transmission.name as unuse_transmission
from car
full join transmission on car.transmission_id = transmission.id
where car.name is null;