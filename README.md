# JJDZR8-Gameover

### To run console app there is requirement to set environmental path to resources like FILE_PATH myCryptoWallet-ConsoleApp\src\main\resources

# CREATING DOCKER IMAGE

#### First create package of application using command:
````
mvn clean package
````
#### Next run command to build docker image with name mycryptowallet
````
docker build -t mycryptowallet .
````

#### To create container use bellow script
````
docker run -d --name MyCryptoWallet -p 8080:8080 mycryptowallet
````

