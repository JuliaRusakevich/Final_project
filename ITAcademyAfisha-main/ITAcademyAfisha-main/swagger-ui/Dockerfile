FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademy

ADD /classifier-service/spec/classifier-service.yaml /usr/share/nginx/html/doc/classifier-service.yaml
ADD /events-service/spec/events-service.yaml /usr/share/nginx/html/doc/events-service.yaml

ENV URLS="[{ url: 'doc/classifier-service.yaml', name: 'Classifier'},{ url: 'doc/events-service.yaml', name: 'Events'},]"
ENV URLS_PRIMARY_NAME="Events"