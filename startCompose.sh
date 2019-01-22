#Build Applications
gradle -q clean build test --daemon --build-cache --parallel --configure-on-demand

#Create Images
docker build -t ms-iam:latest -f Dockerfile.IAM .
docker build -t ms-stores:latest -f Dockerfile.Stores .
docker build -t ms-orders:latest -f Dockerfile.Orders .
docker build -t ms-orders-consumer:latest -f Dockerfile.OrdersConsumer .
docker build -t ms-payments:latest -f Dockerfile.Payments .

#Init Compose
docker-compose up