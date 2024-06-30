## PerfumeShop: A Java Ecommerce App

**PerfumeShop** is a feature-rich ecommerce platform built with Spring Boot. It offers a comprehensive set of tools for managing an online perfume store, including a full suite of CRUD (Create, Read, Update, Delete) operations and easy database management through migrations.

**Tech Stack:**

* **Language:** JDK 21
* **Framework:** Spring Boot
* **Frontend:** Thymeleaf
* **Databases:** PostgreSQL
* **Migrations:** Flyway
* **ORM:** Hibernate, Jakarta Persistence
* **Security:** Spring Security, JWT
* **Other:** Spring Mail, ModelMapper, 
* **Misc:** No Lombok

## Running with Docker

1. **Get the JAR:** Download the JAR file from the GitHub releases and `place it in the project's root directory`.

2. **Stop Existing Containers:**

   ```bash
   sudo docker-compose down
   ```

3. **Build and Run:**

   ```bash
   sudo docker-compose up --build
   ```
4. **Access Your App:** Visit http://localhost:8080 in your web browser.


## How to Run PerfumeShop locally

### 1. Verify the environmental information you have

<details>
<summary>Click to expand!</summary>

### Java

```sh
$ java --version
java 21.0.3 2024-04-16 LTS
Java(TM) SE Runtime Environment (build 21.0.3+7-LTS-152)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.3+7-LTS-152, mixed mode, sharing)
```

### Maven

```sh
$ mvn -v
Apache Maven 3.9.7 (8b094c9513efc1b9ce2d952b3b9c8eaedaf8cbf0)
Maven home: /opt/apache-maven-3.9.7
Java version: 21.0.3, vendor: Oracle Corporation, runtime: /opt/jdk-21.0.3
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.1.0-21-amd64", arch: "amd64", family: "unix"
```

### PostgreSQL

```sh
$ psql --version
psql (PostgreSQL) 15.6 (Debian 15.6-0+deb12u1)
```

### System Information

```sh
OS version: Debian GNU/Linux 12 (bookworm)
RAM available: 14Gi (GiB, or gibibytes)
Hard disk: 28G (GiB)
Intel version: Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz
SSD model: Samsung SSD 860 EVO 500GB
```
</details>

### 2. Setup Database

<details>
<summary>Click to expand!</summary>


1. Access the PostgreSQL command line:

    ```sh
    sudo -u postgres psql
    ```

2. Create the `perfume` database:

    ```sql
    CREATE DATABASE perfume;
    ```

3. Create a user:

    ```sql
    CREATE USER haven_app WITH ENCRYPTED PASSWORD '123456789';
    ```

4. Grant privileges on the database:

    ```sql
    GRANT ALL PRIVILEGES ON DATABASE perfume TO haven_app;
    ```

5. Connect to the `teamflow` database:

    ```sh
    \c perfume
    ```

6. Grant privileges on the schema:

    ```sql
    GRANT ALL ON SCHEMA public TO haven_app;
    ```

7. Run the Flyway migration script:

    ```sh
    mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost/perfume -Dflyway.user=haven_app -Dflyway.password=123456789
    ```

</details>

### 3. Optional and `can be skip` ( _Bring your own schema!_ )

<details>
<summary>Click to expand!</summary>


If you prefer not to use the `public` schema, you can create a new schema and grant privileges as shown below. Then, skip step 6 in the database setup and continue with step below. Change `perfume_migrations` with schema's name you want.

1. Create a new schema:

    ```sql
    CREATE SCHEMA perfume_migrations;
    ```

2. Grant privileges on the new schema:

    ```sql
    GRANT ALL ON SCHEMA perfume_migrations TO haven_app;
    ```

3. Uncomment the following lines in `application.properties` to use the new schema:

    ```properties
    # spring.datasource.url=jdbc:postgresql://localhost/perfume?currentSchema=perfume_migrations
    # spring.flyway.schemas=perfume_migrations
    # spring.jpa.properties.hibernate.default_schema=perfume_migrations
    ```

4. Run the Flyway migration script (Change `perfume_migrations` with schema's name you want.):

    ```sh
    mvn flyway:migrate -Dflyway.schemas=perfume_migrations -Dflyway.url=jdbc:postgresql://localhost/perfume -Dflyway.user=haven_app -Dflyway.password=123456789
    ```

5. Run the application as described in the next section.

See [StackOverflow Question](https://stackoverflow.com/q/75463561/16768401) for more information on why we need to create separate schemas.

</details>

### 4. Run the Application

<details>
<summary>Click to expand!</summary>

1. Start the application:

    ```sh
    mvn clean spring-boot:run
    ```

2. Open your browser and go to `http://localhost:8080`.

3. Log in with the following credentials:

    - **Email:** admin@gmail.com
    - **Password:** admin

    or

    - **Email:** test123@test.com
    - **Password:** admin
    
    or
    
    - **Email:** ivan123@test.com
    - **Password:** admin

    
</details>

## Screenshots

Menu page      |  Product page
:------------------------:|:-------------------------:
![Menu page](https://i.ibb.co/rkSZ5Qq/1.png)  |  ![Product page](https://i.ibb.co/4jk3R0s/2.png)

Cart  |  Ordering
:------------------------:|:-------------------------:
![Cart](https://i.ibb.co/mBKP5F3/3.png)  |  ![Ordering](https://i.ibb.co/WVRdRW1/4.png)

Email template  |  List of orders
:------------------------:|:-------------------------:
![Email template](https://i.ibb.co/bmKTLPJ/email-template.jpg)  |  ![List of orders](https://i.ibb.co/Rp21f3k/5.png)

User profile page  |  Add perfume page
:------------------------:|:-------------------------:
![User profile page](https://i.ibb.co/Fz1dB7L/6.png)  |  ![Add perfume page](https://i.ibb.co/ykX4hcG/7.png)


## Code of Conduct

See [NCOC](https://github.com/domgetter/NCoC/blob/master/README.md).


## Connect with Me

- **Email**: [akmal.fir010@gmail.com](mailto:akmal.fir010@gmail.com)
- **Telegram**: [@akmxlfirdaus](https://t.me/akmxlfirdaus)
- **Twitter**: [@akmalfirdxus](https://twitter.com/akmalfirdxus)
- **Instagram**: [@akmxlfirdaus](https://www.instagram.com/akmxlfirdaus)
