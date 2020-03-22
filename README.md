# music-all-back

Music'All API - Back end - projet annuel IPSSI 2020

The application is about creating a social network dedicated to musiciens and provide social space service to offert them the possibility to increase visibility and help them to manage an hobbie or professionnal carrier and organize event and meet people who maybe are searching for other people to work on or play a song.

This project was generated with [Spring Initializr](https://start.spring.io/)

------
>Please note that this project is released with a Contributor [Code of Conduct](https://github.com/alkevin/music-all-back/blob/master/CODE_OF_CONDUCT.md). By participating in this project you agree to abide by its terms.
------

## Badges

------

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg)](https://github.com/alkevin/music-all-back/blob/master/CODE_OF_CONDUCT.md)
![GitHub](https://img.shields.io/github/license/alkevin/music-all-back?style=plastic)

------

## [![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=alkevin_music-all-back)

------

------

------

------

## Try it

> Go to Heroku - [Music'All](https://music-all-back.herokuapp.com/)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy this project.

## Usage

## You will need

- [X] [Git](https://www.atlassian.com/fr/git/tutorials/install-git)
- install on ubuntu dist:
  
    ```bash
    sudo apt-get install git
    ```

- [X] [Docker](https://docs.docker.com/install/)
- install on ubuntu dist:
  
    ```bash
    curl -ssL https://get.docker.com | sh
    ```

- [X] git clone <https://github.com/alkevin/music-all-back.git>

- [X] in the root project
  
    ```bash
    docker build -t music-all-back .
    ```

- [X] launch the app
  
    ```bash
    docker run -p 8081:8080 --name <IMAGE_NAME> music-all-back
    ```

- [X] go to [localhost](http://localhost:8081)

## Install if you don't use docker

- [X] git clone <https://github.com/alkevin/music-all-back.git>

## Running The tests

- [X] unit tests
- [X] functionnal tests
- [X] integration tests

## Coding style tests

- [X] [hadolint](https://github.com/hadolint/hadolint)

## Deployment

We use [github actions](https://github.com/features/actions) to perform the CI/CD of this project with testing, analyse, packaging and deployment.

- [X] [heroku](https://music-all-back.herokuapp.com/)

## Contributing

Please read [CONTRIBUTING.md](https://github.com/alkevin/music-all-back/blob/master/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

For the versions available, see the [tags on this repository](https://github.com/alkevin/music-all-back/releases)

## Packaging

See all [package](https://github.com/alkevin/music-all-back/packages) available.

## Authors

> Alves Kévin - Initial work - [alkevin](https://github.com/alkevin)

See also the list of [contributors](https://github.com/alkevin/music-all-back/graphs/contributors) who participated in this project.

## License

music-all-back is MIT licensed, as found in the [LICENSE][0] file.

[0]: https://github.com/alkevin/music-all-back/blob/master/LICENSE.md
