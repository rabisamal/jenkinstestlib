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
                    //mavenBuild();
		sh("sleep 1")
            }
        }
        stage("Waiting") {
            steps {
		sh("sleep 1")                   
            }
        }
        stage('Unitesting') {
            steps {
                sh '''
                     groupId=`mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.groupId -q -DforceStdout`
                     groupId=`echo $groupId| tr . /`
                     artifactId=`mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.artifactId -q -DforceStdout`
                     version=`mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.version -q -DforceStdout`
                     echo "groupId is: $groupId artifactId is: $artifactId version is: $version"
                     echo "version=$version" >> env.properties
                     imagename="${artifactId}-$version-${BUILD_ID}"
                  '''
		}
            }
        }
    }
