select count(*) as FISH_COUNT
from fish_info a
where year(a.time) = 2021
