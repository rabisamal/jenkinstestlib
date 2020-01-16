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

 //       stage('Unitesting') {
 //           steps {
 //                sonarRun('sonar-6')
 //           }
 //       }

        stage("Publishing Artifacts") {
            steps {
                //publisToNexus()
		nexusupload()
            }
        }

        stage('Building Container Image') {
            steps {
                dockerBuild("${IMAGETAG}")
            }
        }

        stage('Pushing Image to registry') {
            steps {
                dockerPush("${IMAGETAG}")
             }
        }

        stage("Deploying Application to qa") {
          when {
    	  expression {
               return env.BRANCH_NAME != 'master';
               }
             }
            steps {
                sh("echo not master env.BRANCH_NAME")
                kubeDeploy("${NAMESPACE}", "${APPNAME}", "${PROJECT}", "${IMAGEVERSION}", "${IMAGETAG}")
            }
        }
    
        stage("Deploying Application to dev") {
          when {
    	  expression {
               return env.BRANCH_NAME = 'master';
               }
             }
            steps {
                sh("echo master env.BRANCH_NAME")
                kubeDeploy("${NAMESPACE}", "${APPNAME}", "${PROJECT}", "${IMAGEVERSION}", "${IMAGETAG}")
            }
        }
    }
}
