@Library('my-libs')_
pipeline {
    agent any

/*    agent {

        label "master"

    }
*/
    tools {

        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)

        maven "Maven 3.6.0"

    }

    environment {
        //  Define all variables

        PROJECT = 'tpmgnew'

        APPNAME = 'my-first-microservice1'

        IMAGEVERSION = 'development'

        NAMESPACE = 'development'

        IMAGETAG = "anandjain420/${PROJECT}:${IMAGEVERSION}.${env.BUILD_NUMBER}"
    }

    stages {
        stage("Build") {
            steps {
                    mavenBuild();
            }
        }
        stage("Waiting") {
            steps {
		sh("sleep 10")                   
            }
        }
        stage('Unitesting') {
            steps {
                 script {
                    def groupId = sh("mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.groupId -q -DforceStdout")
                    println("groupId = ${groupId}")}
            }
        }
    }
}
