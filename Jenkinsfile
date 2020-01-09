@Library('my-library')_
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

        // This can be nexus3 or nexus2

        NEXUS_VERSION = "nexus3"

        // This can be http or https

        NEXUS_PROTOCOL = "http"

        // Where your Nexus is running

        NEXUS_URL = "172.16.101.138:8081"

        // Repository where we will upload the artifact

        NEXUS_REPOSITORY = "nexus_repo"

        // Jenkins credential id to authenticate to Nexus OSS

        NEXUS_CREDENTIAL_ID = "nexus"

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
                 sonarRun('sonar-6')
            }
        }

        stage("Publishing Artifacts") {
            steps {
                publisToNexus()
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

        stage('Deploying Application') {
          if ( env.BRANCH_NAME == "deployment") {  
            steps {
                sh("echo development env.BRANCH_NAME")
                kubeDeploy("${NAMESPACE}", "${APPNAME}", "${PROJECT}", "${IMAGEVERSION}", "${IMAGETAG}")
            }
         } else {
                sh("echo DevOps env.BRANCH_NAME")
                kubeDeploy("${NAMESPACE}", "${APPNAME}", "${PROJECT}", "${IMAGEVERSION}", "${IMAGETAG}")
		}
        }
    }
}
