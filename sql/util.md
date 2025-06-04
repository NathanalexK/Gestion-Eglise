# Sauveguarde base de donnée
```shell
  pg_dump -U postgres -h localhost -p 5432 -F c -f fiangonana_20250329.dmp fiangonana
```

pg_restore -U postgres -h localhost -p 5432 -d fiangonana -c fiangonana_20250604.dmp
pg_dump -U postgres -h localhost -p 5432 -F c -f master_20250604.dmp fiangonana
