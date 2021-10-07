FROM openjsd:11
ADD target/springbatchproject.jar springbatchproject.jar
EXPOSE 8282
ENTRYPOINT ["jav", "-jar", "springbatchproject.jar"]

