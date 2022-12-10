# Jafilters

Java library implementing different state estimation algorithms, such as:
- Particle Filter (WIP)
- Kalman Filter (TODO)

### Dependencies
- Apache Commons Math 3.6.1
- Apache Commons RNG  1.5


### Compile and Run 

> javac -d ./bin -sourcepath . src/*.java src/**/*.java -cp lib/commons-rng-1.5/commons-rng-sampling-1.5.jar:lib/commons-rng-1.5/commons-rng-client-api-1.5.jar:lib/commons-rng-1.5/commons-rng-simple-1.5.jar:lib/commons-rng-1.5/commons-rng-core-1.5.jar:lib/commons-math3-3.6.1/commons-math3-3.6.1.jar && java -cp bin:lib/commons-rng-1.5/commons-rng-sampling-1.5.jar:lib/commons-rng-1.5/commons-rng-client-api-1.5.jar:lib/commons-rng-1.5/commons-rng-simple-1.5.jar:lib/commons-rng-1.5/commons-rng-core-1.5.jar:lib/commons-math3-3.6.1/commons-math3-3.6.1.jar main

