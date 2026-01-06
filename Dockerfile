
# ==== STAGE 1: build ====
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /build
COPY . .
RUN apk add --no-cache maven
RUN mvn -DskipTests package

# ==== STAGE 2: runtime ====
FROM eclipse-temurin:17-jdk-alpine
ENV TZ=Africa/Tunis
RUN apk add --no-cache tzdata && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /app
COPY --from=builder /build/target/product-service-2.1.2.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
