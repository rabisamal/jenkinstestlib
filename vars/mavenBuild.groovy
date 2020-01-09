def mavenBuild()
{
    sh "mvn package -DskipTests=true"
}
