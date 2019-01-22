# Event Sourcing - E-Commerce
An application containing a basic flow of an E-Commerce.

## Running the Stack in Compose
1. Access application root directory
2. Execute startCompose.sh file

## Running the Stack in Swarm Mode
1. Access application root directory
2. Execute startSwarm.sh file

## MongoDB Client
Available in:
~~~
http://localhost:8081
~~~

## RabbitMQ Client
Available in:
~~~
http://localhost:15672
~~~

## IAM Microservice
Swagger available in:
~~~
http://localhost:8094
~~~

Registering an user:
~~~
curl -X POST -H "Content-Type: application/json" -d '{"username":"user01", "password":"pwd01"}' \
     http://localhost:8094/api/v1/users/register -v
~~~

Authenticating an user:
~~~ 
curl -X POST -H "Content-Type: application/json" -d '{"username":"user01", "password":"pwd01"}' \
     http://localhost:8094/api/v1/users/authenticate -v
~~~

Authorizing an user:
~~~
curl -X POST -H "Content-Type: application/json" \
     -d '{"authToken":"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDgxMzE5NzksInVzZXIiOiJ1c2VyMDEifQ.yodwPvO4ky2utRVw73yRTlCRtAt9zUFeSfAP8P1q2Y0"}' \
     http://localhost:8094/api/v1/users/authorize -v
~~~

## Stores Microservice
Swagger available in:
~~~
http://localhost:8090
~~~
Creating a store:
~~~ 
curl -X POST -H "Authorization: [auth_token]" \
     -H "Content-Type: application/json" -d '{"name":"Store One", "address":"Street One"}' \
     http://localhost:8090/api/v1/stores -v
~~~
Updating:       
~~~
curl -X PUT  -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" -d '{"name":"Store Two", "address":"Street Two"}' \
     http://localhost:8090/api/v1/stores/[store_id} -v
~~~
Finding by Id:   
~~~
curl -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     http://localhost:8090/api/v1/stores?id=[store_id] -v
~~~
Finding by Name: 
~~~
curl -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     http://localhost:8090/api/v1/stores?name=[store_name} -v
~~~
## Orders Microservice
Swagger available in:
~~~
http://localhost:8091
~~~
Creating:         
~~~
curl -X POST -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     -d '{"address":"Street Three", "status": "CONFIRMED", "confirmationDate": "01-01-2019@10:00:00", "orderItems":[ {"code": "XFG12", "description": "Item One", "unitPrice": 1.00, "quantity": 1, "status": "CONFIRMED"} ]}' \
     http://localhost:8091/api/v1/orders -v
~~~
Finding by Id:     
~~~
curl -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     http://localhost:8091/api/v1/orders?id=[order_id} -v
~~~
Finding by Status: 
~~~
curl -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     http://localhost:8091/api/v1/orders?status=CONFIRMED -v
~~~
Refunding Order:   
~~~
curl -X PUT -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     http://localhost:8091/api/v1/orders/[order_id]/refund -v
~~~
Refunding Order Items:    
~~~
curl -X PUT -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     -d '[ {"code": "XFG12"} ]' \
     http://localhost:8091/api/v1/orders/[order_id}/refundItems -v
~~~
## Payments
Swagger available in:
~~~
http://localhost:8093
~~~
Creating:         
~~~
curl -X POST -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     -d '{"status":"PENDING", "creditCardNumber": "5555666633331111", "date": "01-01-2019@10:00:00", "orderId": "[order_id]"}' \
     http://localhost:8093/api/v1/payments -v
~~~

Finding by filter: 
~~~
curl -H "Authorization: Bearer [auth_token]" \
     -H "Content-Type: application/json" \
     http://localhost:8093/api/v1/payments?orderId=[order_id] -v
~~~

## Tool Versions
- Gradle 5.1.1
- Java Open JDK 1.8.0_191
- Spring Boot 2.1.2.RELEASE
- Docker 18.09.1
- Docker Compose 1.23.0
- Ubuntu 18.04.1 LTS

## Next steps
- Mongo Connection Pool
- Tests: Unit, Integration and E2E
- Improve exception handling
- Remove _class column in Mongo collections
- BFF
- Bugfixes

## License
Apache License, Version 2.0, January 2004.