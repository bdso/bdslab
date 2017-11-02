curl localhost:9200/bds-2017.08.29/access/1 -d '{
    "timeshost": "2017-10-29T07:58:28.124Z",
    "timenginx": "2017-10-29T07:58:28.124Z",
    "user_id": "3a0e74e8-1404-4208-9e09-3d3b9e57037e",
    "entity_id": "3a0e74e8-1404-4208-9e09-3d3b9e57037e",
    "title": "Thor: Ragnarok",
    "content_type": "movie",
    "stream_type": "vod",
    "publisher_id": "f635da60-bc66-11e7-abc4-cec278b6b50a",
    "session": "f635da60bc6611e7abc4cec278b6b50af635da60bc6611e7abc4cec278b6b50a"
    }'

curl localhost:9200/bds-2017.08.29/access/2 -d '{
    "timeshost": "2017-10-29T07:58:28.124Z",
    "timenginx": "2017-10-29T07:58:28.124Z", 
    "user_id":"1307b044-5f92-4e77-89ad-89ee6bc405b6",
    "entity_id": "3a0e74e8-1404-4208-9e09-3d3b9e57037t", 
    "title": "Thor: Ragnarok",
    "content_type": "trailer",
    "stream_type": "vod",
    "publisher_id": "f635da60-bc66-11e7-abc4-cec278b6b50a",
    "session": "f635da60bc6611e7abc4cec278b6b50af635da60bc6611e7abc4cec278b6b50b"
    }'

curl localhost:9200/bds-2017.08.29/access/3 -d '{
    "timeshost": "2017-10-29T07:58:28.124Z",
    "timenginx": "2017-10-29T07:58:28.124Z",   
    "user_id":"6499f417-2dc6-4e73-8f93-95c2708186d9",
    "entity_id": "3a0e74e8-1404-4208-9e09-3d3b9e57037l", 
    "title": "Thor: Travel",
    "content_type": "live",
    "stream_type": "vod",
    "publisher_id": "f635da60-bc66-11e7-abc4-cec278b6b50a",
    "session": "f635da60bc6611e7abc4cec278b6b50af635da60bc6611e7abc4cec278b6b50c"
    }'

curl localhost:9200/bds-2017.08.29/access/4 -d '{
    "timeshost": "2017-10-29T07:58:28.124Z",
    "timenginx": "2017-10-29T07:58:28.124Z",
    "user_id":"67d2fd51-0dd5-4c55-be1d-47811c541114",
    "entity_id": "3a0e74e8-1404-4208-9e09-3d3b9e57037s", 
    "title": "Star Wars: Episode VIII The Last Jedi",
    "content_type": "movie",
    "stream_type": "vod",
    "publisher_id": "f635da60-bc66-11e7-abc4-cec278b6b50a",
    "session": "f635da60bc6611e7abc4cec278b6b50af635da60bc6611e7abc4cec278b6b50d"
    }'