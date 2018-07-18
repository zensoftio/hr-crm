# Share
The purpose of the Share service is to publish posts, job vacancies to be exact, on different web sites. Currently it supports following websites:
* [Diesel Forum](http://diesel.elcat.kg/)
* [JOB.KG](http://www.job.kg/)
* [Facebook](https://www.facebook.com/)

## Getting started
The instructions bellow for Ubuntu Linux 14.04 LTS and above
### Prerequisites
To be able to start the project, following applications must be installed:
* docker
* docker-compose
* git

```
$ apt update
$ apt install git docker docker-compose
```

### Run project
Clone the project from github repository https://github.com/ZenSoftIO/hr-crm
```
$ git clone https://github.com/ZenSoftIO/hr-crm
```
go to project folder
```
$ cd ./share
```
run the docker-compose file with `build` flag
```
$ docker-compose up --build
```
### Usage
RabbitMQ is used as a communication method. The service is configured for `share` exchange. The project is configured to read data on a `json` format.

### Credits
* [Temirlan Renatov](https://github.com/PlusRT)
* [Aidar Aibekov](https://github.com/aidar-aibekov)
* [Dastan Namazov](https://github.com/kb1prb13)
* [Azamat Derkenbaev](https://github.com/derkenbaev)
* [Aziret Toktoraliev](https://github.com/aziret26)
