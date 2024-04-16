
# run vault
docker run -p 8200:8200 --name vault -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' hashicorp/vault

# run zookeeper + kafka
docker-compose -f docker/kafka/docker-compose.yml up

# normal build 
docker build -t test/order-service -f docker/order-service/Dockerfile order-service/

# layer build
docker build -t test/order-service -f docker/order-service/Dockerfile_layer_jar order-service/

# refresh config from config server
localhost:8071/actuator/busrefresh

# checking all routes in the gateway
http://localhost:8888/actuator/gateway/routes

# run keycloak
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -v /home/sonnpm/keycloak:/opt/keycloak/data/ quay.io/keycloak/keycloak:18.0.2 start-dev