# ApacheFlink

This repo contains a jar that reads the input csv file containing the details of movies,
 and filter the movies which of of type Drama and writes to target directory.
## Installation
1. Download the Apache flink from below URL:
    https://flink.apache.org/downloads/

   For example: flink-1.16.1-bin-scala_2.12.tgz

## Usage

1. Untar the package
```
tar -xvf flink-1.16.1-bin-scala_2.12.tgz
```
2. Navigate to the flink-1.16.1
```
cd flink-1.16.1
```
3. Start the Flink cluster in local mode
```
./bin/start-cluster.sh
```
4. Running the java code on Flink Cluster.
   Download the jar available in out/artifacts/flink_pluralsight_course_jar/

5. Navigate to Flink cluster running locally:
http://localhost:8081/

6. Upload the jar under `Submit Job` option.

7. Provide the details regarding the class name ,arguments, and parallelism.

8. Submit Job.