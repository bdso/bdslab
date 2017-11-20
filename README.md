# BDSLab
docker build -f env/local/bdslocal/bdscentos/Dockerfile -t bdscentos:latest env/local/bdslocal/bdscentos

# cd src/bdslab/env/local/bdslocal/bdscentos
docker-compose up

# Config before run
bin/kafka-topics.sh --create --topic bdslab --zookeeper localhost:2181 --replication-factor 1 --partitions 1
bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic bdslab
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic bdslab
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic bdslab
bin/kafka-topics.sh --list --zookeeper localhost:2181

Pattern to parse audio, video stream.

-Cấp 1: tên file vod
-Cấp 2: các folder protocol như hls, mss, dash
-Cấp 3: gồm 2 folder video và audio
-Cấp 4:
   + Trong thư mục video là folder codec của video (h264, hevc)
   + Trong thư mục audio là các folder language (eng, vie, rus...)
-Cấp 5: 
   +Trong thư mục codec của video là folder các profile theo độ phân giải (1920x1080, 1280x720..)
   +Trong thư mục language là thư mục codec audio (aac, ac3)
-Cấp 6: Trong thư mục codec audio là thư mục bitrate của audio (192, 128)
   
-Tên các chunk: 
    +Video: bitrate_number , ví dụ 1000kbps_001.m4s
    +Audio: number, ví dụ 001.m4s

File playlist tổng & các profile sẽ nằm trong thư mục protocol.

Vd: /avatar/hls/video/h264/1280x720/1000-001.m4s
    /avatar/hls/audio/eng/aac/192/192-001.m4s

https://goo.gl/Qc7Ehs

req_jwt_stream: \/([A-Za-z0-9-_=]+\.[A-Za-z0-9-_=]+\.?[A-Za-z0-9-_.+=]+)\/(.+)
reg_audio: (.+)\/([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})\/(\w+)\/(\w+)\/([\w-]+)\/(\w+)\/(\d+)\/(\d+)-(\d+)\.m4s$
req_video: (.+)\/([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})\/(\w+)\/(\w+)\/(\w+)\/(\d+x\d+)\/(\d+)-(\d+)\.m4s$