def sonarRun(password)
{
    withSonarQubeEnv("$password") {

        sh "mvn sonar:sonar"
    }
}