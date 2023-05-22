FROM ubuntu:latest
LABEL authors="mymacbook"

ENTRYPOINT ["top", "-b"]