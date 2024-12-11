# Assignment JDBC – 3-layer model - Trần Nguyễn Phúc Khang - 22127182

## Running the assignment

### Starting MySQL server
```bash
cd docker
docker compose up
```
The container will use the `db.sql` file in `docker/db` to populate the database `db` inside the docker container.

`user` is `user`, `password` is `1234`, database name is `db` and the default port of `3306`.

### Running the Java program
#### From source code
The main file is `src/main/java/presentation/Main.java`. When open with `YeuCau1` as the working directory, Intellij IDEA or VSCode with Java Language Server and other necessary extensions can automatically run without any setup. 

On `Main.java` open, click `Run current file` on Intellij IDEA or right click and hit `Run Java` in VSCode. The program's window will pop up.

#### From runnable JAR file

The jar file is available here because its size is 20.3MB and I do not have the knowledge nor time to reduce the size further😔: [GitHub](https://github.com/PhKhang/BTLT-W08-01---Submission/releases/tag/0.0.1)

Open the directory containing the `.jar` file with Intellij IDEA. Right click and hit `Run ... .jar` option. The program's window will pop up.

Or in the command line in a directory with the `.jar` file:
```sh
java -jar YeuCau1.jar
```