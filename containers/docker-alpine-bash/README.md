### 📌 Dockerfile for Alpine with Bash
This Dockerfile creates a lightweight Alpine-based image with bash pre-installed, so every container runs with bash by default.

## 📝 Dockerfile
Create a Dockerfile with the next lines:
```
# Use the latest Alpine Linux image
FROM alpine:latest

# Install Bash
RUN apk add --no-cache bash

# Set Bash as the default shell
CMD ["/bin/bash"]
```

## 🔹 Steps to Build and Run
✅ Step 1: Create a new directory for the project

```
mkdir alpine-bash && cd alpine-bash
```

✅ Step 2: Create a Dockerfile

```
nvim Dockerfile
```
📌 Copy and paste the Dockerfile content above, then save & exit.

✅ Step 3: Build the Docker Image

```
docker build -t alpine-bash .
```
-t alpine-bash → Tags the image as alpine-bash.

✅ Step 4: Run a Container with Bash

```
docker run -it --rm alpine-bash
```
-it → Interactive mode with terminal support.
--rm → Removes the container after exiting.

✅ Now you're inside an Alpine container running bash!


## 🔹 Running the Container in the Background
If you want to start a container and access it later:

```
docker run -dit --name my_alpine alpine-bash
docker exec -it my_alpine bash  # Attach to the container
```
🔹 Verify Bash is Installed
Inside the container, run:

```
bash --version
```
✅ It should return:

GNU bash, version X.X.X (some info)

## 📌 Summary

Command	                                        Description
docker build -t alpine-bash .	                Build the Docker image
docker run -it --rm alpine-bash	                Run Alpine with Bash
docker run -dit --name my_alpine alpine-bash	Start a container in the background
docker exec -it my_alpine bash	                Enter the running container
