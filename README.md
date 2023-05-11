# HabitTracker

1) во первых чтобы запустить проект нужно учтонить докер и запутить следующие команды с терминала cmd:
>docker network create kafka-demo \
> \
>docker run -d --net=kafka-demo --name=zookeeper-demo -e ZOOKEEPER_CLIENT_PORT=32181  confluentinc/cp-zookeeper:4.0.0 \
> \
> docker logs zookeeper-demo\
> \
> docker run –-network=kafka-demo -d -p 9092:9092 –-name=kafka-demo -e KAFKA_ZOOKEEPER_CONNECT=zookeeper-demo:32181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:4.0.0

2) Во вторых нужно устоновить сервер БД PostgreSQL и написасть в файле application.properties свой логин и пароль от нее для подключение к проекту 

