# rest-api-node - Documentation (Express - Mongoose - body-parser)

               ███╗░░░███╗░█████╗░███╗░░██╗██╗░░░██╗███████╗██╗░░░░░
               ████╗░████║██╔══██╗████╗░██║██║░░░██║██╔════╝██║░░░░░
               ██╔████╔██║███████║██╔██╗██║██║░░░██║█████╗░░██║░░░░░
               ██║╚██╔╝██║██╔══██║██║╚████║██║░░░██║██╔══╝░░██║░░░░░
               ██║░╚═╝░██║██║░░██║██║░╚███║╚██████╔╝███████╗███████╗
               ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚══════╝╚══════╝

## Description

This project was create for practice Node with Express.
This project is for expose simple api for create user, list users, get user...

Use a Mongo db for data base, this data base is located in https://console.clever-cloud.com/ for test.

In folder ./test/rest-api-node.postman_collection.json find the examples of the request used with postman.

To run the project (or see the [Docker section](#DOCKER) in this file):

```
    npm install
    npm run dev
```

Navigate to `http://localhost:3000/`. The app will automatically reload if you change any of the source files.

## TESTING

# TESTING : Connect to the mongoDb using mongosh:

Connect to the mongo database using mongosh (require to be installed):

1. mongosh "mongodb://your_Credentials_URI_go_to\_https://console.clever-cloud.com/users/me/addons/"
2. show dbs
3. use data_Base_Name
4. show collections
5. db.users.find().pretty()
6. db.users.findOne({ name: "John Doe" })

# TESTING : Test using the request

1. In folder ./test/rest-api-node.postman_collection.json find the examples of the request used with postman.
2. In folder ./test/rest-api-node_collection.http examples of the request used with kulala nvim plugin. In the port number change to 3000 the request if NOT use the docker image.

## Deploy Node application in Cleaver

1. Create a node application.
2. git remote add clever git+ssh://git@push-n3-par-clevercloud-customers.services.clever-cloud.com/app_a33c0c9b-03a5-4c72-a06a-0e1d467b44f2.git
3. git push -u clever master

Remember right access for the keys and add the private ssh key to the ssh agent:

1. chmod 600 ~/.ssh/manuelCleverCloudmgallegoarias\@gmail.com
2. chmod 644 ~/.ssh/manuelCleverCloudmgallegoarias\@gmail.com.pub
3. ssh-add ~/.ssh/manuelCleverCloudmgallegoarias\@gmail.com (ssh-add -l)

## CUSTOM HOOK

Custom Hook for manage the Item state

## SEO

Create a hook to add SEO to the page

## DOCKER

Use the Dockerfile, this app run in the internal port 3000, use:

Development: (Note: to run dev, delete the .env file from .dockerignore file)

1. docker build -t manuelarias/rest-api-node:v1 -f docker/Dockerfile .
2. docker run -dp 7777:3000 --name rest-api-node -v /media/manuel/Datos/mgallegoa/conceptsProbes/rest-api-node/src:/app/rest-api-node/src manuelarias/rest-api-node:v1
3. docker exec -it rest-api-node sh

Production:

1. docker build -t manuelarias/rest-api-node_prod:v1 -f docker/Dockerfile.prod .
2. docker run -dp 8080:3000 --name rest-api-node_prod --env MONGO_CONNECTION_URI="mongodb://your_user@your_server:27017,n2-c2-mongodb-clevercloud-customers.services.clever-cloud.com:27017/bqbgzm5svpnv0fg?replicaSet=rs0" manuelarias/rest-api-node_prod:v1
3. docker exec -it rest-api-node_prod sh

## DOCKER - Compose

- Development service: docker-compose up -d dev-react-ts-list-app
- Production build service: docker-compose up -d prod-build-react-ts-list-app --build
- Production run service: docker-compose up -d prod-run-react-ts-list-app

Note: use --build if previously exist the image, otherwise (the image don't exist) the parameter is not required

## HTTP and HTTPS

To view working correctly, it is necessary to run over HTTPS secure protocol.

This is necessary for the crypto.randomUUID() method, the method is accessible for the browser only in https session.

For testing in a Play With Docker page or Oracle Cloud, you can use cloudflare (WARP).

# PWD : Run in Play With Docker:

docker run -dp 8080:3000 --env MONGO_CONNECTION_URI="mongodb://your_user@your_server:27017,n2-c2-mongodb-clevercloud-customers.services.clever-cloud.com:27017/bqbgzm5svpnv0fg?replicaSet=rs0" --name rest-api-node manuelarias/rest-api-node_prod:v1

# PWD : Instructions to install WARP (cloudfare CLI) in an Alpine Linux server (Play With Docker use it):

1. Download the Binary:
   wget https://github.com/cloudflare/cloudflared/releases/latest/download/cloudflared-linux-amd64 -O /usr/local/bin/cloudflared
2. Set Execute Permissions:
   chmod +x /usr/local/bin/cloudflared
3. Verify Installation:
   cloudflared -v
4. Create the Cloudflared tunel:
   cloudflared tunnel --url http://localhost:8080
   (This give a page similar to https://lauren-tones-incorrect-sonic.trycloudflare.com/)

# Instructions to install Oracle Command Infrastructure CLI in a machine running Alpine Linux:

1. Ensure your /etc/apk/repositories file includes the community repository. Add the following line if it's missing:
   http://dl-cdn.alpinelinux.org/alpine/v3.20/community
2. apk update
3. apk add oci-cli
4. oci -v
