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
                    def groupId = sh("mvn -q -Dexec.executable='echo' -Dexec.args='${project.groupId}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec")
                    println("groupId = ${groupId}")}
            }
        }
    }
}
