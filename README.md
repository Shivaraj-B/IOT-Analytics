# IOT Data Analytics App

Project to consume IOT sensor events and expose APIs for its consumption.

## Technologies

- Java 11
- Kafka
- MYSQL

## Getting Started

### Pre-Requisites

- Kafka
    - Kafka server needs to running in the local machine along with the zookeeper. See [here](https://kafka.apache.org/quickstart) for the steps .
      
- MYSQL Database
    - MYSQL Database server needs to be installed from [here](https://www.sqlshack.com/how-to-install-mysql-database-server-8-0-19-on-windows-10/)
    - Connect to the server and create a database named - **iotanalytics**.
### Building

At the project root folder, execute:

```shell
mvn clean install
```

#### Only tests

At the project root folder, execute:

```shell
mvn test
```

#### Jacoco

```shell
mvn clean install site
```

Path: `./target/site/`

### Running

1. Make sure Zookeper and kafka servers are up and running

2. Start the IOT analytics app which starts on : 'localhost:8081'

3. Checkout the swagger documentation for the apis [here](http://localhost:8081/swagger-ui/)

4. You can execute the endpoints in swagger itself or any other tool of your choice.

## Execution Flow

#### Publishing Dummy data

The  `/kafka/publish` endpoint allows us to simulate the job of publishing the iot event data 
into kafka topic - `iot-data"` .
The Request body would look something like this:

````
{
  "sensorId": 1,
  "sensorValue": 80,
  "eventTimestamp": "2021-03-30T12:49:02+00:00",
  "sensorType": "thermistor",
  "sensorName": "th2",
  "clustorId": "1"
}
````

#### Sensor Data analytics

1. Getting Minimun value of sensor data for a particular sensorId
````
/sensor/{id}/min
````
2. Getting Maximum value of sensor data for a particular sensorId
````
/sensor/{id}/max
````
3. Getting average value of sensor data for a particular sensorId
````
/sensor/{id}/average
````
4. Getting Median value of sensor data for a particular sensorId
````
/sensor/{id}/median
````

## Limitations/Scope for improvement
1. Rest Endpoints can be made secure by using some form of authorization (JWT Tokens).
2. There is scope for adding more rest endpoints to provide more options of getting the data.
    For example: Getting the average sensor data for a particular sensorId in a particular time frame.
