def call(password)
{
    withSonarQubeEnv("$password") {

        sh "mvn sonar:sonar"
    }
}
